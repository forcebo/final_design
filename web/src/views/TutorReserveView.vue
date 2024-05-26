<template>
  <NavBar />
  <ContentField>
    <div class="container justify-content-center" style="display: flex">
      <div class="card" style="border-radius: 0%; width: 700px">
        <div
          class="flex justify-content-center"
          style="background-color: rgb(253, 250, 198); border-radius: 0%"
        >
          <img
            src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
            alt="图像"
            style="margin-left: 10px"
          />
          <span style="font-weight: bold; margin-left: 10px"
            >预约上门授课</span
          >
        </div>
        <div
          style="text-align: center; color: darkgray; font-weight: bold"
        >
          <span>你将要预约的</span>
          <span style="color: red;">{{ teacherName }} </span>
          <span>编号是</span>
          <span style="color: red;">{{ teacherId }}</span>
          <br>
          <span>请填写以下详细内容，我们会尽早联系你</span>
        </div>
        <div
                style="
                  background-color: rgb(216, 247, 245);
                  margin-right: 10px;
                  margin-left: 10px;
                  margin-top: 20px;
                "
              >
                <img
                  src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                  alt="图像"
                  style="margin-left: 10px"
                />
                <span style="font-weight: 600; margin-left: 10px"
                  >请填写你的联系方式!</span
                >
              </div>
        <form @submit.prevent="reserveTeacher">
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="name"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span> 联系人
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="name"
                      type="text"
                      class="form-control"
                      id="name"
                      placeholder="请输入联系人"
                      style="height: 30px;font-size: x-small"
                    />
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="phone"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    联系电话
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="phone"
                      type="text"
                      class="form-control"
                      id="phone"
                      placeholder="请输入联系电话"
                      style="height: 30px;font-size: x-small"
                    />
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="area"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    你所在的位置地址
                  </label>
                  <div class="col-sm-4">
                    <select
                      v-model="area"
                      class="form-select"
                      id="area"
                      style="height: 30px; font-size: x-small"
                    >
                      <option value="" selected disabled>请选择</option>
                      <option value="莲湖区">莲湖区</option>
                      <option value="新城区">新城区</option>
                      <option value="碑林区">碑林区</option>
                      <option value="雁塔区">雁塔区</option>
                      <option value="灞桥区">灞桥区</option>
                      <option value="未央区">未央区</option>
                      <option value="阎良区">阎良区</option>
                      <option value="临潼区">临潼区</option>
                      <option value="长安区">长安区</option>
                      <option value="高陵区">高陵区</option>
                      <option value="蓝田区">蓝田区</option>
                      <option value="户县区">户县区</option>
                      <option value="周至区">周至区</option>
                      <option value="高新区">高新区</option>
                      <option value="沣渭新区">沣渭新区</option>
                      <option value="曲江新区">曲江新区</option>
                      <option value="杨凌">杨凌</option>
                    </select>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="areaDetail"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    授课地区详细描述
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="areaDetail"
                      type="text"
                      class="form-control"
                      id="areaDetail"
                      style="height: 30px;font-size: x-small"
                    />
                  </div>
                  <div class="col-sm-4">
                    <div
                      class="card"
                      style="
                        border-radius: 0%;
                        background-color: rgb(255, 249, 237);
                      "
                    >
                      <span style="font-size: xx-small"
                        >请具体到居住的街道、小区，方便老师查询上课距离<strong
                          >（重要）</strong
                        ></span
                      >
                    </div>
                  </div>
                </div>
                <div
                  style="
                    background-color: rgb(216, 247, 245);
                    margin-right: 10px;
                    margin-left: 10px;
                    margin-top: 20px;
                  "
                >
                  <img
                    src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                    alt="图像"
                    style="margin-left: 10px"
                  />
                  <span style="font-weight: 600; margin-left: 10px"
                    >请填写学员的基本信息!</span
                  >
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentSex"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    学员性别
                  </label>
                  <div class="col-sm-1">
                    <div class="form-check">
                      <input
                        v-model="studentSex"
                        class="form-check-input"
                        type="radio"
                        name="studentSex"
                        value="男"
                      />
                      <label
                        class="form-check-label"
                        for="studentSex"
                        style="font-size: x-small"
                        >男</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-1">
                    <div class="form-check">
                      <input
                        v-model="studentSex"
                        class="form-check-input"
                        type="radio"
                        name="studentSex"
                        value="女"
                      />
                      <label
                        class="form-check-label"
                        for="studentSex"
                        style="font-size: x-small"
                        >女</label
                      >
                    </div>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentGrade"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    学生年级
                  </label>
                  <div class="col-sm-4">
                    <select
                      v-model="studentGrade"
                      class="form-select"
                      id="studentGrade"
                      style="height: 30px; font-size: x-small"
                    >
                      <option value="" selected disabled>请选择</option>
                      <option value="幼儿">幼儿</option>
                      <option value="一年级">一年级</option>
                      <option value="二年级">二年级</option>
                      <option value="三年级">三年级</option>
                      <option value="四年级">四年级</option>
                      <option value="五年级">五年级</option>
                      <option value="六年级">六年级</option>
                      <option value="初一">初一</option>
                      <option value="初二">初二</option>
                      <option value="初三">初三</option>
                      <option value="高一">高一</option>
                      <option value="高二">高二</option>
                      <option value="高三">高三</option>
                      <option value="自考生">自考生</option>
                      <option value="大一">大一</option>
                      <option value="大二">大二</option>
                      <option value="大三">大三</option>
                      <option value="大四">大四</option>
                      <option value="社会人">社会人</option>
                      <option value="外国人">外国人</option>
                    </select>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentSubject"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    求教科目
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="subject"
                      type="text"
                      class="form-control"
                      id="studentSubject"
                      placeholder="请输入求教科目"
                      style="height: 30px; font-size: x-small"
                    />
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="classTime"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    可上课时间
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="classTime"
                      type="text"
                      class="form-control"
                      id="classTime"
                      style="height: 30px; font-size: x-small"
                    />
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentDetail"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    学员情况说明
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="studentDetail"
                      type="text"
                      class="form-control"
                      id="studentDetail"
                      style="height: 60px; font-size: x-small"
                    />
                  </div>
                  <div class="col-sm-4">
                    <div
                      class="card"
                      style="
                        border-radius: 0%;
                        background-color: rgb(255, 249, 237);
                      "
                    >
                      <span style="font-size: xx-small"
                        >请详细说明学员基础、学习状况、性格等便于有针对性地找到合适的教员</span
                      >
                    </div>
                  </div>
                </div>
                <div
                  style="
                    background-color: rgb(216, 247, 245);
                    margin-right: 10px;
                    margin-left: 10px;
                    margin-top: 20px;
                  "
                >
                  <img
                    src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                    alt="图像"
                    style="margin-left: 10px"
                  />
                  <span style="font-weight: 600; margin-left: 10px"
                    >请填写你对教员的要求！</span
                  >
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="mode"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    辅导方式
                  </label>
                  <div class="col-sm-2">
                    <div class="form-check">
                      <input
                        v-model="mode"
                        class="form-check-input"
                        type="radio"
                        name="mode"
                        value="上门辅导"
                      />
                      <label
                        class="form-check-label"
                        for="mode"
                        style="font-size: x-small"
                        >上门辅导</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-2">
                    <div class="form-check">
                      <input
                        v-model="mode"
                        class="form-check-input"
                        type="radio"
                        name="mode"
                        value="网络辅导"
                      />
                      <label
                        class="form-check-label"
                        for="mode"
                        style="font-size: x-small"
                        >网络辅导</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-2">
                    <div class="form-check">
                      <input
                        v-model="mode"
                        class="form-check-input"
                        type="radio"
                        name="mode"
                        value="均可"
                      />
                      <label
                        class="form-check-label"
                        for="mode"
                        style="font-size: x-small"
                        >均可</label
                      >
                    </div>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="teacherRequire"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    对教员其他要求
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="teacherRequire"
                      type="text"
                      class="form-control"
                      id="teacherRequire"
                      style="height: 60px; font-size: x-small"
                    />
                  </div>
                  <div class="col-sm-4">
                    <div
                      class="card"
                      style="
                        border-radius: 0%;
                        background-color: rgb(255, 249, 237);
                      "
                    >
                      <span style="font-size: xx-small"
                        >如对教员的学历，教学经历，性格等要求</span
                      >
                    </div>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="charge"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    收费要求
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="charge"
                      type="text"
                      class="form-control"
                      id="charge"
                      style="height: 30px; font-size: x-small"
                    />
                  </div>
                </div>
                <!-- <div
                  class="d-flex justify-content-center"
                  style="margin-left: 100px; font-size: x-small;"
                >
                  <div class="error-message">{{ error_message }}</div>
                </div> -->
                <div
                  class="mb-3 d-flex justify-content-center"
                  style="margin-left: 100px; margin-top: 15px"
                >
                  <button
                    type="submit"
                    class="btn btn-primary me-2"
                    style="width: 60px; height: 30px; font-size: x-small"
                  >
                    提交
                  </button>
                  <button
                    type="reset"
                    class="btn btn-primary"
                    style="width: 60px; height: 30px; font-size: x-small"
                  >
                    重置
                  </button>
                </div>
              </form>
      </div>
    </div>
    <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="successModalLabel">提示</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            预约成功！
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确定</button>
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
import router from "@/router";
import { Modal } from 'bootstrap';
export default {
  components: {
    ContentField,
    NavBar,
  },

  setup() {
    const store = useStore();
    let teacherName = ref("");
    let teacherId = ref("");
    let name = ref("");
    let phone = ref("");
    let area = ref("");
    let areaDetail = ref("");
    let studentSex = ref("");
    let studentGrade = ref("");
    let subject = ref("");
    let classTime = ref("");
    let studentDetail = ref("");
    let mode = ref("");
    let teacherRequire = ref("");
    let charge = ref("");
    charge.value = "面议";
    const urlParams = new URLSearchParams(window.location.search);
    teacherId.value = urlParams.get("id");
    teacherName.value = urlParams.get("name");



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
    const reserveTeacher = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/reserve/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          teacherId: teacherId.value,
          name: name.value,
          phone: phone.value,
          area: area.value,
          areaDetail: areaDetail.value,
          studentSex: studentSex.value,
          studentGrade: studentGrade.value,
          subject: subject.value,
          classTime: classTime.value,
          studentDetail: studentDetail.value,
          mode: mode.value,
          teacherRequire: teacherRequire.value,
          charge: charge.value,
        }),
        success(resp) {
          if (resp.success == true) {
            console.log(resp);
            const modalElement = document.getElementById('successModal');
            const modal = new Modal(modalElement);
            modal.show();
            modalElement.addEventListener('hidden.bs.modal', () => {
              router.push({path: '/tutor/detail/', query: { id: teacherId.value }});
            });
          } else {
            alert("预约失败，" + resp.errorMsg);
          }
        },
        error(resp) {
          console.log(resp);
        },
      })
    }
    return {
      teacherId,
      teacherName,
      name,
      phone,
      area,
      areaDetail,
      studentSex,
      studentGrade,
      subject,
      classTime,
      studentDetail,
      teacherRequire,
      mode,
      charge,
      reserveTeacher,
    }
  },
};
</script>
  
  <style scoped>
</style>
  
  