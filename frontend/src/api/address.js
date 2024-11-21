import { authApi } from "./Instance/authInstance";

const addressApi = authApi();

// 주소지 목록 조회
export async function getAddressByMemberId(success, fail) {
  return addressApi.get("/address").then(success).catch(fail);
}

// 주소지 추가
export async function addAddress(address, success, fail) {
  return addressApi.post("/address", address).then(success).catch(fail);
}

// 주소지 수정
export async function updateAddress(addressId, address, success, fail) {
  return addressApi
    .put(`/address/${addressId}`, address)
    .then(success)
    .catch(fail);
}

// 주소지 삭제
export async function deleteAddress(addressId, success, fail) {
  return addressApi.delete(`/address/${addressId}`).then(success).catch(fail);
}
