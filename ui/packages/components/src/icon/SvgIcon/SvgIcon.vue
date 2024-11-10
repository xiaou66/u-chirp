<script setup lang="ts">
import { svgIconDefaultProps } from './SvgIcon'
import type { SvgIconProps } from './SvgIcon'
import { computed } from 'vue'

const props = withDefaults(defineProps<SvgIconProps>(), svgIconDefaultProps)

const symbolId = computed(() => `#${props.prefix}-${props.name}`)
const isColor = computed(() => props.color.startsWith('oklch'))
</script>

<template>
  <span :style="{fontSize: `${size}px`}">
    <svg v-if="!isColor" aria-hidden="true" :class="svgClass">
      <use :xlink:href="symbolId" :fill="color" />
    </svg>
    <svg :style="{ color, fill: color }" v-else aria-hidden="true" :class="svgClass">
      <use :xlink:href="symbolId" />
    </svg>
  </span>
</template>

<style  scoped>
svg {
  width: 1em;
  height: 1em;
}
</style>
