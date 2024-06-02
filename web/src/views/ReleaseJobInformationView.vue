<template>
  <NavBar />
  <ContentField>
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="align-items: center; justify-content: center;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">提示</h5>
        <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>{{ modal_error_message }}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" @click="closeModal">关闭</button>
      </div>
    </div>
  </div>
</div>
    <div class="row justify-content-center">
      <div
        class="card"
        style="border-radius: 0%"
      >
        <div class="row" style="margin-top: 10px">
          <div class="col-md-12">
            <div
              class="card"
              style="border-radius: 0%"
            >
              <div style="background-color: rgb(247, 251, 254)">
                <img
                  src="https://xa.eduease.com/beijingjiajiao/i/d10.gif"
                  alt="图像"
                  style="margin-left: 10px"
                />
                <span style="color: red; font-weight: 700; margin-left: 10px"
                  >家教求职步骤: </span
                ><span style=""
                  >1.填写简历&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.提交简历&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.发布求职信息成功.</span
                >
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
                  src="@/assets/images/zcimg3.png"
                  alt="图像"
                  style="margin-left: 10px"
                />
                <span style="font-weight: 600; margin-left: 10px"
                  >请填写下面的详细信息!</span
                >
              </div>
              <form @submit.prevent="releaseJobInformation">
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="subjects"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    可教授科目
                  </label>
                  <div class="col-sm-4">
                    <button
                      type="button"
                      class="btn btn-primary"
                      data-bs-toggle="modal" data-bs-target="#myModal"
                      id="subjects"
                    >
                      选择科目
                    </button>
                  </div>
                  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="myModalLabel">选择科目</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          <div class="kmlist">              
                            <p class="kmt01">--------------小学--------------</p>                
                            <p><label><input type="checkbox" name="select_kemu" value="小学语文">小学语文</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="小学数学">小学数学</label></p> 
                            <p><label><input type="checkbox" name="select_kemu" value="小学英语" >小学英语</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="小学奥数" >小学奥数</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="小学陪读" >小学陪读</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="小学作文" >小学作文</label></p>  
                            <!-- 继续添加其他小学科目的复选框 -->
                            <p class="kmt01">--------------初中--------------</p>
                            <p><label><input type="checkbox" name="select_kemu" value="初中全科">初中全科</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初中数理化">初中数理化</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初中历史">初中历史</label></p>   
                            <p><label><input type="checkbox" name="select_kemu" value="初中地理" >初中地理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初中生物" >初中生物</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初中政治" >初中政治</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初一初二语文" >初一初二语文</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初一初二英语" >初一初二英语</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初一初二数学" >初一初二数学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初一初二物理" >初一初二物理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初一初二化学" >初一初二化学</label></p>                
                            <p><label><input type="checkbox" name="select_kemu" value="初三语文" >初三语文</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初三英语" >初三英语</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初三数学" >初三数学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初三物理" >初三物理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初三化学" >初三化学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="初中奥数" >初中奥数</label></p>  
                            <!-- 继续添加其他初中科目的复选框 -->
                            <p class="kmt01">--------------高中--------------</p>
                            <p><label><input type="checkbox" name="select_kemu" value="高中全科" >高中全科</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中文综" >高中文综</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中数理化" >高中数理化</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中历史" >高中历史</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中地理" >高中地理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中政治" >高中政治</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中生物" >高中生物</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高一高二语文" >高一高二语文</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高一高二英语" >高一高二英语</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高一高二数学" >高一高二数学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高一高二物理" >高一高二物理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高一高二化学" >高一高二化学</label></p> 
                            <p><label><input type="checkbox" name="select_kemu" value="高三语文" >高三语文</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高三英语" >高三英语</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高三数学" >高三数学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高三物理" >高三物理</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高三化学" >高三化学</label></p>                  
                            <p><label><input type="checkbox" name="select_kemu" value="高中奥数" >高中奥数</label></p> 
                          </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="getSubjects">确定</button>
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="certificate"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    所获证书
                  </label>
                  <div class="col-sm-4">
                    <textarea
                      v-model="certificate"
                      class="form-control"
                      id="certificate"
                      rows="3"
                      style="font-size: x-small"
                    ></textarea>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    for="description"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    自我描述
                  </label>
                  <div class="col-sm-4">
                    <textarea
                      v-model="description"
                      class="form-control"
                      id="description"
                      rows="3"
                      style="font-size: x-small"
                    ></textarea>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    可授课区域
                  </label>
                  <div class="col-sm-8">
                    <div class="row">
                      <div class="col-sm-3" v-for="(area, index) in myAreas" :key="index">
                        <div class="form-check">
                          <input
                            v-model="selectedAreas"
                            class="form-check-input"
                            type="checkbox"
                            name="mode"
                            :value="area"
                          />
                          <label
                            class="form-check-label"
                            style="font-size: x-small"
                          >{{ area }}</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    家教简历，学员评价教学成果
                  </label>
                  <div class="col-sm-4">
                    <textarea
                      v-model="studentEvaluate"
                      class="form-control"
                      id="studentEvaluate"
                      rows="3"
                      style="font-size: x-small"
                    ></textarea>
                  </div>
                </div>
                <div
                  class="mb-3 row align-items-center"
                  style="margin-top: 10px"
                >
                  <label
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
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
                    for="salary"
                    class="col-sm-4 col-form-label text-end"
                    style="font-size: x-small"
                  >
                    <span style="color: red; font-weight: bold">*</span>
                    薪水要求
                  </label>
                  <div class="col-sm-4">
                    <textarea
                      v-model="salary"
                      class="form-control"
                      id="salary"
                      rows="3"
                      style="font-size: x-small;"
                    ></textarea>
                  </div>
                </div>
                <div
                  class="d-flex justify-content-center"
                  style="margin-left: 100px; font-size: x-small"
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
        </div>
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "../components/ContentField.vue";
import bootstrap, { Modal } from 'bootstrap/dist/js/bootstrap'
import { ref } from "vue";
import $ from "jquery"
import { useStore } from 'vuex';
export default {
  components: {
    NavBar,
    ContentField,
  },

  setup() {
    const store = useStore();
    let myAreas = ref(["莲湖区", "新城区", "碑林区", "雁塔区", "灞桥区", "未央区", "阎良区", "临潼区", "长安区", "高陵区", "蓝田区", "户县区", "周至区", "高新区", "沣渭新区", "曲江新区", "杨凌"]);
    let subjects = ref("");
    let certificate = ref("");
    let description = ref("");
    let selectedAreas = ref([]);
    let areas = ref("");
    let studentEvaluate = ref("");
    let mode = ref("");
    let salary = ref("");
    let error_message = ref("");
    let modal_error_message = ref("");

    const closeModal = () => {
      Modal.getInstance("#successModal").hide();
      window.location.reload();
    }

    const getSubjects = () => {
      subjects.value = "";
      const checkboxes = document.querySelectorAll('input[name="select_kemu"]:checked');
      checkboxes.forEach((checkbox, index) => {
        subjects.value += checkbox.value;
        if (index !== checkboxes.length - 1) {
          subjects.value += ",";
        }
      });
      console.log(subjects.value);
      Modal.getInstance("#myModal").hide();
    }

    const releaseJobInformation = () => {
      areas.value = "";
      areas.value = selectedAreas.value.join(',');
      console.log(areas.value);
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/release/job/information/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          subjects: subjects.value,
          certificate: certificate.value,
          description: description.value,
          areas: areas.value,
          studentEvaluate: studentEvaluate.value,
          mode: mode.value,
          salary: salary.value,
        }),
        success(resp) {
          if (resp.success == true) {
            modal_error_message.value = "发布求职信息成功!";
            var myModal = new bootstrap.Modal(document.getElementById('successModal'));
            myModal.show();
          } else if(resp.success == false){
            error_message.value = resp.errorMsg;
          }
        },
        error(resp) {
          error_message.value = "未知错误，请重试！";
          console.log(resp);
        },
      })
    }

    return {
      myAreas,
      subjects,
      certificate,
      description,
      selectedAreas,
      areas,
      studentEvaluate,
      mode,
      salary,
      error_message,
      modal_error_message,
      getSubjects,
      releaseJobInformation,
      closeModal,
    }
  }
};
</script>
  
  <style scoped>
  div.error-message {
  font-weight: 700;
  color: red;
}
</style>
  
  