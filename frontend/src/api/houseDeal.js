import { api } from "./Instance/axiosInstance";

const houseDealApi = api();

function getHouseDealByKeyword(param, success, fail) {
    console.log(param);
    houseDealApi.get('/house/keyword', {
        params: {
        type: param.type,
        code: param.keyword,
        pgSize: param.pgSize,
        pgno: param.pgno,
        sort: param.sort
      },
    })
    .then(success)
    .catch(fail);
}

function getCountHouseDealByKeyword(param, success, fail) {
    houseDealApi.get('/house/keyword/count', {
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
    getHouseDealByKeyword,
    getHouseDeal,
    getCountHouseDealByKeyword
}
