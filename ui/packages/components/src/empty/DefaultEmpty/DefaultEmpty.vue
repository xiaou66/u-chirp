<script setup lang="ts">
import { SvgIcon } from "@u-chirp/components";
</script>

<template>
  <div class="w-full h-full flex items-center  flex-col">
    <div>
      <slot name="icon">
        <svg-icon :size="256" name="default-empty" color="oklch(var(--sc))" />
      </slot>
    </div>
    <div class="text-sm" style="color: oklch(var(--sc))">
      <slot name="description">
        无数据
      </slot>
    </div>
  </div>
</template>

<style scoped>

</style>
