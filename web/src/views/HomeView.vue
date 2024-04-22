<template>
  <NavBar/>
  <ContentField> home </ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import ContentField from "../components/ContentField.vue"
import { useStore } from "vuex";

export default {
  components: {
    NavBar,
    ContentField,
  },
  setup () {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      const role = localStorage.getItem("role");
      if ( role === "STUDENT") {
        store.commit("updateStudentToken", jwt_token);
        store.dispatch("getStudentInfo", {
          success() {
            store.commit("updateStudentPullingInfo", false);
          },
          error() {
            store.commit("updateStudentPullingInfo", false);
          }
        })
      } else if (role === "TEACHER"){
        store.commit("updateTeacherToken", jwt_token);
        store.dispatch("getTeacherInfo", {
          success() {
            store.commit("updateTeacherPullingInfo", false);
          },
          error() {
            store.commit("updateTeacherPullingInfo", false);
          }
        })
      }
    } else {
      store.commit("updateStudentPullingInfo", false);
      store.commit("updateTeacherPullingInfo", false);
    }
  }
};
</script>

<style scoped>
</style>

