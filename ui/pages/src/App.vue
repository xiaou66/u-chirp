<script setup lang="ts">
import { Toaster } from "@u-chirp/shadcn";
import {useSystemStore} from "./stores";
import {onMounted} from "vue";

const systemStore = useSystemStore();
onMounted(() => {
  window.dictDataLock =[]
  systemStore.loadLanguage('zh');
})
</script>

<template>
    <toaster  />
    <div class="dark min-h-screen">
      <router-view />
    </div>
</template>

<style scoped>
</style>
