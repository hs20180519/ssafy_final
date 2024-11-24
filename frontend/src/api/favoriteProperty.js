import {authApi} from "./Instance/authInstance";
import { api } from "./Instance/axiosInstance";

const favoritePropertyApi = authApi();
const noAuthApi = api();

export function createFavoriteProperty(body, success, fail) {
  return favoritePropertyApi.post("/favorite-properties", body).then(success).catch(fail);
}

export async function getFavoriteProperties(success, fail) {
  return favoritePropertyApi.get(`/favorite-properties`).then(success).catch(fail);
}

export async function deleteFavoriteProperty(favoritePropertyId, success, fail) {
  return favoritePropertyApi
    .delete(`/favorite-properties/${favoritePropertyId}`)
    .then(success)
    .catch(fail);
}

export async function existsFavoritePropertyByAptSeqAndId(aptSeq, success, fail) {
  return favoritePropertyApi
    .get(`/favorite-properties/${aptSeq}/exists`)
    .then(success)
    .catch(fail);
}
