<template>
  <NavBar/>
  <ContentField>家教搜索</ContentField>
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

  setup() {
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateStudentToken", jwt_token);
      store.dispatch("getStudentInfo", {
        success() {
          store.commit("updateStudentPullingInfo", false);
        },
        error() {
          store.commit("updateStudentPullingInfo", false);
        }
      })
    } else {
      store.commit("updateStudentPullingInfo", false);
    }
  }
};
</script>

<style scoped>

</style>

