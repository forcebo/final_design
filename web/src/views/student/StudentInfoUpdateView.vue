<template>
  <div class="modal" tabindex="-1" id="exampleModal" v-if="showSuccess">
    <div class="modal-dialog" style="width: 400px">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">提示</h5>
          <button
            type="button"
            class="btn-close"
            @click="closeModal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <p>修改信息成功！</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="closeModal">
            关闭
          </button>
          <button type="button" class="btn btn-primary" @click="closeModal">
            确定
          </button>
        </div>
      </div>
    </div>
  </div>
  <StudentInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold">修改资料</span>
    </div>
    <form @submit.prevent="updateStudentInfo" style="margin-top: 20px">
      <div class="mb-3 row align-items-center">
        <label for="studentUsername" class="col-sm-4 col-form-label text-end">
          用户名:
        </label>
        <div class="col-sm-6">
          <input
            v-model="username"
            type="text"
            class="form-control"
            id="studentUsername"
            placeholder="请输入修改后的用户名"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="studentRealname" class="col-sm-4 col-form-label text-end">
          学员姓名:
        </label>
        <div class="col-sm-6">
          <input
            v-model="realname"
            type="text"
            class="form-control"
            id="studentRealname"
            placeholder="请输入修改后的真实姓名"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="studentPhone" class="col-sm-4 col-form-label text-end">
          电话号码:
        </label>
        <div class="col-sm-6">
          <input
            v-model="phone"
            type="text"
            class="form-control"
            id="studentPhone"
            placeholder="请输入修改后的电话号码"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="studentAddress" class="col-sm-4 col-form-label text-end">
          通讯地址:
        </label>
        <div class="col-sm-6">
          <input
            v-model="address"
            type="text"
            class="form-control"
            id="studentAddress"
            placeholder="请输入修改后的通讯地址"
          />
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <div class="error-message">{{ error_message }}</div>
      </div>
      <div class="mb-3 d-flex justify-content-center">
        <button type="submit" class="btn btn-primary me-2" style="width: 100px">
          提交
        </button>
      </div>
    </form>
  </StudentInfoTemplate>
</template>
  
  <script>
import StudentInfoTemplate from "@/components/StudentInfoTemplate.vue";

import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
export default {
  components: {
    StudentInfoTemplate,
  },

  setup() {
    const store = useStore();
    let username = ref("");
    let realname = ref("");
    let phone = ref("");
    let address = ref("");
    let error_message = ref("");
    let showSuccess = ref(false);

    username.value = store.state.student.username;
    realname.value = store.state.student.realname;
    phone.value = store.state.student.phone;
    address.value = store.state.student.address;

    const closeModal = () => {
      showSuccess.value = false;
    };

    const updateStudentInfo = () => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/student/account/info/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        data: {
          username: username.value,
          realname: realname.value,
          phone: phone.value,
          address: address.value,
        },
        success(resp) {
          console.log(resp);
          if (resp.success == true) {
            store.dispatch("getStudentInfo", {
              success() {
                store.commit("updateStudentPullingInfo", false);
              },
              error() {
                store.commit("updateStudentPullingInfo", false);
              },
            });
            showSuccess.value = true;
          } else {
            error_message.value = resp.errorMsg;
          }
        },
        error(resp) {
          error_message.value = "未知错误，请重试";
          console.log(resp);
        },
      });
    };

    const handleAvatarChange = (event) => {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append("avatar", file);
      console.log(formData);
      $.ajax({
        url: "http://127.0.0.1:3000/student/account/photo/update/", // 修改为实际的后端接口地址
        type: "post",
        data: formData,
        processData: false, // 不对 data 进行序列化处理
        contentType: false, // 不设置 content-type 头部
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          console.log("文件上传成功:", resp);
          if (resp.success == true) {
            store.dispatch("getStudentInfo", {
              success() {
                store.commit("updateStudentPullingInfo", false);
              },
              error() {
                store.commit("updateStudentPullingInfo", false);
              },
            });
            showSuccess.value = true;
          } else {
            error_message.value = resp.errorMsg;
          }
        },
        error(xhr, status, error) {
          console.error("文件上传失败:", error);
        },
      });
    };

    return {
      username,
      realname,
      phone,
      address,
      error_message,
      handleAvatarChange,
      updateStudentInfo,
      showSuccess,
      closeModal,
    };
  },
};
</script>
  
  <style scoped>
div.error-message {
  font-weight: 700;
  color: red;
}
/* 模态框居中显示 */
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 为模态框添加一些样式以保持居中 */
.modal-dialog {
  max-width: 80%;
}

/* 遮罩层样式 */
.modal-backdrop.show {
  opacity: 0.5;
}
</style>
  
  