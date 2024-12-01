import type {ProductPostCommentItem} from "../../../../../api";

export interface ProductReplyCommentInstance {
  show: (data: ProductPostCommentItem) => void;
}
