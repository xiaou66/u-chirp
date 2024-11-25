import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useI18n } from "vue-i18n";
import { MutexLock } from "@u-chirp/utils";

export interface DictDataItem {
  sort: number;
  label: string;
  value: string;
  status: number;
  colorType: string;
  cssClass: string;
  meta: Record<string, any>;
}
const dictGetLock = new MutexLock();
export const useDictDataStore = defineStore('DictDataStore', () => {
  const dictTree = ref<Record<string, DictDataItem[]>>({});

  async function loadDictData(dictType: string) {
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
      });
    dictTree.value[dictType] = data;
  }


  const { t } = useI18n();
  async function getDictItemInner(dictType: string, value: any, first = true) {
    const dictTreeValue = dictTree.value
    if (dictTreeValue[dictType]) {
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
    await loadDictData(dictType);
    return await getDictItemInner(dictType, value, false)

  }
  async function getDictItem(dictType: string, value: any): Promise<DictDataItem | undefined> {
    const release = await dictGetLock.tryAcquire(dictType);
    try {
      return await getDictItemInner(dictType, value);
    }finally {
      release();
    }
  }

  return {
    dictTree,
    getDictData: loadDictData,
    getDictItem,
  }
})
