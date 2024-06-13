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
              <td style="width: 5%">
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
              <td style="width: 10%">
                <span>{{ teacher.sex }}</span>
                &nbsp; &nbsp; &nbsp;
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
                  class="btn btn-primary"
                  data-bs-toggle="modal"
                  :data-bs-target="'#update-teacher-btn-' + teacher.id"
                  style="font-size: x-small; height: 30px"
                >
                  编辑
                </button>
                <div
                  class="modal fade"
                  :id="'update-teacher-btn-' + teacher.id"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5">
                          编辑{{ teacher.id }}号教员
                        </h1>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body" style="font-size: medium;">
                        <div class="mb-3">
                          <span>名称</span>
                          <input
                            v-model="teacher.username"
                            type="text"
                            class="form-control"
                          />
                        </div>
                        <div class="col-sm-7">
                          <div class="row align-items-center">
                            <div class="col">
                              <div class="form-check">
                                <input
                                  v-model="teacher.identity"
                                  class="form-check-input"
                                  type="radio"
                                  value="在校大学生(研究生),不含外籍留学生"
                                />
                                <label class="form-check-label" for="identity"
                                  >在校大学生(研究生),不含外籍留学生</label
                                >
                              </div>
                            </div>
                          </div>
                          <div class="row align-items-center">
                            <div class="col">
                              <div class="form-check">
                                <input
                                  v-model="teacher.identity"
                                  class="form-check-input"
                                  type="radio"
                                  value="专业老师（注册教师应符合相关政策法规）"
                                />
                                <label class="form-check-label" for="identity2"
                                  >专业老师（注册教师应符合相关政策法规）</label
                                >
                              </div>
                            </div>
                          </div>
                          <div class="row align-items-center">
                            <div class="col">
                              <div class="form-check">
                                <input
                                  v-model="teacher.identity"
                                  class="form-check-input"
                                  type="radio"
                                  value="其他(已经毕业离校的人员)"
                                />
                                <label class="form-check-label" for="identity"
                                  >其他(已经毕业离校的人员)</label
                                >
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="mb-3">
                          <span>真实姓名</span>
                          <input
                            v-model="teacher.realname"
                            type="text"
                            class="form-control"
                          />
                        </div>
                        <div class="mb-3">
                          <span style="display: block;">性别</span>
                          <input
                            v-model="teacher.sex"
                            class="form-check-input"
                            type="radio"
                            value="男"
                          />
                          <span> 男 </span>
                          <input
                            v-model="teacher.sex"
                            class="form-check-input"
                            type="radio"
                            value="女"
                            style="margin-left: 10px"
                          />
                          <span> 女 </span>
                        </div>
                        <div class="mb-3">
                          <span>年龄</span>
                          <input
                            v-model="teacher.age"
                            type="text"
                            class="form-control"
                          />
                        </div>
                        <div class="mb-3">
                          <span>学历</span>
                          <select v-model="teacher.education" class="form-select" id="education">
                            <option value="" selected disabled>请选择学历</option>
                            <option value="大专以下">大专以下</option>
                            <option value="大专在读">大专在读</option>
                            <option value="大专毕业">大专毕业</option>
                            <option value="本科在读">本科在读</option>
                            <option value="本科毕业">本科毕业</option>
                            <option value="硕士在读">硕士在读</option>
                            <option value="硕士毕业">硕士毕业</option>
                            <option value="博士在读">博士在读</option>
                            <option value="博士毕业">博士毕业</option>
                          </select>
                        </div>
                        <div class="mb-3">
                          <span>学校</span>
                          <input
                            v-model="teacher.school"
                            type="text"
                            class="form-control"
                            placeholder="请输入就读或毕业学校"
                          />
                        </div>
                        <div class="mb-3">
                          <span>专业</span>
                          <input
                            v-model="teacher.major"
                            type="text"
                            class="form-control"
                            placeholder="请输入专业"
                          />
                        </div>
                        <div class="mb-3">
                          <span>电话</span>
                          <input
                            v-model="teacher.phone"
                            type="text"
                            class="form-control"
                            placeholder="请输入电话"
                          />
                        </div>
                        <div class="mb-3">
                          <span>地址</span>
                          <input
                            v-model="teacher.address"
                            type="text"
                            class="form-control"
                            placeholder="请输入地址"
                          />
                        </div>
                        

                        <!-- <div class="mb-3">
                                        <label for="add-bot-title" class="form-label">名称</label>
                                        <input v-model="botadd.title" type="text" class="form-control" id="add-bot-title" placeholder="请输入Bot名称">
                                    </div>
                                    <div class="mb-3">
                                        <label for="add-bot-description" class="form-label">Bot简介</label>
                                        <textarea v-model="botadd.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot简介"></textarea>
                                    </div> -->
                      </div>
                      <div class="modal-footer">
                        <div class="error-message">{{ error_message }}</div>
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="update_teacher(teacher.id, teacher.username, teacher.identity, teacher.realname, teacher.age, teacher.education, teacher.major, teacher.phone, teacher.address)"
                        >
                          更新
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
              <a class="page-link" href="#" @click="click_teacher_page(-2)"
                >上一页</a
              >
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
              <td style="width: 20%">
                <span>{{ student.address }}</span>
              </td>
              <td>
                <button
                  type="button"
                  class="btn btn-primary"
                  data-bs-toggle="modal"
                  :data-bs-target="'#update-student-btn-' + student.id"
                  style="font-size: x-small; height: 30px"
                >
                  编辑
                </button>
                <div
                  class="modal fade"
                  :id="'update-student-btn-' + student.id"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5">
                          编辑{{ student.id }}号学员
                        </h1>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body" style="font-size: medium;">
                        <div class="mb-3">
                          <span>名称</span>
                          <input
                            v-model="student.username"
                            type="text"
                            class="form-control"
                          />
                        </div>
                        <div class="mb-3">
                          <span>真实姓名</span>
                          <input
                            v-model="student.realname"
                            type="text"
                            class="form-control"
                          />
                        </div>
                        <div class="mb-3">
                          <span style="display: block;">性别</span>
                          <input
                            v-model="student.sex"
                            class="form-check-input"
                            type="radio"
                            value="男"
                          />
                          <span> 男 </span>
                          <input
                            v-model="student.sex"
                            class="form-check-input"
                            type="radio"
                            value="女"
                            style="margin-left: 10px"
                          />
                          <span> 女 </span>
                        </div>
                        <div class="mb-3">
                          <span>电话</span>
                          <input
                            v-model="student.phone"
                            type="text"
                            class="form-control"
                            placeholder="请输入电话"
                          />
                        </div>
                        <div class="mb-3">
                          <span>地址</span>
                          <input
                            v-model="student.address"
                            type="text"
                            class="form-control"
                            placeholder="请输入地址"
                          />
                        </div>
                      </div>
                      <div class="modal-footer">
                        <div class="error-message">{{ error_message }}</div>
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="update_student(student.id, student.username, student.realname, student.phone, student.address)"
                        >
                          更新
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
              <a class="page-link" href="#" @click="click_student_page(-2)"
                >上一页</a
              >
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
    let error_message = ref("");

    const click_teacher_page = (page) => {
      if (page === -2) page = current_teacher_page - 1;
      if (page === -1) page = current_teacher_page + 1;
      let max_pages = parseInt(Math.ceil(total_teachers / 10));

      if (page >= 1 && page <= max_pages) {
        getTeachers(page);
      }
    };

    const update_teacher = (id, username, identity, realname, age, education, major, phone, address) => {
      
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/account/info/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        data: {
          id: id,
          username: username,
          identity: identity,
          realname: realname,
          age: age,
          education: education,
          major: major,
          phone: phone,
          address: address,
        },
        success(resp) {
          if (resp.success == true) {
            error_message.value = "";
            alert("更新成功");
            getTeachers(current_teacher_page);
          } else {
            error_message.value = resp.errorMsg;
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    const update_student = (id, username, realname, phone, address) => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/student/account/info/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.admin.token,
        },
        data: {
          id: id,
          username: username,
          realname: realname,
          phone: phone,
          address: address,
        },
        success(resp) {
          console.log(resp);
          if (resp.success == true) {
            error_message.value = "";
            alert("更新成功");
            getStudents(current_student_page);
          } else {
            error_message.value = resp.errorMsg;
          }
        },
        error(resp) {
          error_message.value = "未知错误，请重试";
          console.log(resp);
        },
      });
    };


    const update_teacher_pages = () => {
      let max_pages = parseInt(Math.ceil(total_teachers / 10)); //返回大于等于参数x的最小整数,即对浮点数向上取整.
      let new_pages = [];
      for (
        let i = current_teacher_page - 2;
        i <= current_teacher_page + 2;
        i++
      ) {
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
      for (
        let i = current_student_page - 2;
        i <= current_student_page + 2;
        i++
      ) {
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
      error_message,
      update_teacher,
      update_student
    };
  },
};
</script>
    
    <style scoped>

div.error-message {
  font-size: medium;
  font-weight: 700;
  color: red;
}
</style>
    
    