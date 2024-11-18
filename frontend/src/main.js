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

library.add(faHouse, faUser, faCircleInfo, faStar, faMagnifyingGlass);

const app = createApp(App);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(createPinia());
app.use(router);

app.mount("#app");
