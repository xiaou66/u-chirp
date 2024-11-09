import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import path from "path";
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
// https://vite.dev/config/
export default defineConfig({
  build: {
    outDir: path.resolve("build/dist/dashboard"),
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
      iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
      // 指定symbolId格式
      symbolId: 'icon-[dir]-[name]',
    })
  ],
  resolve: {
  },
})

console.log([path.resolve(process.cwd(), 'src/assets/icons')])
