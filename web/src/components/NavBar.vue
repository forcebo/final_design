<template>
  <div class="topT">
    <div
      class="container d-flex align-items-center justify-content-between"
      style="width: 70vw"
    >
      <div class="d-flex align-items-center">
        <h1 class="topTl">
          <a href="https://www.eduease.com/" title="师生之家-北京家教网">
            <img
              src="@/assets/logo.png"
              width="150"
              height="100"
              alt="师生之家-北京家教"
              class="me-2"
            />
            <span></span>
          </a>
        </h1>
        <div class="topTc">
          <div class="topTcT">
            <div class="topTct_left">
              当前城市：<strong
                style="font-size: 14px; font-weight: bold; color: #000"
                >西安</strong
              >
              [<a
                class="changecity"
                href="https://www.eduease.com/changecity.php"
                >切换其它城市</a
              >]<strong class="red">西安家教</strong>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <nav class="navbar navbar-expand-lg navbar-light navbar-height">
    <div class="container" style="width: 70vw">
      <!-- <a class="navbar-brand" href="#">师生之家</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarText"
        aria-controls="navbarText"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button> -->
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link
              :class="
                route_name == 'home_index' ? 'nav-link active' : 'nav-link'
              "
              :to="{ name: 'home_index' }"
              >首页</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              :class="
                route_name == 'tutor_search_index' || route_name == 'tutor_detail_index'
                || route_name == 'tutor_course_index' || route_name == 'tutor_course_listen_index'
                || route_name == 'tutor_reserve_index'
                  ? 'nav-link active'
                  : 'nav-link'
              "
              :to="{ name: 'tutor_search_index' }"
              >家教搜索</router-link
            >
          </li>
          
          <!-- <li class="nav-item">
            <router-link
              :class="
                route_name == 'course_listen_index'
                  ? 'nav-link active'
                  : 'nav-link'
              "
              :to="{ name: 'course_listen_index' }"
              >课程试听</router-link
            >
          </li> -->
          <li class="nav-item" v-if="$store.state.teacher.is_login & !$store.state.teacher.release_job_information">
            <router-link
              :class="
                route_name == 'teacher_release_job_information_index'
                  ? 'nav-link active'
                  : 'nav-link'
              "
              :to="{ name: 'teacher_release_job_information_index' }"
              >发布求职信息</router-link
            >
          </li>
          <li class="nav-item" v-if="$store.state.teacher.is_login & $store.state.teacher.release_job_information">
            <router-link
              :class="
                route_name == 'teacher_update_job_information_index'
                  ? 'nav-link active'
                  : 'nav-link'
              "
              :to="{ name: 'teacher_update_job_information_index' }"
              >更新求职信息</router-link
            >
          </li>
          <li class="nav-item" v-if="$store.state.student.is_login">
            <router-link
              :class="
                route_name == 'release_requirement_index'
                  ? 'nav-link active'
                  : 'nav-link'
              "
              :to="{ name: 'release_requirement_index' }"
              >发布家教需求信息</router-link
            >
          </li>
        </ul>
        <ul class="navbar-nav" v-if="$store.state.student.is_login">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              @click.prevent
            >
            <img
            class="rounded-circle"
                  :src="$store.state.student.photo"
                  alt=""
                  style="width: 30px; height: 30px;"
                />
              <!-- {{ $store.state.student.username }} -->
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'student_info_index' }"
                  >个人信息</router-link
                >
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else-if="$store.state.teacher.is_login">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              @click.prevent
            >
            <img
            class="rounded-circle"
                  :src="$store.state.teacher.photo"
                  alt=""
                  style="width: 30px; height: 30px;"
                />
              <!-- {{ $store.state.student.username }} -->
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'teacher_info_index' }"
                  >个人信息</router-link
                >
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else-if="!$store.state.student.pulling_info | !$store.state.teacher.pulling_info">
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{name: 'user_account_login'}"
              role="button"
            >
              登录
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{name: 'user_account_register'}"
              role="button"
            >
              注册
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    let route_name = computed(() => route.name);

    const logout = () => {
      store.dispatch("studentLogout");
      store.dispatch("teacherLogout");
    }

    return {
      route_name,
      logout,
    };
  },
};
</script>
    
<style scoped>
.navbar-height {
  height: 30px; /* 设置导航栏的高度 */
  background-color: rgb(200,59,40);
}
</style>