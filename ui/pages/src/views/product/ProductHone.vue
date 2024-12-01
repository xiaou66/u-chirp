<script setup lang="ts">
import { ProductTopMenu } from "./components";
import { provide, ref} from "vue";
import type {ProductHomeInject} from "./type";
import {BackTop} from "@u-chirp/components";

const containerRef = ref<HTMLElement>();
provide<ProductHomeInject>('productHome', {
  container: containerRef
});


</script>

<template>
  <BackTop :target="containerRef"  />
  <!-- 产品主页 -->
  <div class="grid min-h-screen"
       style="grid-template-rows: 70px calc(100vh - 70px)">
    <ProductTopMenu />
    <div ref="containerRef" class="bg-opacity-60 bg-base-200 flex justify-center overflow-y-auto">
      <div class="h-full w-10/12 lg:w-10/12 xl:w-9/12 2xl:w-8/12  mobile:w-full mobile:p-2">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>

</style>
