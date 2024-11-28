<script setup lang="ts">
import {ref} from "vue";

const first = ref(true);
</script>

<template>
  <div v-if="first" class="file-preview">
    <div class="switchover">
      <div>左</div>
      <div>右</div>
    </div>
    <div class="file-preview-container">
      <img src="https://vip.helloimg.com/i/2024/06/08/6664694be4124.png" alt="">
    </div>
  </div>
</template>

<style lang="less" scoped>
.file-preview {
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 9999;
  background: rgba(0, 0, 0, 0.5);
}
.switchover {
  width: 100%;
  height: 100%;
  position: absolute;
  >div {
    width: 40px;
    height: 40px;
    background: #606266;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #ffffff;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
  }
  >div:first-child {
    left: 10px;
  }
  >div:last-child {
    right: 10px;
  }
}
.file-preview-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 32px;
  img {
    max-width: 100% !important;
    max-height: 100% !important;
    object-fit: contain !important;
  }
}
</style>
