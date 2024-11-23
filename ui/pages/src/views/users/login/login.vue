<script setup lang="ts">
import UserPassword from "./components/UserPassword.vue";
</script>

<template>
  <div class="hero bg-base-200 min-h-screen">
    <div class="hero-content flex-col lg:flex-row-reverse">
      <div class="text-center lg:text-left">
        <h1 class="text-5xl font-bold">槽小圈</h1>
        <p class="py-6 w-96">
          吐槽有理，槽小圈助力产品进化！
        </p>
      </div>
      <div class="card bg-base-100 w-full max-w-sm shrink-0 shadow-2xl">
        <form class="card-body">
          <div class="avatar justify-center pb-4">
            <div class="w-16 rounded-xl gap-10 flex">
              <img src="https://s21.ax1x.com/2024/11/08/pAyzY7j.png" />
            </div>
          </div>
          <UserPassword />
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
