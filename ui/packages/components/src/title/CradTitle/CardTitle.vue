<script setup lang="ts">
import type {CardTitleProps} from "./CardTitle";

defineProps<CardTitleProps>();
</script>

<template>
  <div class="card-title">
    <div class="vertical" :style="{ background: borderLeftColor || 'oklch(var(--bc))' }" />
    <div class="font-medium pl-3 text-base">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="less" scoped>
.card-title {
  position: relative;
  .vertical {
    position: absolute;
    left: 0;
    width: 4px;
    height: 100%;
  }
}
</style>
