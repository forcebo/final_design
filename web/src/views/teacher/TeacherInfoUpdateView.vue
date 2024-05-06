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
      <span style="color: red; font-weight: bold">修改资料</span>
    </div>

    <form @submit.prevent="updateTeacherInfo" style="margin-top: 20px">
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label
          for="username"
          class="col-sm-4 col-form-label text-end"
        >
          <span style="color: red; font-weight: bold">*</span>请输入用户名
        </label>
        <div class="col-sm-6">
          <input
            v-model="username"
            type="text"
            class="form-control"
            id="username"
            placeholder="请输入用户名"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center">
        <label for="identity1" class="col-sm-4 col-form-label text-end">
          <img src="@/assets/images/zcimg4.png" width="18" height="15" />
          选择身份
        </label>
        <div class="col-sm-6">
          <div class="row align-items-center">
            <div class="col">
              <div class="form-check">
                <input
                  v-model="identity"
                  class="form-check-input"
                  type="radio"
                  value="在校大学生(研究生),不含外籍留学生"
                />
                <label class="form-check-label" for="identity"
                  >在校大学生(研究生),不含外籍留学生</label
                >
              </div>
            </div>
          </div>
          <div class="row align-items-center">
            <div class="col">
              <div class="form-check">
                <input
                  v-model="identity"
                  class="form-check-input"
                  type="radio"
                  value="专业老师（注册教师应符合相关政策法规）"
                />
                <label class="form-check-label" for="identity2"
                  >专业老师（注册教师应符合相关政策法规）</label
                >
              </div>
            </div>
          </div>
          <div class="row align-items-center">
            <div class="col">
              <div class="form-check">
                <input
                  v-model="identity"
                  class="form-check-input"
                  type="radio"
                  value="其他(已经毕业离校的人员)"
                />
                <label class="form-check-label" for="identity"
                  >其他(已经毕业离校的人员)</label
                >
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="teacherName" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>真实姓名
        </label>
        <div class="col-sm-6">
          <input
            v-model="realname"
            type="text"
            class="form-control"
            id="teacherName"
            placeholder="请输入真实姓名"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="age" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>年龄
        </label>
        <div class="col-sm-6">
          <input
            v-model="age"
            type="text"
            class="form-control"
            id="age"
            placeholder="请输入真实年龄"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="education" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>目前学历
        </label>
        <div class="col-sm-6">
          <select v-model="education" class="form-select" id="education">
            <option value="" selected disabled>请选择学历</option>
            <option value="大专以下">大专以下</option>
            <option value="大专在读">大专在读</option>
            <option value="大专毕业">大专毕业</option>
            <option value="本科在读">本科在读</option>
            <option value="本科毕业">本科毕业</option>
            <option value="硕士在读">硕士在读</option>
            <option value="硕士毕业">硕士毕业</option>
            <option value="博士在读">博士在读</option>
            <option value="博士毕业">博士毕业</option>
          </select>
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="major" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>专业
        </label>
        <div class="col-sm-6">
          <input
            v-model="major"
            type="text"
            class="form-control"
            id="major"
            placeholder="请输入专业"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center" style="margin-top: 10px">
        <label for="teacherPhone" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>手机号
        </label>
        <div class="col-sm-6">
          <input
            v-model="phone"
            type="text"
            class="form-control"
            id="teacherPhone"
            placeholder="请输入手机号"
          />
        </div>
      </div>
      <div class="mb-3 row align-items-center">
        <label for="address" class="col-sm-4 col-form-label text-end">
          <span style="color: red; font-weight: bold">*</span>通讯地址
        </label>
        <div class="col-sm-6">
          <input
            v-model="address"
            type="text"
            class="form-control"
            id="address"
            placeholder="请输入通讯地址"
          />
        </div>
      </div>
      <div class="d-flex justify-content-center" style="margin-left: 100px">
        <div class="error-message">{{ error_message }}</div>
      </div>
      <div
        class="mb-3 d-flex justify-content-center"
      >
        <button type="submit" class="btn btn-primary me-2" style="width: 100px">
          提交
        </button>
        <button type="reset" class="btn btn-primary" style="width: 100px">
          重置
        </button>
      </div>
    </form>
  </TeacherInfoTemplate>
</template>
  
  <script>
import TeacherInfoTemplate from "@/components/TeacherInfoTemplate.vue";
import { ref } from "vue";
import { useStore } from 'vuex';
import $ from "jquery";
export default {
  components: {
    TeacherInfoTemplate,
  },
  setup() {
    const store = useStore();
    let username = ref(store.state.teacher.username);
    let identity = ref(store.state.teacher.identity);
    let realname = ref(store.state.teacher.realname);
    let age = ref(store.state.teacher.age);
    let education = ref(store.state.teacher.education);
    let major = ref(store.state.teacher.major);
    let phone = ref(store.state.teacher.phone);
    let address = ref(store.state.teacher.address);
    let error_message = ref("");
    let modal_error_message = ref("");
    let showSuccess = ref(false);

    const closeModal = () => {
      showSuccess.value = false;
      modal_error_message.value = "";
    };

    const updateTeacherInfo = () => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/account/info/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
        },
        data: {
          username: username.value,
          identity: identity.value,
          realname: realname.value,
          age: age.value,
          education: education.value,
          major: major.value,
          phone: phone.value,
          address: address.value,
        },
        success(resp) {
          if (resp.success == true) {
            store.dispatch("getTeacherInfo", {
              success() {
                store.commit("updateTeacherPullingInfo", false);
              },
              error() {
                store.commit("updateTeacherPullingInfo", false);
              },
            });
            modal_error_message.value = "修改信息成功";
            showSuccess.value = true;
          } else {
            error_message.value = resp.errorMsg;
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    return {
      username,
      identity,
      realname,
      education,
      age,
      major,
      phone,
      address,
      error_message,
      updateTeacherInfo,
      showSuccess,
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
  
  