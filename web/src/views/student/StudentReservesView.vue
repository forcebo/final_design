<template>
  <StudentInfoTemplate>
    <div class="row">
      <span style="text-align: center;">我的预约</span>
    </div>
    <div class="d-flex justify-content-center">
      <table class="table table-striped table-hover" style="font-size: x-small;">
                            <thead>
                                <tr>
                                    <th>教员编号</th>
                                    <th>学科</th>
                                    <th>上课时间</th>
                                    <th>辅导方式</th>
                                    <th>收费</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="reserve in reserves" :key="reserve.id">
                                    <td style="width: 10%;">
                                      <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <span style="font-weight: bold;">{{ reserve.teacherId }}</span>
                                      </div>
                                    </td>
                                    <td style="width: 10%;">
                                      <span>{{ reserve.subject }}</span>
                                    </td>
                                    <td style="width: 20%;">{{ reserve.classTime }}</td>
                                    <td style="width: 20%;">{{ reserve.mode }}</td>
                                    <td style="width: 20%;">{{ reserve.charge }}</td>
                                    <td style="width: 20%;">
                                      <button type="button" @click="() => router.push({ path: '/tutor/detail/', query: { id: reserve.teacherId } })" class="btn btn-info" style="font-size: x-small; height: 30px;">查看详情</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
    </div>
  </StudentInfoTemplate>
</template>
  
  <script>
import StudentInfoTemplate from "@/components/StudentInfoTemplate.vue";
import { useStore } from "vuex";
import $ from "jquery";
import { ref } from "vue";
import { useRouter } from 'vue-router';
export default {
  components: {
    StudentInfoTemplate,
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    let reserves = ref([]);
    const getReserves = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/reserve/get/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            reserves.value = resp.data;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }
    getReserves();
    return {
      reserves,
      router,
    }
  }
};
</script>
  
  <style scoped>
</style>
  
  