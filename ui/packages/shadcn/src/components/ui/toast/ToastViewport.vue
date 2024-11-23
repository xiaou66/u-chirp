<script setup lang="ts">
import { cn } from '@/lib/utils'
import { ToastViewport, type ToastViewportProps } from 'radix-vue'
import { computed, type HTMLAttributes } from 'vue'

const props = defineProps<ToastViewportProps & { class?: HTMLAttributes['class'] }>()

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props

  return delegated
})
</script>

<template>
  <ToastViewport v-bind="delegatedProps" :class="cn('tw-fixed tw-top-0 tw-z-[100] tw-flex tw-max-h-screen tw-w-full tw-flex-col-reverse tw-p-4 sm:tw-bottom-0 sm:tw-right-0 sm:tw-top-auto sm:tw-flex-col md:tw-max-w-[420px]', props.class)" />
</template>
