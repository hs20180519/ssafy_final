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
const isLoading = ref(false);

const getDurations = async (departure) => {
    console.log(departure);
    isLoading.value = true;

    const params = {
        startX: departure.coordinates.longitude,
        startY: departure.coordinates.latitude,
        // startAddress: departure.roadNameAddress,
        endX: props.lng,
        endY: props.lat
    };

    try {
        await getCarDuration(
            params,
            ({data}) => {
                routeTimes.value.push({
                    name: "승용차",
                    duration: formatTime(data.totalTime)
                })
                console.log("getCarDuration", data);
            },
            (error) => {
                routeTimes.value.push({
                    name: "승용차",
                    duration: "측정불가"
                })
                console.log(error);
            }
        );
    
        // await getTransitDuration(
        //     params,
        //     ({data}) => {
        //         routeTimes.value.push({
        //             name: "대중교통",
        //             duration: formatTime(data.totalTime)
        //         })
        //         console.log("getTransitDuration", data);
        //     },
        //     (error) => {
        //         routeTimes.value.push({
        //             name: "대중교통",
        //             duration: "측정불가"
        //         })
        //         console.log(error);
        //     }
        // )
    
        // 대중교통 dump data
        routeTimes.value.push({
                    name: "대중교통",
                    duration: "측정불가"
                });
    
        await getWalkDuration({
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
            routeTimes.value.push({
                    name: "도보",
                    duration: "측정불가"
                })
            console.log(error);
        })

    } catch(error) {
        console.log("Error in getDurations: ", error);
    } finally {
        isLoading.value = false;
    }
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
        <h3>소요시간</h3>
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
            <template v-if="isLoading">
                <!-- 로딩 중 -->
                <p>소요시간을 계산 중입니다...</p>
            </template>
            <template v-else>
                <!-- 로딩 완료 -->
                <ul>
                    <li v-for="(mode, index) in routeTimes" :key="index">
                        <strong>{{ mode.name }}:</strong> 
                        <span class="duration-value animated-slide-in" 
                              :style="{ animationDelay: `${index * 0.3}s` }">
                            {{ mode.duration }}
                        </span>
                    </li>
                </ul>
            </template>
        </div>
    </div>
</template>

  
<style scoped>
.route-time-component {
  margin: auto;
  padding: 20px;
  border-radius: 12px;
  font-family: 'Helvetica Neue', Arial, sans-serif;
  max-width: 400px;
}

.start-location {
  margin-bottom: 16px;
}

.start-location label {
  margin-right: 8px;
  font-weight: bold;
  font-size: 14px;
  color: #333333;
}

.start-location select {
  padding: 8px 12px;
  border: 1px solid #cccccc;
  border-radius: 6px;
  font-size: 14px;
  color: #555555;
  background-color: #fafafa;
  transition: border-color 0.2s, box-shadow 0.2s;
}


.transport-times h3 {
  margin-bottom: 12px;
  font-size: 18px;
  font-weight: bold;
  color: #222222;
}

.transport-times ul {
  list-style: none;
  padding: 0;
}

.transport-times li {
  margin-bottom: 12px;
  font-size: 14px;
  font-weight: normal;
  color: #444444;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.transport-times li strong {
  font-weight: bold;
  color: #222222;
}

.animated-slide-in {
  animation: slideIn 1s ease-in-out forwards;
}

@keyframes slideIn {
  from {
    transform: translateY(10px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
