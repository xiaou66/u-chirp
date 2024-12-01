<script setup lang="ts">
defineProps<{
  content: string
}>();
</script>

<template>
  <div class="post-preview">
    <div v-html="content"></div>
  </div>
</template>

<style lang="less">
.post-preview {
  @import "@u-chirp/components/src/assets/poststyle";
}
</style>
