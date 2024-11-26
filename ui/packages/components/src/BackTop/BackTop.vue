<script lang="ts" setup>
import { SvgIcon } from "@u-chirp/components";
import { onMounted, onUnmounted, ref } from "vue";
import type { BackTopProps } from "./type";


const props = withDefaults(defineProps<BackTopProps>(), {
  visibilityHeight: 250,
});

const show = ref(false);

function handleWheel(e: WheelEvent) {
  let scrollHeight = window.scrollY || document.documentElement.scrollTop;
  show.value = scrollHeight > props.visibilityHeight;
}

onMounted(() => {
  window.document.addEventListener('wheel', handleWheel);
});

onUnmounted(() => {
  window.document.removeEventListener('wheel', handleWheel);
});

function handleBackTop() {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
  show.value = false;
}
</script>
<template>
  <div v-if="show" class="fixed right-10 bottom-10" @click="handleBackTop">
    <div class="w-10 h-10 rounded-full bg-base-100 hover:bg-base-200 flex items-center justify-center cursor-pointer shadow-2xl backTop">
      <slot name="icon">
        <svg-icon :size="20"
                   name="default-backTop"
                   color="#0078d4"
        />
      </slot>
    </div>
  </div>
</template>
<style lang="less" scoped>
.backTop {
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
              rgba(60, 64, 67, 0.15) 0px 2px 6px 2px;
}
</style>
