<script setup>
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import { defineProps, watch, ref } from "vue";
import { useHouseDealStore } from "@/stores/houseDealStore";

const props = defineProps({
    clickedItem: Object
})

const houseDealStore = useHouseDealStore();

const map = ref();
const latitude = ref("33.450705");
const longitude = ref("126.570667");
const info = ref("");
const markervisible = ref(false);
const markerInfoList = ref([]);

let bounds;
let marker;

watch(
    () => props.clickedItem,
    (newItem) => {
        markervisible.value = true;
        latitude.value = newItem.latitude;
        longitude.value = newItem.longitude;
        info.value = newItem.aptNm;
        initMarkerList();
    }
)

const initMarkerList = () => {
    markerInfoList.value = [
        {
            key: 1,
            lat: latitude.value,
            lng: longitude.value
        }
    ]
}

watch(
    () => houseDealStore.selectedDeparture,
    (newObj) => {
        initMarkerList();
        // let markerImage = new kakao.maps.MarkerImage('http://www.w3.org/2000/svg', new kakao.maps.Size(24,35));
        markerInfoList.value.push({
            key: 2,
            lat: newObj.coordinates.latitude,
            lng: newObj.coordinates.longitude,
            // image: markerImage
        });
    }
)


const onLoadKakaoMap = (mapRef) => {
    map.value = mapRef;
    bounds = new kakao.maps.LatLngBounds();
}

const setBounds = () => {
    console.log("setBounds", markerInfoList.value);
    let point;
    console.log(markerInfoList.value);
    markerInfoList.value.forEach((markerInfo) => {
        point = new kakao.maps.LatLng(markerInfo.lat, markerInfo.lng);
        
        bounds.extend(point);
    });
    
    if(map.value !== undefined) {
        map.value.setBounds(bounds);
    }

    if(markerInfoList.value.length == 1) {
        map.value.setLevel(3);
    }
}

watch(markerInfoList, setBounds, { deep: true, immediate: true });
</script>


<template>
    <div class="map-container">
        <KakaoMap :lat=latitude :lng=longitude :markerList="markerInfoList" @onLoadKakaoMap="onLoadKakaoMap"
            style="width: 100%; height: 100vh; z-index: 1;"
        >
            <!-- <div v-if="markervisible">
                <KakaoMapMarker :lat=latitude :lng=longitude>
                    <template v-slot:infoWindow>
                        <div style="padding: 5px;text-align: center">{{ info }}</div>
                    </template>
                </KakaoMapMarker>
                <KakaoMapMarker :lat=houseDealStore.selectedDeparture.coordinates.latitude :lng=houseDealStore.selectedDeparture.coordinates.longitude>
                    <template v-slot:infoWindow>
                        <div style="padding: 5px;text-align: center">{{ houseDealStore.selectedDeparture.name }}</div>
                    </template>
                </KakaoMapMarker>
            </div> -->
        </KakaoMap>
    </div>
</template>

<style scoped>
</style>