<template>
  <TeacherInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold">上传课程</span>
    </div>
    <form @submit.prevent="submitForm" enctype="multipart/form-data" style="margin-top: 20px;">
          <div class="mb-3 row align-items-center">
            <label for="courseName" class="form-label col-sm-4 text-end" >课程名字</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" id="courseName" v-model="courseName">
            </div>
            
          </div>
          <div class="mb-3 row align-items-center">
            <label for="description" class="form-label col-sm-4 text-end">描述</label>
            <div class="col-sm-5">
              <textarea class="form-control" id="description" rows="3" v-model="description"></textarea>
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="imageFile" class="form-label col-sm-4 text-end">背景图片</label>
            <div class="col-sm-5">
              <input class="form-control" type="file" id="imageFile" @change="handleImageUpload">
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="location" class="form-label col-sm-4 text-end">上课地点</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" id="location" v-model="location">
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="videoFile" class="form-label col-sm-4 text-end">上传视频</label>
            <div class="col-sm-5">
              <input class="form-control" type="file" id="videoFile" @change="handleVideoUpload">
            </div>
            
          </div>
          <div class="mb-3 row align-items-center">
            <label for="price" class="form-label col-sm-4 text-end">价格</label>
            <div class="col-sm-5">
              <input type="number" class="form-control" id="price" v-model="price">
            </div>
            
          </div>
          <div class="mb-3 d-flex justify-content-center" style="margin-left: 100px; margin-top: 15px;">
            <button
              type="submit"
              class="btn btn-primary me-2"
              style="width: 100px"
            >
              上传
            </button>
          </div>
        </form>
  </TeacherInfoTemplate>
</template>
    
    <script>
import TeacherInfoTemplate from "@/components/TeacherInfoTemplate.vue";
import { ref } from "vue"
import { useStore } from "vuex"
import $ from 'jquery'
export default {
  components: {
    TeacherInfoTemplate,
  },
  setup() {
    const store = useStore();
    let courseName = ref("");
    let description = ref("");
    let imageFile = ref(null);
    let location = ref("");
    let videoFile = ref(null);
    let price = ref("");

    const handleImageUpload = (event) => {
      imageFile.value = event.target.files[0];
    };

    const handleVideoUpload = (event) => {
      videoFile.value = event.target.files[0];
    };

    const submitForm = () => {
      const formData = new FormData();
      formData.append('courseName', courseName.value);
      formData.append('description', description.value);
      formData.append('imageFile', imageFile.value);
      formData.append('location', location.value);
      formData.append('videoFile', videoFile.value);
      formData.append('price', price.value);
      console.log(formData);

      $.ajax({
        url: "http://127.0.0.1:3000/teacher/account/upload/course/",
        type: "POST",
        data: formData,
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
        },
        processData: false,
        contentType: false,
        success(resp) {
          if (resp.success == true) {
            courseName.value = "";
            description.value = "";
            imageFile.value = null;
            location.value = "";
            videoFile.value = null;
            price.value = "";
            alert('课程资料上传成功!');
          } else {
            alert(resp.errorMsg);
          }
          console.log( resp);
        },
        error(resp) {
          console.error(resp);
          alert('课程资料上传失败!');
        }
      });
    };


    return {
      courseName,
      description,
      imageFile,
      location,
      videoFile,
      price,
      handleImageUpload,
      handleVideoUpload,
      submitForm,
    }
  }
};
</script>
    
    <style scoped>
</style>
    
    