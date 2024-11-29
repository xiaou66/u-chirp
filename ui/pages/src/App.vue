<script setup lang="ts">
import { Toaster } from "@u-chirp/shadcn";
import {useSystemStore} from "./stores";
import {onMounted} from "vue";
import { FilePreview } from "@u-chirp/components";

const systemStore = useSystemStore();
onMounted(async () => {
  await systemStore.loadLanguage('zh');
})
</script>

<template>
    <toaster  />
    <FilePreview >
      <div class="dark min-h-screen">
        <router-view />
      </div>
    </FilePreview>
</template>

<style scoped>
</style>
