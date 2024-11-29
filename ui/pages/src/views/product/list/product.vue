<script lang="ts" setup>
import { SvgIcon, BackTop, TagPlus, DictData, DefaultEmpty } from '@u-chirp/components'
import {inject, nextTick, onMounted, ref} from 'vue'
import { Button, Input, Tabs, TabsList, TabsTrigger } from '@u-chirp/shadcn'
import ProductProblemIssue from './components/ProductProblemIssue.vue'
import MemberInfoCard from "./components/MemberInfoCard.vue";
import {useI18n} from "vue-i18n";
import {
  productPostFollowApi, productPostFollowRecord,
  productPostListApi,
  type ProductPostListReq,
  type ProductPostListResp,
  productPostThumbsUpApi, productPostThumbsUpRecordApi
} from "../../../api";
import {useRoute, useRouter} from "vue-router";
import type {RollResult} from "../../../api/appService";
import { ProductConstants } from "../../../constant";
import {formatUserTime, isMobile} from "@u-chirp/utils";
import type {ProductHomeInject} from "../type";
import {useInfiniteScroll} from "@vueuse/core";

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
];

const route = useRoute();
const searchQueryParams = ref<ProductPostListReq>({
  next: 0,
  tab: 'HOT',
  productCode: route.params.productCode as string,
});


function handleTabChange(key: string) {
  searchQueryParams.value.tab = key;
  requestList(0);
}
const listData = ref<RollResult<ProductPostListResp>>({
  next: 0,
  list: [],
});


const itemRefs = ref<HTMLDivElement[]>([]);

function checkOverflow(container: HTMLDivElement) {
  const button = container.querySelector('.overflow-cover')! as HTMLButtonElement;
  if (container.scrollHeight > container.clientHeight) {
    button.style.display = 'block'; // Show the button if overflow
  } else {
    button.style.display = 'none'; // Hide the button if no overflow
  }
  container.setAttribute('checkOverflow', 'true');
}

const memberFollowPostIds = ref<string[]>([]);
const memberThumbsUpPostIds = ref<string[]>([]);

function requestList(next = listData.value.next) {
  if (next == null) {
    return;
  }
  productPostListApi({
    ...searchQueryParams.value,
    next,
  }).then(res => {
    if (next === 0) {
      listData.value = res
      memberFollowPostIds.value.length = 0;
      memberThumbsUpPostIds.value.length = 0;
    } else {
      listData.value.list.push(...res.list);
      listData.value.next = res.next;
    }
    const postIds = res.list.map(({ postId }) => postId);
    if (postIds.length > 0) {
      // 调用当前用户跟进和点赞内容
      productPostThumbsUpRecordApi(postIds).then((thumbsUpPostIds) => {
        memberThumbsUpPostIds.value.push(...thumbsUpPostIds)
      });
      productPostFollowRecord(postIds).then((followPostIds) => {
        memberFollowPostIds.value.push(...followPostIds);
      });
    }
    nextTick(() => {
      // 过滤掉已经被计算过的
      itemRefs.value.filter(item => !item.hasAttribute('checkOverflow'))
        .map(el => {
          checkOverflow(el);
        })
    });
  })
}
const postContainerRef = ref<HTMLElement>();

onMounted(() => {
  requestList()
});
function handlePostThumbs(postId: string, thumbsUp: boolean) {
  productPostThumbsUpApi({
    productCode: route.params.productCode as string,
    postId,
    thumbsUp
  }).then(() => {});
  const listItem = listData.value.list.find((item) => item.postId === postId);
  if (thumbsUp && listItem) {
    memberThumbsUpPostIds.value.push(postId);
    listItem.postThumbsUpCount = (Number((listItem?.postThumbsUpCount || 0)) + 1).toString();
  } else if (listItem) {
    const index = memberThumbsUpPostIds.value.findIndex(thumbsUpPostId => thumbsUpPostId === postId);
    memberThumbsUpPostIds.value.splice(index, 1);
    listItem.postThumbsUpCount = (Number((listItem?.postThumbsUpCount || 0)) - 1).toString();
  }
}

function handleFollow(postId: string, follow: boolean) {
  productPostFollowApi({
    productCode: route.params.productCode as string,
    postId,
    follow
  }).then(() => {});

  const listItem = listData.value.list.find((item) => item.postId === postId);
  if (follow && listItem) {
    memberFollowPostIds.value.push(postId);
    listItem.postFollowCount = (Number((listItem?.postFollowCount || 0)) + 1).toString();
  } else if (listItem) {
    const index = memberFollowPostIds.value.findIndex(thumbsUpPostId => thumbsUpPostId === postId);
    memberFollowPostIds.value.splice(index, 1);
    console.log(memberFollowPostIds.value)
    listItem.postFollowCount = (Number((listItem?.postFollowCount || 0)) - 1).toString();
  }
}

const router = useRouter();
function handleOpenPostDetail(postId: string) {
  console.log('handleOpenPostDetail');
  const productCode = route.query.productCode;
  router.push({ path: `/product/${productCode}/post/${postId}` });
}

const productHomeInject = inject<ProductHomeInject>('productHome');

// 无限加载
const { reset } = useInfiniteScroll(
  productHomeInject!.container,
  () => requestList(),
  {
    distance: 30,
    interval: 2500,
    canLoadMore: () => !!listData.value.next
  }
);
</script>
<template>
  <BackTop :target="postContainerRef"  />
  <div ref="postContainerRef"
       class="flex-1 w-full h-full pt-10 mobile:pt-2">
    <!-- 检索区域  -->
    <div v-if="isMobile()" class="flex justify-between mb-3">
      <div class="relative w-64 max-w-sm items-center">
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
    <div class="flex justify-between">
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
        <div v-if="!isMobile()" class="relative w-72 max-w-sm items-center">
          <Input id="search" type="text" placeholder="搜索..." class="!pl-7" />
          <span class="absolute start-0 inset-y-0 flex items-center justify-center px-2">
                  <svg-icon name="default-search"></svg-icon>
                </span>
        </div>
        <ProductProblemIssue v-if="!isMobile()">
          <template #trigger>
            <Button>{{$t('product.createIssue')}}</Button>
          </template>
        </ProductProblemIssue>
      </div>
    </div>
    <!--  主体区域  -->
    <div class="pt-5 pb-8">
      <div class="h-full grid grid-cols-[1fr_auto] gap-2">
        <!-- 列表区域 -->
        <div>
          <div v-if="listData.list && listData.list.length > 0" class="flex flex-col gap-5">
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
              <div ref="itemRefs"
                   class="max-h-64 overflow-y-hidden relative cursor-pointer"
                   @click="() => handleOpenPostDetail(data.postId)">
                <div class="post-container">
                  <div v-html="data.postRawHtml"></div>
                </div>
                <div class="overflow-cover" />
              </div>
              <div class="flex items-center mt-6 justify-between">
                <div class="flex items-center gap-2">
                  <div class="avatar cursor-pointer">
                    <div class="w-10 rounded-full">
                      <img :src="data.memberInfo.memberAvatar"  alt=""/>
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
                <div class="flex items-center gap-2 select-none">
                  <div class="flex items-center gap-1 cursor-pointer w-10">
                    <svg-icon v-if="!memberThumbsUpPostIds.includes(data.postId)"
                              svg-class="text-xl"
                              name="product-thumbsUp"
                              @click="handlePostThumbs(data.postId, true)"/>
                    <svg-icon v-else
                              svg-class="text-xl"
                              color="#3C98FF"
                              name="product-thumbsUpFill"
                              @click="handlePostThumbs(data.postId, false)"/>
                    {{ data.postThumbsUpCount }}
                  </div>
                  <el-tooltip :content="t('product.follow')">
                    <div class="flex items-center gap-1 cursor-pointer w-10">
                      <svg-icon v-if="!memberFollowPostIds.includes(data.postId)"
                                color="#CDCDCD"
                                svg-class="text-2xl"
                                name="product-follow"
                                @click="handleFollow(data.postId, true)" />
                      <svg-icon v-else color="#3C98FF"
                                svg-class="text-2xl"
                                name="product-follow"
                                @click="handleFollow(data.postId, false)" />
                      <div>{{ data.postFollowCount }}</div>
                    </div>
                  </el-tooltip>
                </div>
              </div>
            </div>
          </div>
          <DefaultEmpty v-else>
            <template #description>
              <div v-if="searchQueryParams.tab === 'FOLLOW'">
                还没有关注任何帖子
              </div>
              <div v-else>
                还没有数据噢
              </div>
            </template>
          </DefaultEmpty>
        </div>
        <!--  右边信息栏  -->
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
</template>
<style lang="less">
.post-container {
  @import "@u-chirp/components/src/assets/poststyle";
}
</style>
<style lang="less" scoped>
//@import "mdmdt.css";
.overflow-cover {
  position: absolute;
  bottom: 0;
  background: linear-gradient(0deg, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0.1));
  width: 100%;
  height: 200px;
}
</style>
