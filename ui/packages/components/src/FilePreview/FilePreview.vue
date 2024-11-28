<script setup lang="ts">
import {computed, ref, provide, onMounted, onUnmounted} from "vue";
import { SvgIcon } from "@u-chirp/components";
import type {FilePreviewInstance, FilePreviewProps} from "./type";
import hotkeys from "hotkeys-js";

const props = defineProps<FilePreviewProps>();

const fileList = ref<(File | object)[]>([]);
const fileIndex = ref(0);

const first = ref(false);
const fileUrl = computed(() => {
  return (item: File | object) => {
    if (item instanceof File) {
      return window.URL.createObjectURL(item);
    } else if (item) {
      return (item as any)[props.fileUrlKey || 'url'] as string;
    }
  }
})
function fileChange(e: any) {
  fileList.value = Array.from(e.target.files);
}
function next() {
  if (fileIndex.value === fileList.value.length - 1) {
    fileIndex.value = 0;
  } else {
    fileIndex.value += 1;
  }
}
function previous() {
  if (fileIndex.value === 0) {
    fileIndex.value = fileList.value.length - 1;
  } else {
    fileIndex.value -= 1;
  }
}

const show = ref(false);

function previewFile(files: (File | object)[], index = 0) {
  if (!first.value) {
    first.value = true
  }
  fileIndex.value = index;
  fileList.value = files;
  show.value = true;
  hotkeys.setScope('filePreview');
  hotKeyScope.value = hotkeys.getScope();
}
function hidePreviewFile() {
  show.value = false;
  fileIndex.value = 0;
  fileList.value.length = 0;
  hotkeys.setScope(hotKeyScope.value);
}
const hotKeyScope = ref<string>('default');
onMounted(() => {
  hotkeys.unbind('esc', 'filePreview');
  hotkeys('esc', {
    scope: 'filePreview'
  }, (e) => {
    e.stopPropagation();
    hidePreviewFile();
  })
  previewFile([]);
});

onUnmounted(() => {
  hotkeys.unbind('esc', 'filePreview');
});

provide<FilePreviewInstance>('filePreviewInstance', {
  previewFile
});

defineExpose<FilePreviewInstance>({
  previewFile
});
</script>

<template>
  <Transition name="fade">
    <div v-if="first"
         v-show="show"
         class="file-preview">
      <div class="switchover">
        <div v-show="fileIndex !== 0"
             @click="previous">
          <svg-icon color="#ffffff" name="default-arrowLeft" />
        </div>
        <div v-show="fileIndex !== fileList.length - 1 && fileList.length > 1"
             @click="next">
          <svg-icon color="#ffffff" name="default-arrowRight" />
        </div>
      </div>
      <div class="file-preview-container">
        <img class="select-none"
             :src="fileUrl(fileList[fileIndex])"
             alt="">
      </div>
      <div class="close"
           @click="hidePreviewFile">
        <svg-icon :size="18"
                  color="#bfbfbf"
                  hover-color="#ffffff"
                  name="default-close" />
      </div>
      <div class="action">
      </div>
    </div>
  </Transition>
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
.close {
  position: absolute;
  right: 20px;
  top: 10px;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  cursor: pointer;
  background: rgba(0,0,0,0.4);
  display: flex;
  justify-content: center;
  align-items: center;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 250ms ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
