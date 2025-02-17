<script setup lang="ts">
import {computed, markRaw, onMounted, ref, watch} from 'vue'
import Quill from "quill";
import 'quill/dist/quill.snow.css';
import Link from './components/Link';
import type {UserEditorInstance} from "./Editor";
import {SvgIcon} from "../icon";
import {useFilePreview} from "../FilePreview/FilePreview";
import type Toolbar from "quill/modules/toolbar";
import { FileList } from '@u-chirp/components';

const props = withDefaults( defineProps<{
  hideToolbar?: '#H'[];
  disable?: boolean;
}>(), {
  hideToolbar: [] as any,
  disable: false
});

const editor = ref<Quill>();


const fileList = ref<File[]>([]);

watch(() => props.disable, (disable: boolean) => {
  editor.value!.enable(!disable);
});

const editorRef = ref<HTMLElement>();
const toolbarRef = ref<HTMLElement>();
onMounted(() => {
  Quill.register('modules/link', Link, true);
  const quill = new Quill(editorRef.value, {
    theme: "snow",
    modules: {
      toolbar: toolbarRef.value,
      link: true,
      clipboard: false
    },
  });
  editor.value = markRaw(quill);
  quill.enable(!props.disable);

  const toolbar = quill.getModule("toolbar") as Toolbar;
  //对工具栏的image添加回调函数覆盖原本的方法
  toolbar.addHandler("image", () => {
    window.platform.fileOps.openSelectFile()
      .then(files => {
        fileList.value.push(...files);
      });
  });

  // 自定义粘贴图片功能
  quill.root.addEventListener(
      "paste",
      evt => {
        if (
            evt.clipboardData &&
            evt.clipboardData.files &&
            evt.clipboardData.files.length
        ) {
          evt.preventDefault();
          //上传服务器并拿到返回的图片地址，插入到富文本中
          fileList.value.push(...Array.from(evt.clipboardData.files));
        }
      },
      true //注意
  );

  // 处理拖拽事件
  quill.root.addEventListener('dragover', (evt) => {
    evt.preventDefault(); // 阻止默认行为，允许拖拽
  }, true);
  quill.root.addEventListener('dragend', (evt) => {
    evt.preventDefault(); // 阻止默认行为，允许拖拽
  }, true);
  quill.root.addEventListener('drop', evt => {
    if (evt.dataTransfer && evt.dataTransfer.files && evt.dataTransfer.files.length) {
      evt.preventDefault();
      // 上传服务器并拿到返回的图片地址，插入到富文本中
      fileList.value.push(...Array.from(evt.dataTransfer.files));
      // 这里可以调用上传函数并在上传成功后插入图片
    }
  }, true);
});

function clearAllData() {

  editor.value!.setText("");
  fileList.value.length = 0;
}
// 文件列表
defineExpose<UserEditorInstance>({
  getEditor: () => editor.value!,
  getFileList: () => fileList.value,
  clearAllData,
});

</script>

<template>
  <div class="w-full h-full grid" style="display: flex; flex-direction: column;">
    <div class="light w-full h-full">
      <div id="editor" ref="editorRef" class="h-full post-edit-container overflow-x-auto"></div>
      <!--  工具条  -->
      <div id="toolbar" ref="toolbarRef" class="flex items-center justify-between">
        <div class="flex-1">
          <div class="ql-formats">
            <button class="ql-image"></button>
            <button class="ql-link" id="u-link">
              <svg viewBox="0 0 18 18">
                <line class="ql-stroke" x1="7" x2="11" y1="7" y2="11"></line>
                <path class="ql-even ql-stroke"
                      d="M8.9,4.577a3.476,3.476,0,0,1,.36,4.679A3.476,3.476,0,0,1,4.577,8.9C3.185,7.5,2.035,6.4,4.217,4.217S7.5,3.185,8.9,4.577Z"></path>
                <path class="ql-even ql-stroke"
                      d="M13.423,9.1a3.476,3.476,0,0,0-4.679-.36,3.476,3.476,0,0,0,.36,4.679c1.392,1.392,2.5,2.542,4.679.36S14.815,10.5,13.423,9.1Z"></path>
              </svg>
            </button>
            <button class="ql-code"></button>
          </div>
          <div class="ql-formats" v-if="!hideToolbar.includes('#H')">
            <button class="ql-header" value="1"></button>
            <button class="ql-header" value="2"></button>
            <button class="ql-header" value="3"></button>
          </div>
          <div class="ql-formats">
            <button class="ql-list" value="ordered"></button>
            <button class="ql-list" value="bullet"></button>
          </div>
          <div class="ql-formats">
            <button class="ql-bold"></button>
            <button class="ql-strike"></button>
          </div>
        </div>
        <div>
          <slot name="tool-right"></slot>
        </div>
      </div>
    </div>
    <!-- 文件上传区 -->
    <FileList v-model:file-list="fileList" />
  </div>
</template>
<style lang="less">
.post-edit-container {
  @import "@u-chirp/components/src/assets/poststyle";

  ol {
    padding-left: 10px !important;
  }

  ul {
    padding-left: 12px !important;
  }
}

.custom-container {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1000;
}

.light {
  position: relative;
  display: grid;
  grid-template-rows: calc(100% - 33px) 33px;

  .ql-snow {
    padding: 4px;
  }

  .ql-snow .ql-tooltip {
    z-index: 100;
  }

  .ql-editor {
    padding: 10px;
  }

  .ql-editor li.ql-indent-1:not(.ql-direction-rtl) {
    padding-left: 2rem;
  }

  .ql-editor li.ql-indent-2:not(.ql-direction-rtl) {
    padding-left: 4rem;
  }

  .ql-editor li.ql-indent-3:not(.ql-direction-rtl) {
    padding-left: 6rem;
  }

  .ql-editor li.ql-indent-4:not(.ql-direction-rtl) {
    padding-left: 8rem;
  }

  .ql-editor li.ql-indent-5:not(.ql-direction-rtl) {
    padding-left: 10rem;
  }

  .ql-editor li.ql-indent-6:not(.ql-direction-rtl) {
    padding-left: 12rem;
  }

  .ql-editor li.ql-indent-7:not(.ql-direction-rtl) {
    padding-left: 14rem;
  }

  .ql-editor li.ql-indent-8:not(.ql-direction-rtl) {
    padding-left: 16rem;
  }

  .ql-toolbar {
    position: absolute;
    background: oklch(var(--b1));
    border: 1px solid oklch(var(--nc));
    border-top: none;
    width: 100%;
    bottom: 0;

    .ql-formats {
      margin-right: 2px;
      border-right: 1px solid hsl(240 5.9% 90%);
    }

    .ql-formats:last-child {
      border-right: none;
      margin-right: 0;
    }

    button {
      padding: 0;
      width: 24px;
      margin: 0 3px;
      height: 24px;
      display: flex;
      justify-content: center;
      align-items: center;

      .ql-stroke {
        stroke: oklch(var(--nc));
        transition: all 200ms linear;
      }

      .ql-fill {
        fill: oklch(var(--nc));
        color: oklch(var(--nc));
        transition: all 220ms ease-in-out;
      }

      svg {
        width: 18px;
        height: 18px;
      }

      &:hover {
        background-color: oklch(var(--sc));
        border-radius: 6px;

        .ql-stroke {
          stroke: oklch(var(--nc));
        }

        .ql-fill {
          fill: oklch(var(--nc));
          color: oklch(var(--nc));
        }
      }

      &.ql-active, &:hover {
        background-color: oklch(var(--sc));
        border-radius: 6px;

        .ql-stroke {
          stroke: oklch(var(--n));
        }

        .ql-fill {
          fill: oklch(var(--n));
          color: oklch(var(--n));
        }
      }
    }
  }

  .ql-container {
    border-bottom: none;
  }
}
</style>
