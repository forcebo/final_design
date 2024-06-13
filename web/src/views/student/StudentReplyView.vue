<template>
  <StudentInfoTemplate>
    <div class="d-flex justify-content-center">
      <span style="color: red; font-weight: bold">我的回复</span>
    </div>
    <table class="table table-striped table-hover" style="font-size: x-small">
      <thead>
        <tr>
          <th>老师</th>
          <th>内容</th>
          <th>时间</th>
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
          <td style="width: 20%">
            <span>{{ comment.time }}</span>
          </td>
          <td style="width: 10%">
            <button
              type="button"
              class="btn btn-info"
              data-bs-toggle="modal"
              :data-bs-target="'#reply-btn-' + comment.id"
              style="font-size: x-small; height: 30px"
            >
              回复
            </button>
            <div
              class="modal fade"
              :id="'reply-btn-' + comment.id"
              tabindex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">回复{{ comment.username }}老师</h1>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body" style="font-size: medium">
                    <div class="mb-3">
                      <span>内容</span>
                      <textarea
                        class="form-control"
                        id="description"
                        rows="3"
                        v-model="replyContent"
                      ></textarea>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <div class="error-message">{{ error_message }}</div>
                    <button
                      type="button"
                      class="btn btn-primary"
                      @click="replyContentSubmit(comment.studentId)"
                    >
                      回复
                    </button>
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-bs-dismiss="modal"
                    >
                      取消
                    </button>
                  </div>
                </div>
              </div>
            </div>
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
  </StudentInfoTemplate>
</template>
  
  <script>
import StudentInfoTemplate from "@/components/StudentInfoTemplate.vue";
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
export default {
  components: {
    StudentInfoTemplate,
  },
  setup() {
    const store = useStore();
    let current_page = 1;
    let records = ref([]);
    let total_records = 0;
    let pages = ref([]);
    let replyContent = ref("");

    const click_page = (page) => {
      if (page === -2) page = current_page - 1;
      if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_records / 10));

      if (page >= 1 && page <= max_pages) {
        getStudentComments(page);
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

    const getStudentComments = (page) => {
      current_page = page;
      $.ajax({
        url:
          "http://127.0.0.1:3000/comment/get/identity/" +
          store.state.student.id +
          "/" +
          page +
          "/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
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
    getStudentComments(current_page);

    const replyContentSubmit = (id) => {
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/comment/reply/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.student.token,
        },
        contentType: "application/json", // 指定请求的Content-Type为JSON
        dataType: "json", // 指定预期的响应数据类型为JSON
        data: JSON.stringify({
          receiveId: id,
          content: replyContent.value,
      }),
        success(resp) {
          if (resp.success == true) {
            alert("回复成功");
            replyContent.value = "";
          } else {
            alert("回复失败");
          }
        },
        error(resp) {
          alert("回复失败");
          console.error(resp);
        },
      });
    }

    return {
      click_page,
      update_pages,
      records,
      total_records,
      pages,
      replyContent,
      replyContentSubmit,
    };
  },
};
</script>
  
  <style scoped>
</style>
  
  