<template>
  <ContentField v-if="!$store.state.student.pulling_info">
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div
          class="card"
          style="
            width: 500px;
            height: 350px;
            margin-top: 100px;
            border-radius: 0;
          "
        >
          <div
            class="card"
            style="
              width: 480px;
              height: 110px;
              margin-top: 10px;
              border-radius: 0;
            "
          >
            <div class="d-flex align-items-center">
              <img src="@/assets/logo.png" alt="图像" style="width: 50px" />
              <span
                class="denglut"
                style="
                  margin-top: 10px;
                  margin-left: 10px;
                  color: red;
                  font-weight: 900;
                "
                >欢迎你登录师生之家</span
              >
            </div>
            <!-- 选项卡 -->
            <ul
              class="nav nav-tabs justify-content-center mb-3"
              id="loginTabs"
              role="tablist"
              style="margin-top: 20px; cursor: pointer"
              @click="clearErrorMessage"
            >
              <li class="nav-item" role="presentation">
                <a
                  class="nav-link active"
                  id="teacher-tab"
                  data-bs-toggle="tab"
                  data-bs-target="#teacherLogin"
                  role="tab"
                  aria-controls="teacherLogin"
                  aria-selected="true"
                  >教员登录</a
                >
              </li>
              <li class="nav-item" role="presentation">
                <a
                  class="nav-link"
                  id="student-tab"
                  data-bs-toggle="tab"
                  data-bs-target="#studentLogin"
                  role="tab"
                  aria-controls="studentLogin"
                  aria-selected="false"
                  >学员登录</a
                >
              </li>
            </ul>
          </div>
          <div class="card-body">
            <!-- 表单 -->
            <div class="tab-content">
              <!-- 教员登录表单 -->
              <div
                class="tab-pane fade show active"
                id="teacherLogin"
                role="tabpanel"
                aria-labelledby="teacher-tab"
              >
                <form @submit.prevent="teacherLogin">
                  <div class="mb-3 row align-items-center">
                    <label for="teacherUsername" class="col-sm-2 col-form-label"
                      >用户名</label
                    >
                    <div class="col-sm-9">
                      <input
                      v-model="teacherUsername"
                        type="text"
                        class="form-control"
                        id="teacherUsername"
                        placeholder="请输入用户名或手机号"
                      />
                    </div>
                  </div>
                  <div class="mb-3 row align-items-center">
                    <label for="teacherPassword" class="col-sm-2 col-form-label"
                      >密码</label
                    >
                    <div class="col-sm-9">
                      <input
                      v-model="teacherPassword"
                        type="password"
                        class="form-control"
                        id="teacherPassword"
                        placeholder="请输入密码"
                      />
                    </div>
                  </div>
                  <div class="d-flex justify-content-center">
                    <div class="teacher-error-message">{{ error_message }}</div>
                  </div>
                  <div class="mb-3 d-flex justify-content-center">
                    <button
                      type="submit"
                      class="btn btn-primary me-2"
                      style="width: 100px"
                    >
                      登录
                    </button>
                    <button
                      type="submit"
                      class="btn btn-primary"
                      style="width: 100px"
                      @click="$router.push('/teacher/account/register/')"
                    >
                      注册教员
                    </button>
                  </div>
                </form>
              </div>
              <!-- 学员登录表单 -->
              <div
                class="tab-pane fade"
                id="studentLogin"
                role="tabpanel"
                aria-labelledby="student-tab"
              >
                <form @submit.prevent="studentLogin">
                  <div class="mb-3 row align-items-center">
                    <label for="studentUsername" class="col-sm-2 col-form-label"
                      >用户名</label
                    >
                    <div class="col-sm-9">
                      <input
                        v-model="studentUsername"
                        type="text"
                        class="form-control"
                        id="studentUsername"
                        placeholder="请输入用户名或手机号"
                      />
                    </div>
                  </div>
                  <div class="mb-3 row align-items-center">
                    <label for="studentPassword" class="col-sm-2 col-form-label"
                      >密码</label
                    >
                    <div class="col-sm-9">
                      <input
                        v-model="studentPassword"
                        type="password"
                        class="form-control"
                        id="studentPassword"
                        placeholder="请输入密码"
                      />
                    </div>
                  </div>
                  <div class="d-flex justify-content-center">
                    <div class="student-error-message">{{ error_message }}</div>
                  </div>
                  <div class="mb-3 d-flex justify-content-center">
                    <button
                      type="submit"
                      class="btn btn-primary me-2"
                      style="width: 100px"
                    >
                      登录
                    </button>
                    <button
                      type="submit"
                      class="btn btn-primary"
                      style="width: 100px"
                      @click="$router.push('/student/account/register/')"
                    >
                      注册学员
                    </button>
                  </div>
                </form>
              </div>
            </div>
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
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import ContentField from "@/components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import router from "@/router/index";

export default {
  components: {
    ContentField,
  },

  mounted() {
    // 设置默认选项卡为教员登录
    //document.getElementById("teacher-tab").click();
  },
  setup() {
    const store = useStore();
    let teacherUsername = ref("");
    let teacherPassword = ref("");
    let studentUsername = ref("");
    let studentPassword = ref("");
    let error_message = ref("");

    const clearErrorMessage = () => {
      error_message.value = ""; // 将error_message的值设置为空
    };

    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      const role = localStorage.getItem("role");
      if (role === "STUDENT") {
        store.commit("updateStudentToken", jwt_token);
        store.dispatch("getStudentInfo", {
          success() {
            router.push({name: "home"});
            store.commit("updateStudentPullingInfo", false);
          },
          error() {
            store.commit("updateStudentPullingInfo", false);
          }
        })
      } else if (role === "TEACHER") {
        store.commit("updateTeacherToken", jwt_token);
        store.dispatch("getTeacherInfo", {
          success() {
            router.push({name: "home"});
            store.commit("updateTeacherPullingInfo", false);
          },
          error() {
            store.commit("updateTeacherPullingInfo", false);
          }
        })
      }
    } else {
      store.commit("updateStudentPullingInfo", false);
      store.commit("updateTeacherPullingInfo", false);
      
    }

    const teacherLogin = () => {
      error_message.value = "";
      store.dispatch("teacherLogin", {
        username: teacherUsername.value,
        password: teacherPassword.value,
        success(resp) {
          if (resp.success == false) {
            error_message.value = resp.errorMsg;
          } else if (resp.success == true) {
            console.log(resp);
            store.dispatch("getTeacherInfo", {
              success() {
                router.push({ name: "home" });
                console.log(store.state.Teacher);
              },
            });
          }
        },
        error() {
          error_message.value = "用户名或密码错误";
        },
      });
    }

    const studentLogin = () => {
      error_message.value = "";
      store.dispatch("studentLogin", {
        username: studentUsername.value,
        password: studentPassword.value,
        success(resp) {
          if (resp.success == false) {
            error_message.value = resp.errorMsg;
          } else if (resp.success == true) {
            store.dispatch("getStudentInfo", {
              success() {
                router.push({ name: "home" });
                console.log(store.state.student);
              },
            });
          }
        },
        error() {
          error_message.value = "用户名或密码错误";
        },
      });
    };

    return {
      teacherUsername,
      teacherPassword,
      studentUsername,
      studentPassword,
      error_message,
      teacherLogin,
      studentLogin,
      clearErrorMessage,
    };
  },
};
</script>
  
<style scoped>
div.teacher-error-message {
  font-weight: 700;
  color: red;
}

div.student-error-message {
  font-weight: 700;
  color: red;
}
</style>
  