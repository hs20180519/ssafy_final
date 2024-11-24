import { authApi } from "./Instance/authInstance";

const durationApi = authApi();

function getCarDuration(param, success, fail) {
    durationApi.get("/duration/car", {
        params: param
    })
    .then(success)
    .catch(fail);
}

function getWalkDuration(param, succes, fail) {
    durationApi.get("/duration/walk", {
        params: param
    })
    .then(succes)
    .catch(fail)
}

function getTransitDuration(param, success, fail) {
    durationApi.get("/duration/transit", {
        params: param
    })
    .then(success)
    .catch(fail);
}

export {getCarDuration, getWalkDuration, getTransitDuration}