<script setup lang="ts">
import { Tabs, TabsList, TabsTrigger, Button } from '@u-chirp/shadcn'
import { SvgIcon, UDialog } from '@u-chirp/components'
import UserEditor from '@u-chirp/components/src/Editor/UserEditor.vue'
</script>

<template>
  <UDialog>
    <template #trigger>
      <slot name="trigger"></slot>
    </template>
    <template #title>
      吐槽
    </template>
    <template #description>
      感谢你愿意和我们分享您的想法！我们非常珍视每一位用户的反馈，因为这能帮助我们不断改进产品
    </template>
    <Tabs default-value="account" class="lg:w-6/12">
      <TabsList class="grid w-full grid-cols-2">
        <TabsTrigger class="w-full" value="bug">
          提缺陷
        </TabsTrigger>
        <TabsTrigger  class="w-full" value="idea">
          提意见
        </TabsTrigger>
        <TabsTrigger  class="w-full" value="enhance">
          我想要
        </TabsTrigger>
      </TabsList>
    </Tabs>
    <div class="pt-3 h-56 mobile:h-72">
      <UserEditor></UserEditor>
    </div>
    <template #footer>
      <Button>
        <svg-icon color="hsl(var(--primary-foreground))"
                  name="default-publish" />
        吐槽
      </Button>
    </template>
  </UDialog>
</template>

<style scoped>

</style>
