import instance from "./appService";


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
export async function userPasswordLogin(req: UserPasswordLoginReq) {
  return await instance.post('/member/login', {
    ...req
  });
}

