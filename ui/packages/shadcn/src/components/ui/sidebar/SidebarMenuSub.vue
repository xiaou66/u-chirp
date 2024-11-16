<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <ul
    data-sidebar="menu-badge"
    :class="cn(
      'tw-mx-3.5 tw-flex tw-min-w-0 tw-translate-x-px tw-flex-col tw-gap-1 tw-border-l tw-border-sidebar-border tw-px-2.5 tw-py-0.5',
      'group-data-[collapsible=icon]:tw-hidden',
      props.class,
    )"
  >
    <slot />
  </ul>
</template>
