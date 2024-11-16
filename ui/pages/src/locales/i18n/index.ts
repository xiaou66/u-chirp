// i18n配置
import { createI18n } from "vue-i18n";
import zhCn from "./zh-cn";
import en from "./en";

// 创建 i18n

const i18n = createI18n({
  locale: localStorage.getItem("language") || "zhCn", // 语言标识
  globalInjection: true, // 全局注入,可以直接使用$t
  // 处理报错: Uncaught (in promise) SyntaxError: Not available in legacy mode (at message-compiler.esm-bundler.js:54:19)
  legacy: false,
  messages: {
  zhCn,
  en
}
})

export default i18n;
