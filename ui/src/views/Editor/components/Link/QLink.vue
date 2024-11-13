<script lang="ts" setup>

import {SvgIcon} from "@u-chirp/components";
import Quill from 'quill'
import { ref } from 'vue'
import type { QLinkInstance } from './types'
import Link from './index'


const props = defineProps({ quill: Quill });

const linkRef = ref<HTMLDivElement>();
const inputRef = ref<HTMLInputElement>();
const hrefValue = ref<string>('');
function show(href = '') {
  hrefValue.value = href;
  inputRef.value?.focus();
  setTimeout(() => {
    inputRef.value?.select();
  });
}
function handleInputEnterEvent(e: Event) {
  console.log(props.quill)
  const module = props.quill.getModule('link') as Link;
  module.__qLinkTooltip.save();
}

defineExpose<QLinkInstance>({
  href: hrefValue,
  show
})
</script>
<template>
  <div class="p-2 bg-base-100 rounded shadow border absolute z-30"
       ref="linkRef">
    <div class="flex gap-1 items-center">
      <svg-icon :size="18"
                name="default-link"
                color="oklch(var(--nc))" />
      <div class="divider divider-horizontal divider-start m-0 w-2"></div>
      <input autofocus ref="inputRef" v-model="hrefValue"
             class="pl-1 w-60"
             placeholder="粘贴链接"
             @keydown.enter.stop="handleInputEnterEvent">
    </div>
  </div>
</template>
<style lang="less" scoped>
input {
  border: none;
  outline: none;
}
</style>
