import { authApi } from "./Instance/authInstance";

const memberApi = authApi();

export async function checkPassword(body, success, fail) {
  return memberApi
    .post("/member/verify-password", body)
    .then(success)
    .catch(fail);
}

// 회원 정보 가져오기 (GET)
export async function getMemberInfo() {
  try {
    const response = await memberApi.get("/member/info");
    return response.data; // 회원 정보 반환
  } catch (error) {
    throw new Error("회원 정보를 불러오는 데 실패했습니다." + error);
  }
}

// 회원 정보 수정하기 (POST)
export async function updateMemberInfo(updatedData) {
  try {
    const response = await memberApi.post("/member/update", updatedData); // 수정된 데이터 전송
    return response;
  } catch (error) {
    throw new Error("회원 정보 수정에 실패했습니다." + error);
  }
}
