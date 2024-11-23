<script lang="ts" setup>
import ProductTopMenu from './components/ProductTopMenu.vue'
import { SvgIcon } from '@u-chirp/components'
import { onMounted } from 'vue'
import TagPlus from '@u-chirp/components/src/tags/TagPlus/TagPlus.vue'
import { Button, Input, Tabs, TabsList, TabsTrigger } from '@u-chirp/shadcn'
import ProductProblemIssue from './components/ProductProblemIssue.vue'
import {useMemberStore} from "../../stores/userStore";
import MemberInfoCard from "./components/MemberInfoCard.vue";

function checkOverflow() {
  const container = document.getElementById('contentContainer')!;
  const button = document.getElementById('showMoreBtn')!;

  // Check if the content overflows the container
  if (container.scrollHeight > container.clientHeight) {
    button.style.display = 'block'; // Show the button if overflow
  } else {
    button.style.display = 'none'; // Hide the button if no overflow
  }
}
onMounted(() => checkOverflow())

</script>
<template>
  <div class="grid grid-rows-[auto_1fr] min-h-screen">
    <div>
      <ProductTopMenu />
    </div>
    <div class="flex-1 bg-opacity-60 bg-base-200 flex justify-center">
      <div class="mt-6  min-w-full flex justify-center">
        <div class="w-10/12 grid grid-rows-[auto_1fr] h-full">
          <!-- 检索区域  -->
          <div class="flex justify-between items-center">
            <div class="flex gap-3">
              <button class="btn btn-sm">
                <svg-icon name="product-hot"></svg-icon>
                {{$t('product.hot')}}
              </button>
              <button class="btn btn-sm">
                <svg-icon name="product-new"></svg-icon>
                {{$t('product.new')}}
              </button>
              <button class="btn btn-sm">
                <svg-icon :size="16" name="product-goodProblem"></svg-icon>
                {{$t('product.goodProblem')}}
              </button>
              <button class="btn btn-sm">
                <svg-icon name="product-follow"></svg-icon>
                {{$t('product.follow')}}
              </button>
            </div>
            <div class="flex gap-3">
              <div class="relative w-72 max-w-sm items-center hidden lg:block">
                <Input id="search" type="text" placeholder="搜索..." class="!pl-7" />
                <span class="absolute start-0 inset-y-0 flex items-center justify-center px-2">
                  <svg-icon name="default-search"></svg-icon>
                </span>
              </div>
              <ProductProblemIssue>
                <template #trigger>
                  <Button>{{$t('product.createProblem')}}</Button>
                </template>
              </ProductProblemIssue>
            </div>
          </div>
          <!--  主体区域  -->
          <div class="pt-5">
            <div class="h-full grid grid-cols-[1fr_auto] gap-2">
              <div class="flex flex-col gap-5">
                <!--          skeleton-->
                <div class="rounded-xl shadow p-4 skeleton bg-base-100">
                  <div class="flex mb-3 justify-between">
                    <div class="flex gap-2">
                      <tag-plus :icon="{ name: 'product-status-loading', svgClass: 'animate-spin'}"
                                tag-name="进行中"
                                type="primary" />
                      <tag-plus :icon="{ name: 'product-status-check' }"
                                tag-name="已处理"
                                type="success" />
                      <tag-plus :icon="{ name: 'product-status-wait' }"
                                tag-name="待处理"
                                type="warning" />

                      <tag-plus :icon="{ name: 'product-status-plan' }"
                                tag-name="计划中"
                                type="primary" />
                      <tag-plus :icon="{ name: 'product-status-reject' }"
                                tag-name="不采纳"
                                type="info" />
                      <tag-plus :icon="{ name: 'product-status-repeat' }"
                                tag-name="重复"
                                type="info" />
                    </div>
                    <div class="flex gap-2">
                      <tag-plus :icon="{ name: 'product-bug' }"
                                tag-name="缺陷"
                                type="danger" round />
                      <tag-plus :icon="{ name: 'product-idea' }"
                                tag-name="缺陷"
                                type="primary" round />
                      <tag-plus :icon="{ name: 'default-rocket' }"
                                tag-name="优化"
                                type="primary" round />

                      <tag-plus :icon="{ name: 'product-pin' }"
                                tag-name="置顶"
                                type="primary"
                                effect="plain"
                                round />
                    </div>
                  </div>
                  <div class="font-bold">
                    我是一个标题呀呀呀呀呀呀呀呀呀呀呀呀呀呀呀呀呀呀
                  </div>
                  <div id="contentContainer" class="max-h-64 overflow-y-hidden relative">
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <p>1.我要吐槽</p>
                    <button id="showMoreBtn"
                            class="text-sm hover:bg-gray-200 absolute bottom-0 left-0 right-0 bg-gray-100 p-2 text-center rounded">
                      点击查看更多
                    </button>
                  </div>
                  <div class="flex items-center mt-6 justify-between">
                    <div class="flex items-center gap-2">
                      <div class="avatar cursor-pointer">
                        <div class="w-10 rounded-full">
                          <img src="https://s2.loli.net/2023/04/15/k4IbQGzMZ9v6fYN.jpg" />
                        </div>
                      </div>
                      <div class="flex flex-col gap-1">
                        <div class="flex gap-2">
                          <div>xiaou</div>
                          <div>
                            <el-tag type="danger" size="small">超级管理员</el-tag>
                          </div>
                        </div>
                        <div class="text-xs">04月17日 20:06</div>
                      </div>
                    </div>
                    <div class="flex gap-2">
                      <div class="flex items-center gap-1 cursor-pointer">
                        <svg-icon svg-class="text-lg" name="product-thumbsUp"></svg-icon>
<!--                        <svg-icon svg-class="text-lg" color="#2563eb" name="product-thumbsUpFill"></svg-icon>-->
                        100
                      </div>
                      <el-tooltip content="关注数">
                        <div class="flex items-center gap-1 cursor-pointer">
                          <svg-icon svg-class="text-lg"  name="product-follow"></svg-icon>
                          100
                        </div>
                      </el-tooltip>
                    </div>
                  </div>
                </div>
                <div class="h-64 rounded-xl shadow p-2  bg-base-100">
                  <div class="text-xl"></div>
                </div>
                <div class="h-64 rounded-xl shadow p-2  bg-base-100">
                  <div class="text-xl"></div>
                </div>
                <div class="h-64 rounded-xl shadow p-2  bg-base-100">
                  <div class="text-xl"></div>
                </div>
              </div>
              <div>
                <div class="flex-col gap-5 hidden lg:flex">
                  <!--  个人信息   -->
                  <MemberInfoCard />
                  <div class="card-body shadow rounded-2xl card-compact  bg-base-100 w-full p-5">
                    <div class="card-title text-sm">产品进度</div>
                    <Tabs default-value="account">
                      <TabsList class="grid w-full grid-cols-2">
                        <TabsTrigger class="w-full" value="account">
                          进行中
                        </TabsTrigger>
                        <TabsTrigger class="w-full" value="password">
                          待规划
                        </TabsTrigger>
                      </TabsList>
                    </Tabs>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>
<style lang="less" scoped>
</style>
