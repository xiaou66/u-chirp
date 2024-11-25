<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import {type DictDataItem, useDictDataStore} from './store/DictDataStore'
import {useI18n} from "vue-i18n";

const props = defineProps<{
  dictType: string
  value?: any
}>()

const dict = ref<DictDataItem>()
const dictDataStore = useDictDataStore()
async function initDict() {
  if (props.value !== undefined) {
    dict.value = await dictDataStore.getDictItem(props.dictType, props.value)
  } else {
    dict.value = undefined;
  }
}
watch(
  () => props.value,
  () => {
    initDict()
  },
)
onMounted(() => {
  initDict()
})
</script>

<template>
  <slot v-if="dict" :dict="dict"></slot>
</template>

<style scoped></style>
