<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <span :class="cn('tw-ml-auto tw-text-xs tw-tracking-widest tw-opacity-60', props.class)">
    <slot />
  </span>
</template>
