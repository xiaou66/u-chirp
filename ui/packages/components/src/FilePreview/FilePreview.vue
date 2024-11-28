<script setup lang="ts">
import {ref} from "vue";

const first = ref(true);
</script>

<template>
  <div v-if="first" class="file-preview">
    <div class="container">
      <img src="https://vip.helloimg.com/i/2024/06/08/6664694be4124.png" alt="">
    </div>
  </div>
</template>

<style scoped>
.file-preview {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 9999;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 16px;
}
</style>
