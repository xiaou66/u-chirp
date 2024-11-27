import { request, type RollParam, type RollResult} from "../appService";
import type {ProductInfoResp} from "./productApi";

export interface ProductPostSaveReq {

  /**
   * 产品 code
   */
  productCode: string;

  /**
   * 帖子 id
   */
  postId?: string;

  /**
   * 帖子标题
   */
  postTitle: string;

  /**
   * 原始内容 Delta 格式
   */
  postRawContent: string;

  /**
   * 原始 html 内容
   */
  postRawHtml: string;

  /**
   * 帖子类型
   */
  postType: string;
}

/**
 * 发布帖子
 * @param data
 */
export async function productPostPostSaveApi(data: ProductPostSaveReq) {
  return await request<ProductInfoResp>('POST', '/product/post/save', {
    data
  });
}

export interface ProductPostListReq extends RollParam {
  productCode: string;
  postTitle?: string;
  tab?: string;
}

export interface ProductPostListResp {
  /**
   * 帖子 id
   */
  postId: string;

  /**
   * 帖子标题
   */
  postTitle: string;

  /**
   * 原始 html 内容
   */
  postRawHtml: string;

  /**
   * 是否秒评
   */
  postGood: boolean;

  /**
   * 点赞数
   */
  postThumbsUpCount: string;

  /**
   * 收藏数
   */
  postFollowCount: string;

  /**
   * 是否置顶
   */
  postTop: boolean;

  /**
   * 帖子类型
   */
  postType: string;

  /**
   * 帖子处理进度
   */
  postHandleProgress?: number;
  createTime: string;
  memberInfo: {
    memberId: string;
    memberNickname: string;
    memberAvatar: string;
  }
}

/**
 * 帖子列表
 * @param params
 */
export async function productPostListApi(params: ProductPostListReq) {
  return await request<RollResult<ProductPostListResp>>('GET', '/product/post/list', {
    params
  });
}

export interface ProductPostActionReq {
  /**
   * 产品 id
   */
  productCode: string;
  /**
   * 帖子 id
   */
  postId: string;
}

export interface ProductPostThumbsUpReq extends ProductPostActionReq {
  thumbsUp: boolean;
}

/**
 * 帖子点赞
 * @param data
 */
export async function productPostThumbsUpApi(data: ProductPostThumbsUpReq) {
  return await request<ProductInfoResp>('POST', '/product/post/thumbsUp', {
    data
  });
}

/**
 * 提取当前用户点赞帖子
 * @param postIds
 */
export async function productPostThumbsUpRecordApi(postIds: string[]): Promise<string[]> {
  return await request<string[]>('POST', '/product/post/getThumbsUpRecord', {
    data: {
      postIds,
    }
  });
}
export interface ProductPostCollectReq extends ProductPostActionReq {
  follow: boolean;
}

/**
 * 关注
 * @param req
 */
export async function productPostFollowApi(req: ProductPostCollectReq) {
  return await request('POST', '/product/post/follow', {
    data: req
  });
}


/**
 * 提取当前用户关注帖子
 * @param req
 */
export async function productPostFollowRecord(postIds: string[]) {
  return await request<string[]>('POST', '/product/post/getFollowRecord', {
    data: {
      postIds,
    }
  });
}
