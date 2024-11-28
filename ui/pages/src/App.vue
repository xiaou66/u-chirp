<script setup lang="ts">
import { Toaster } from "@u-chirp/shadcn";
import {useSystemStore} from "./stores";
import {onMounted} from "vue";
import { FilePreview } from "@u-chirp/components";

const systemStore = useSystemStore();
onMounted(() => {
  systemStore.loadLanguage('zh');
})
</script>

<template>
    <FilePreview />
    <toaster  />
    <div class="dark min-h-screen">
      <router-view />
    </div>
</template>

<style scoped>
</style>
