<script setup lang="ts">
import { svgIconDefaultProps } from './SvgIcon'
import type { SvgIconProps } from './SvgIcon'
import { computed } from 'vue'

const props = withDefaults(defineProps<SvgIconProps>(), svgIconDefaultProps)

const symbolId = computed(() => `#${props.prefix}-${props.name}`)

</script>

<template>
  <span :style="{fontSize: `${size}px`}">
    <svg aria-hidden="true">
    <use :xlink:href="symbolId" :fill="color" />
  </svg>
  </span>
</template>

<style  scoped>
svg {
  width: 1em;
  height: 1em;
}
</style>
