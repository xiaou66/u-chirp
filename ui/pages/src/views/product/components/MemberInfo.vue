<script setup lang="ts">
import { formatUserTime } from "@u-chirp/utils";
const props = defineProps<{
  memberId: string;
  memberNickname: string;
  memberAvatar: string;
  createTime: string;

}>();
</script>

<template>
  <div class="flex items-center gap-2">
    <div class="avatar cursor-pointer">
      <div class="w-10 rounded-full">
        <img :src="memberAvatar"  alt=""/>
      </div>
    </div>
    <div class="flex flex-col gap-1">
      <div class="flex gap-2 text-xs text-base-content items-center">
        <div class="font-medium">{{memberNickname}}</div>
        <div>
          <!--          <el-tag type="danger" size="small">超级管理员</el-tag>-->
        </div>
      </div>
      <div v-if="props.createTime" class="text-xs text-base-content font-medium">
        {{ formatUserTime(createTime) }}
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
