<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <div
    data-sidebar="group"
    :class="cn('tw-relative tw-flex tw-w-full tw-min-w-0 tw-flex-col tw-p-2', props.class)"
  >
    <slot />
  </div>
</template>
