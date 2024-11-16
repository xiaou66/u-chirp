<script setup lang="ts">
import type { PrimitiveProps } from 'radix-vue'
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'
import { Primitive } from 'radix-vue'

const props = defineProps<PrimitiveProps & {
  class?: HTMLAttributes['class']
}>()
</script>

<template>
  <Primitive
    data-sidebar="group-label"
    :as="as"
    :as-child="asChild"
    :class="cn(
      'tw-duration-200 tw-flex tw-h-8 tw-shrink-0 tw-items-center tw-rounded-md tw-px-2 tw-text-xs tw-font-medium tw-text-sidebar-foreground/70 tw-outline-none tw-ring-sidebar-ring tw-transition-[margin,opa] tw-ease-linear focus-visible:tw-ring-2 [&>svg]:tw-size-4 [&>svg]:tw-shrink-0',
      'group-data-[collapsible=icon]:tw--mt-8 group-data-[collapsible=icon]:tw-opacity-0',
      props.class)"
  >
    <slot />
  </Primitive>
</template>
