<script setup>
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import { defineProps, watch, ref } from "vue";

const props = defineProps({
    lat: String,
    lng: String
})

const latitude = ref("33.450705");
const longitude = ref("126.570667");
const markervisible = ref(false);

watch(
    () => [props.lat, props.lng],
    ([newLat, newLng]) => {
        markervisible.value = true;
        latitude.value = newLat;
        longitude.value = newLng;
    }
)

</script>

<template>
    <div>
        <KakaoMap :lat=latitude :lng=longitude>
            <div v-if="markervisible">
                <KakaoMapMarker :lat=latitude :lng=longitude />
            </div>
        </KakaoMap>
    </div>
</template>

<style scoped>

</style>