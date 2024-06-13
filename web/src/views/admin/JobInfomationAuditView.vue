<template>
  <AdminInfoTemplate>
    <table
      class="table table-striped table-hover"
      style="font-size: x-small; background-color: rgb(39, 41, 61)"
    >
      <thead style="background-color: rgb(39, 41, 61)">
        <tr>
          <th>老师id</th>
          <th>可教授科目</th>
          <th>证书</th>
          <th>自我描述</th>
          <th>可教授地区</th>
          <th>学生评价</th>
          <th>可上课方式</th>
          <th>薪资要求</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="jobInfomation in records" :key="jobInfomation.id">
          <td style="width: 10%">
            <span>{{ jobInfomation.teacherId }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.subjects }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.certificate }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.description }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.areas }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.studentEvaluate }}</span>
          </td>
          <td style="width: 10%">
            <span>{{ jobInfomation.mode }}</span>
          </td>
          <td style="width: 5%">
            <span>{{ jobInfomation.salary }}</span>
          </td>
          <td>
            <button
            v-if="jobInfomation.isExamine == 0"
              type="button"
              @click="examineYes(jobInfomation.id)"
              class="btn btn-info"
              style="font-size: x-small; height: 30px"
            >
              通过
            </button>
            &nbsp;&nbsp;&nbsp;
            <button
            v-if="jobInfomation.isExamine == 0"
              type="button"
              @click="examineNot(jobInfomation.id)"
              class="btn btn-info"
              style="font-size: x-small; height: 30px"
            >
              不通过
            </button>
            <span v-if="jobInfomation.isExamine == 1">
              已审核
              {{ jobInfomation.status == 1 ? "通过": "不通过"}}
            </span>
          </td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example" style="margin-top: 10px">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" @click="click_page(-2)">上一页</a>
        </li>
        <li
          @click="click_page(page.number)"
          :class="'page-item ' + page.is_active"
          v-for="page in pages"
          :key="page.number"
        >
          <a class="page-link" href="#">{{ page.number }}</a>
        </li>
        <li class="page-item" @click="click_page(-1)">
          <a class="page-link" href="#">下一页</a>
        </li>
      </ul>
    </nav>
  </AdminInfoTemplate>
</template>
    
    <script>
import AdminInfoTemplate from "@/views//admin/AdminInfoTemplate.vue";
import { ref } from 'vue';
import $ from "jquery";
import { useStore } from "vuex";
export default {
  components: {
    AdminInfoTemplate,
  },

  setup() {
    const store = useStore();
    let current_page = 1;
    let records = ref([]);
    let total_records = 0;
    let pages = ref([]);

    const click_page = (page) => {
      if (page === -2) page = current_page - 1;
      if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_records / 10));

      if (page >= 1 && page <= max_pages) {
        getJobInfomation(page);
      }
    };

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_records / 10)); //返回大于等于参数x的最小整数,即对浮点数向上取整.
      let new_pages = [];
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page ? "active" : "",
          });
        }
        pages.value = new_pages;
      }
    };

    const getJobInfomation = page => {
      current_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/admin/get/all/job/information/",
        type: "get",
        data: {
          page,
        },
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          records.value = resp.data.records;
          total_records = resp.data.total_records;
          console.log(records.value);
          update_pages();
        },
        error(resp) {
          console.log(resp);
        },
      });
    }

    getJobInfomation(current_page);

    const examineYes = (id) => {
      $.ajax({
        url: "http://127.0.0.1:3000/admin/examine/job/information/yes/" + id + "/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          if (resp.success == true) {
            getJobInfomation(current_page);
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    }
    const examineNot = (id) => {
      $.ajax({
        url: "http://127.0.0.1:3000/admin/examine/job/information/not/" + id + "/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          if (resp.success == true) {
            getJobInfomation(current_page);
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    }
    return {
      click_page,
      update_pages,
      getJobInfomation,
      records,
      total_records,
      pages,
      examineYes,
      examineNot,

    }
  },
};
</script>
    
    <style scoped>
</style>
    
    