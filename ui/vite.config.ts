import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
// import vueDevTools from 'vite-plugin-vue-devtools'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import path from "path";
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import {fileURLToPath} from "node:url";
import { codeInspectorPlugin } from 'code-inspector-plugin'
// https://vite.dev/config/
export default defineConfig({
  build: {
    outDir: path.resolve("build/dist"),
    emptyOutDir: false,
  },
  plugins: [
    vue(),
    vueJsx(),
    // vueDevTools(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
    createSvgIconsPlugin({
      // 指定需要缓存的图标文件夹
      iconDirs: [path.resolve(process.cwd(), 'pages/common/assets/icons')],
      // 指定symbolId格式
      symbolId: 'icon-[dir]-[name]',
    }),
    codeInspectorPlugin({
      bundler: 'vite',
    }),
  ],
  resolve: {
    alias: {
      '@common': fileURLToPath(new URL('./pages/common', import.meta.url)),
      '@app': fileURLToPath(new URL('./pages/src', import.meta.url))
    }
  },
})

console.log([path.resolve(process.cwd(), 'pages/common/assets/icons')])
