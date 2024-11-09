<script setup lang="ts">
import { TopMenu, SvgIcon } from '@u-chirp/components'
import type { MenuItem } from '@u-chirp/components'
import { ref } from 'vue'
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
</script>

<template>
  <TopMenu :menu-items="menuItems"
           v-model:active="menuActive"
           menu-position="left"
           @menu-item-click="handleMenuItemClick"
           class="shadow"
  >
    <template #extra>
      <div class="flex items-center">
        <!-- 未登录  -->
        <button class="btn btn-sm">{{$t('user.login')}}</button>
      </div>
      <div class="dropdown dropdown-end">
        <div tabindex="0" role="button" class="flex items-center">
          <div class="avatar cursor-pointer">
            <div class="w-10 rounded-full">
              <img src="https://s2.loli.net/2023/04/15/k4IbQGzMZ9v6fYN.jpg" />
            </div>
          </div>
        </div>
        <ul tabindex="0" class="dropdown-content menu  rounded-box z-[1] w-32 p-2 shadow">
          <li>
            <a class="flex">
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
