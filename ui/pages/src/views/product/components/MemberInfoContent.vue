<script setup lang="ts">
import { formatUserTime } from "@u-chirp/utils";
import {computed} from "vue";
const props = withDefaults(defineProps<{
  memberId: string;
  memberNickname: string;
  memberAvatar: string;
  createTime?: string;
  size?: 'small' | 'default';
}>(), {
  size: 'default'
});
const calcSize = computed(() => {
 return  props.size == 'small' ? '1.8rem' : '2.5rem';
})
</script>

<template>
  <div class="w-full">
    <div class="grid items-start gap-3"
         :style="`grid-template-columns: ${calcSize} calc(100% - ${calcSize})`">
      <div class="avatar cursor-pointer">
        <div class="w-10 rounded-full" :style="{ width: calcSize }">
          <img :src="memberAvatar"  alt=""/>
        </div>
      </div>
      <div class="flex flex-col">
        <div class="flex gap-1 items-center">
          <div class="flex gap-1 text-sm items-center">
            <div class="font-medium">{{memberNickname}}</div>
            <slot name="extend"></slot>
            <div>
              <!--          <el-tag type="danger" size="small">超级管理员</el-tag>-->
            </div>
          </div>
          <div v-if="createTime"
               class="text-xs text-base-content font-medium">
            {{ formatUserTime(createTime) }}
          </div>
        </div>
        <div class="w-full">
          <slot></slot>
        </div>
      </div>
    </div>
    <slot name="footer"></slot>
  </div>
</template>

<style scoped>

</style>
