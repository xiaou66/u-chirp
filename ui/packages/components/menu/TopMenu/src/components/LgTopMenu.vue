<script setup lang="ts">
import type { TopMenuEmits, TopMenuProps } from '../TopMenu'

defineProps<Pick<TopMenuProps, 'menuItems'>>();

const emits = defineEmits<{
  (event: 'click', key: string): void;
}>()
</script>

<template>
  <ul class="menu menu-horizontal px-1">
    <li v-for="menuItem in menuItems" :key="menuItem.key">
      <a v-if="!menuItem.children || !menuItem.children.length"
         @click="emits('click', menuItem.key)">
        {{ menuItem.title }}
      </a>
      <details v-else>
        <summary>{{menuItem.title}}</summary>
        <ul class="p-2">
          <li v-for="subMenuItem in menuItem.children"
              :key="subMenuItem.key"
              @click="emits('click', menuItem.key)">
            <a>{{ subMenuItem.title }}</a>
          </li>
        </ul>
      </details>
    </li>
  </ul>
</template>

<style scoped>

</style>
