<script setup lang="ts">
import {
  SvgIcon,
  UButton,
  UDialog,
  type UDialogInstance,
  UserEditor,
  type UserEditorInstance
} from "@u-chirp/components";
import {ref} from "vue";
import type {ProductReplyCommentInstance} from "./ProductReplyComment";
import {productPostCommentCreateApi, type ProductPostCommentItem, uploadFile} from "../../../../../api";
import {useRoute} from "vue-router";


const userEditorRef = ref<UserEditorInstance>();
const dialogRef = ref<UDialogInstance>();

const emits = defineEmits<{
  (e: 'ok', commentId: string): void;
}>();

const commentData = ref<ProductPostCommentItem>();
function show(data: ProductPostCommentItem) {
  commentData.value = data;
  dialogRef.value.show();
}
const route = useRoute();
async function handleReplyComment() {
  debugger
  const editor = userEditorRef.value!.getEditor();
  const text = editor.getText().trim();

  if (!text) {
    return;
  }

  const fileList = userEditorRef.value!.getFileList();

  const fileIds: string[] = [];
  if (fileList.length > 0) {
    const fileInfoItems = await Promise.all(fileList.map(file => uploadFile(file)));
    fileIds.push(...fileInfoItems.map(item => item.fileId))
  }

  const { commentId, parentCommentId } = commentData.value!;
  const { postId } = route.params as {postId: string};
  await productPostCommentCreateApi({
    postId,
    parentCommentId: parentCommentId.toString() === '0' ? commentId : parentCommentId,
    replyCommentId: commentId,
    commentRawHtml: editor.getSemanticHTML(),
    fileIds
  });
  dialogRef.value!.close();
  emits('ok', parentCommentId.toString() === '0' ? commentId : parentCommentId);
}


defineExpose<ProductReplyCommentInstance>( {
  show
});
</script>

<template>
  <UDialog ref="dialogRef" hide-trigger>
    <template #title>
      回复 「{{ commentData.commenterInfo.memberNickname }}」
    </template>
    <div class="pt-3 h-56 mobile:h-72">
      <UserEditor ref="userEditorRef" :hide-toolbar="['#H']"></UserEditor>
    </div>
    <template #footer>
      <UButton size="auto"
               :handle-click="handleReplyComment">
        <template #icon>
          <svg-icon color="hsl(var(--primary-foreground))"
                    name="default-publish" />
        </template>
        回复
      </UButton>
    </template>
  </UDialog>
</template>

<style scoped>

</style>
