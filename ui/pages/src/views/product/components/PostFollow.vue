<script setup lang="ts">
import {SvgIcon} from "@u-chirp/components";
import {productPostFollowApi} from "../../../api";
import {useRoute} from "vue-router";
import {useI18n} from "vue-i18n";

defineProps<{
  postId: string;
  postFollowCount: string;
  followStatus?: boolean;
}>();

const { t } = useI18n();
const emits = defineEmits<{
  (e: 'handleFollow', postId: string, follow: boolean): void
}>();

const route = useRoute();
function handleFollow(postId: string, follow: boolean) {
  productPostFollowApi({
    productCode: route.params.productCode as string,
    postId,
    follow
  }).then(() => {});
  emits('handleFollow', postId, follow);
}

</script>

<template>
  <el-tooltip :content="t('product.follow')">
    <div class="flex items-center gap-1 cursor-pointer w-10 select-none">
      <svg-icon v-if="!followStatus"
                color="#CDCDCD"
                svg-class="text-2xl"
                name="product-follow"
                @click="handleFollow(postId, true)" />
      <svg-icon v-else color="#3C98FF"
                svg-class="text-2xl"
                name="product-follow"
                @click="handleFollow(postId, false)" />
      <div>{{ postFollowCount }}</div>
    </div>
  </el-tooltip>
</template>

<style scoped>

</style>
