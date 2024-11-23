import axios from "axios";
import type {AxiosInstance, AxiosResponse, InternalAxiosRequestConfig} from 'axios';

console.log( import.meta.env)
import {useToast} from "@u-chirp/shadcn";
const instance = axios.create({
  timeout: 5000,    //超时配置
  withCredentials: true,  //跨域携带cookie
  baseURL: import.meta.env.VITE_API_BASE_URL + '/app',
});


export interface ApiResult<T> {
  /**
   * 错误码
   */
  code: number;
  /**
   * 数据
   */
  data: T;
  /**
   * 错误提示，用户可阅读
   */
  msg: string;
}


/**
 * 响应接口处理的拦截器
 * @param response 响应
 * @private
 */
function handleResponseInterceptor(response: AxiosResponse<any, any>) {
  if (response.headers['content-type']
    && (response.headers['content-type'].toString().includes('text/plain;')
      || response.headers['content-type'].toString().includes('application/json'))) {
    const { data, code, msg } = response.data as ApiResult<string>;
    if (code === 0) {
      return data;
    }
    const { toast } = useToast();
    if (code === 401) {
      toast({
        title: '登录失效, 请重新登录或授权'
      });
    } else {
      if (!msg.includes("hide:")) {
        toast({
          title: msg
        });
      }
    }
    // 登录失败
    return Promise.reject(response);
  }
}
// @ts-ignore
instance.interceptors.response.use(handleResponseInterceptor)
export default instance;
