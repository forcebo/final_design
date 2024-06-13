<template>
  <div class="app-container">
    <nav class="navbar navbar-expand-lg navbar-dark bg">
      <div class="container-fluid">
        <div class="col-md-2" style="text-align: center">
          <a class="navbar-brand" href="#">{{ route_name }}</a>
        </div>
        <div
          class="collapse navbar-collapse"
          id="navbarTop"
          style="margin-right: 10px"
        >
          <ul class="navbar-nav ms-auto">
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
              <img
                  class="rounded-circle"
                  :src="$store.state.admin.photo"
                  alt=""
                  style="width: 35px; height: 35px;"
                />
              </a>
              <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="navbarDropdown"
              >
                <li><a class="dropdown-item" href="#">个人资料</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="#" @click="logout">退出登录</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid" style="margin-top: 20px">
      <div class="row">
        <div class="col-md-2">
          <div
            class="card"
            style="background-color: rgb(64, 179, 127); height: 700px"
          >
            <nav class="navbar navbar-expand-lg navbar-dark flex-column">
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav flex-column">
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '首页'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '首页' }"
                      >首页</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '用户管理'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '用户管理' }"
                      >用户管理</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '家教需求信息审核'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '家教需求信息审核' }"
                      >家教需求信息审核</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '求职信息审核'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '求职信息审核' }"
                      >求职信息审核</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '课程管理'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '课程管理' }"
                      >课程管理</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '订单管理'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '订单管理' }"
                      >订单管理</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '评价审核'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '评价审核' }"
                      >评价审核</router-link
                    >
                  </li>
                  <li class="nav-item">
                    <router-link
                      :class="
                        route_name == '用户信息统计分析'
                          ? 'nav-link active'
                          : 'nav-link'
                      "
                      :to="{ name: '用户信息统计分析' }"
                      >用户信息统计分析</router-link
                    >
                  </li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
        <div class="col-md-10">
          <div class="card" style="background-color: rgb(39, 41, 61)">
            <div class="card-body">
              <slot></slot>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div
      class="collapse position-fixed top-50 end-0 translate-middle-y"
      id="collapseExample"
      style="z-index: 1000"
    >
      <div
        class="card card-body bg-dark text-white"
        style="max-height: calc(100vh - 56px); overflow-y: auto"
      >
        <!-- 这里可以放一些其他的内容，例如链接或者菜单 -->
        <a href="#" class="text-white">链接1</a>
        <br />
        <a href="#" class="text-white">链接2</a>
      </div>
    </div>
  </div>
</template>
  
  <script>
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";
import router from "@/router";

export default {
  components: {},
  setup() {
    const store = useStore();
    const route = useRoute();
    let route_name = computed(() => route.name);

    const logout = () => {
      store.dispatch("adminLogout");
      router.push("/admin/login/");
    }

    return {
      route_name,
      logout,
    };
  },
};
</script>
  
  <style scoped>
.app-container {
  background-color: rgb(30, 30, 46); /* 黑色背景 */
  min-height: 100vh; /* 确保覆盖整个视口 */
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  font-family: Arial, sans-serif;
}
</style>
  