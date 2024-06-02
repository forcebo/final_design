<template>
  <AdminInfoTemplate>
    <ul
      class="nav nav-tabs mb-3"
      id="userTabs"
      role="tablist"
      style="margin-top: 20px; cursor: pointer"
      @click="clearErrorMessage"
    >
      <li class="nav-item" role="presentation">
        <a
          class="nav-link active"
          id="teacher-tab"
          data-bs-toggle="tab"
          data-bs-target="#teacher"
          role="tab"
          aria-controls="teacher"
          aria-selected="true"
          >教员</a
        >
      </li>
      <li class="nav-item" role="presentation">
        <a
          class="nav-link"
          id="student-tab"
          data-bs-toggle="tab"
          data-bs-target="#student"
          role="tab"
          aria-controls="student"
          aria-selected="false"
          >学员</a
        >
      </li>
    </ul>
    <div class="tab-content">
      <div
        class="tab-pane fade show active"
        id="teacher"
        role="tabpanel"
        aria-labelledby="teacher-tab"
      >
        <table
          class="table table-striped table-hover"
          style="font-size: x-small; background-color: rgb(39, 41, 61)"
        >
          <thead style="background-color: rgb(39, 41, 61)">
            <tr>
              <th>id</th>
              <th>用户名</th>
              <th>身份</th>
              <th>真实姓名</th>
              <th>性别</th>
              <th>学历</th>
              <th>学校</th>
              <th>专业</th>
              <th>电话号码</th>
              <th>地址</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="teacher in teachers" :key="teacher.id">
              <td style="width: 5%;">
                <span>{{ teacher.id }}</span>
              </td>
              <td style="width: 5%">
                <span>{{ teacher.username }}</span>
              </td>
              <td style="width: 15%">
                <span>{{ teacher.identity }}</span>
              </td>
              <td style="width: 5%">
                <span>{{ teacher.realname }}</span>
              </td>
              <td style="width:10%">
                <span>{{ teacher.sex }}</span>
                &nbsp;
                &nbsp;
                &nbsp;
                <span>{{ teacher.age }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ teacher.education }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ teacher.school }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ teacher.major }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ teacher.phone }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ teacher.address }}</span>
              </td>
              <td>
                <button
                  type="button"
                  @click="lookDetail(teacher.id)"
                  class="btn btn-info"
                  style="font-size: x-small; height: 30px"
                >
                  编辑
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <nav aria-label="Page navigation example" style="margin-top: 10px">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#" @click="click_teacher_page(-2)">上一页</a>
            </li>
            <li
              @click="click_teacher_page(page.number)"
              :class="'page-item ' + page.is_active"
              v-for="page in teacher_pages"
              :key="page.number"
            >
              <a class="page-link" href="#">{{ page.number }}</a>
            </li>
            <li class="page-item" @click="click_teacher_page(-1)">
              <a class="page-link" href="#">下一页</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
    <div class="tab-content">
      <div
        class="tab-pane fade"
        id="student"
        role="tabpanel"
        aria-labelledby="student-tab"
      >
        <table
          class="table table-striped table-hover"
          style="font-size: x-small; background-color: rgb(39, 41, 61)"
        >
          <thead style="background-color: rgb(39, 41, 61)">
            <tr>
              <th>id</th>
              <th>用户名</th>
              <th>真实姓名</th>
              <th>性别</th>
              <th>电话</th>
              <th>地址</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="student in students" :key="student.id">
              <td style="width: 5%">
                <span>{{ student.id }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ student.username }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ student.realname }}</span>
              </td>
              <td style="width: 10%">
                <span>{{ student.sex }}</span>
              </td>
              <td style="width: 20%">
                <span>{{ student.phone }}</span>
              </td>
              <td style="width:20%">
                <span>{{ student.address }}</span>
              </td>
              <td>
                <button
                  type="button"
                  @click="lookDetail(teacher.teacher.id)"
                  class="btn btn-info"
                  style="font-size: x-small; height: 30px"
                >
                  编辑
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <nav aria-label="Page navigation example" style="margin-top: 10px">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#" @click="click_student_page(-2)">上一页</a>
            </li>
            <li
              @click="click_student_page(page.number)"
              :class="'page-item ' + page.is_active"
              v-for="page in student_pages"
              :key="page.number"
            >
              <a class="page-link" href="#">{{ page.number }}</a>
            </li>
            <li class="page-item" @click="click_student_page(-1)">
              <a class="page-link" href="#">下一页</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </AdminInfoTemplate>
</template>
    
    <script>
import AdminInfoTemplate from "@/views/admin/AdminInfoTemplate.vue";
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
export default {
  components: {
    AdminInfoTemplate,
  },

  setup() {
    const store = useStore();
    let teachers = ref([]);
    let total_teachers = 0;
    let students = ref([]);
    let total_students = 0;
    let current_teacher_page = 1;
    let current_student_page = 1;
    let teacher_pages = ref([]);
    let student_pages = ref([]);

    const click_teacher_page = (page) => {
      if (page === -2) page = current_teacher_page - 1;
      if (page === -1) page = current_teacher_page + 1;
      let max_pages = parseInt(Math.ceil(total_teachers / 10));

      if (page >= 1 && page <= max_pages) {
        getTeachers(page);
      }
    };

    const update_teacher_pages = () => {
      let max_pages = parseInt(Math.ceil(total_teachers / 10)); //返回大于等于参数x的最小整数,即对浮点数向上取整.
      let new_pages = [];
      for (let i = current_teacher_page - 2; i <= current_teacher_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_teacher_page ? "active" : "",
          });
        }
        teacher_pages.value = new_pages;
      }
    };

    const click_student_page = (page) => {
      if (page === -2) page = current_student_page - 1;
      if (page === -1) page = current_student_page + 1;
      let max_pages = parseInt(Math.ceil(total_students / 10));

      if (page >= 1 && page <= max_pages) {
        getStudents(page);
      }
    };

    const update_student_pages = () => {
      let max_pages = parseInt(Math.ceil(total_students / 10)); //返回大于等于参数x的最小整数,即对浮点数向上取整.
      let new_pages = [];
      for (let i = current_student_page - 2; i <= current_student_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_student_page ? "active" : "",
          });
        }
        student_pages.value = new_pages;
      }
    };

    const getTeachers = (page) => {
      current_teacher_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/admin/get/all/teacher/",
        type: "get",
        data: {
          page,
        },
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          teachers.value = resp.data.teachers;
          total_teachers = resp.data.total_teachers;
          update_teacher_pages();
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    getTeachers(current_teacher_page);

    const getStudents = (page) => {
      current_student_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/admin/get/all/student/",
        type: "get",
        data: {
          page,
        },
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        success(resp) {
          students.value = resp.data.students;
          total_students = resp.data.total_students;
          console.log(students.value);
          update_student_pages();
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    getStudents(current_student_page);

    return {
      teachers,
      total_teachers,
      students,
      total_students,
      click_teacher_page,
      update_teacher_pages,
      current_teacher_page,
      teacher_pages,
      click_student_page,
      update_student_pages,
      current_student_page,
      student_pages,
      

    };
  },
};
</script>
    
    <style scoped>
</style>
    
    