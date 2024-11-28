<script setup lang="ts">
import {computed, ref} from "vue";
import { SvgIcon } from "@u-chirp/components";

const fileList = ref<(File | object)[]>([]);
const index = ref(0);
// const src =
const first = ref(true);
const fileUrl = computed(() => {
  return (item: File | object) => {
    if (item instanceof File) {
      return window.URL.createObjectURL(item);
    }
  }
})
function fileChange(e: any) {
  fileList.value = Array.from(e.target.files);
}
function next() {
  if (index.value === fileList.value.length - 1) {
    index.value = 0;
  } else {
    index.value += 1;
  }
}
function previous() {
  if (index.value === 0) {
    index.value = fileList.value.length - 1;
  } else {
    index.value -= 1;
  }
}
</script>

<template>
  <div v-if="first" class="file-preview">
    <input type="file" @change="fileChange" multiple>
    <div class="switchover">
      <div v-show="index !== 0"
           @click="previous">
        <svg-icon color="#ffffff" name="default-arrowLeft" />
      </div>
      <div v-show="index !== fileList.length - 1 && fileList.length > 1"
           @click="next">
        <svg-icon color="#ffffff" name="default-arrowRight" />
      </div>
    </div>
    <div class="file-preview-container">
      <img class="select-none"
           :src="fileUrl(fileList[index])"
           alt="">
    </div>
    <div class="action">
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
  padding: 64px;
  img {
    max-width: 100% !important;
    max-height: 100% !important;
    object-fit: contain !important;
  }
}
.action {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px;
  height: 32px;
  background: #141414;
  color: #ffffff;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
}
</style>
