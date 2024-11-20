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

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

library.add(faHouse, faUser, faCircleInfo, faStar, faMagnifyingGlass);

<<<<<<< HEAD
useKakao(import.meta.env.VITE_KAKAO_APP_KEY)

const app = createApp(App)
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(createPinia())
app.use(router)


app.mount('#app')
=======
const app = createApp(App);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(createPinia());
app.use(router);

app.mount("#app");
>>>>>>> fe24f83 (:sparkles: Feat: 질문 게시글 조회/작성, 답변 조회/작성)
