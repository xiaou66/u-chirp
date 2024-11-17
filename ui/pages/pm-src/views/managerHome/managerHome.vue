<script setup lang="ts">
import {
  SidebarInset,
  Breadcrumb, BreadcrumbList, BreadcrumbItem,
  BreadcrumbLink,
  Separator, SidebarProvider, SidebarTrigger
} from "@u-chirp/shadcn";
import LeftMenu from "./components/LeftMenu.vue";
import {useRouter} from "vue-router";

const router = useRouter();
router.replace({name: 'overview'})

</script>
<template>
  <SidebarProvider>
    <LeftMenu />
    <SidebarInset>
      <div class="flex items-center gap-2 px-4 mt-1">
        <SidebarTrigger class="!-ml-1 !w-7 !h-6" />
        <Separator orientation="vertical" class="!mr-2 !h-4" />
        <Breadcrumb>
          <BreadcrumbList>
            <BreadcrumbItem class="hidden md:block">
              <BreadcrumbLink href="#">
                全部吐槽
              </BreadcrumbLink>
            </BreadcrumbItem>
          </BreadcrumbList>
        </Breadcrumb>
      </div>
      <router-view></router-view>
    </SidebarInset>
  </SidebarProvider>
</template>
<style lang="less" scoped>
</style>
