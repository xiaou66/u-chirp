<script setup lang="ts">
import type { TopMenuEmits, TopMenuProps } from './TopMenu'
import  { topMenuDefaultProps } from './TopMenu'
import LgTopMenu from './components/LgTopMenu.vue'
withDefaults(defineProps<TopMenuProps>(), topMenuDefaultProps);

const emits = defineEmits<TopMenuEmits>()
</script>

<template>
  <div class="navbar bg-base-100">
    <div class="navbar-start">
      <div class="dropdown">
        <div tabindex="0" role="button" class="btn btn-ghost lg:hidden">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor">
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h8m-8 6h16" />
          </svg>
        </div>
        <ul
          tabindex="0"
          class="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow">
          <li v-for="menuItem in menuItems"
              :key="menuItem.key">
            <a v-if="!menuItem.children || !menuItem.children.length"
               @click="() => emits('menuItemClick', menuItem.key)">
              {{ menuItem.title }}
            </a>
            <ul v-else class="p-2">
              <a>{{ menuItem.title }}</a>
              <li v-for="subMenuItem in menuItem.children"
                  :key="subMenuItem.key"
                  @click="() => emits('menuItemClick', menuItem.key)">
                <a>{{ menuItem.title }}</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <a class="btn btn-ghost text-xl">
        <div class="flex items-center gap-2">
          <div class="w-9 rounded-xl">
            <img src="https://s21.ax1x.com/2024/11/08/pAyzY7j.png" />
          </div>
          <div>槽小圈</div>
        </div>
      </a>
      <div v-if="menuPosition === 'left'"  class="navbar-start hidden lg:flex w-1/2">
        <lg-top-menu :menu-items="menuItems"
                     @click="(key) => emits('menuItemClick', key)" />
      </div>
    </div>
    <div v-if="menuPosition === 'center'"  class="navbar-center hidden lg:flex">
      <lg-top-menu :menu-items="menuItems"
                   @click="(key) => emits('menuItemClick', key)" />
    </div>
    <div class="navbar-end">
      <slot name="extra"></slot>
    </div>
  </div>
</template>

<style scoped>

</style>
