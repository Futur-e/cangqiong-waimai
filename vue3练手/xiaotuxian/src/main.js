import { createApp } from 'vue'
import App from './App.vue'
import {store} from "core-js/internals/reflect-metadata";
import router from "@/router";
import vuex from "vue-persistedstate/src/vuex";

const  app = createApp(App)
app.use(vuex)

app.use(store).use(router).mount('#app')
