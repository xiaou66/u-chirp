<script setup lang="ts">
import { MemberInfo } from "../../components";
import type { ChirpMemberBaseInfo } from "../../../../api";
import {ProductConstants} from "../../../../constant";
import {CardTitle, DictData, TagPlus} from "@u-chirp/components";

defineProps<{
  postHandleProgress: number;
  postType: string;
  memberInfo: ChirpMemberBaseInfo,
  createTime: string;
}>();
</script>

<template>
  <div v-if="memberInfo && createTime">
    <div class="mb-4">
      <CardTitle>
        吐槽人信息
      </CardTitle>
    </div>
    <div class="flex items-center justify-between text-sm">
      <div>
        当前阶段:
      </div>
      <DictData :dict-type="ProductConstants.post.post_status"
                :value="postHandleProgress">
        <template #default="{ dict }">
          <tag-plus v-bind="dict.meta.tag"
                    :tag-name="dict.label" />
        </template>
      </DictData>
    </div>
    <div class="flex items-center justify-between text-sm mt-2">
      <div>
        吐槽类型:
      </div>
      <!--   帖子类型   -->
      <DictData :dict-type="ProductConstants.post.post_type"
                :value="postType">
        <template #default="{ dict }">
          <tag-plus v-bind:="dict.meta.tag"
                    :tag-name="dict.label" />
        </template>
      </DictData>
    </div>
    <div class="divider"></div>
    <!--  帖子发布人  -->
    <MemberInfo v-bind="memberInfo" :create-time="createTime" />
  </div>
</template>

<style scoped>

</style>
