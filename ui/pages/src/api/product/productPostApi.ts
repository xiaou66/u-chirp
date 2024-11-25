import {type PageResult, request} from "../appService";
import type {ProductInfoResp} from "./productApi";

export interface ProductPostSaveReq {

  /**
   * 产品 code
   */
  productCode: string;

  /**
   * 帖子 id
   */
  postId?: number;

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

export interface ProductPostListReq {
  productCode: string;
  postTitle?: string;
  tab?: 'HOT' | 'NEW' | 'GOOD_PROBLEM' | 'FOLLOW'
}

export interface ProductPostListResp {
  /**
   * 帖子 id
   */
  postId: number;

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
  postThumbsUpCount: number;

  /**
   * 收藏数
   */
  postFollowCount: number;

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
}

/**
 * 帖子列表
 * @param params
 */
export async function productPostListApiApi(params: ProductPostListReq) {
  return await request<PageResult<ProductPostListResp>>('GET', '/product/post/list', {
    params
  });
}
