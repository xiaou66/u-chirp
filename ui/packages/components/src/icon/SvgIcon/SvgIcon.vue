<script setup lang="ts">
import { svgIconDefaultProps } from './SvgIcon'
import type { SvgIconProps } from './SvgIcon'
import {computed, ref} from 'vue'

const props = withDefaults(defineProps<SvgIconProps>(), svgIconDefaultProps)

const symbolId = computed(() => `#${props.prefix}-${props.name}`)
const isColor = computed(() => props.color.startsWith('oklch'));
const hover = ref(false);
function handleMouseEnter() {
  hover.value = true;
}
function handleMouseLeave() {
  hover.value = false;
}
</script>

<template>
  <span :style="{fontSize: `${size}px`}"
        @mouseenter="handleMouseEnter"
        @mouseleave="handleMouseLeave">
    <svg v-if="!isColor" aria-hidden="true" :class="svgClass">
      <use :xlink:href="symbolId"
           :fill="hover ? hoverColor || color : color" />
    </svg>
    <svg v-else
         :style="{ color: hover ? hoverColor || color : color, fill: hover ? hoverColor || color : color }"
         aria-hidden="true"
         :class="svgClass">
      <use :xlink:href="symbolId" />
    </svg>
  </span>
</template>

<style  scoped>
svg, span {
  transition: all 250ms linear;
}
svg {
  width: 1em;
  height: 1em;
  stroke: #ffffff;
}
</style>
