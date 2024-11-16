<script setup lang="ts">
import { onMounted } from 'vue'
import Quill from "quill";
import 'quill/dist/quill.snow.css';
import Link from './components/Link';
onMounted(() => {
  Quill.register('modules/link', Link, true);
  const quill = new Quill("#editor", {
    theme: "snow",
    modules: {
      toolbar: '#toolbar',
      link: true,
      clipboard: false
    },
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
      }
    },
    true //注意
  );
})
</script>

<template>
  <div class="light w-full h-full" >
    <div id="editor" class="h-full"></div>
    <div id="toolbar" class="flex items-center justify-between">
      <div class="flex-1">
        <div class="ql-formats">
          <button class="ql-image"></button>
          <button class="ql-link" id="u-link">
            <svg viewBox="0 0 18 18">
              <line class="ql-stroke" x1="7" x2="11" y1="7" y2="11"></line>
              <path class="ql-even ql-stroke" d="M8.9,4.577a3.476,3.476,0,0,1,.36,4.679A3.476,3.476,0,0,1,4.577,8.9C3.185,7.5,2.035,6.4,4.217,4.217S7.5,3.185,8.9,4.577Z"></path>
              <path class="ql-even ql-stroke" d="M13.423,9.1a3.476,3.476,0,0,0-4.679-.36,3.476,3.476,0,0,0,.36,4.679c1.392,1.392,2.5,2.542,4.679.36S14.815,10.5,13.423,9.1Z"></path>
            </svg>
          </button>
          <button class="ql-code"></button>
        </div>
        <div class="ql-formats">
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
</template>

<style lang="less">
.custom-container {
  position: absolute;
  z-index: 1000;
}
.light {
  position: relative;
  display: grid;
  grid-template-rows: calc(100% - 33px) 33px ;
  .ql-snow {
    padding: 4px;
  }
  .ql-snow .ql-tooltip {
    z-index: 100;
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
      &.ql-active,&:hover {
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
