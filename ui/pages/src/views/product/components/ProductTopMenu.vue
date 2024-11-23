<script setup lang="ts">
import { TopMenu, SvgIcon } from '@u-chirp/components'
import type { MenuItem } from '@u-chirp/components'
import {ref, toRefs} from 'vue'
import {useMemberStore, useProductStore} from "../../../stores";
import {useToast} from "@u-chirp/shadcn";
import {useRouter} from "vue-router";
const menuItems = ref<MenuItem[]>([
  {
    key: '1',
    title: '首页',
  },
]);
const menuActive = ref('1');
// 菜单单击
function handleMenuItemClick(key: string | number) {

}

const { token, memberInfo } = toRefs(useMemberStore());
const { clearMember } = useMemberStore();
const { toast } = useToast();
function handleLogout() {
  clearMember();
  toast({
    title: '登出成功',
    description: '欢迎下次再来吐槽'
  })
}
const router = useRouter();
function handleLogin() {
  router.push({ name: 'userLogin' });
}
const { productInfo } = toRefs(useProductStore());
</script>

<template>
  <TopMenu :menu-items="menuItems"
           v-model:active="menuActive"
           menu-position="left"
           @menu-item-click="handleMenuItemClick"
           class="shadow"
  >
    <template #logo>
      <div class="w-9 rounded-xl" v-if="productInfo">
        <img :src="productInfo.productLogo" />
      </div>
      <div v-if="productInfo">{{productInfo.productName}}</div>
    </template>
    <template #extra>
      <div v-if="!token" class="flex items-center">
        <!-- 未登录  -->
        <button class="btn btn-sm" @click="handleLogin">{{$t('user.login')}}</button>
      </div>
      <div v-if="token && memberInfo" class="dropdown dropdown-end">
        <div tabindex="0" role="button" class="flex items-center">
          <div class="avatar cursor-pointer">
            <div class="w-10 rounded-full">
              <img :src="memberInfo.memberAvatar" />
            </div>
          </div>
        </div>
        <ul tabindex="0" class="dropdown-content bg-base-100 menu rounded-box z-[1] w-32 p-2 shadow">
          <li>
            <a class="flex" @click="handleLogout">
              <svg-icon name="user-logout" />
              {{$t('user.logout')}}
            </a>
          </li>
        </ul>
      </div>
    </template>
  </TopMenu>
</template>

<style scoped>

</style>
