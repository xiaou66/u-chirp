import { defineConfig, Plugin } from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath} from "node:url";
import Dts from "vite-plugin-dts";
import * as path from 'node:path'
// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), Dts({
    tsconfigPath: "./tsconfig.app.json",
    entryRoot: "./src",
    outDir: "./dist",
    insertTypesEntry: true,
  }) as Plugin,],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    lib: {
      entry: path.resolve(__dirname, "src/index.ts"),
      name: "shadcn",
      formats: ["es", "iife"],
      fileName: (format) => `shadcn.${format}.js`,
    },
    rollupOptions: {
      external: [
        "vue"
      ]
    }
  },
})
