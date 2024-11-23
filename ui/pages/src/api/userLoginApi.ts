import { request } from "./appService";


export interface UserPasswordLoginReq {
  /**
   * 邮箱
   */
  email: string;
  /**
   * 密码
   */
  password: string;
}

/**
 * 用户密码登录
 */
export async function userPasswordLogin(req: UserPasswordLoginReq): Promise<string> {
  return await request<string>('POST', '/member/login', {
    data: req,
    autoShowToast: false
  }).then((res => res));
}


