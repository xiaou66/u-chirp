<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <div
    data-sidebar="footer"
    :class="cn('tw-flex tw-flex-col tw-gap-2 tw-p-2', props.class)"
  >
    <slot />
  </div>
</template>
