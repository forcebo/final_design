<template>
  <NavBar />
  <ContentField>
    <div class="container justify-content-center" style="display: flex">
      <div class="card" style="border-radius: 0%; width: 700px">
        <div
          class="flex justify-content-center"
          style="
            background-color: rgb(253, 250, 198);
            border-radius: 0%;
            text-align: center;
          "
        >
          <span style="font-weight: bold">{{ tno }}号教员基本信息</span>
        </div>
        <table class="table">
          <tbody>
            <tr>
              <th scope="row" style="text-align: right">教员编号:</th>
              <td>{{ tno }}</td>
              <td rowspan="3" style="text-align: right; font-weight: bold">
                <br /><br />头像:
              </td>
              <td rowspan="3" style="width: 25%">
                <div class="text-center">
                  <br />
                  <img :src="photo" alt="" style="width: 80%" />
                </div>
              </td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">姓名:</th>
              <td>{{ realname }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">来自省份:</th>
              <td>{{ province }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">年龄:</th>
              <td>{{ age }}</td>
              <td style="text-align: right; font-weight: bold">高校:</td>
              <td>{{ school }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">学历:</th>
              <td>{{ education }}</td>
              <td style="text-align: right; font-weight: bold">目前身份:</td>
              <td>{{ identity }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">专业:</th>
              <td>{{ major }}</td>
              <td style="text-align: right; font-weight: bold">住址:</td>
              <td>{{ address }}</td>
            </tr>
          </tbody>
        </table>
        <div class="mb-3 d-flex" style="justify-content: flex-end">
          <button
            v-if="!ifReserve"
            type="button"
            class="btn btn-primary me-2"
            @click="reserveTo"
          >
            预约该教员
          </button>
          <button
            v-if="ifReserve"
            type="button"
            class="btn btn-primary me-2"
            @click="cancelReserve"
          >
            取消预约
          </button>
          <button type="button" class="btn btn-primary me-2" @click="buyCourse">
            购买课程
          </button>
        </div>
        <div
          class="flex justify-content-center"
          style="
            background-color: rgb(253, 250, 198);
            border-radius: 0%;
            text-align: center;
          "
        >
          <span style="font-weight: bold">家教信息</span>
        </div>
        <table class="table" style="width: 100%">
          <tbody>
            <tr>
              <th scope="row" style="text-align: right; width: 25%">
                自我描述特长展示:
              </th>
              <td colspan="3">
                {{ description }}
              </td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right; width: 25%">
                可授课区域:
              </th>
              <td style="width: 25%">{{ areas }}</td>
              <th style="text-align: right; width: 25%">可辅导方式:</th>
              <td style="width: 25%">{{ mode }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">所获证书:</th>
              <td colspan="3">{{ certificate }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">家教简历:</th>
              <td colspan="3">{{ studentEvaluate }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">薪水要求:</th>
              <td colspan="3">{{ salary }}</td>
            </tr>
            <tr>
              <th scope="row" style="text-align: right">可教授科目:</th>
              <td colspan="3">{{ subjects }}</td>
            </tr>
          </tbody>
        </table>
        <div
          class="flex justify-content-center"
          style="
            background-color: rgb(253, 250, 198);
            border-radius: 0%;
            text-align: center;
          "
        >
          <span style="font-weight: bold"
            >学员评价&nbsp;({{ total_records }})&nbsp;好评率</span
          >
          <span style="color: red">{{ goodRate }}</span>
        </div>
        <table class="table table-striped table-hover">
          <tbody>
            <tr v-for="comment in records" :key="comment.id">
              <td style="width: 100%; position: relative">
                <img
                  class="rounded-circle"
                  :src="comment.photo"
                  alt=""
                  style="width: 30px; height: 30px"
                />&nbsp;
                <span>{{ comment.username }}</span>
                <span style="position: absolute; right: 10px; color: red">{{
                  comment.isGood
                }}</span>
                <br />
                <span style="font-size: x-small">{{ comment.content }}</span>
                <span
                  style="font-size: x-small; position: absolute; right: 10px"
                  >{{ comment.time }}</span
                >
              </td>
            </tr>
          </tbody>
        </table>
        <nav aria-label="Page navigation example" style="margin-top: 10px">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#" @click="click_page(-2)">上一页</a>
            </li>
            <li
              @click="click_page(page.number)"
              :class="'page-item ' + page.is_active"
              v-for="page in pages"
              :key="page.number"
            >
              <a class="page-link" href="#">{{ page.number }}</a>
            </li>
            <li class="page-item" @click="click_page(-1)">
              <a class="page-link" href="#">下一页</a>
            </li>
          </ul>
        </nav>
        <div class="card mt-3">
          <div class="card-header">
            <strong>发表评论</strong>
          </div>
          <div class="card-body">
            <form @submit.prevent="submitComment">
              <div class="mb-3">
                <label for="commentContent" class="form-label">评论内容</label>
                <textarea
                  id="commentContent"
                  v-model="newCommentContent"
                  class="form-control"
                  rows="3"
                  required
                ></textarea>
              </div>
              <div class="mb-3 form-check">
                <input
                  type="checkbox"
                  v-model="isGoodComment"
                  class="form-check-input"
                  id="isGoodComment"
                />
                <label class="form-check-label" for="isGoodComment"
                  >是否好评</label
                >
              </div>
              <button
                type="submit"
                class="btn btn-primary"
                style="text-align: right"
              >
                提交评论
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "../components/ContentField.vue";
import { useStore } from "vuex";
import $ from "jquery";
import { ref } from "vue";
// import router from '@/router';
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
    let tno = ref("");
    let realname = ref("");
    let province = ref("");
    let sex = ref("");
    let photo = ref("");
    let age = ref("");
    let school = ref("");
    let education = ref("");
    let identity = ref("");
    let major = ref("");
    let address = ref("");
    
    let description = ref("");
    let areas = ref("");
    let mode = ref("");
    let certificate = ref("");
    let salary = ref("");
    let subjects = ref("");
    let studentEvaluate = ref("");
    let goodRate = ref("");
    let ifReserve = ref(false);

    let current_page = 1;
    let records = ref([]);
    let total_records = ref("");
    let pages = ref([]);

    let newCommentContent = ref("");
    let isGoodComment = ref(false); 
    const urlParams = new URLSearchParams(window.location.search);
    tno.value = urlParams.get('id');

    const getTeacherInfoById = () => {
      if (tno.value == null || tno.value < 0) {
        return;
      }
      $.ajax({
        url: "http://127.0.0.1:3000/student/get/teacher/info/" + tno.value + "/", // 修改为实际的后端接口地址
        type: "get",
        headers: {
          Authorization: "Bearer " + (store.state.student.token == "" ? store.state.teacher.token : store.state.student.token),
        },
        success(resp) {
          if (resp.success == true) {
            realname.value = resp.data.teacher.realname.charAt(0) + "教员";
            photo.value = resp.data.teacher.photo;
            sex.value = resp.data.teacher.sex;
            age.value = resp.data.teacher.age;
            school.value = resp.data.teacher.school;
            education.value = resp.data.teacher.education;
            
            identity.value = resp.data.teacher.identity.split('（')[0].trim();
            major.value = resp.data.teacher.major;
            address.value = resp.data.teacher.address;
            description.value = resp.data.cv.description;
            areas.value = resp.data.cv.areas;
            mode.value = resp.data.cv.mode;
            certificate.value = resp.data.cv.certificate;
            studentEvaluate.value = resp.data.cv.studentEvaluate;
            salary.value = resp.data.cv.salary;
            subjects.value = resp.data.cv.subjects;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    };

    const checkIfReserve = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/reserve/check/" + tno.value + "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            ifReserve.value = resp.data;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }
    checkIfReserve();

    const cancelReserve = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/reserve/cancel/" + tno.value + "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            ifReserve.value = resp.data;
            alert("取消预约成功!");
          }else {
            alert("取消预约失败");
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }

    const click_page = (page) => {
      if (page === -2) page = current_page - 1;
      if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_records.value / 10));

      if (page >= 1 && page <= max_pages) {
        getComments(page);
      }
    };

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_records.value / 10)); //返回大于等于参数x的最小整数,即对浮点数向上取整.
      let new_pages = [];
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page ? "active" : "",
          });
        }
        pages.value = new_pages;
      }
    };

    const getComments = (page) => {
      current_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/comment/get/" + tno.value + "/"+ page + "/", // 修改为实际的后端接口地址
        type: "get",
        headers: {
          Authorization: "Bearer " + (store.state.student.token == "" ? store.state.teacher.token : store.state.student.token),
        },
        success(resp) {
          if (resp.success == true) {
            records.value = resp.data.records;
            goodRate.value = resp.data.goodRate * 100 + "%";
            total_records.value = resp.data.total_records;
            update_pages();
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }

    const submitComment = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/comment/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          receiveId: tno.value,
          content: newCommentContent.value,
          isGood: isGoodComment.value? 1:0,
      }),
        success(resp) {
          if (resp.success == true) {
            alert("评论已提交，等待管理员审核");
            newCommentContent.value = "";
            isGoodComment.value = false;
          } else {
            alert("评论提交失败");
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    };

    const reserveTo = () => {
      window.open('/tutor/reserve/?id='+ tno.value + '&name=' + realname.value, '_blank');
    }

    const buyCourse = () => {
      window.open('/tutor/course/?id='+ tno.value + '&name=' + realname.value + '&photo=' + photo.value, '_blank');
    }

    getTeacherInfoById();
    getComments(current_page);
    return {
      tno,
      realname,
      sex,
      province,
      photo,
      age,
      school,
      education,
      identity,
      major,
      address,
      description,
      areas,
      mode,
      certificate,
      salary,
      subjects,
      studentEvaluate,
      goodRate,
      records,
      total_records,
      ifReserve,
      newCommentContent,
      isGoodComment,
      reserveTo,
      buyCourse,
      cancelReserve,
      submitComment,
      click_page,
      update_pages,
      pages,
    }

  }
};
</script>

<style scoped>
.table td,
.table th {
  border: 1px solid #dee2e6;
}
</style>

