<script setup lang="ts">
import {useRouter} from "vue-router";

const router = useRouter();
function userLogin() {
  router.push({ name: 'userLogin' });
}
</script>

<template>
  <div
    class="hero min-h-screen"
    style="background-image: url(https://s21.ax1x.com/2024/11/08/pAyx2Sf.webp);">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-neutral-content text-center">
      <div class="max-w-md " style="padding-bottom: 100%;">
        <div class="avatar">
          <div class="w-24 rounded-xl">
            <img src="https://s21.ax1x.com/2024/11/08/pAyzY7j.png" />
          </div>
        </div>
        <h1 class="mb-5 text-5xl font-bold">槽小圈</h1>
        <p class="mb-5">
          在槽小圈，吐槽让产品焕然一新
        </p>
        <button class="btn btn-primary" @click="userLogin">立即使用</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
