<script lang="ts" setup>
import ProductTopMenu from './components/ProductTopMenu.vue'
import { SvgIcon } from '@u-chirp/components'
import { onMounted } from 'vue'
import TagPlus from '@u-chirp/components/src/tags/TagPlus/TagPlus.vue'

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
      <div class="mt-6  min-w-full place-items-center">
        <div class="w-11/12 grid grid-rows-[auto_1fr] h-full">
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
                            class=" hover:bg-gray-200 absolute bottom-0 left-0 right-0 bg-gray-100 p-2 text-center rounded">
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
                  <div class="card-body w-80 shadow rounded-2xl  card-compact  bg-base-100 p-5">
                    <div class="flex items-center gap-2">
                      <div class="avatar cursor-pointer">
                        <div class="w-10 rounded-full">
                          <img src="https://s2.loli.net/2023/04/15/k4IbQGzMZ9v6fYN.jpg" />
                        </div>
                      </div>
                      <div>ssss</div>
                    </div>
                    <div>
                      <div class="stats">
                        <div class="stat">
                          <div class="stat-title text-center">{{$t('user.issue')}}</div>
                          <div class="stat-value text-sm">10000</div>
                        </div>
                        <div class="stat">
                          <div class="stat-title text-center">{{$t('user.answer')}}</div>
                          <div class="stat-value text-sm">10000</div>
                        </div>
                        <div class="stat">
                          <div class="stat-title text-center">{{$t('user.beLiked')}}</div>
                          <div class="stat-value text-sm">10000</div>
                        </div>
                    </div>
                    </div>
                  </div>
                  <div class="card-body shadow rounded-2xl card-compact  bg-base-100 w-full p-5">
                    <div class="card-title text-sm">产品进度</div>
                    <div role="tablist" class="tabs tabs-boxed">
                      <a role="tab" class="tab tab-active">进行中</a>
                      <a role="tab" class="tab">待规划</a>
                    </div>
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
<style lang="less" scoped></style>
