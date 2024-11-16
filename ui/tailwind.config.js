/** @type {import('tailwindcss').Config} */
import daisyui from "daisyui"
import theme from "daisyui/src/theming/themes.js"
export default {
  content: [
    "./index.html",
    "./um.html",
    './packages/components/**/*.{vue,ts,tsx}',
    "./src/**/*.{vue,ts,tsx}",
  ],
  theme: {
    screens: {
      'sm': '640px',
      'md': '768px',
      'lg': '1024px',
      'xl': '1280px',
      '2xl': '1536px',
      'mobile': '360px'
    },
    extend: {},
  },
  plugins: [
    daisyui
  ],
  daisyui: {
    themes: [
      {
        winter: {
          ...theme['winter']
        }
      },
    ],
  },
}

