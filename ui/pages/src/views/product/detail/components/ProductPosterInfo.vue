<script setup lang="ts">
import { MemberInfo } from "../../components";
import type { ChirpMemberBaseInfo } from "../../../../api";
import {ProductConstants} from "../../../../constant";
import {CardTitle, DictData, TagPlus, SvgIcon} from "@u-chirp/components";
import {DropdownMenu, DropdownMenuTrigger, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, Button} from "@u-chirp/shadcn";

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
    <!-- 订阅 -->
<!--    <div class="w-full">
      <div class="divider"></div>
      <DropdownMenu>
        <DropdownMenuTrigger class="w-full">
          <div class="w-full">
            <Button class="w-full" variant="outline">
              订阅
            </Button>
          </div>
        </DropdownMenuTrigger>
        <DropdownMenuContent>
          <DropdownMenuItem>Profile</DropdownMenuItem>
          <DropdownMenuItem>Billing</DropdownMenuItem>
          <DropdownMenuItem>Team</DropdownMenuItem>
          <DropdownMenuItem>Subscription</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    </div>-->
    <!-- 参与人 -->
    <div class="divider divider-start">
      <div class="text-sm text-neutral opacity-70">
        参与人
      </div>
    </div>
    <div class="">
      <div class="avatar-group -space-x-6 rtl:space-x-reverse">
        <div class="avatar">
          <div class="w-9">
            <img src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp" />
          </div>
        </div>
        <div class="avatar">
          <div class="w-9">
            <img src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"  alt=""/>
          </div>
        </div>
        <div class="avatar">
          <div class="w-9">
            <img src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp" />
          </div>
        </div>
        <div class="avatar">
          <div class="w-9">
            <img src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp" />
          </div>
        </div>
      </div>
    </div>
    <!-- 额外操作区   -->
    <div class="divider"></div>
    <div class="flex flex-col gap-2 ">
      <div class="action items-center gap-2 text-sm">
        <div>
          <svg-icon :size="18" name="product-ding" />
        </div>
        <div>钉住吐槽</div>
<!--        <div>取消钉住</div>-->
      </div>
      <div class="action items-center gap-2 text-sm">
        <div>
          <svg-icon :size="18" name="default-lock" />
        </div>
        <div>锁定吐槽</div>
        <!--        <div>取消钉住</div>-->
      </div>
      <div class="action text-sm">
        <div>
          <svg-icon :size="18" name="default-delete" />
        </div>
        <div>删除吐槽</div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
.action {
  display: flex;
  gap: 1rem;
  &:hover {
    color: oklch(var(--p));
    cursor: pointer;
    fill: #0d50cc;
  }
}
</style>
