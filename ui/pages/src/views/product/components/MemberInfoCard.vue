<script setup lang="ts">
import {useMemberStore, useProductStore} from "../../../stores";
import {toRefs} from "vue";

const { memberInfo } = toRefs(useMemberStore());
const { productMemberInfo } = toRefs(useProductStore());
</script>

<template>
  <div v-if="memberInfo" class="card-body w-80 shadow rounded-2xl  card-compact  bg-base-100 p-5">
    <div class="flex items-center gap-2">
      <div class="avatar cursor-pointer">
        <div class="w-10 rounded-full">
          <img :src="memberInfo.memberAvatar" />
        </div>
      </div>
      <div>{{ memberInfo.memberNickname }}</div>
    </div>
    <div v-if="productMemberInfo">
      <div class="stats flex items-center gap-2">
        <div class="stat cursor-pointer">
          <div class="stat-title text-center">{{$t('user.issue')}}</div>
          <div class="stat-value text-sm text-center">{{ productMemberInfo.problemCount }}</div>
        </div>
        <div class="stat cursor-pointer">
          <div class="stat-title text-center">{{$t('user.beLiked')}}</div>
          <div class="stat-value text-sm text-center">{{ productMemberInfo.thumbsUpCount }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
