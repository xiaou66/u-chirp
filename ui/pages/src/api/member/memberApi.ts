import { request } from "../appService";


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
export async function memberPasswordLoginApi(req: UserPasswordLoginReq): Promise<string> {
  return await request<string>('POST', '/member/login', {
    data: req,
    autoShowToast: false
  }).then((res => res));
}


export interface MemberInfoResp {
  /**
   * 会员 id
   */
  memberId: number;
  /**
   * 会员名称
   */
  memberNickname: string;
  /**
   * 会员头像
   */
  memberAvatar: string;
}

export async function memberInfoApi(): Promise<MemberInfoResp> {
  return await request<MemberInfoResp>('GET', '/member/info');
}
