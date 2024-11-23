import {defineStore} from "pinia";
import {ref} from "vue";
import {memberInfoApi, type MemberInfoResp} from "../api";


/**
 * 存储用户信息
 */
export const useMemberStore = defineStore('userStore', () => {
  const token = ref<string>();
  const memberInfo  = ref<MemberInfoResp>();

  async function setToken(userToken: string) {
    token.value = userToken;
    if (userToken) {
      memberInfoApi().then(res => {
        memberInfo.value = res;
      })
    }
  }

  async function clearMember() {
    token.value = '';
    memberInfo.value  = undefined;
  }
  return {
    token,
    setToken,
    clearMember,
    memberInfo,
  }
}, {
  persist: true,
});
