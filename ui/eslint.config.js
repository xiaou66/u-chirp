import pluginVue from 'eslint-plugin-vue'
import vueTsEslintConfig from '@vue/eslint-config-typescript'
import skipFormatting from '@vue/eslint-config-prettier/skip-formatting'

export default {
  // 这个数组包含要 lint 的文件
  files: ['**/*.{ts,mts,tsx,vue}'],

  // 这个数组包含要忽略的文件
  ignores: ['**/dist/**', '**/dist-ssr/**', '**/coverage/**'],

  // 使用 ESLint 插件的配置
  ...pluginVue.configs['flat/essential'],
  ...vueTsEslintConfig(),

  // 确保 skipFormatting 不会导致问题
  skipFormatting,

  // 额外的规则可以在这里定义
  rules: {
    'vue/multi-word-component-names': 'off', // 根据需要启用或禁用此规则
  }
}
