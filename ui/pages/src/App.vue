<script setup lang="ts">
import { Toaster } from "@u-chirp/shadcn";
</script>

<template>
    <toaster />
    <div class="dark min-h-screen">
      <router-view />
    </div>
</template>

<style scoped>
</style>
