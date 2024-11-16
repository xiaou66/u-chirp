<script setup lang="ts">
import Skeleton from '@/components/ui/skeleton/Skeleton.vue'
import { cn } from '@/lib/utils'
import { computed, type HTMLAttributes } from 'vue'

const props = defineProps<{
  showIcon?: boolean
  class?: HTMLAttributes['class']
}>()

const width = computed(() => {
  return `${Math.floor(Math.random() * 40) + 50}%`;
})
</script>

<template>
  <div
    data-sidebar="menu-skeleton"
    :class="cn('tw-rounded-md tw-h-8 tw-flex tw-gap-2 tw-px-2 tw-items-center', props.class)"
  >
    <Skeleton
      v-if="showIcon"
      class="tw-size-4 tw-rounded-md"
      data-sidebar="menu-skeleton-icon"
    />

    <Skeleton
      class="tw-h-4 tw-flex-1 tw-max-w-[--skeleton-width]"
      data-sidebar="menu-skeleton-text"
      :style="{ '--skeleton-width': width }"
    />
  </div>
</template>
