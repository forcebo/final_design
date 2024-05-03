<template>
  <NavBar />
  <div class="modal" tabindex="-1" id="exampleModal" v-if="showSuccess">
  <div class="modal-dialog" style="width: 400px;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">提示</h5>
        <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>修改信息成功！</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" @click="closeModal">关闭</button>
        <button type="button" class="btn btn-primary" @click="closeModal">确定</button>
      </div>
    </div>
  </div>
</div>
  <ContentField>
    <div class="row justify-content-center">
      <div class="card" style="height: 700px; width: 1000px; border-radius: 0%">
        <div class="row" style="margin-top: 10px">
          <!-- 左侧导航栏 -->
          <div class="col-md-3">
            <div
              class="card"
              style="height: 210px; width: 200px; border-radius: 0%"
            >
              <div class="card-body">
                <div class="text-center">
                  <img
                  :src="$store.state.student.photo"
                  alt=""
                  style="width: 80%"
                />
                </div>
                
              </div>
              <div class="text-center" style="margin-bottom: 20px;">
                <!-- 允许用户上传新的头像 -->
                <input
                  type="file"
                  id="avatar"
                  accept="image/*"
                  style="display: none"
                  @change="handleAvatarChange"
                />
                <label for="avatar" class="btn btn-primary">更改头像</label>
              </div>
            </div>
            <div
              class="card"
              style="height: 200px; width: 200px; border-radius: 0%; margin-top: 10px"
              v-if="!$store.state.student.pulling_info"
            >
              <div
                class="d-flex justify-content-center"
                style="height: 40px; background-color: rgb(243, 147, 36)"
              >
                <span
                  style="color: aliceblue; font-size: larger; margin-top: 5px"
                  >学员中心</span
                >
              </div>
              <div style="margin-left: 10px; margin-top: 10px">
                用户名：<span style="color: red">{{
                  $store.state.student.username
                }}</span>
              </div>
              <div style="margin-left: 10px; margin-top: 10px">
                手机号：<span style="color: red">{{
                  $store.state.student.phone
                }}</span>
              </div>
              <div style="margin-left: 10px; margin-top: 10px">
                学员编号：<span style="color: red">{{
                  $store.state.student.id
                }}</span>
              </div>
            </div>
            <div
              class="card"
              style="
                margin-top: 10px;
                height: 250px;
                width: 200px;
                border-radius: 0%;
              "
            >
              <div style="height: 30px; background-color: rgb(240, 240, 240)">
                <span style="font-weight: bold; margin-left: 20px"
                  >系统功能区</span
                >
              </div>
              <div class="list-group" style="width: 200px; border-radius: 0%">
                <router-link
                  to="/student/order"
                  style="height: 35px"
                  class="list-group-item list-group-item-action"
                  aria-current="true"
                >
                  请家教订单
                </router-link>
                <router-link
                  to="/student/release"
                  style="height: 35px"
                  class="list-group-item list-group-item-action"
                  >我的预约</router-link
                >
                <router-link
                  to="/student/info/update/"
                  style="height: 35px"
                  class="list-group-item list-group-item-action"
                  >修改资料</router-link
                >
                <router-link
                  to="/student/info/password/update"
                  style="height: 35px"
                  class="list-group-item list-group-item-action"
                  >修改密码</router-link
                >
              </div>
            </div>
          </div>
          <!-- 右侧内容 -->
          <div class="col-md-9">
            <div
              class="card"
              style="height: 460px; width: 700px; border-radius: 0%"
            >
              <div class="d-flex justify-content-center">
                <span style="color: red; font-weight: bold">修改资料</span>
              </div>
              <form
                @submit.prevent="updateStudentInfo"
                style="margin-top: 20px"
              >
                <div class="mb-3 row align-items-center">
                  <label
                    for="studentUsername"
                    class="col-sm-4 col-form-label text-end"
                  >
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
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentRealname"
                    class="col-sm-4 col-form-label text-end"
                  >
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
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentPhone"
                    class="col-sm-4 col-form-label text-end"
                  >
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
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentAddress"
                    class="col-sm-4 col-form-label text-end"
                  >
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
                  <button
                    type="submit"
                    class="btn btn-primary me-2"
                    style="width: 100px"
                  >
                    提交
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "@/components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
export default {
  components: {
    ContentField,
    NavBar,
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
    }

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
      })
    }

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
  
  