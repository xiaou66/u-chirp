import {defineStore} from "pinia";
import {ref} from "vue";

export const useUserStore = defineStore('userStore', () => {
  const token = ref<string>();

  async function setToken(userToken: string) {
    token.value = userToken;
  }
  return {
    token,
    setToken
  }
});
