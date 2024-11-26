<script lang="ts" setup>
import ProductTopMenu from './components/ProductTopMenu.vue'
import { SvgIcon } from '@u-chirp/components'
import {nextTick, onMounted, ref} from 'vue'
import TagPlus from '@u-chirp/components/src/tags/TagPlus/TagPlus.vue'
import { Button, Input, Tabs, TabsList, TabsTrigger } from '@u-chirp/shadcn'
import ProductProblemIssue from './components/ProductProblemIssue.vue'
import MemberInfoCard from "./components/MemberInfoCard.vue";
import {useI18n} from "vue-i18n";
import {productPostListApi, type ProductPostListResp} from "../../api";
import {useRoute} from "vue-router";
import type {PageResult} from "../../api/appService";
import DictData from "@u-chirp/components/src/dict/DictData.vue";
import { ProductConstants } from "../../constant";
import dayjs from "dayjs";
import {formatUserTime} from "@u-chirp/utils";
import BackTop from "@u-chirp/components/src/BackTop/BackTop.vue";

const { t } = useI18n();
const tabs = [
  {
    key: 'HOT',
    name: t('product.hot'),
    icon: 'product-hot'
  },
  {
    key: 'NEW',
    name: t('product.new'),
    icon: 'product-new'
  },
  {
    key: 'GOOD_PROBLEM',
    name: t('product.goodIssue'),
    icon: 'product-goodProblem'
  },
  {
    key: 'FOLLOW',
    name: t('product.follow'),
    icon: 'product-follow'
  },
]
const searchQueryParams = ref({
  pageSize: 10,
  pageNo: 1,
  tab: 'HOT'
})

function handleTabChange(key: string) {
  searchQueryParams.value.tab = key;
}
const route = useRoute();
const listData = ref<PageResult<ProductPostListResp>>({
  total: 0,
  list: [],
});


const itemRefs = ref<HTMLDivElement[]>([]);

function checkOverflow(container: HTMLDivElement) {
  const button = container.querySelector('.showMoreBtn')! as HTMLButtonElement;
  if (container.scrollHeight > container.clientHeight) {
    button.style.display = 'block'; // Show the button if overflow
  } else {
    button.style.display = 'none'; // Hide the button if no overflow
  }
}
function requestList() {
  productPostListApi({
    pageSize: 100,
    productCode: route.params.productCode as string,
    tab: searchQueryParams.value.tab as any,
  }).then(res => {
    listData.value = res
    nextTick(() => {
      itemRefs.value.map(el => {
        checkOverflow(el);
      })
    });
  })
}

function load() {
  console.log('1111')
}
onMounted(() => {
  requestList()
});
</script>
<template>
  <BackTop />
  <div v-infinite-scroll="load" class="grid min-h-screen"
       style="grid-template-rows: 70px calc(100vh - 70px)">
    <ProductTopMenu />
    <div class="flex-1 bg-opacity-60 bg-base-200 flex justify-center overflow-y-auto">
      <div class="mt-6  min-w-full flex justify-center">
        <div class="w-10/12 grid grid-rows-[auto_1fr] h-full">
          <!-- 检索区域  -->
          <div class="flex justify-between items-center">
            <div class="flex gap-3">
              <button v-for="tab in tabs" :key="tab.key"
                      class="btn btn-sm"
                      :class="searchQueryParams.tab === tab.key ? 'btn-active' : ''"
                      @click="handleTabChange(tab.key)">
                <svg-icon :name="tab.icon"></svg-icon>
                {{tab.name}}
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
                  <Button>{{$t('product.createIssue')}}</Button>
                </template>
              </ProductProblemIssue>
            </div>
          </div>
          <!--  主体区域  -->
          <div class="pt-5">
            <div class="h-full grid grid-cols-[1fr_auto] gap-2">
              <div class="flex flex-col gap-5">
                <!--          skeleton-->
                <div v-for="data in listData.list" :key="data.postId"
                     class="rounded-xl shadow p-4 skeleton bg-base-100">
                  <div class="flex mb-3 justify-between">
                    <div class="flex gap-2">
                      <!--  产品阶段状态  -->
                      <DictData :dict-type="ProductConstants.post.post_status"
                                :value="data.postHandleProgress">
                        <template #default="{ dict }">
                          <tag-plus v-bind="dict.meta.tag"
                                    :tag-name="dict.label" />
                        </template>
                      </DictData>
                    </div>
                    <div class="flex gap-2">
                      <!--   帖子类型   -->
                      <DictData :dict-type="ProductConstants.post.post_type"
                                :value="data.postType">
                        <template #default="{ dict }">
                          <tag-plus v-bind:="dict.meta.tag"
                                    :tag-name="dict.label" />
                        </template>
                      </DictData>
                      <tag-plus v-if="data.postTop"
                                :icon="{ name: 'product-pin' }"
                                :tag-name="t('common.top')"
                                type="primary"
                                effect="plain"
                                round />
                    </div>
                  </div>
                  <div v-if="data.postTitle" class="font-bold">
                    {{data.postTitle}}
                  </div>
                  <div ref="itemRefs" class="max-h-64 overflow-y-hidden relative">
                    <div class="post-container">
                      <div v-html="data.postRawHtml"></div>
                    </div>
                    <button class="showMoreBtn text-sm hover:bg-gray-200 absolute bottom-0 left-0 right-0 bg-gray-100 p-2 text-center rounded">
                      点击查看更多
                    </button>
                  </div>
                  <div class="flex items-center mt-6 justify-between">
                    <div class="flex items-center gap-2">
                      <div class="avatar cursor-pointer">
                        <div class="w-10 rounded-full">
                          <img :src="data.memberInfo.memberAvatar" />
                        </div>
                      </div>
                      <div class="flex flex-col gap-1">
                        <div class="flex gap-2 text-xs text-base-content">
                          <div class="font-medium">{{data.memberInfo.memberNickname}}</div>
                          <div>
<!--                            <el-tag type="danger" size="small">超级管理员</el-tag>-->
                          </div>
                        </div>
                        <div class="text-xs text-base-content font-medium">{{formatUserTime(data.createTime)}}</div>
                      </div>
                    </div>
                    <div class="flex gap-2">
                      <div class="flex items-center gap-1 cursor-pointer">
                        <svg-icon svg-class="text-lg" name="product-thumbsUp"></svg-icon>
<!--                        <svg-icon svg-class="text-lg" color="#2563eb" name="product-thumbsUpFill"></svg-icon>-->
                        {{ data.postThumbsUpCount }}
                      </div>
                      <el-tooltip :content="t('product.follow')">
                        <div class="flex items-center gap-1 cursor-pointer">
                          <svg-icon svg-class="text-lg"  name="product-follow"></svg-icon>
                          {{ data.postFollowCount }}
                        </div>
                      </el-tooltip>
                    </div>
                  </div>
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
<style lang="less">
.post-container {
  @import "@u-chirp/components/src/assets/poststyle";
}
</style>
<style lang="less" scoped>
//@import "mdmdt.css";
</style>
