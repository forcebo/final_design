<template>
  <div class="d-flex justify-content-center align-items-center vh-100">
    <div class="card" style="width: 30rem; height: 18rem">
      <div class="card-body">
        <h5 class="card-title text-center">管理员登录</h5>
        <form @submit.prevent="adminLogin" style="margin-top: 50px">
          <div class="mb-3 row align-items-center">
            <label for="adminUsername" class="col-sm-3 col-form-label"
              >用户名:</label
            >
            <div class="col-sm-9">
              <input
                v-model="username"
                type="text"
                class="form-control"
                id="username"
                placeholder="请输入用户名或手机号"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="password" class="col-sm-3 col-form-label">密码:</label>
            <div class="col-sm-9">
              <input
                v-model="password"
                type="password"
                class="form-control"
                id="password"
                placeholder="请输入密码"
              />
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <div class="student-error-message text-danger">
              {{ error_message }}
            </div>
          </div>
          <div class="mb-3 d-flex justify-content-center">
            <button
              type="submit"
              class="btn btn-primary me-2"
              style="width: 100px"
            >
              登录
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script>

import { useStore } from "vuex";
import { ref } from "vue";
import router from "@/router/index";
export default {
  setup() {
    const store = useStore();
    let username = ref("");
    let password = ref("");
    let error_message = ref("");

    const adminLogin = () => {
      error_message.value = "";
      store.dispatch("adminLogin", {
        username: username.value,
        password: password.value,
        success(resp) {
          if (resp.success == false) {
            error_message.value = resp.errorMsg;
          } else if (resp.success == true) {
            store.dispatch("getAdminInfo", {
              success() {
                router.push({ name: "首页" });
                console.log(store.state.admin);
              },
            });
          }
        },
        error() {
          error_message.value = "用户名或密码错误";
        },
      });
      // 登录逻辑
    };
    return {
      username,
      password,
      error_message,
      adminLogin,
    };
  },
};
</script>
  
  <style scoped>
.vh-100 {
  height: 100vh;
}
</style>
  