import { api } from "./axiosInstance";

const searchApi = api();

function searchKeyword(param, success, fail) {
    searchApi.get('/dong-code/search', { params: {
        keyword: param
    } }).then(success).catch(fail);
}

export {
    searchKeyword
}