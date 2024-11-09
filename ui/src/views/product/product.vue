<script lang="ts" setup>
import { type MenuItem, TopMenu } from '@u-chirp/components'
import { ref } from 'vue'
const menuItems = ref<MenuItem[]>([
  {
    key: '1',
    title: '主页',
    children: [
      {
        key: '1',
        title: '111',
      }
    ]
  }
]);
// 菜单单击
function handleMenuItemClick(key: string) {
  console.log('handleMenuItemClick', key)
}
</script>
<template>
  <div>
    <TopMenu :menu-items="menuItems"
             menu-position="left"
             @menu-item-click="handleMenuItemClick">
    </TopMenu>
  </div>
</template>
<style lang="less" scoped></style>
