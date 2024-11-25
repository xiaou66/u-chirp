import {defineStore} from "pinia";
import {ref} from "vue";
import {useI18n} from "vue-i18n";
import {getLanguageConfig} from "../api";

export const useSystemStore = defineStore('systemStore', () => {
  const language = ref<string>('zh');

  function loadLanguage(lang: string) {
    language.value = lang;
    const composer = useI18n();
    getLanguageConfig(lang)
      .then(res => {
        composer.setLocaleMessage(lang, res);
        composer.locale.value = lang;
      });
  }
  return {
    language,
    loadLanguage
  }
})
