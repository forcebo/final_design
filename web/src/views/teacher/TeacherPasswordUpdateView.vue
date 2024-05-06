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
          <p>{{ modal_error_message }}</p>
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
  <TeacherInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold">修改密码</span>
    </div>
    <form @submit.prevent="updateTeacherPassword" style="margin-top: 20px">
      <div class="mb-3 row align-items-center">
        <label
          for="teacherOldPassword"
          class="col-sm-4 col-form-label text-end"
        >
          原来的密码:
        </label>
        <div class="col-sm-6">
          <input
            v-model="oldPassword"
            type="password"
            class="form-control"
            id="teacherOldPassword"
            placeholder="请输入原来的密码"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label
          for="teacherNewPassword"
          class="col-sm-4 col-form-label text-end"
        >
          输入新密码:
        </label>
        <div class="col-sm-6">
          <input
            v-model="newPassword"
            type="password"
            class="form-control"
            id="teacherNewPassword"
            placeholder="请输入修改后的密码"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label
          for="teacherConfirmPassword"
          class="col-sm-4 col-form-label text-end"
        >
          确认新密码:
        </label>
        <div class="col-sm-6">
          <input
            v-model="confirmNewPassword"
            type="password"
            class="form-control"
            id="teacherConfirmPassword"
            placeholder="请输入确认修改后的密码"
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
  </TeacherInfoTemplate>
</template>
    
    <script>
import TeacherInfoTemplate from "@/components/TeacherInfoTemplate.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
export default {
  components: {
    TeacherInfoTemplate,
  },

  setup() {
    const store = useStore();
    let oldPassword = ref("");
    let newPassword = ref("");
    let confirmNewPassword = ref("");
    let error_message = ref("");
    let showSuccess = ref(false);
    let modal_error_message = ref("");

    const closeModal = () => {
      showSuccess.value = false;
      modal_error_message.value = "";
    };

    const updateTeacherPassword = () => {
      error_message.value = "";
      console.log(oldPassword.value);
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/account/password/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
        },
        data: {
          oldPassword: oldPassword.value,
          newPassword: newPassword.value,
          confirmNewPassword: confirmNewPassword.value,
        },
        success(resp) {
          if (resp.success == true) {
            // store.dispatch("getStudentInfo", {
            //   success() {
            //     store.commit("updateStudentPullingInfo", false);
            //   },
            //   error() {
            //     store.commit("updateStudentPullingInfo", false);
            //   },
            // });
            oldPassword.value = "";
            newPassword.value = "";
            confirmNewPassword.value = "";
            showSuccess.value = true;
            modal_error_message.value = "修改信息成功！";
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

    return {
      oldPassword,
      newPassword,
      showSuccess,
      confirmNewPassword,
      updateTeacherPassword,
      error_message,
      closeModal,
      modal_error_message,
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
    
    