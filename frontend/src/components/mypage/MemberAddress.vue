<template>
  <div class="address-page">

    <!-- 주소지 추가 버튼 -->
    <button @click="addAddress" class="add-address-btn">
      <font-awesome-icon :icon="['fas', 'plus']"/>
    </button>

    <!-- 모달: 주소지 추가/수정 폼 -->
    <div v-if="isAddingOrEditing" class="modal-overlay" @click="cancelEdit">
      <div class="modal-content" @click.stop>
        <h3>{{ isEditing ? "주소지 수정" : "주소지 추가" }}</h3>

        <form @submit.prevent="handleSubmit">
          <div class="input-group">
            <label for="name">주소지 이름</label>
            <input
              type="text"
              id="name"
              v-model="form.name"
              placeholder="주소지 이름을 입력하세요"
              required
            />
          </div>

          <!-- 주소 검색 컴포넌트 -->
          <AddressSearch v-model="form"/>

          <!-- 주소가 없을 때 경고 메시지 -->
          <div v-if="!form.address" class="warning-message">
            주소를 입력하세요
          </div>

          <button type="submit" :disabled="!form.address">
            {{ isEditing ? "수정 완료" : "추가 완료" }}
          </button>
          <button type="button" @click="cancelEdit">취소</button>
        </form>
      </div>
    </div>

    <!-- 주소지 목록 -->
    <div v-if="addresses.length">
      <h3>주소지 목록</h3>
      <ul>
        <li v-for="address in addresses" :key="address.addressId">
          <div class="address-info">
            <p>{{ address.name }} : {{ address.roadNameAddress }}</p>
            <div class="button-group">
              <button @click="editAddress(address)" class="edit-btn">
                <font-awesome-icon :icon="['fas', 'pen']"/>
              </button>
              <button @click="deleteAddress(address.addressId)" class="delete-btn">
                <font-awesome-icon :icon="['fas', 'trash']"/>
              </button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <h3>주소지가 없습니다.</h3>
    </div>

    <!-- 오류 메시지 -->
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
import {
  getAddressByMemberId,
  addAddress,
  updateAddress,
  deleteAddress,
} from "@/api/address";
import AddressSearch from "@/components/AddressSearch.vue";
import {toast} from "vue3-toastify"; // AddressSearch 컴포넌트 임포트

export default {
  components: {
    AddressSearch, // 컴포넌트 등록
  },
  data() {
    return {
      addresses: [],
      form: {
        name: "",
        address: "",
      },
      isAddingOrEditing: false,
      isEditing: false,
      editingAddressId: null,
      errorMessage: "",
    };
  },
  async created() {
    await this.loadAddresses();
  },
  methods: {
    // 주소지 목록 로드
    async loadAddresses() {
      try {
        const response = await getAddressByMemberId();
        this.addresses = response.data;
      } catch (error) {
        toast.error("주소지 목록을 불러오는 데 오류가 발생했습니다.");
      }
    },

    // 주소지 추가 폼 열기
    addAddress() {
      this.isAddingOrEditing = true;
      this.isEditing = false;
      this.form = {name: "", address: ""};
    },

    // 주소지 수정 폼 열기
    editAddress(address) {
      this.isAddingOrEditing = true;
      this.isEditing = true;
      // 기존 주소지 정보로 폼 초기화
      this.form = {...address};
      this.editingAddressId = address.addressId;
    },

    // 주소지 추가/수정 제출
    async handleSubmit() {
      try {
        this.form = {
          name: this.form.name,
          roadNameAddress: this.form.address,
        };
        if (this.isEditing) {
          await updateAddress(this.editingAddressId, this.form);
        } else {
          await addAddress(this.form);
        }
        await this.loadAddresses();
        this.isAddingOrEditing = false;
      } catch (error) {
        this.errorMessage = "주소지 처리 중 오류가 발생했습니다.";
      }
    },

    // 주소지 삭제
    async deleteAddress(addressId) {
      try {
        await deleteAddress(addressId);
        await this.loadAddresses();
      } catch (error) {
        this.errorMessage = "주소지 삭제 중 오류가 발생했습니다.";
      }
    },

    // 수정/추가 취소
    cancelEdit() {
      this.isAddingOrEditing = false;
      this.errorMessage = "";
    },
  },
};
</script>

<style scoped>
.address-page {
  width: 100%;
  max-width: 500px;
  margin: 0 auto;
  border-radius: 8px;
  position: relative;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.add-address-btn {
  background-color: #4e4e4e;
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 6px 12px;
  font-size: 0.9em;
  color: white;
  border: none; /* 테두리 없앰 */
  border-radius: 4px;
  cursor: pointer;
  width: auto;
  height: auto;
}

.add-address-btn:hover {
  background-color: #333;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-content {
  background-color: white;
  padding: 72px;
  border-radius: 8px;
  width: 100%;
  max-width: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
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

button[type="submit"],
button[type="button"] {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  background-color: #4e4e4e;
  color: white;
  border: none; /* 테두리 없앰 */
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover,
button[type="button"]:hover {
  background-color: #333;
}

.error-message {
  color: red;
  font-size: 0.9em;
  margin-top: 15px;
}

.warning-message {
  color: red;
  font-size: 0.9em;
  margin-top: 5px;
}

ul {
  list-style-type: none;
  padding: 0;
}

ul li {
  margin-bottom: 10px;
}

.address-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button-group {
  display: flex;
  gap: 8px;
}

ul li .edit-btn,
ul li .delete-btn {
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  border: none; /* 테두리 없앰 */
}

ul li .edit-btn {
  //background-color: #d4f4ff;
  color: #00796b;
}

ul li .edit-btn:hover {
  background-color: #b2dadf;
}

ul li .delete-btn {
  //background-color: #ffebee;
  color: #c62828;
}

ul li .delete-btn:hover {
  background-color: #ef9a9a;
}
</style>
