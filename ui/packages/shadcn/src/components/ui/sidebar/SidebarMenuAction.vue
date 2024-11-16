<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'
import { Primitive, type PrimitiveProps } from 'radix-vue'

const props = withDefaults(defineProps<PrimitiveProps & {
  showOnHover?: boolean
  class?: HTMLAttributes['class']
}>(), {
  as: 'button',
})
</script>

<template>
  <Primitive
    data-sidebar="menu-action"
    :class="cn(
      'tw-absolute tw-right-1 tw-top-1.5 tw-flex tw-aspect-square tw-w-5 tw-items-center tw-justify-center tw-rounded-md tw-p-0 tw-text-sidebar-foreground tw-outline-none tw-ring-sidebar-ring tw-transition-transform hover:tw-bg-sidebar-accent hover:tw-text-sidebar-accent-foreground focus-visible:tw-ring-2 tw-peer-hover/menu-button:text-sidebar-accent-foreground [&>svg]:tw-size-4 [&>svg]:tw-shrink-0',
      // Increases the hit area of the button on mobile.
      'after:tw-absolute after:tw--inset-2 after:md:tw-hidden',
      'tw-peer-data-[size=sm]/menu-button:top-1',
      'tw-peer-data-[size=default]/menu-button:top-1.5',
      'tw-peer-data-[size=lg]/menu-button:top-2.5',
      'group-data-[collapsible=icon]:tw-hidden',
      showOnHover
        && 'tw-group-focus-within/menu-item:opacity-100 tw-group-hover/menu-item:opacity-100 data-[state=open]:tw-opacity-100 tw-peer-data-[active=true]/menu-button:text-sidebar-accent-foreground md:tw-opacity-0',
      props.class,
    )"
    :as="as"
    :as-child="asChild"
  >
    <slot />
  </Primitive>
</template>
