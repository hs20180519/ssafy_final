import { api } from "./Instance/axiosInstance";

const houseDealApi = api();

function getHomeDealByKeyword(param, success, fail) {
    console.log(param.type, param.keyword);
    houseDealApi.get('/house/keyword', {
        params: {
        type: param.type,
        code: param.keyword,
      },
    })
    .then(success)
    .catch(fail);
}

function getHouseDeal(param, success, fail) {
    houseDealApi.get('/house', {
        params: {
            sidoCode: param.sidoCode,
            gugunCode: param.gugunCode,
            dongCode: param.dongCode,
            keyword: param.keyword
        }
    }).then(success).catch(fail);
}

export {
    getHomeDealByKeyword,
    getHouseDeal
}
