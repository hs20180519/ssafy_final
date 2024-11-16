import { api } from "./axiosInstance";

const searchApi = api();

function searchKeyword(param, success, fail) {
    searchApi.get('/dong-code/search', { params: {
        keyword: param
    } }).then(success).catch(fail);
}

function getFilter(param, success, fail) {
    console.log(param.param)
    searchApi.get('dong-code', {
        params: {
            parentCode: param.param==undefined ? '' : param.param
        }
    })
        .then(success)
        .catch(fail)
}

export {
    searchKeyword,
    getFilter
}