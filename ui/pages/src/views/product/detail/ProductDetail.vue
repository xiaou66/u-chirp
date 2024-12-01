<script setup lang="ts">
import {CardTitle, FileList, SvgIcon, UButton, UserEditor, type UserEditorInstance} from "@u-chirp/components";
import { Button } from "@u-chirp/shadcn";
import { useMediaQuery } from "@vueuse/core";
import { MemberInfoContent } from "../components";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {
  productPostCommentChildrenListApi,
  productPostCommentCreateApi, type ProductPostCommentItem,
  productPostCommentListApi,
  productPostDetailApi,
  type ProductPostDetailResp, productPostFollowRecordApi, productPostThumbsUpRecordApi,
  uploadFile
} from "../../../api";
import {ProductPosterInfo, ProductReplyComment, type ProductReplyCommentInstance} from "./components";
import PostPreview from "../components/PostPreview.vue";
import {useMemberStore} from "../../../stores";
import type {RollResult} from "../../../api/appService";
import PostThumbsUp from "../components/PostThumbsUp.vue";
import PostFollow from "../components/PostFollow.vue";
const isDesktop = useMediaQuery('(min-width: 768px)');

const memberStore = useMemberStore();

const postDetail = ref<ProductPostDetailResp>();
const route = useRoute();

const postThumbsUp = ref(false);
const postFollowStatus = ref(false);

function requestPostDetail() {
  const { postId, productCode } = route.params as any;
  productPostDetailApi({
    postId,
    productCode
  }).then(res => {
    postDetail.value = res;
  });

  productPostFollowRecordApi([postId]).then((followPostIds) => {
    postFollowStatus.value = followPostIds.length > 0;
  });

  productPostThumbsUpRecordApi([postId]).then((thumbsUpPostIds) => {
    postThumbsUp.value = thumbsUpPostIds.length > 0;
  });
}
onMounted(() => {
  requestPostDetail()
});

function handlePostThumbs(postId: string, thumbsUp: boolean) {
  postThumbsUp.value = thumbsUp;
  const postThumbsUpCount = postDetail.value.postThumbsUpCount;
  postDetail.value.postThumbsUpCount = (Number(postThumbsUpCount) + (thumbsUp ? 1 : -1)).toString();
}

function handleFollow(postId: string, follow: boolean) {
  postFollowStatus.value = follow;
  const postFollowCount = postDetail.value.postFollowCount;
  postDetail.value.postFollowCount = (Number(postFollowCount) + (follow ? 1 : -1)).toString();
}
const commentEditor = ref<UserEditorInstance>();

async function handleCreateComment() {
  const editor = commentEditor.value!.getEditor();
  const text = editor.getText().trim();

  if (!text) {
    return;
  }

  const { postId } = route.params as {postId: string};
  const fileList = commentEditor.value!.getFileList();

  const fileIds: string[] = [];
  if (fileList.length > 0) {
    const fileInfoItems = await Promise.all(fileList.map(file => uploadFile(file)));
    fileIds.push(...fileInfoItems.map(item => item.fileId))
  }

  await productPostCommentCreateApi({
    postId,
    commentRawHtml: editor.getSemanticHTML(),
    fileIds
  }).then(() => {
    // 清空输入框
    commentEditor.value!.clearAllData();
  });
  requestCommentList(null);
}


const commentList = ref<RollResult<ProductPostCommentItem>>([]);

function requestCommentList(next = commentList.value.next) {
  const { postId, productCode } = route.params as any;
  productPostCommentListApi({
    postId,
    productCode,
    next
  }).then(res => {
    if (next == null) {
      commentList.value = res;
    } else {
      commentList.value.list.push(...res.list);
      commentList.value.next = res.next;
    }
  });
}

onMounted(() => {
  requestCommentList(null);
})


function handleCopyShareUrl() {
  window.platform.clipboard.copyText(window.location.href);
}
const replyCommentRef = ref<ProductReplyCommentInstance>();
function openReplyComment(comment: ProductPostCommentItem) {
  console.log('comment', comment);
  replyCommentRef.value.show(comment);
}

function requestReplyChildrenComment(commentId: string, next: string) {
  const pageSize = 10;
  productPostCommentChildrenListApi({
    next,
    commentId,
    pageSize,
  }).then(res => {
    const list = commentList.value.list;
    const comment = list.find(item => item.commentId === commentId);
    if (comment) {
      if (next) {
        comment.children.push(...res.list);
        comment.end = res.list.length !== pageSize;
      } else {
        const commentIds = new Set(comment.children.map(item => item.commentId));
        for (const item of res.list) {
          if (commentIds.has(item.commentId)) {
            return;
          }
          comment.children.unshift(item);
        }
      }
    }
  })
}
</script>

<template>
  <div class="flex gap-4">
    <div :class="isDesktop ? 'w-9/12' : 'w-full'">
      <!-- 吐槽详情  -->
      <div class="bg-base-100 p-5 rounded-xl shadow">
        <CardTitle>
          吐槽详情
        </CardTitle>
        <div class="p-2">
          <PostPreview v-if="postDetail"
                       :content="postDetail.postRawHtml" />
        </div>
        <FileList v-if="postDetail"
                  v-model:file-list="postDetail.fileList"
                  readonly />

        <div class="flex items-center justify-between">
          <div></div>
          <div class="flex items-center gap-2">
            <div class="text-sm flex items-center">
              <div class="flex items-center gap-1 cursor-pointer"
                   style="padding-top: 2.5px;"
                   @click="handleCopyShareUrl">
                <svg-icon :size="18" name="default-share"></svg-icon>
                <span class="text-xs font-medium">
                  分享
                </span>
              </div>
            </div>
            <div v-if="postDetail" class="flex items-center gap-2">
              <PostThumbsUp :post-id="route.params.postId"
                            :post-thumbs-up-count="postDetail.postThumbsUpCount"
                            @handlePostThumbs="handlePostThumbs"
                            :thumbs-up-status="postThumbsUp"
              />
              <div style="padding-top: 1px">
                <PostFollow :post-id="route.params.postId"
                            :post-follow-count="postDetail.postFollowCount"
                            @handleFollow="handleFollow"
                            :follow-status="postFollowStatus" />
              </div>
            </div>
          </div>
        </div>
        <!--  mobile:吐槽信息  -->
        <div v-if="!isDesktop" class="w-full mt-2 shrink-0">
          <div class="border mt-4 mb-4" style="border-width: 1px"></div>
          <ProductPosterInfo v-if="postDetail"
                             :member-info="postDetail.memberInfo"
                             :post-type="postDetail.postType"
                             :post-handle-progress="postDetail.postHandleProgress"
                             :create-time="postDetail.createTime" />
        </div>
      </div>
      <!-- 评论列表  -->
      <div class="mt-8 bg-base-100 p-5 shadow rounded-xl">
        <!-- PC:发表评论  -->
        <div>
          <CardTitle>
            评论
          </CardTitle>
          <div class="grid grid-cols-[50px_1fr] mt-6 min-h-20 max-h-52">
            <div class="w-10 avatar shrink-0">
              <div class="w-10 h-10 rounded-full">
                <img v-if="memberStore.memberInfo"
                     :src="memberStore.memberInfo.memberAvatar"  alt="" />
              </div>
            </div>
            <div class="h-full max-h-48">
              <user-editor ref="commentEditor" :hide-toolbar="['#H']"></user-editor>
            </div>
          </div>
          <div class="flex justify-end mt-4">
            <UButton v-if="memberStore.memberInfo"
                     :handle-click="handleCreateComment">
              <template #icon>
                <svg-icon color="hsl(var(--primary-foreground))"
                          name="default-publish" />
              </template>
              评论
            </UButton>
            <Button v-else>
              登录
            </Button>
          </div>
          <div class="border mt-4 mb-4 border-dashed"></div>
        </div>
        <!--  列表   -->
        <ProductReplyComment ref="replyCommentRef"
                             @ok="(commentId) => requestReplyChildrenComment(commentId, null)" />
        <div class="flex"
             v-for="comment in commentList.list"
             :key="comment.commentId">
          <!--  帖子发布人  -->
          <member-info-content v-if="comment"
                               v-bind="comment.commenterInfo"
                               :create-time="comment.createTime">
            <div class="w-full pr-5">
              <!--   评论内容   -->
             <div class="w-full">
               <PostPreview :content="comment.commentRawHtml" />
             </div>
              <div class="flex items-center justify-between">
                <div></div>
                <div class="flex items-center gap-2">
                  <div class="text-sm cursor-pointer link-hover"
                       style="padding-top: 2px;"
                       @click="openReplyComment(comment)">
                    回复
                  </div>
<!--                  <div class="flex items-center gap-1 cursor-pointer">
                    <div>
                      <svg-icon :size="18" name="product-thumbsUp" />
                    </div>
                    <div>
                      {{comment.commentThumbsUpCount || 0}}
                    </div>
                  </div>-->
                </div>
              </div>
              <!--  回复  -->
              <div v-for="subComment in comment.children"
                   :key="subComment.commentId"
                   class="pt-5 flex flex-col gap-4">
                <member-info-content v-if="subComment"
                                     v-bind="subComment.commenterInfo"
                                     :create-time="subComment.createTime"
                                     size="small">
                  <div class="w-full pr-5">
                    <post-preview :content="subComment.commentRawHtml" />
                  </div>
                  <template v-if="subComment.beCommenterInfo" #extend>
                    <div class="flex items-center font-medium text-sm gap-1">
                      <div>&gt;</div>
                      <div class="font-medium">{{ subComment.beCommenterInfo.memberNickname }}</div>
                    </div>
                  </template>
                  <div class="flex items-center justify-between pr-3">
                    <div></div>
                    <div class="flex items-center gap-2">
                      <div class="text-sm cursor-pointer link-hover"
                           style="padding-top: 2px;"
                           @click="openReplyComment(subComment)">
                        回复
                      </div>
<!--                      <div class="flex items-center gap-1 cursor-pointer">
                        <div>
                          <svg-icon :size="18" name="product-thumbsUp" />
                        </div>
                        <div>
                          {{comment.commentThumbsUpCount || 0}}
                        </div>
                      </div>-->
                    </div>
                  </div>
                </member-info-content>
              </div>
              <div v-if="comment.children && comment.commentChildrenCount > 5 && !comment.end"
                   class="flex justify-center"
                   @click="() => requestReplyChildrenComment(comment.commentId, comment.children[comment.children.length - 1].commentId)">
                <div class="text-sm text-center btn btn-xs btn-outline">
                  加载更多
                </div>
              </div>
              <div class="border mt-4 mb-4" style="border-width: 1px"></div>
            </div>
          </member-info-content>
        </div>
      </div>
    </div>
    <!--  PC:吐槽信息  -->
    <div v-if="isDesktop" class="w-3/12 shrink-0" >
      <div class="w-full p-5 rounded-xl shadow bg-base-100">
        <ProductPosterInfo v-if="postDetail"
                           :member-info="postDetail.memberInfo"
                           :post-type="postDetail.postType"
                           :post-handle-progress="postDetail.postHandleProgress"
                           :create-time="postDetail.createTime" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
p {
  /* 允许文本换行 */
  word-wrap: break-word; /* 旧版支持 */
  overflow-wrap: break-word; /* 新版支持 */
  white-space: normal; /* 允许文本在到达边界时换行 */
}
</style>
