<template>
  <div class="address-search">
    <div class="input-group">
      <label for="address">주소</label>
      <input
        type="text"
        id="address"
        v-model="selectedAddress"
        placeholder="주소를 검색하세요"
        readonly
      />
      <button type="button" @click="openAddressSearch">
        주소 검색
      </button>
    </div>

  </div>
</template>

<script>
export default {
  props: {
    modelValue: Object,
  },
  data() {
    return {
      selectedAddress: this.modelValue?.address || "",
      detailAddress: this.modelValue?.detailAddress || "",
    };
  },
  watch: {
    selectedAddress(newValue) {
      this.updateAddress();
    },
    detailAddress(newValue) {
      this.updateAddress();
    },
  },
  methods: {
    openAddressSearch() {
      // 카카오 API 연동 (Kakao Daum Postcode)
      new daum.Postcode({
        oncomplete: (data) => {
          this.selectedAddress = data.address; // 기본 주소값
          this.$emit("update:modelValue", {
            address: this.selectedAddress,
            detailAddress: this.detailAddress,
          });
        },
      }).open();
    },
    updateAddress() {
      // 부모 컴포넌트에 데이터 전달
      this.$emit("update:modelValue", {
        address: this.selectedAddress,
      });
    },
  },
};
</script>

<style scoped>
.address-search {
  margin-bottom: 20px;
}

.input-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.input-group label {
  margin-bottom: 5px;
}

.input-group input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.input-group button {
  margin-top: 10px;
  padding: 8px;
  background-color: #70abd8;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:hover {
  background-color: #0056b3;
}
</style>
