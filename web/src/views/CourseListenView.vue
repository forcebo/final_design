<template>
  <NavBar/>
  <ContentField>
    <div class="container mt-5">
      <h1>{{ course.name }}</h1>
      <video
        id="videoPlayer"
        controls
        autoplay
        class="w-100"
        @timeupdate="checkTime"
        :src="course.url"
      ></video>
      <p class="mt-3" style="text-align: right;">{{ course.description }}</p>
      <div v-if="!course.canWatchFull" style="text-align: right;">
        <p style="color: red; font-weight: bold">请购买后观看完整视频。</p>
        <button @click="purchaseCourse" class="btn btn-primary">购买视频</button>
      </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="purchaseModal" tabindex="-1" aria-labelledby="purchaseModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="purchaseModalLabel">观看限制</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            请购买后观看完整视频。
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" @click="purchaseCourse">购买视频</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Purchase Modal -->
    <div
      class="modal fade"
      id="paymentModal"
      tabindex="-1"
      aria-labelledby="paymentModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="paymentModalLabel">支付</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <p>确认支付¥{{ course.price }}以购买视频。</p>
            <button class="btn btn-success" @click="processPayment">确认支付</button>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              关闭
            </button>
          </div>
        </div>
      </div>
    </div>
  </ContentField>

  
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import ContentField from "../components/ContentField.vue"
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
import { Modal } from "bootstrap";
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
    const urlParams = new URLSearchParams(window.location.search);
    const courseId = ref("");
    courseId.value = urlParams.get("id");
    // 模拟从服务器获取视频信息
    const course = ref({
      id: '',
      teacherId: '',
      name: '',
      description: '',
      url: '',
      price: '',
      canWatchFull: false,
    });

    const getCourseById = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/course/get/" + courseId.value + "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + (store.state.student.token == "" ? store.state.teacher.token : store.state.student.token),
        },
        success(resp) {
          if (resp.success == true) {
            course.value.id = resp.data.id;
            course.value.name = resp.data.name;
            course.value.description = resp.data.description;
            course.value.url = resp.data.videoUrl;
            course.value.price = resp.data.price;
            course.value.teacherId = resp.data.teacherId;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }

    getCourseById();

    const showModal = (modalId) => {
      const modalElement = document.getElementById(modalId);
      const modal = new Modal(modalElement);
      modal.show();
    };

    const checkTime = () => {
      const videoElement = document.getElementById("videoPlayer");
      if (!course.value.canWatchFull && videoElement.currentTime >= 60) {
        videoElement.currentTime = 59;
        videoElement.pause();
        setTimeout(() => showModal("purchaseModal"), 200); // 确保模态对话框正确显示
      }
    };

    const purchaseCourse = () => {
      const purchaseModalElement = document.getElementById("purchaseModal");
      const purchaseModal = Modal.getInstance(purchaseModalElement);
      if (purchaseModal) purchaseModal.hide();
      showModal("paymentModal");
    };

    const checkIsPay = () => {
      if (course.value.teacherId == store.state.teacher.id) {
        course.value.canWatchFull = true;
        return;
      }
      $.ajax({
        url: "http://127.0.0.1:3000/student/course/order/check/" + courseId.value + "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            if (resp.data == true) {
              course.value.canWatchFull = true;
            }
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }

    checkIsPay();

    const processPayment = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/course/order/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          teacherId: course.value.teacherId,
          courseId: course.value.id,
          price: course.value.price,
        }),
        success(resp) {
          if (resp.success == true) {
            console.log(resp);
            alert("支付成功！");
            course.value.canWatchFull = true;
            const modalElement = document.getElementById("paymentModal");
            const modal = Modal.getInstance(modalElement);
            modal.hide();
          }
        },
        error(resp) {
          console.log(resp);
        },
      })
    }

    
    return {
      course,
      checkTime,
      purchaseCourse,
      processPayment,
    }
  }
};
</script>

<style scoped>

</style>

