<script setup lang="ts">
import {
  SidebarProvider, Sidebar, SidebarHeader, SidebarMenuItem,
  SidebarMenu, SidebarMenuButton,  SidebarContent, SidebarGroup,
  SidebarFooter, SidebarInset, SidebarTrigger,
  DropdownMenuSeparator, DropdownMenuTrigger, DropdownMenu,
  DropdownMenuContent, DropdownMenuLabel, DropdownMenuItem,
  Breadcrumb, BreadcrumbList, BreadcrumbItem,
  BreadcrumbLink,
  Separator
} from "@u-chirp/shadcn";
import { SvgIcon } from "@u-chirp/components";
</script>
<template>
  <SidebarProvider>
    <Sidebar collapsible="icon" class="bg-muted/30">
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <DropdownMenu>
              <DropdownMenuTrigger as-child>
                <SidebarMenuButton
                  size="lg"
                  class="hover:tw-text-accent-foreground hover:tw-bg-accent min-icon"
                >
                  <div class="flex aspect-square size-8 items-center justify-center rounded-lg">
                    <img src="https://s21.ax1x.com/2024/11/08/pAyzY7j.png">
                  </div>
                  <div class="grid flex-1 text-left text-sm leading-tight">
                    <span class="truncate font-semibold">{{ 123 }}</span>
                    <span class="truncate text-xs">{{ 456 }}</span>
                  </div>
                  <svg-icon name="default-upDown" :size="18"></svg-icon>
                </SidebarMenuButton>
              </DropdownMenuTrigger>
              <DropdownMenuContent
                class="w-[--radix-dropdown-menu-trigger-width] !min-w-56 !rounded-lg"
                align="start"
                side="bottom"
                :side-offset="4"
              >
                <DropdownMenuItem class="flex justify-between !cursor-pointer">
                  <div class="flex gap-2 items-center">
                    <div class="flex size-9 p-1 items-center justify-center rounded-sm border">
                      <svg-icon :size="20" name="pm-menu-overview" />
                    </div>
                    总览
                  </div>
                </DropdownMenuItem>
                <DropdownMenuSeparator />
                <DropdownMenuLabel class="text-xs tw-text-muted-foreground">
                  产品
                </DropdownMenuLabel>
                <DropdownMenuItem class="flex justify-between !cursor-pointer">
                  <div class="flex gap-2 items-center">
                    <div class="flex size-9 p-1 items-center justify-center rounded-sm border">
                      <img src="https://s21.ax1x.com/2024/11/08/pAyzY7j.png">
                    </div>
                    槽小圈
                  </div>
                  <div>
                    <svg-icon name="default-rightArrow" :size="14"></svg-icon>
                  </div>
                </DropdownMenuItem>
                <DropdownMenuSeparator />
                <DropdownMenuItem class="gap-2 p-2">
                  <div class="flex size-6 items-center justify-center rounded-md border bg-background">
                    <svg-icon name="default-add"></svg-icon>
                  </div>
                  <div class="font-medium text-muted-foreground">
                    新增产品
                  </div>
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>
      <SidebarContent>
        <SidebarGroup>
          <SidebarMenu>
            <SidebarMenuItem>
              <SidebarMenuButton class="cursor-pointer hover:tw-text-accent-foreground hover:tw-bg-accent" as-child>
                <div class="!h-10 !pl-5">
                  <div>
                    <svg-icon :size="16" name="pm-menu-overview" />
                  </div>
                  <div>首页</div>
                </div>
              </SidebarMenuButton>
            </SidebarMenuItem>
            <Separator />
            <SidebarMenuItem>
              <SidebarMenuButton class="cursor-pointer hover:tw-text-accent-foreground hover:tw-bg-accent" as-child>
                <div class="!h-10 !pl-5">
                  <div>
                    <svg-icon :size="16" name="pm-menu-allProblem" />
                  </div>
                  <div>全部吐槽</div>
                </div>
              </SidebarMenuButton>
            </SidebarMenuItem>
            <SidebarMenuItem>
              <SidebarMenuButton class="cursor-pointer hover:tw-text-accent-foreground hover:tw-bg-accent" as-child>
                <div class="!h-10 !pl-5">
                  <div class="">
                    <svg-icon :size="16" name="pm-menu-problemUntreated" />
                  </div>
                  <div>未处理</div>
                </div>
              </SidebarMenuButton>
            </SidebarMenuItem>
          </SidebarMenu>
        </SidebarGroup>
      </SidebarContent>
      <SidebarFooter>
        <SidebarMenu>
          <SidebarMenuItem>
            <DropdownMenu>
              <DropdownMenuTrigger as-child>
                <SidebarMenuButton
                  size="lg"
                  class="hover:tw-bg-accent min-icon">
                  <div class="w-10 rounded-xl overflow-hidden group-data-[collapsible=icon]:w-full">
                    <img  src="https://s2.loli.net/2023/04/15/k4IbQGzMZ9v6fYN.jpg" alt="">
                  </div>
                  <div class="grid flex-1 text-left text-sm leading-tight group-data-[collapsible=icon]:tw-hidden">
                    <span class="truncate font-semibold">xiaou</span>
                    <span class="truncate text-xs">
                      <el-tag size="small">超级管理员</el-tag>
                    </span>
                  </div>
                  <div class="group-data-[collapsible=icon]:tw-hidden">
                    <svg-icon name="default-upDown" />
                  </div>
                </SidebarMenuButton>
              </DropdownMenuTrigger>
              <DropdownMenuContent class="w-[--radix-dropdown-menu-trigger-width] !min-w-32 !rounded-lg">
                <DropdownMenuItem>
                  <div class="flex gap-2 items-center">
                    <svg-icon name="default-logout" />
                    登出
                  </div>
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarFooter>
    </Sidebar>
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
    </SidebarInset>
  </SidebarProvider>
</template>
<style lang="less" scoped>
.tw-group[data-collapsible=icon]  {
  .min-icon {
    padding: 0;
    &:hover {
      background: transparent;
    }
  }
}
</style>
