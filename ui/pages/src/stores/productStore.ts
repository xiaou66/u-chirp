import {defineStore} from "pinia";
import {
  productInfoApi,
  type ProductInfoResp,
  productMemberInfoApi,
  type ProductMemberInfoResp
} from "../api";
import {ref} from "vue";

export const useProductStore = defineStore('productStore', () => {
  const productMemberInfo = ref<ProductMemberInfoResp>();
  function loadProductMemberInfo(productCode: string) {
    productMemberInfoApi(productCode).then((res) => {
      productMemberInfo.value = res;
    })
  }

  const productInfo = ref<ProductInfoResp>()
  function loadProductInfo(productCode: string) {
    productInfoApi(productCode)
      .then((res) => {
        productInfo.value = res;
      })
  }

  return {
    productMemberInfo,
    loadProductMemberInfo,
    productInfo,
    loadProductInfo
  }
}, {
  persist: true,
})
