import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

function api() {
    const instance = axios.create({
        baseURL: VITE_VUE_API_URL,
        headers: {
          "Content-Type": "application/json;charset=utf-8",
        },
      });
      instance.interceptors.request.use(config => {
        console.log(`${config.url}`);
        return config;
      })
      return instance;
    }


    

export { api }