<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import Sheet from '@/components/ui/sheet/Sheet.vue'
import SheetContent from '@/components/ui/sheet/SheetContent.vue'
import { cn } from '@/lib/utils'
import { SIDEBAR_WIDTH_MOBILE, useSidebar } from './utils'

defineOptions({
  inheritAttrs: false,
})

const props = withDefaults(defineProps<{
  side?: 'left' | 'right'
  variant?: 'sidebar' | 'floating' | 'inset'
  collapsible?: 'offcanvas' | 'icon' | 'none'
  class?: HTMLAttributes['class']
}>(), {
  side: 'left',
  variant: 'sidebar',
  collapsible: 'offcanvas',
})

const { isMobile, state, openMobile, setOpenMobile } = useSidebar()
</script>

<template>
  <div
    v-if="collapsible === 'none'"
    :class="cn('tw-flex tw-h-full tw-w-[--sidebar-width] tw-flex-col tw-bg-sidebar tw-text-sidebar-foreground', props.class)"
    v-bind="$attrs"
  >
    <slot />
  </div>

  <Sheet v-else-if="isMobile" :open="openMobile" v-bind="$attrs" @update:open="setOpenMobile">
    <SheetContent
      data-sidebar="sidebar"
      data-mobile="true"
      class="tw-w-[--sidebar-width] tw-bg-sidebar tw-p-0 tw-text-sidebar-foreground [&>button]:tw-hidden"
      :style="{
        '--sidebar-width': SIDEBAR_WIDTH_MOBILE,
      }"
    >
      <div class="tw-flex tw-h-full tw-w-full tw-flex-col">
        <slot />
      </div>
    </SheetContent>
  </Sheet>

  <div
    v-else class="tw-group tw-peer tw-hidden md:tw-block"
    :data-state="state"
    :data-collapsible="state === 'collapsed' ? collapsible : ''"
    :data-variant="variant"
    :data-side="side"
  >
    <!-- This is what handles the sidebar gap on desktop  -->
    <div
      :class="cn(
        'tw-duration-200 tw-relative tw-h-svh tw-w-[--sidebar-width] tw-bg-transparent tw-transition-[width] tw-ease-linear',
        'group-data-[collapsible=offcanvas]:tw-w-0',
        'group-data-[side=right]:tw-rotate-180',
        variant === 'floating' || variant === 'inset'
          ? 'group-data-[collapsible=icon]:tw-w-[calc(var(--sidebar-width-icon)_+_theme(spacing.4))]'
          : 'group-data-[collapsible=icon]:tw-w-[--sidebar-width-icon]',
      )"
    />
    <div
      :class="cn(
        'tw-duration-200 tw-fixed tw-inset-y-0 tw-z-10 tw-hidden tw-h-svh tw-w-[--sidebar-width] tw-transition-[left,right,width] tw-ease-linear md:tw-flex',
        side === 'left'
          ? 'tw-left-0 group-data-[collapsible=offcanvas]:tw-left-[calc(var(--sidebar-width)*-1)]'
          : 'tw-right-0 group-data-[collapsible=offcanvas]:tw-right-[calc(var(--sidebar-width)*-1)]',
        // Adjust the padding for floating and inset variants.
        variant === 'floating' || variant === 'inset'
          ? 'tw-p-2 group-data-[collapsible=icon]:tw-w-[calc(var(--sidebar-width-icon)_+_theme(spacing.4)_+2px)]'
          : 'group-data-[collapsible=icon]:tw-w-[--sidebar-width-icon] group-data-[side=left]:tw-border-r group-data-[side=right]:tw-border-l',
        props.class,
      )"
      v-bind="$attrs"
    >
      <div
        data-sidebar="sidebar"
        class="tw-flex tw-h-full tw-w-full tw-flex-col tw-bg-sidebar group-data-[variant=floating]:tw-rounded-lg group-data-[variant=floating]:tw-border group-data-[variant=floating]:tw-border-sidebar-border group-data-[variant=floating]:tw-shadow"
      >
        <slot />
      </div>
    </div>
  </div>
</template>
