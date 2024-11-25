import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useI18n } from "vue-i18n";

export interface DictDataItem {
  sort: number;
  label: string;
  value: string;
  status: number;
  colorType: string;
  cssClass: string;
  meta: Record<string, any>;
}

export const useDictDataStore = defineStore('DictDataStore', () => {
  const dictTree = ref<Record<string, DictDataItem[]>>({})

  async function getDictData(dictType: string) {
    // TODO 这里要优化
    const data = await fetch(
      `${import.meta.env.VITE_API_BASE_URL}/app/dict/getDictList?dictType=${dictType}`,
    )
      .then((res) => res.json())
      .then((res) => {
        return res.data.map((item: any) => {
          item.meta = JSON.parse(item.meta)
          return item
        })
      })
    dictTree.value[dictType] = data
  }

  const { t } = useI18n();
  async function getDictItemInner(dictType: string, value: any, first = true) {
    const dictTreeValue = dictTree.value
    if (dictTreeValue[dictType] && dictTreeValue[dictType].length > 0) {
      const dictItem = dictTreeValue[dictType].find(
        (item) => item.value.toString() === value.toString(),
      )
      if (dictItem) {
        if (dictItem.label.startsWith('$t')) {
          const match = dictItem.label.toString().match(/\$t\('(.+?)'\)/)
          if (match && match[1]) {
            dictItem.label = t(match[1])
          }
        }
        return dictItem;
      }
    }
    if (!first) {
      return undefined
    }
    await getDictData(dictType)
    return await getDictItemInner(dictType, value, false)
  }

  async function getDictItem(dictType: string, value: any): Promise<DictDataItem | undefined> {
    return getDictItemInner(dictType, value)
  }

  return {
    dictTree,
    getDictData,
    getDictItem,
  }
})
