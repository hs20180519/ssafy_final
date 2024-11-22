import {authApi} from "./Instance/authInstance";

const favoritePropertyApi = authApi();

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
