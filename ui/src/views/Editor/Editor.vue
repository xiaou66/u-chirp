<script setup lang="ts">
import { onMounted } from 'vue';
import Quill from 'quill';
import 'quill/dist/quill.snow.css'; // 引入样式
onMounted(() => {
  const quill = new Quill("#editor", {
    theme: "snow",
  });
})
</script>

<template>
  <div id="editor"></div>
</template>

<style scoped>

</style>
