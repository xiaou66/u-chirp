<script setup lang="ts">
import { onMounted } from 'vue';
import Quill from "quill";
import 'quill/dist/quill.snow.css'; // 引入样式
onMounted(() => {
  const quill = new Quill("#editor", {
    theme: "snow",
  });
})
</script>

<template>
  <div class="light" style="width: 50%;">
    <div id="editor"></div>
  </div>
</template>

<style lang="less">
.light {
  .ql-snow {
    padding: 4px;
  }
  .ql-toolbar {
    background: oklch(var(--b1));
    border: 1px solid oklch(var(--nc));
    .ql-formats {
      margin-right: 2px;
      border: 1px solid #c3c3c3;
    }
    button {
      padding: 0;
      width: 24px;
      height: 18px;
      display: flex;
      justify-content: center;
      align-items: center;
      svg {
        width: 14px;
        height: 14px;
      }
    }
  }
}
</style>
