<template>
  <NavBar />
  <ContentField>
    <div class="row justify-content-center">
      <div class="card" style="height: 300px; width: 800px; border-radius: 0%">
        <div
          class="card"
          style="
            margin-top: 20px;
            border-radius: 0%;
            background-color: rgb(0, 161, 239);
          "
        >
          <div class="d-flex align-items-center">
            <img
              src="@/assets/1.png"
              alt="图像"
              style="height: 150px; width: 200px"
            />
            <span
              class="denglut"
              style="margin-top: 10px; margin-left: 10px; font-weight: 900"
              >欢迎你注册师生之家</span
            >
          </div>
        </div>
        <div class="mb-3 d-flex justify-content-center" style="margin-top: 50px;">
          <button
            type="text"
            class="btn btn-primary me-2"
            style="width: 200px;"
            @click="$router.push('/teacher/account/register/')"
          >
            教员注册（做家教）
          </button>
          <button type="text" class="btn btn-primary" style="width: 200px"
          @click="$router.push('/student/account/register/')">
            学员注册（请家教）
          </button>
        </div>
      </div>
    </div>
  </ContentField>
</template>
  
  <script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "@/components/ContentField.vue";
export default {
  components: {
    NavBar,
    ContentField,
  },
};
</script>
  
<style scoped>


</style>
  
  