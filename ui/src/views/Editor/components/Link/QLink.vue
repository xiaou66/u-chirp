<script lang="ts" setup>

import {SvgIcon} from "@u-chirp/components";
import Quill from 'quill'
import { onMounted, ref } from 'vue'
import type Toolbar from 'quill/modules/toolbar'

const linkRef = ref<HTMLDivElement>();
const props = defineProps({ quill: Quill });
function handleLinkClick(value: string) {
  linkRef.value!.classList.remove('hidden');
  const quill = props.quill!;
  const range = quill.getSelection()!;
  console.log(quill.getBounds(range.index));
  const contents = quill.getContents(range.index);
  console.log(contents)
  quill.format('link', 'https://www.baidu.com');
  return;
}
onMounted(() => {
  const quill = props.quill!;
  const toolbar = quill.getModule('toolbar') as Toolbar;
  toolbar.addHandler('link', handleLinkClick);
})
defineExpose({
  color: 11
})
</script>
<template>
  <div class="p-2 bg-base-100 rounded shadow  border absolute z-30 hidden"
       ref="linkRef">
    <div class="flex gap-1 items-center">
      <svg-icon :size="18"
                name="default-link"
                color="oklch(var(--nc))" />
      <div class="divider divider-horizontal divider-start m-0 w-2"></div>
      <input class="pl-1 w-60" placeholder="粘贴链接">
    </div>
  </div>
</template>
<style lang="less" scoped>
input {
  border: none;
  outline: none;
}
</style>
