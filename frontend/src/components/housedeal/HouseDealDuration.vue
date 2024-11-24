<script setup>
import { getAddressByMemberId } from '@/api/address';
import { getCarDuration } from '@/api/dufation';
import { onMounted, ref, defineProps } from 'vue';

const props = defineProps({
    lat: String,
    lng: String
})

onMounted(() => {
    console.log("onMounted");
    getDepartures().then(getDurations(selectedDeparture.roadNameAddress));
})


// 출발지 리스트 (사용자가 등록한 주소지)
const departures = ref([]);
const selectedDeparture = ref();
const destination = ref({ // 목적지
    lat: props.lat,
    lng: props.lng
});

const getDepartures = () => {
    return new Promise((resolve, reject) => {
        getAddressByMemberId(
            ({data}) => {
                console.log("getDepartures:", data);
                departures.value = data;
                if(data.length >= 1) {
                    selectedDeparture.value = data[0];
                }
                resolve();
            },
            (error) => {
                console.log(error);
                reject(error);
            }
        )

    })
}

// 선택된 출발지
// const selectedStartLocation = ref(savedLocations.value[0].address);

// 교통수단별 소요시간
const routeTimes = ref({
    도보: null,
    대중교통: null,
    승용차: null,
});

const getDurations = (departure) => {
    getCarDuration({
        startX: 0,
        startY: 0,
        startAddress: departure.roadNameAddress,
        endX: props.lng,
        endY: props.lat
    })
}

// // 교통수단별 소요시간 가져오기
// const fetchRouteTimes = async () => {
// try {
// const startLocation = selectedStartLocation.value;

// // API 호출 로직 (예: axios 사용)
// const response = await fakeApiCall(startLocation, destination);

// // 데이터 업데이트
// routeTimes.value = response;
// } catch (error) {
// console.error("길찾기 데이터를 가져오는 중 오류 발생:", error);
// }
// };

// // API 호출 대체 함수 (테스트용)
// const fakeApiCall = async (start, end) => {
// console.log(`출발지: ${start}, 목적지: ${end}`);
// return new Promise((resolve) => {
// setTimeout(() => {
// resolve({
// 도보: 50,
// 대중교통: 30,
// 승용차: 15,
// });
// }, 1000);
// });
// };

// 컴포넌트 로드 시 초기 데이터 가져오기
// fetchRouteTimes();
</script>

<template>
    <div class="route-time-component">
        <h3>교통수단별 소요시간</h3>
      <!-- 출발지 선택 -->
        <div class="start-location">
            <label for="departure">출발지:</label>
            <select id="departure" v-model="selectedDeparture.roadNameAddress" @change="">
                <option v-for="departure in departures" :key="departure.id" :value="departure.roadNameAddress">
                    {{ departure.name }}
                </option>
            </select>
        </div>
    
        <!-- 교통수단별 소요시간 -->
        <!-- <div class="transport-times">
            <ul>
            <li v-for="(time, mode) in routeTimes" :key="mode">
                <strong>{{ mode }}:</strong> {{ time }} 분
            </li>
            </ul>
        </div> -->
    </div>
</template>
  
  <style scoped>
  .route-time-component {
    margin: auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #f9f9f9;
    font-family: Arial, sans-serif;
  }
  
  .start-location {
    margin-bottom: 20px;
  }
  
  .start-location label {
    margin-right: 10px;
    font-weight: bold;
  }
  
  .start-location select {
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .transport-times h3 {
    margin-bottom: 10px;
  }
  
  .transport-times ul {
    list-style: none;
    padding: 0;
  }
  
  .transport-times li {
    margin-bottom: 10px;
    font-size: 16px;
  }
  </style>
  