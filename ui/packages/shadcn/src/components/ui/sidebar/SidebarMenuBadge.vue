<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <div
    data-sidebar="menu-badge"
    :class="cn(
      'tw-absolute tw-right-1 tw-flex tw-h-5 tw-min-w-5 tw-items-center tw-justify-center tw-rounded-md tw-px-1 tw-text-xs tw-font-medium tw-tabular-nums tw-text-sidebar-foreground tw-select-none tw-pointer-events-none',
      'tw-peer-hover/menu-button:text-sidebar-accent-foreground tw-peer-data-[active=true]/menu-button:text-sidebar-accent-foreground',
      'tw-peer-data-[size=sm]/menu-button:top-1',
      'tw-peer-data-[size=default]/menu-button:top-1.5',
      'tw-peer-data-[size=lg]/menu-button:top-2.5',
      'group-data-[collapsible=icon]:tw-hidden',
      props.class,
    )"
  >
    <slot />
  </div>
</template>
