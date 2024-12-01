<script setup lang="ts">
import { SvgIcon } from "../icon";
import {computed} from "vue";
import {useFilePreview} from "../FilePreview/FilePreview";
import type { FileListProp } from "./FileList";

defineProps<FileListProp>();


const fileList = defineModel('fileList');

function handleOpenFilePreview(index: number) {
  filePreviewInstance?.previewFile(fileList.value, index)
}

function handleFileDelete(index: number) {
  fileList.value.splice(index, 1);
}

const fileUrl = computed(() => {
  return (item: File | object) => {
    if (item instanceof File) {
      return window.URL.createObjectURL(item);
    } else {
      return item.previewUrl.toString().startsWith('http')
        ? item.previewUrl.toString()
        : `${import.meta.env.VITE_API_BASE_URL}${item.previewUrl}`;
    }
  }
});

const filePreviewInstance = useFilePreview();
</script>

<template>
  <div v-if="fileList.length > 0"
       class="mt-2 flex overflow-x-auto gap-2 h-20 shrink-0">
    <div v-for="(file, index) in fileList"
         :key="index"
         class="h-20 w-20 rounded image-box"
         @click="() => handleOpenFilePreview(index)">
      <div class="h-20 w-20 cover">
        <div v-if="!readonly"
             class="right-action"
             @click.stop="handleFileDelete(index)">
          <div>
            <svg-icon :size="18"
                      color="#ffffff"
                      hover-color="#ec1f22"
                      name="default-circleClose"/>
          </div>
        </div>
        <div class="flex justify-center items-center w-full h-full">
          <div>
            <svg-icon :size="24" name="default-see" color="#ffffff"></svg-icon>
          </div>
        </div>
      </div>
      <img :src="fileUrl(file)" alt="">
    </div>
  </div>
</template>

<style lang="less" scoped>
.image-box {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  position: relative;
  flex-shrink: 0;

  &:hover {
    .cover {
      opacity: 1;
      pointer-events: auto;
    }
  }

  .cover {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 100;
    background: rgba(0, 0, 0, 0.2);
    opacity: 0;
    pointer-events: none;
    transition: opacity 250ms linear;

    .right-action {
      position: absolute;
      right: 2px;
      top: 2px;
    }
  }
}
</style>
