<template>
  <NavBar/>
  <ContentField>个人信息</ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import ContentField from "../components/ContentField.vue";
export default {
  components: {
    ContentField,
    NavBar
  },
};
</script>

<style scoped>

</style>

