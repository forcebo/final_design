<template>
  <NavBar />
  <ContentField>
    <div class="container justify-content-center" style="display: flex">
      <div class="card" style="border-radius: 0%; width: 700px">
        <div style="margin-top: 10px">
          <img
            :src="photo"
            style="width: 30px; height: 30px; margin-left: 10px"
          />
          <span style="margin-left: 10px">{{ teacherName }}</span>
        </div>
        <hr />

        <div class="row">
          <div class="col-md-4" v-for="course in courses" :key="course.id">
            <div class="card mb-4" style="cursor: pointer">
              <img
                :src="course.thumbnail"
                class="card-img-top"
                :alt="course.name"
                style="height: 180px;"
              />
              <div class="card-body">
                <h5 class="card-title">{{ course.name }}</h5>
                <p class="card-text">{{ course.description }}</p>
                <button
                  type="button"
                  class="btn btn-primary me-2"
                  @click="courseListen(course.id)"
                >
                试看视频
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import ContentField from "../components/ContentField.vue";
import NavBar from "@/components/NavBar.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
export default {
  components: {
    NavBar,
    ContentField,
  },

  setup() {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      const role = localStorage.getItem("role");
      if (role === "STUDENT") {
        store.commit("updateStudentToken", jwt_token);
        store.dispatch("getStudentInfo", {
          success() {
            store.commit("updateStudentPullingInfo", false);
          },
          error() {
            store.commit("updateStudentPullingInfo", false);
          },
        });
      } else if (role === "TEACHER") {
        store.commit("updateTeacherToken", jwt_token);
        store.dispatch("getTeacherInfo", {
          success() {
            store.commit("updateTeacherPullingInfo", false);
          },
          error() {
            store.commit("updateTeacherPullingInfo", false);
          },
        });
      }
    } else {
      store.commit("updateStudentPullingInfo", false);
      store.commit("updateTeacherPullingInfo", false);
    }
    const teacherName = ref("");
    const photo = ref("");
    const teacherId = ref("");
    const urlParams = new URLSearchParams(window.location.search);
    teacherName.value = urlParams.get("name");
    photo.value = urlParams.get("photo");
    teacherId.value = urlParams.get("id");
    const courses = ref([]);

    const getCourses = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/course/get/" + teacherId.value + "/", // 修改为实际的后端接口地址
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            courses.value = resp.data;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }

    getCourses();

    const courseListen = (id) => {
      window.open('/tutor/course/listen/?id='+ id, '_blank');
    }

    return {
      teacherName,
      photo,
      courses,
      courseListen
    };
  },
};
</script>
  
  <style scoped>
</style>
  
  