import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faHouse,
  faUser,
  faCircleInfo,
  faStar,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";

import { useKakao } from "vue3-kakao-maps";

library.add(faHouse, faUser, faCircleInfo, faStar, faMagnifyingGlass);

useKakao(import.meta.env.VITE_KAKAO_APP_KEY)

const app = createApp(App)
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(createPinia())
app.use(router)


app.mount('#app')
