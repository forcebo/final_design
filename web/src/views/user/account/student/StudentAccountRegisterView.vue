<template>
  <NavBar />
  <ContentField>
    <div class="row justify-content-center">
      <div class="card" style="height: 700px; width: 800px; border-radius: 0%">
        <div
          class="card"
          style="
            margin-top: 20px;
            border-radius: 0%;
            background-color: rgb(0, 161, 239);
          "
        >
          <div class="d-flex align-items-center">
            <img
              src="@/assets/1.png"
              alt="图像"
              style="height: 150px; width: 200px"
            />
            <span
              class="denglut"
              style="margin-top: 10px; margin-left: 10px; font-weight: 900"
              >欢迎你注册成为师生之家学员.<br />
              为了更好的找到适合您的家教老师，请仔细填写以下内容</span
            >
          </div>
        </div>
        <form @submit.prevent="register" style="width: 600px; margin-top: 20px">
          <div class="mb-3 row align-items-center">
            <label
              for="studentRegisterUsername"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>请输入用户名
            </label>
            <div class="col-sm-7">
              <input
                v-model="username"
                type="text"
                class="form-control"
                id="studentRegisterUsername"
                placeholder="请输入用户名"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentRegisterUsername"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>请输入密码
            </label>
            <div class="col-sm-7">
              <input
                v-model="password"
                type="password"
                class="form-control"
                id="studentRegisterPassword"
                placeholder="请输入密码"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentRegisterConfirmedPassword"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>请确认密码
            </label>
            <div class="col-sm-7">
              <input
                v-model="confirmedPassword"
                type="password"
                class="form-control"
                id="studentRegisterConfirmedPassword"
                placeholder="请确认密码"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentName"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>学员姓名
            </label>
            <div class="col-sm-7">
              <input
                v-model="name"
                type="text"
                class="form-control"
                id="studentName"
                placeholder="请输入真实姓名"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentSex"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>学员性别
            </label>
            <div class="col-sm-7">
              <input v-model="sex" class="form-check-input" type="radio" name="gender" id="male" value="男">
                <label class="form-check-label" for="male">
                  男
                </label>
                <input v-model="sex" class="form-check-input" type="radio" name="gender" id="female" value="女" style="margin-left: 10px;">
                <label class="form-check-label" for="female">
                  女
                </label>
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentPhone"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>手机号
            </label>
            <div class="col-sm-7">
              <input
                v-model="phone"
                type="text"
                class="form-control"
                id="studentPhone"
                placeholder="请输入手机号"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="studentAddress"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>通讯地址
            </label>
            <div class="col-sm-7">
              <input
                v-model="address"
                type="text"
                class="form-control"
                id="studentAddress"
                placeholder="请输入通讯地址"
              />
            </div>
          </div>
          <div class="d-flex justify-content-center" style="margin-left: 100px;">
            <div class="error-message">{{ error_message }}</div>
          </div>
          <div class="mb-3 d-flex justify-content-center" style="margin-left: 100px; margin-top: 15px;">
            <button
              type="submit"
              class="btn btn-primary me-2"
              style="width: 100px"
            >
              注册
            </button>
            <button type="reset" class="btn btn-primary" style="width: 100px">
              重置
            </button>
          </div>
        </form>
        <div>
              <router-link
                class="nav-link"
                :to="{ name: 'home' }"
                style="margin-left: auto; cursor: pointer"
                role="button"
              >
                &lt;&lt; 返回首页
              </router-link>
        </div>
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import ContentField from "@/components/ContentField.vue"
import { ref } from "vue"
//import { useStore } from "vuex"
import router from "@/router"
import $ from 'jquery'

export default {
  components: {
    ContentField,
  },

  setup() {
    //const store = useStore();
    let username = ref('');
    let password = ref('');
    let confirmedPassword = ref('');
    let name = ref('');
    let sex = ref('');
    let phone = ref('');
    let address = ref('');
    let error_message = ref('');


    const register = () => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/student/account/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
          realname: name.value,
          sex: sex.value,
          phone: phone.value,
          address: address.value,
        },
        success(resp) {
          if (resp.success == true) {
            router.push({name: "user_account_login"});
          } else {
            error_message.value = resp.errorMsg;
          }
          console.log(resp);
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    return {
      username,
      password,
      confirmedPassword,
      name,
      sex,
      phone,
      address,
      error_message,
      register,
    }
  }
};
</script>
  
<style scoped>
div.error-message {
  font-weight:700;
  color: red;
}
</style>
  
  