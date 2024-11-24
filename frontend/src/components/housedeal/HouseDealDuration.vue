<script setup>
import { getAddressByMemberId } from '@/api/address';
import { geocode, getCarDuration, getTransitDuration, getWalkDuration } from '@/api/duration';
import { onMounted, ref, defineProps, watch } from 'vue';
import { useHouseDealStore } from '@/stores/houseDealStore';

const props = defineProps({
    lat: String,
    lng: String
})
const houseDealStore = useHouseDealStore();

onMounted(async () => {
    try {
        await getDepartures();
        getDurations(selectedDeparture.value);
        console.log("Departures loaded:", departures.value);
    } catch (error) {
        console.error("Failed to load departures:", error);
    }
});


// 출발지 리스트 (사용자가 등록한 주소지)
const departures = ref([]);
const selectedDeparture = ref();

watch(selectedDeparture, (newValue) => {
    houseDealStore.setSelectedDeparture(newValue);
    console.log("selectedDeparture changed:", houseDealStore.selectedDeparture);
});

const getDepartures = async () => {
    try {
        // getAddressByMemberId 호출 및 data 가져오기
        const data = await new Promise((resolve, reject) => {
            getAddressByMemberId(
                ({ data }) => resolve(data),
                (error) => reject(error)
            );
        });

        console.log("getDepartures:", data);

        // departures 배열 초기화
        departures.value = await Promise.all(
            data.map(async (item) => {
                // geocode 호출로 좌표 가져오기
                const geocodeResult = await geocode(item.roadNameAddress);
                // 새로운 속성 추가
                return {
                    ...item,
                    coordinates: geocodeResult,
                };
            })

        );
        console.log(departures);

        if (departures.value.length >= 1) {
            selectedDeparture.value = departures.value[0];
            console.log("selectedDeparture:", selectedDeparture.value);
        }
    } catch (error) {
        console.error("Error in getDepartures:", error);
        throw error; // 에러 발생 시 상위 호출자에게 전달
    }
};

// 교통수단별 소요시간
const routeTimes = ref([]);

const getDurations = (departure) => {
    console.log(departure);

    const params = {
        startX: departure.coordinates.longitude,
        startY: departure.coordinates.latitude,
        // startAddress: departure.roadNameAddress,
        endX: props.lng,
        endY: props.lat
    };

    getCarDuration(
        params,
        ({data}) => {
            routeTimes.value.push({
                name: "승용차",
                duration: formatTime(data.totalTime)
            })
            console.log("getCarDuration", data);
        },
        (error) => {
            console.log(error);
        }
    );

    getWalkDuration({
        ...params,
        startName: departure.name,
        endName: "매물"
    },
    ({data}) => {
        routeTimes.value.push({
                name: "도보",
                duration: formatTime(data.totalTime)
            })
        console.log("getWalkDuration", data);
    },
    (error) => {
        console.log(error);
    })

    // getTransitDuration(
    //     params,
    //     ({data}) => {
    //         routeTimes.value.push({
    //             name: "대중교통",
    //             duration: formatTime(data.totalTime)
    //         })
    //         console.log("getTransitDuration", data);
    //     },
    //     (error) => {
    //         console.log(error);
    //     }
    // )

}

const formatTime = (seconds) => {
    const minutes = Math.floor(seconds/60);
    if(minutes >= 60) {
        const hours = Math.floor(minutes / 60);
        const remainingMinutes = minutes % 60;
        return `${hours}시간 ${remainingMinutes}분`;
    }
    return `${minutes}분`;
}

const onChange = () => {
    routeTimes.value = [];
    console.log("onChange", selectedDeparture.value);
    getDurations(selectedDeparture.value);
}

</script>

<template>
    <div class="route-time-component">
        <h3>교통수단별 소요시간</h3>
      <!-- 출발지 선택 -->
        <div class="start-location" v-if="selectedDeparture">
            <label for="departure">출발지:</label>
            <select id="departure" v-model="selectedDeparture" @change="onChange">
                <option v-for="departure in departures" :key="departure.addressId" :value="departure">
                    {{ departure.name }}
                </option>
            </select>
        </div>
    
        <!-- 교통수단별 소요시간 -->
        <div class="transport-times">
            <ul>
            <li v-for="mode in routeTimes" :key="mode">
                <strong>{{ mode.name }}:</strong> {{ mode.duration }}
            </li>
            </ul>
        </div>
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
  