<script setup lang="ts">
import { CollapsibleContent, type CollapsibleContentProps } from 'radix-vue'

const props = defineProps<CollapsibleContentProps>()
</script>

<template>
  <CollapsibleContent v-bind="props" class="tw-overflow-hidden tw-transition-all data-[state=closed]:tw-animate-collapsible-up data-[state=open]:tw-animate-collapsible-down">
    <slot />
  </CollapsibleContent>
</template>
