<script lang="ts" setup>
import ProductTopMenu from './components/ProductTopMenu.vue'
</script>
<template>
  <div>
    <ProductTopMenu />
  </div>
  <div class="bg-opacity-60 bg-base-200 flex justify-center min-h-screen ">
    <div class="mt-6 min-h-screen min-w-full place-items-center">
        <!-- 检索区域  -->
        <div class="w-11/12 flex justify-between items-center">
          <div class="flex gap-3">
            <button class="btn btn-sm">{{$t('product.hot')}}</button>
            <button class="btn btn-sm">{{$t('product.new')}}</button>
            <button class="btn btn-sm">{{$t('product.good')}}</button>
            <button class="btn btn-sm">{{$t('product.follow')}}</button>
          </div>
          <div class="flex gap-3">
            <label class="input input-bordered flex items-center gap-2 hidden lg:flex">
              <input type="text" class="grow" placeholder="Search" />
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 16 16"
                fill="currentColor"
                class="h-4 w-4 opacity-70">
                <path
                  fill-rule="evenodd"
                  d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
                  clip-rule="evenodd" />
              </svg>
            </label>
            <button class="btn btn-primary hidden lg:block">{{$t('product.createProblem')}}</button>
            <button class="btn btn-sm btn-primary lg:hidden">+</button>
          </div>
      </div>
    </div>
  </div>
</template>
<style lang="less" scoped></style>
