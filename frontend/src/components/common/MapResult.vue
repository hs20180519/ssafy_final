<script setup>
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import { defineProps, onMounted, watch, ref } from "vue";

const props = defineProps({
    clickedItem: Object
})

const latitude = ref("33.450705");
const longitude = ref("126.570667");
const info = ref("");
const markervisible = ref(false);

watch(
    () => props.clickedItem,
    (newItem) => {
        console.log("clickedItem: ", props.clickedItem)
        markervisible.value = true;
        latitude.value = newItem.latitude;
        longitude.value = newItem.longitude;
        info.value = newItem.aptNm;
    }
)

</script>

<template>
    <div>
        <KakaoMap :lat=latitude :lng=longitude>
            <div v-if="markervisible">
                <KakaoMapMarker :lat=latitude :lng=longitude>
                    <template v-slot:infoWindow>
                        <div style="padding: 5px;text-align: center;">{{ info }}</div>
                    </template>
                </KakaoMapMarker>
            </div>
        </KakaoMap>
    </div>
</template>

<style scoped>

</style>