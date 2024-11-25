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

async function geocode(param) {
    console.log(param);
    try {
        const response = await durationApi.get("/duration/geocode", {
            params: {
                address: param
            }
        });
        return response.data; // 성공 시 데이터 반환
    } catch (error) {
        throw error; // 실패 시 에러 던짐
    }
}

export {getCarDuration, getWalkDuration, getTransitDuration, geocode}