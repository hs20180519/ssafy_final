import {createApp} from "vue";
import {createPinia} from "pinia";
import {useAuthStore} from "./stores/authStore";

import App from "./App.vue";
import router from "./router";

import {library} from "@fortawesome/fontawesome-svg-core";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {
  faHouse,
  faUser,
  faCircleInfo,
  faStar,
  faMagnifyingGlass, faBookmark,
} from "@fortawesome/free-solid-svg-icons";

import {useKakao} from "vue3-kakao-maps";

import ElementPlus from 'element-plus'


library.add(faHouse, faUser, faCircleInfo, faStar, faMagnifyingGlass, faBookmark);

useKakao(import.meta.env.VITE_KAKAO_APP_KEY);

const app = createApp(App);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(createPinia());

const authStore = useAuthStore();
authStore.restoreFromSessionStorage(); // 페이지 로드 시 상태 복원

app.use(ElementPlus);
app.use(router);
app.mount("#app");
