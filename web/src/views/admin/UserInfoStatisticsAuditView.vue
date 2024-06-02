<template>
  <AdminInfoTemplate>
    <span style="color: aliceblue">用户信息统计分析</span>
    <div class="row">
      <div class="col-md-6" style="text-align: center">
        <div
          id="main1"
          style="width: 100%; height: 300px; margin-top: 2%"
        ></div>
        <span style="color: aliceblue">预约前五的教员id</span>
      </div>
      <div class="col-md-6" style="text-align: center">
        <div
          id="main2"
          style="width: 100%; height: 300px; margin-top: 2%"
        ></div>
        <span style="color: aliceblue">预约前五的教员所占比例</span>
      </div>
    </div>
  </AdminInfoTemplate>
</template>

<script>
import AdminInfoTemplate from "@/views/admin/AdminInfoTemplate.vue";
import * as echarts from "echarts";
import { onMounted, ref, watch } from "vue";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  components: {
    AdminInfoTemplate,
  },

  setup() {
    const store = useStore();
    let totalReserveCount = ref("");
    let otherCount = ref("");
    let top5ReserveTeacherInfo = ref([]);

    const getStatisticsDatas = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/admin/statistics/teacher/reserves/top/5/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          if (resp.success == true) {
            top5ReserveTeacherInfo.value = resp.data.data;
            
            totalReserveCount.value = top5ReserveTeacherInfo.value.reduce(
              (sum, teacher) => sum + teacher.reserveCount,
              0
            );
            otherCount.value = resp.data.count - totalReserveCount.value;
          }
        },
        error(resp) {
          console.error(resp);
        },
      });
    };

    watch(top5ReserveTeacherInfo, (newValue) => {
      if (newValue.length > 0) {
        updateCharts();
      }
    });

    const updateCharts = () => {
      var chartDom1 = document.getElementById("main1");
      var myChart1 = echarts.init(chartDom1);

      var option1 = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
          textStyle: {
            color: "black",
          },
          formatter: function (params) {
            var dataIndex = params[0].dataIndex;
            var reserveCount =
              top5ReserveTeacherInfo.value[dataIndex].reserveCount;
            return "预约数: " + reserveCount;
          },
        },
        xAxis: {
          type: "category",
          data: top5ReserveTeacherInfo.value.map((teacher) => teacher.id),
          axisLabel: {
            color: "aliceblue",
          },
        },
        yAxis: {
          type: "value",
          axisLabel: {
            color: "aliceblue",
          },
        },
        series: [
          {
            data: top5ReserveTeacherInfo.value.map(
              (teacher) => teacher.reserveCount
            ),
            type: "bar",
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
          },
        ],
      };

      myChart1.setOption(option1);

      var chartDom2 = document.getElementById("main2");
      var myChart2 = echarts.init(chartDom2);

      var option2 = {
        title: {
          text: "预约前五的教员所占比例",
          left: "center",
          textStyle: {
            color: "aliceblue",
          },
        },

        legend: {
          orient: "vertical",
          left: "left",
          textStyle: {
            color: "aliceblue",
          },
        },
        series: [
          {
            name: "预约数",
            type: "pie",
            radius: "50%",
            data: [
              ...top5ReserveTeacherInfo.value.map((teacher) => ({
                value: teacher.reserveCount,
                name: teacher.id + " " + teacher.realName,
              })),
              { value: otherCount.value, name: "其他" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
            label: {
              color: "aliceblue",
            },
          },
        ],
      };

      myChart2.setOption(option2);
    };

    onMounted(() => {
      getStatisticsDatas();
    });

    return {
      top5ReserveTeacherInfo,
    };
  },
};
</script>

<style scoped>
</style>
