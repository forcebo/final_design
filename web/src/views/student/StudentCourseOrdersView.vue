<template>
  <StudentInfoTemplate>
    <div class="row">
      <span style="text-align: center;">我的课程订单</span>
    </div>
    <div class="d-flex justify-content-center">
      <table class="table table-striped table-hover" style="font-size: x-small;">
                            <thead>
                                <tr>
                                    <th>订单编号</th>
                                    <th>价格</th>
                                    <th>下单时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="order in orders" :key="order.id">
                                  <td style="width: 10%;">
                                      <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <span style="font-weight: bold;">{{ order.no }}</span>
                                      </div>
                                    </td>
                                    <td style="width: 10%;">
                                      <span>{{ order.price }}</span>
                                    </td>
                                    <td style="width: 20%;">{{ order.time }}</td>
                                    <td style="width: 20%;">
                                      <button type="button" @click="() => router.push({ path: '/tutor/course/', query: { id: order.teacherId } })" class="btn btn-info" style="font-size: x-small; height: 30px;">查看详情</button>
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
    let orders = ref([]);
    const getCourseOrders = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/student/course/order/get/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        success(resp) {
          if (resp.success == true) {
            orders.value = resp.data;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    }
    getCourseOrders();
    return {
      orders,
      router,
    }
  }
};
</script>
  
  <style scoped>
</style>
  
  