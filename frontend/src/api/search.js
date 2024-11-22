import { api } from "./Instance/axiosInstance";

const searchApi = api();

function searchKeyword(param, success, fail) {
  console.log(param)
  searchApi
    .get("/dong-code/search", {
      params: {
        sidoCode: param.sidoCode,
        gugunCode: param.gugunCode,
        dongCode: param.dongCode,
        keyword: param.keyword
      }
    })
    .then(success)
    .catch(fail);
}

function getFilter(param, success, fail) {
  console.log(param.param);
  searchApi
    .get("dong-code", {
      params: {
        parentCode: param.param == undefined ? "" : param.param,
      },
    })
    .then(success)
    .catch(fail);
}

export { searchKeyword, getFilter };
