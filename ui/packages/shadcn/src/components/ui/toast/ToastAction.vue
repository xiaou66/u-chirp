<script setup lang="ts">
import { cn } from '@/lib/utils'
import { ToastAction, type ToastActionProps } from 'radix-vue'
import { computed, type HTMLAttributes } from 'vue'

const props = defineProps<ToastActionProps & { class?: HTMLAttributes['class'] }>()

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props

  return delegated
})
</script>

<template>
  <ToastAction v-bind="delegatedProps" :class="cn('tw-inline-flex tw-h-8 tw-shrink-0 tw-items-center tw-justify-center tw-rounded-md tw-border tw-bg-transparent tw-px-3 tw-text-sm tw-font-medium tw-transition-colors hover:tw-bg-secondary focus:tw-outline-none focus:tw-ring-1 focus:tw-ring-ring disabled:tw-pointer-events-none disabled:tw-opacity-50 group-[.destructive]:tw-border-muted/40 group-[.destructive]:hover:tw-border-destructive/30 group-[.destructive]:hover:tw-bg-destructive group-[.destructive]:hover:tw-text-destructive-foreground group-[.destructive]:focus:tw-ring-destructive', props.class)">
    <slot />
  </ToastAction>
</template>
