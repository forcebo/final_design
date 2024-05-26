<template>
  <div class="row">
    <span style="font-size: larger; text-align: center">管理员中心</span>
  </div>
  <div style="margin-top: 20px;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-3">
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav flex-column">
                <li class="nav-item">
                  <a class="nav-link" href="#">首页</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">用户管理</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">课程管理</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">订单管理</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">评价审核</a>
                </li>
              </ul>
            </div>
          </nav>
        </div>
        <div class="col-md-9">
          <div class="card">
            <div class="card-body">
              <!-- 动态内容区域，可以使用 router-view 来切换不同的管理页面 -->
              <router-view></router-view>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {
  components: {},
  setup() {},
};
</script>
  
  <style scoped>
</style>
  