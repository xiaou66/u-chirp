<script setup lang="ts">
import {SvgIcon} from "@u-chirp/components";
import { productPostThumbsUpApi } from "../../../api";
import {useRoute} from "vue-router";

defineProps<{
  postId: string;
  postThumbsUpCount: string;
  thumbsUpStatus?: boolean;
}>();

const route = useRoute();

const emits = defineEmits<{
  (e: 'handlePostThumbs', postId: string, thumbsUp: boolean): void;
  (e: 'postThumbsFinish'): void
}>();

function handlePostThumbs(postId: string, thumbsUp: boolean) {
  productPostThumbsUpApi({
    productCode: route.params.productCode as string,
    postId,
    thumbsUp
  }).then(() => {
    emits('postThumbsFinish')
  });
  emits('handlePostThumbs', postId, thumbsUp);
}

</script>

<template>
  <div class="flex items-center gap-1 cursor-pointer w-10 select-none">
    <svg-icon v-if="!thumbsUpStatus"
              svg-class="text-xl"
              name="product-thumbsUp"
              @click="handlePostThumbs(postId, true)"/>
    <svg-icon v-else
              svg-class="text-xl"
              color="#3C98FF"
              name="product-thumbsUpFill"
              @click="handlePostThumbs(postId, false)"/>
    {{ postThumbsUpCount }}
  </div>
</template>

<style scoped>

</style>
