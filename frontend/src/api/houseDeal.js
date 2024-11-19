import { api } from "./Instance/axiosInstance";

const searchApi = api();

function getHomeDealByKeyword(param, success, fail) {
  console.log(param.type, param.keyword);
  searchApi
    .get("/house/keyword", {
      params: {
        type: param.type,
        code: param.keyword,
      },
    })
    .then(success)
    .catch(fail);
}

export { getHomeDealByKeyword };
