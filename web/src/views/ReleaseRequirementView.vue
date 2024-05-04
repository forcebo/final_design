<template>
  <NavBar />
  <ContentField>
    <div class="modal" tabindex="-1" id="exampleModal" v-if="showSuccess">
      <div class="modal-dialog" style="width: 400px">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">提示</h5>
            <button
              type="button"
              class="btn-close"
              @click="closeModal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <p>{{ modal_error_message }}</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">
              关闭
            </button>
            <button type="button" class="btn btn-primary" @click="closeModal">
              确定
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div
        class="card"
        style="height: 1100px; width: 1000px; border-radius: 0%"
      >
        <div class="row" style="margin-top: 10px">
          <!-- 左侧导航栏 -->
          <div class="col-md-8">
            <div
              class="card"
              style="height: 1050px; width: 650px; border-radius: 0%"
            >
              <div style="background-color: rgb(253, 250, 192)">
                <img
                  src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                  alt="图像"
                  style="margin-left: 10px"
                />
                <span style="font-weight: bold; margin-left: 10px"
                  >请 家 教 方 法!</span
                >
              </div>
              <div style="margin-top: 20px; margin-left: 20px; display: flex">
                <div
                  class="card"
                  style="
                    height: 100px;
                    width: 150px;
                    border-color: rgb(225, 173, 61);
                  "
                >
                  <strong style="font-size: x-small">1.自主填写订单：</strong>
                  <span style="font-size: x-small"
                    >请填写以下表单，为了更好的帮你找到合适的老师，以下<b
                      style="font-size: x-small; color: red"
                      >带星号为必填项.</b
                    ></span
                  >
                </div>
                <div
                  class="card"
                  style="
                    height: 100px;
                    width: 150px;
                    margin-left: 50px;
                    border-color: rgb(94, 205, 255);
                  "
                >
                  <strong style="font-size: x-small">2.在线预约：</strong>
                  <span style="font-size: x-small"
                    >您可以直接进入家教老师信息库挑选您满意的教员.</span
                  >
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
                  >请填写你的联系方式!</span
                >
              </div>
              <form @submit.prevent="releaseRequirement">
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentUsername"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span> 联系人
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="studentName"
                      type="text"
                      class="form-control"
                      id="studentUsername"
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
                    for="studentPhoneNumber"
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
                      id="studentPhoneNumber"
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
                    for="areaDetailDescription"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    授课地区详细描述
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="areaDetailDescription"
                      type="text"
                      class="form-control"
                      id="areaDetailDescription"
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
                        v-model="sex"
                        class="form-check-input"
                        type="radio"
                        name="sex"
                        value="男"
                      />
                      <label
                        class="form-check-label"
                        for="sex"
                        style="font-size: x-small"
                        >男</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-1">
                    <div class="form-check">
                      <input
                        v-model="sex"
                        class="form-check-input"
                        type="radio"
                        name="sex"
                        value="女"
                      />
                      <label
                        class="form-check-label"
                        for="sex"
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
                      v-model="grade"
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
                    for="studentClasstime"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    可上课时间
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="classtime"
                      type="text"
                      class="form-control"
                      id="studentClasstime"
                      style="height: 30px; font-size: x-small"
                    />
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="studentCondition"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    学员情况说明
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="condition"
                      type="text"
                      class="form-control"
                      id="studentCondition"
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
                    for="teacherSex"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    教员性别
                  </label>
                  <div class="col-sm-1">
                    <div class="form-check">
                      <input
                        v-model="teacherSex"
                        class="form-check-input"
                        type="radio"
                        name="teacherSex"
                        value="男"
                      />
                      <label
                        class="form-check-label"
                        for="teacherSex"
                        style="font-size: x-small"
                        >男</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-1">
                    <div class="form-check">
                      <input
                        v-model="teacherSex"
                        class="form-check-input"
                        type="radio"
                        name="teacherSex"
                        value="女"
                      />
                      <label
                        class="form-check-label"
                        for="teacherSex"
                        style="font-size: x-small"
                        >女</label
                      >
                    </div>
                  </div>
                  <div class="col-sm-2">
                    <div class="form-check">
                      <input
                        v-model="teacherSex"
                        class="form-check-input"
                        type="radio"
                        name="teacherSex"
                        value="均可"
                      />
                      <label
                        class="form-check-label"
                        for="teacherSex"
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
                    for="teacherRequirement"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    对教员其他要求
                  </label>
                  <div class="col-sm-4">
                    <input
                      v-model="requirement"
                      type="text"
                      class="form-control"
                      id="teacherRequirement"
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
                <div
                  class="d-flex justify-content-center"
                  style="margin-left: 100px; font-size: x-small;"
                >
                  <div class="error-message">{{ error_message }}</div>
                </div>
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
          <!-- 右侧内容 -->
          <div class="col-md-4">
            <div
              class="card"
              style="height: 200px; width: 300px; border-radius: 0%"
            >
              <div style="background-color: rgb(249, 232, 185)">
                <img
                  src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                  alt="图像"
                  style="margin-left: 10px"
                />
                <span style="font-weight: bold; margin-left: 10px"
                  >最新教员!</span
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import ContentField from "../components/ContentField.vue"
import { ref } from "vue"
import $ from "jquery"
import { useStore } from "vuex"
export default {
  components: {
    NavBar,
    ContentField,
  },

  setup() {
    const store = useStore();
    let studentName = ref("");
    let phone = ref("");
    let area = ref("");
    let areaDetailDescription = ref("");
    let sex = ref("");
    let grade = ref("");
    let subject = ref("");
    let classtime = ref("");
    let condition = ref("");
    let teacherSex = ref("");
    let mode = ref("");
    let requirement = ref("");
    let charge = ref("");
    let error_message = ref("");
    let showSuccess = ref(false);
    let modal_error_message = ref("");
    charge.value = "面议";

    const closeModal = () => {
      showSuccess.value = false;
      window.location.reload();
    }

    const releaseRequirement = () => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/student/release/requirement/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          studentName: studentName.value,
          phone: phone.value,
          area: area.value,
          areaDetailDescription: areaDetailDescription.value,
          sex: sex.value,
          grade: grade.value,
          subject: subject.value,
          classtime: classtime.value,
          condition: condition.value,
          teacherSex: teacherSex.value,
          mode: mode.value,
          requirement: requirement.value,
          charge: charge.value,
        }),
        success(resp) {
          if (resp.success == true) {
            modal_error_message.value = "发布需求成功，您可以在个人中心页面查看";
            showSuccess.value = true;
          } else if(resp.success == false){
            error_message.value = resp.errorMsg;
          }
          console.log(resp);
        },
        error(resp) {
          error_message.value = "未知错误，请重试！";
          console.log(resp);
        },
      })
    }

    return {
      studentName,
      phone,
      area,
      areaDetailDescription,
      sex,
      grade,
      subject,
      classtime,
      condition,
      teacherSex,
      mode,
      requirement,
      charge,
      error_message,
      releaseRequirement,
      showSuccess,
      closeModal,
      modal_error_message,
    };
  },
};
</script>

<style scoped>
/* 模态框居中显示 */
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 为模态框添加一些样式以保持居中 */
.modal-dialog {
  max-width: 80%;
}

/* 遮罩层样式 */
.modal-backdrop.show {
  opacity: 0.5;
}

div.error-message {
  font-weight: 700;
  color: red;
}
</style>

