import axios from "axios";

export const unsplashApi = () => {
  return axios.create({
    baseURL: "https://api.unsplash.com",
  });
};
