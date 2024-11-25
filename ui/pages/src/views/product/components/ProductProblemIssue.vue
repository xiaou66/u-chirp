<script setup lang="ts">
import { Tabs, TabsList, TabsTrigger, Button } from '@u-chirp/shadcn'
import { SvgIcon, UDialog } from '@u-chirp/components'
import UserEditor from '@u-chirp/components/src/Editor/UserEditor.vue'
import {ref} from "vue";
import type {UserEditorInstance} from "@u-chirp/components/src/Editor/Editor";
import {useRoute} from "vue-router";
import {productPostPostSaveApi} from "../../../api";

const tabs = [
  {
    key: 'roast',
    name: '吐槽',
    icon: 'default-roast'
  },
  {
    key: 'bug',
    name: '提缺陷',
    icon: 'product-bug'
  },
  {
    key: 'idea',
    name: '提想法',
    icon: 'product-idea'
  },
]
const submitData = ref({
  tab: 'roast'
});
const userEditorRef = ref<UserEditorInstance>();
const route = useRoute();
function handlePushPost() {
  const editor = userEditorRef.value!.getEditor();
  console.log('route', route.params);
  const { productCode } = route.params as any;
  productPostPostSaveApi({
    productCode,
    postTitle: '',
    postRawContent: JSON.stringify(editor.getContents()),
    postRawHtml: editor.getSemanticHTML(),
    postType: submitData.value.tab
  });
}
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
    <Tabs v-model:modelValue="submitData.tab"
          class="lg:w-6/12">
      <TabsList class="grid w-full grid-cols-2">
        <TabsTrigger v-for="tab in tabs" :key="tab.key" class="w-full" :value="tab.key">
          <div class="flex items-center gap-2">
            <div>
              <svg-icon :name="tab.icon"></svg-icon>
            </div>
            <div>{{ tab.name }}</div>
          </div>
        </TabsTrigger>
      </TabsList>
    </Tabs>
    <div class="pt-3 h-56 mobile:h-72">
      <UserEditor ref="userEditorRef"></UserEditor>
    </div>
    <template #footer>
      <Button @click="handlePushPost">
        <svg-icon color="hsl(var(--primary-foreground))"
                  name="default-publish" />
        发布
      </Button>
    </template>
  </UDialog>
</template>

<style scoped>

</style>
