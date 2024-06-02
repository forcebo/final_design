<template>
  <TeacherInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold">评论</span>
    </div>
    <table class="table table-striped table-hover" style="font-size: x-small">
      <thead>
        <tr>
          <th>学生</th>
          <th>内容</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="comment in records" :key="comment.id">
          <td style="width: 15%">
            <img
                  class="rounded-circle"
                  :src="comment.photo"
                  alt=""
                  style="width: 30px; height: 30px"
                />&nbsp;
            <span>{{ comment.username }}</span>
          </td>
          <td style="width: 50%">
            <span>{{ comment.content }}</span>
          </td>
          <td style="width: 10%">
            <button
              type="button"
              class="btn btn-info"
              style="font-size: x-small; height: 30px"
            >
              回复
            </button>
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
  </TeacherInfoTemplate>
</template>
    
    <script>
import TeacherInfoTemplate from "@/components/TeacherInfoTemplate.vue";
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
export default {
  components: {
    TeacherInfoTemplate,
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
        getTeacherComments(page);
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

    const getTeacherComments = (page) => {
      current_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/comment/get/" + store.state.teacher.id + "/" + page + "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.teacher.token,
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
    };
    getTeacherComments(current_page);

    return {
      click_page,
      update_pages,
      records,
      total_records,
      pages,
    };
  },
};
</script>
    
    <style scoped>
</style>
    
    