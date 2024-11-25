import axios, {type AxiosRequestConfig} from "axios";
import type {AxiosInstance, AxiosResponse, InternalAxiosRequestConfig} from 'axios';

console.log( import.meta.env)
import {useToast} from "@u-chirp/shadcn";
import {useMemberStore} from "../stores";
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
    const { data, code } = response.data as ApiResult<string>;
    if (code === 0) {
      return Promise.resolve(data);
    }
    // 登录失败
    return Promise.reject(response.data);
  }
}
// @ts-ignore
instance.interceptors.response.use(handleResponseInterceptor)


interface RequestOptions extends AxiosRequestConfig {
  autoShowToast?: boolean;
}


export async function request<T>(method: 'GET' | 'POST',
                                 url: string,
                                 options: RequestOptions = { autoShowToast: true }): Promise<T> {
  const { token } = useMemberStore();
  if (token) {
    options = {...options, ...{
      headers: {
        ...options.headers,
        'chirp-token': token
      }
    }}
  }
  return new Promise((resolve, reject) => {
    instance.request<T>({
      method,
      url,
      ...options,
    }).then((res) => resolve(res as T))
      .catch(e => {
      if (Object.hasOwn(e, 'code')) {
        const { toast } = useToast();
        const {code, msg} = e as ApiResult<T>;
        if (code === 401) {
          toast({
            title: '登录失效, 请重新登录或授权'
          });
        } else {
          if (options.autoShowToast && !msg.includes("hide:")) {
            toast({
              title: msg
            });
          }
        }
      }
      reject(e);
    })
  });
}


export interface PageResult<T> {
  total: number;
  list: T[];
}
