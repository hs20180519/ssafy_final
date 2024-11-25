<script setup>
import { ref, watch } from "vue";
import { Line } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
} from "chart.js";

// Chart.js 플러그인 등록
ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement
);

// Props 정의
const props = defineProps({
  dealList: {
    type: Array,
    required: true,
    default: () => [],
  },
});

// 모달 상태 정의
const isModalVisible = ref(false);

// Chart.js 데이터와 옵션 초기화
const chartData = ref({
  labels: [], // x축 레이블 (거래 날짜)
  datasets: [
    {
      label: "거래 금액 (만원)",
      data: [], // y축 데이터 (거래 금액)
      borderColor: "#4e73df",
      backgroundColor: "rgba(78, 115, 223, 0.1)",
      tension: 0.4, // 곡선
    },
  ],
});

const chartOptions = ref({
  responsive: true,
  plugins: {
    legend: {
      position: "top",
    },
    tooltip: {
      enabled: true,
    },
  },
  scales: {
    x: {
      title: {
        display: true,
        text: "거래 날짜",
      },
    },
    y: {
      title: {
        display: true,
        text: "거래 금액 (만원)",
      },
      beginAtZero: true,
    },
  },
});

// 그래프 데이터 업데이트
const updateChartData = () => {
  if (!props.dealList || props.dealList.length === 0) return;

  // 최신순으로 정렬 (거래 연도, 월, 일을 기준으로 내림차순 정렬)
  const sortedDealList = [...props.dealList].sort((a, b) => {
    const dateA = new Date(a.dealYear, a.dealMonth - 1, a.dealDay);
    const dateB = new Date(b.dealYear, b.dealMonth - 1, b.dealDay);
    return dateA - dateB; // 오름차순 정렬(과거 → 최신)
  });

  // x축 레이블과 y축 데이터 설정
  const labels = sortedDealList.map(
    (record) => `${record.dealYear}.${record.dealMonth}.${record.dealDay}`
  );
  const data = sortedDealList.map((record) =>
    parseFloat(record.dealAmount.replace(/,/g, ""))
  );

  // Chart.js 데이터 업데이트
  chartData.value.labels = labels;
  chartData.value.datasets[0].data = data;
};

// Props 변경 감지
watch(
  () => props.dealList,
  () => {
    updateChartData();
  },
  { immediate: true }
);

// 모달 열기/닫기
const openModal = () => {
  isModalVisible.value = true;
};

const closeModal = () => {
  isModalVisible.value = false;
};
</script>

<template>
  <div>
    <!-- 그래프 -->
    <div class="chart-container" @click="openModal">
      <h3 class="chart-title">거래 금액 그래프</h3>
      <Line :data="chartData" :options="chartOptions" />
    </div>

    <!-- 모달 -->
    <div v-if="isModalVisible" class="modal" @click.self="closeModal">
      <div class="modal-content">
        <span class="close-button" @click="closeModal">&times;</span>
        <h3 class="chart-title">거래 금액 그래프</h3>
        <Line :data="chartData" :options="chartOptions" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.chart-container {
  padding: 5px;
  border-bottom: 1px solid #ddd;
  cursor: pointer;
}

.chart-title {
  margin: 10px;
}

/* 모달 스타일 */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  backdrop-filter: blur(5px);
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 800px;
  text-align: center;
  z-index: 1001;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 30px;
  font-weight: bold;
  cursor: pointer;
}
</style>
