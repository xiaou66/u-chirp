import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import i18n from './locales/i18n';
import App from './App.vue'
import router from './router'
import 'virtual:svg-icons-register'
import piniaPluginPersistedState from 'pinia-plugin-persistedstate'

const app = createApp(App)
const pinia = createPinia();
pinia.use(piniaPluginPersistedState);
app.use(pinia)
app.use(router)
app.use(i18n)
app.mount('#app')
