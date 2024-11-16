<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import Input from '@/components/ui/input/Input.vue'
import { cn } from '@/lib/utils'

const props = defineProps<{
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <Input
    data-sidebar="input"
    :class="cn(
      'tw-h-8 tw-w-full tw-bg-background tw-shadow-none focus-visible:tw-ring-2 focus-visible:tw-ring-sidebar-ring',
      props.class,
    )"
  >
    <slot />
  </Input>
</template>
