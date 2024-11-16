<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <ul
    data-sidebar="menu"
    :class="cn('tw-flex tw-w-full tw-min-w-0 tw-flex-col tw-gap-1', props.class)"
  >
    <slot />
  </ul>
</template>
