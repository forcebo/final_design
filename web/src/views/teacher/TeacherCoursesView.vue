<template>
  <TeacherInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold;">我的课程</span>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-md-4" v-for="course in courses" :key="course.id">
          <div class="card mb-4" style="cursor: pointer">
            <img
              :src="course.thumbnail"
              class="card-img-top"
              :alt="course.name"
              style="height: 200px;"
            />
            <div class="card-body">
              <h5 class="card-title">{{ course.name }}</h5>
              <p class="card-text">{{ course.description }}</p>
              <button
                type="button"
                class="btn btn-primary me-2"
                @click="courseDetail(course.id)"
              >
                查看详情
              </button>
            </div>
          </div>
        </div>
      </div>
  </TeacherInfoTemplate>
</template>
    
    <script>
import TeacherInfoTemplate from "@/components/TeacherInfoTemplate.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";

export default {
  components: {
    TeacherInfoTemplate,
  },

  setup() {
    const store = useStore();
    let courses = ref([]);
    const getCourses = () => {
      $.ajax({
        url:
          "http://127.0.0.1:3000/teacher/course/get/" + store.state.teacher.id + "/", // 修改为实际的后端接口地址
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
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
    };

    getCourses();
    
    const courseDetail = (id) => {
      window.open('/tutor/course/listen/?id='+ id, '_blank');
    }


    return {
      courses,
      courseDetail
    }
  },
};
</script>
    
    <style scoped>
</style>
    
    