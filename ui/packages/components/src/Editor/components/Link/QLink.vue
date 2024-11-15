<script lang="ts" setup>
import { SvgIcon } from "@u-chirp/components";
import Quill from 'quill'
import { ref } from 'vue'
import type { QLinkInstance } from './types'
import Link from './index'
import { Button } from '@u-chirp/shadcn'


const props = defineProps<{ quill: Quill }>();

const linkRef = ref<HTMLDivElement>();
const inputRef = ref<HTMLInputElement>();
const linkValue = ref<string>('');
const editStatus = ref<boolean>(false);
const visible = ref(false);
function show(link = '', edit = true) {
  linkValue.value = link;
  editStatus.value = edit;
  visible.value = true;
  inputRef.value?.focus();
  setTimeout(() => {
    inputRef.value?.select();
  });
}
function hide() {
  visible.value = false;
}
function handleInputEnterEvent() {
  console.log(props.quill)
  const module = props.quill.getModule('link') as Link;
  module.__qLinkTooltip.save();
}
function handleInputBlurEvent() {
  const module = props.quill.getModule('link') as Link;
  module.__qLinkTooltip.save();
}
function handleEnterEdit() {
  editStatus.value = true;
  setTimeout(() => {
    inputRef.value?.focus();
  });
  // window.open(LinkValue.value);
}
function handleOpenUrl() {
  console.log(linkValue.value)
  window.open(linkValue.value, '_blank');
}
defineExpose<QLinkInstance>({
  edit: editStatus,
  link: linkValue,
  show,
  hide
})
</script>
<template>
  <div class="bg-base-100 rounded shadow border absolute z-30"
       ref="linkRef">
    <div v-if="editStatus" class="p-2 flex gap-1 items-center">
      <!--  编辑状态  -->
      <svg-icon :size="18"
                name="default-link"
                color="oklch(var(--nc))" />
      <div class="divider divider-horizontal divider-start m-0 w-2"></div>
      <input ref="inputRef"
             v-model="linkValue"
             class="pl-1 w-60 h-5"
             placeholder="粘贴链接"
             @keydown.enter.stop="handleInputEnterEvent"
             @blur="handleInputBlurEvent">
    </div>
    <div v-else>
      <!--  超链接预览  -->
      <div class="p-0.5">
        <Button variant="ghost" class="w-16 h-5"
                @click="handleOpenUrl">
          <svg-icon name="default-link" />
          打开
        </Button>
        <Button variant="ghost" class="w-16 h-5"
                @click="handleEnterEdit">
          <svg-icon name="default-edit" />
          编辑
        </Button>
      </div>
    </div>
  </div>
</template>
<style lang="less" scoped>
input {
  border: none;
  outline: none;
}
</style>
