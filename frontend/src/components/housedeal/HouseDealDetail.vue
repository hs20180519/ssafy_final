<script setup>
import { ref, defineEmits, defineProps } from 'vue'

const emit = defineEmits(['closeDetail']);

defineProps({
    isVisible: Boolean,
    clickedItem: Object
})


const closeDetail = () => {
    emit('closeDetail');
}

</script>

<template>
    <div class="sidebar" v-if='isVisible'>
        <!-- 닫기 버튼 -->
        <button class="close-button" @click="closeDetail">X</button>

        <!-- 이미지 섹션 -->
        <div class="image-section">
            <img src="https://via.placeholder.com/300x200" alt="매물 이미지" />
            <button class="bookmark-button">★</button>
        </div>

        <!-- 길찾기 버튼 -->
        <div class="navigate-button">
            <button>길찾기</button>
        </div>

        <!-- 거래 기록 섹션 -->
        <div class="record-section">
            <h3>거래 기록</h3>
            <ul class="record-list">
                <li v-for="record in clickedItem.dealList" :key="record.id" class="record-item">
                    <p><strong>거래일시:</strong> {{ record.dealYear }}.{{ record.dealMonth }}.{{ record.dealDay }}</p>
                    <p><strong>금액:</strong> {{ record.dealAmount }}</p>
                    <p><strong>층:</strong> {{ record.floor }}</p>
                </li>
            </ul>
        </div>
    </div>
</template>


<style scoped>
/* .sidebar: 사이드바의 전체 스타일 */
.sidebar {
    display: flex;
    flex-direction: column; /* 수직으로 정렬 */
    width: 300px;
    height: 100%; /* 화면 전체 높이 */
    background-color: #ffffff;
    box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px;
    border-left: 1px solid #ddd;
    position: relative;
}

/* .image-section: 이미지 섹션 */
.image-section {
    height: 200px; /* 고정 높이 */
    width: 100%;
    border-bottom: 1px solid #ddd;
}

/* .navigate-button: 길찾기 버튼 */
.navigate-button {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}

.navigate-button button {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 5px;
}

.navigate-button button:hover {
    background-color: #0056b3;
}

/* .record-section: 거래 기록 */
.record-section {
    flex-grow: 1; /* 남은 공간을 모두 차지 */
    overflow-y: auto;
    padding: 15px;
    box-sizing: border-box;
}

/* .close-button: 닫기 버튼 */
.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    background: none;
    border: none;
    font-size: 1.2rem;
    cursor: pointer;
    z-index: 11;
}

.bookmark-button {
    position: absolute;
    top: 50px; /* 닫기 버튼과 겹치지 않도록 아래로 이동 */
    right: 10px;
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1rem;
    cursor: pointer;
}
</style>
