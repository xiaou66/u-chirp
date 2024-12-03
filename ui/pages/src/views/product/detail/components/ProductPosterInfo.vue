<script setup lang="ts">
import { MemberInfo } from "../../components";
import type { ChirpMemberBaseInfo } from "../../../../api";
import {ProductConstants} from "../../../../constant";
import {CardTitle, DictData, TagPlus} from "@u-chirp/components";
import SvgIcon from "@u-chirp/components/src/icon/SvgIcon/SvgIcon.vue";

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
        吐槽信息
      </CardTitle>
    </div>
    <!--  帖子发布人  -->
    <div>
      <div class="divider divider-start">
        <div class="text-sm text-neutral opacity-70">
          发布人
        </div>
      </div>
      <div class="flex  justify-between text-sm mt-2">
        <MemberInfo v-bind="memberInfo" :create-time="createTime" />
      </div>
    </div>

    <div>
      <div class="divider divider-start">
        <div class="text-sm text-neutral opacity-70">
          吐槽类型
        </div>
      </div>
      <div class="flex  justify-between text-sm mt-2">
        <DictData :dict-type="ProductConstants.post.post_type"
                  :value="postType">
          <template #default="{ dict }">
            <tag-plus v-bind="dict.meta.tag"
                      :tag-name="dict.label" />
          </template>
        </DictData>
        <div>
          <svg-icon :size="20" name="default-setting" />
        </div>
      </div>
    </div>
    <div>
      <div class="divider divider-start">
        <div class="text-sm text-neutral opacity-70">
          当前阶段
        </div>
      </div>
      <div class="flex  justify-between text-sm mt-2">
        <DictData :dict-type="ProductConstants.post.post_status"
                  :value="postHandleProgress">
          <template #default="{ dict }">
            <tag-plus v-bind="dict.meta.tag"
                      :tag-name="dict.label" />
          </template>
        </DictData>
        <div>
          <svg-icon :size="20" name="default-setting" />
        </div>
      </div>
    </div>
    <div class="divider"></div>
    <div class="flex items-center gap-2">
      <div>
        <svg-icon :size="18" color="#3a00db" hover-color="#3a00db" name="product-ding" />
      </div>
      <div>钉住</div>
    </div>
  </div>
</template>

<style scoped>

</style>
