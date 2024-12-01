import { request, type RollParam, type RollResult} from "../appService";
import type {ProductPostDetailResp, ProductPostListReq} from "./productPostApi";
import type {FileUrlVO} from "../core";
import type {ChirpMemberBaseInfo} from "../member";

export interface ProductPostCommentCreateReq {
  postId: string;
  parentCommentId?: string;
  replyCommentId?: string;
  commentRawHtml: string;
  fileIds?: string[];
}

/**
 * 发布评论
 * @param req
 * @constructor
 */
export async function productPostCommentCreateApi(req: ProductPostCommentCreateReq) {
  return await request<ProductPostDetailResp>('POST', 'product/post/comment/create', {
    data: req
  });
}


export interface ProductPostListReq extends RollParam {
  productCode: string;
  postId: string;
  keyword?: string;
  parentId?: string;
}

export interface ProductPostCommentItem {
  commentId: string;
  postId: string;
  parentCommentId: string;
  commentRawHtml: string;
  commentTop: boolean;
  commentThumbsUpCount: string;
  fileList: FileUrlVO[];
  commenterInfo: ChirpMemberBaseInfo;
  beCommenterInfo: ChirpMemberBaseInfo;
  createTime: string;
  children: ProductPostCommentItem[];
  commentChildrenCount: number;
  end?: boolean;
}

/**
 * 评论列表
 * @param req
 */
export async function productPostCommentListApi(req: ProductPostListReq) {
  return await request<RollResult<ProductPostCommentItem>>('GET', 'product/post/comment/getList', {
    params: req
  });
}

export interface ProductPostCommentChildrenListReq extends RollParam {
  commentId: string;
}

/**
 * 获取子评论列表
 */
export async function productPostCommentChildrenListApi(req: ProductPostCommentChildrenListReq) {
  return await request<RollResult<ProductPostCommentItem>>('GET', 'product/post/comment/getChildrenList', {
    params: req
  });
}
