/** @type {import('tailwindcss').Config} */
import daisyui from "daisyui"
import theme from "daisyui/src/theming/themes.js"
export default {
  content: [
    "./index.html",
    "./um.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
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

