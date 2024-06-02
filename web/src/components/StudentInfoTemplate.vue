<template>
    <NavBar />
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
                <div class="text-center" style="margin-bottom: 20px">
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
                style="
                  height: 200px;
                  width: 200px;
                  border-radius: 0%;
                  margin-top: 10px;
                "
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
                    to="/student/info/course/orders/"
                    style="height: 35px"
                    class="list-group-item list-group-item-action"
                    aria-current="true"
                  >
                    我的课程订单
                  </router-link>
                  <router-link
                    to="/student/info/reserves/"
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
                    to="/student/info/password/update/"
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
                style="width: 700px; border-radius: 0%"
              >
                <slot></slot>
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
  import $ from "jquery";
  export default {
    components: {
      NavBar,
      ContentField,
    },
  
    setup() {
      const store = useStore();
      const handleAvatarChange = (event) => {
        const file = event.target.files[0];
        const formData = new FormData();
        formData.append("avatar", file);
        // console.log(formData);
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
  
            }
          },
          error(resp) {
            console.error("文件上传失败:", resp);
          },
        });
      };
      return {
        handleAvatarChange,
      }
    }
  };
  </script>
      
      <style scoped>
  </style>
      
      