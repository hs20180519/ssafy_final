import { unsplashApi } from "@/api/Instance/imageInstance";
const imageApi = unsplashApi();

export async function fetchUnsplashImage(query = "house") {
  try {
    const apiKey = import.meta.env.VITE_VUE_APP_UNSPLASH_API_KEY; // 환경 변수에서 API 키 가져오기
    const response = await imageApi.get(
      `/photos/random?query=${query}&client_id=${apiKey}`
    );
    if (response.data && response.data.urls) {
      return response.data.urls.regular; // 이미지 URL 반환
    } else {
      console.error("No image found in Unsplash API response.");
      return null;
    }
  } catch (error) {
    console.error("Error fetching image from Unsplash", error);
    return null;
  }
}
