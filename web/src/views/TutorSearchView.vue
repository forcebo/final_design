<template>
  <NavBar />
  <ContentField>
    <div class="row justify-content-center">
      <div
        class="card"
        style="width: 1000px; border-radius: 0%"
      >
        <div class="row" style="margin-top: 10px">
          <!-- 左侧导航栏 -->
          <div class="col-md-12">
            <div class="card"
              style="border-radius: 0%">
            <div style="margin-top: 10px">
                <form @submit.prevent="pull_page(id, subject, area, school, education, sex, mode, current_page)">
                <div class="input-group mb-3">
                  <div class="col-sm-2">
                    <div class="input-group">
                      <input
                        v-model="id"
                        type="text"
                        class="form-control"
                        id="tno"
                        placeholder="老师编号"
                        style="height: 30px; font-size: x-small; padding-left: 30px;"
                      />
                      <i class="bi bi-search" style="position: absolute; left: 8px; top: 6px; cursor: pointer;"></i>
                      
                    </div>
                    
                  </div>
                  <div class="col-sm-2">
                    <input
                      v-model="subject"
                      type="text"
                      class="form-control"
                      id="subject"
                      placeholder="请输入求教科目"
                      style="height: 30px; font-size: x-small"
                    />
                  </div>
                  <div class="col-sm-2">
                    <select
                      v-model="area"
                      class="form-select"
                      id="area"
                      style="height: 30px; font-size: x-small"
                    >
                      <option value="">请选择区域</option>
                      <option value="莲湖区">莲湖区</option>
                      <option value="新城区">新城区</option>
                      <option value="碑林区">碑林区</option>
                      <option value="雁塔区">雁塔区</option>
                      <option value="灞桥区">灞桥区</option>
                      <option value="未央区">未央区</option>
                      <option value="阎良区">阎良区</option>
                      <option value="临潼区">临潼区</option>
                      <option value="长安区">长安区</option>
                      <option value="高陵区">高陵区</option>
                      <option value="蓝田区">蓝田区</option>
                      <option value="户县区">户县区</option>
                      <option value="周至区">周至区</option>
                      <option value="高新区">高新区</option>
                      <option value="沣渭新区">沣渭新区</option>
                      <option value="曲江新区">曲江新区</option>
                      <option value="杨凌">杨凌</option>
                    </select>
                  </div>
                  <div class="col-sm-1">
                    <select
                      v-model="sex"
                      class="form-select"
                      id="sex"
                      style="height: 30px; font-size: x-small; width: 100px;"
                    >
                      <option value="">性别</option>
                      <option value="男">男</option>
                      <option value="女">女</option>
                    </select>
                  </div>
                  <div class="col-sm-2" style="margin-left: 20px;">
                    <select
                      v-model="school"
                      class="form-select"
                      id="school"
                      style="height: 30px; font-size: x-small"
                    >
                      <option value="">请选择高校</option>
                      <option value="西安交通大学">西安交通大学</option>
                      <option value="西北工业大学">西北工业大学</option>
                      <option value="长安大学">长安大学</option>
                      <option value="陕西师范大学">陕西师范大学</option>
                      <option value="电子科技大学">电子科技大学</option>
                      <option value="西北大学">西北大学</option>
                      <option value="西安理工大学">西安理工大学</option>
                      <option value="西安科技大学">西安科技大学</option>
                      <option value="陕西科技大学">陕西科技大学</option>
                      <option value="西安工业大学">西安工业大学</option>
                      <option value="西安石油大学">西安石油大学</option>
                      <option value="西安工程大学">西安工程大学</option>
                      <option value="西安音乐学院">西安音乐学院</option>
                      <option value="西安外国语大学">西安外国语大学</option>
                      <option value="西安体育学院">西安体育学院</option>
                      <option value="西安美术学院">西安美术学院</option>
                    </select>
                  </div>
                  <div class="col-sm-1">
                    <select v-model="education" class="form-select" id="education" style="height: 30px; width: 100px; font-size: x-small">
                      <option value="">学历</option>
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
                  <button class="btn btn-outline-secondary" type="submit" id="button-addon2" style="height: 30px; font-size: x-small; margin-left: 20px;">检索</button>
                </div>
              </form>
              </div>
          </div>
          <div class="card" style="margin-top: 5px; border-radius: 0%; ">
            <div class="row" style="font-size: x-small; margin-top: 5px; margin-left: 1px;">
              <span class="category">科目分类:</span>
              <span class="options" @click="setSubjectAndPull('语文')">语文</span>
              <span class="options" @click="setSubjectAndPull('数学')">数学</span>
              <span class="options" @click="setSubjectAndPull('物理')">物理</span>
              <span class="options" @click="setSubjectAndPull('化学')">化学</span>
              <span class="options" @click="setSubjectAndPull('生物')">生物</span>
              <span class="options" @click="setSubjectAndPull('地理')">地理</span>
              <span class="options" @click="setSubjectAndPull('历史')">历史</span>
              <span class="options" @click="setSubjectAndPull('政治')">政治</span>
              <span class="options" @click="setSubjectAndPull('作文')">作文</span>
              <span class="options" @click="setSubjectAndPull('奥数')">奥数</span>
              <span class="options" style="width: 40px;" @click="setSubjectAndPull('计算机')">计算机</span>
              <span class="options" style="width: 40px;" @click="setSubjectAndPull('数据库')">数据库</span>
              <span class="options" @click="setSubjectAndPull('艺术')">艺术</span>
              <span class="options" @click="setSubjectAndPull('美术')">美术</span>
              <span class="options" @click="setSubjectAndPull('乐器')">乐器</span>
              <span class="options" @click="setSubjectAndPull('书法')">书法</span>
              <span class="options" @click="setSubjectAndPull('体育')">体育</span>
              <span class="options" @click="setSubjectAndPull('德语')">德语</span>
              <span class="options" @click="setSubjectAndPull('韩语')">韩语</span>
              <span class="options" @click="setSubjectAndPull('俄语')">俄语</span>
              <span class="options" @click="setSubjectAndPull('日语')">日语</span>
              <span class="options" @click="setSubjectAndPull('钢琴')">钢琴</span>
              <span class="options" style="width: 40px;" @click="setSubjectAndPull('数据库')">数据库</span>
            </div>
            <div class="row" style="font-size: x-small; margin-top: 5px; margin-left: 1px;">
              <span class="category">高校分类:</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安交通大学')">西安交通大学</span>
              <span class="options" style="width: 55px" @click="setSchoolAndPull('长安大学')">长安大学</span>
              <span class="options" style="width: 105px" @click="setSchoolAndPull('西安电子科技大学')">西安电子科技大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('陕西师范大学')">陕西师范大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西北工业大学')">西北工业大学</span>
              <span class="options" style="width: 55px" @click="setSchoolAndPull('西北大学')">西北大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安理工大学')">西安理工大学</span>
              <span class="options" style="width: 105px" @click="setSchoolAndPull('西安建筑科技大学')">西安建筑科技大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安科技大学')">西安科技大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安石油大学')">西安石油大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安工程大学')">西安工程大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安工业大学')">西安工业大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('陕西科技大学')">陕西科技大学</span>
              <span class="options" style="width: 90px" @click="setSchoolAndPull('西安外国语大学')">西安外国语大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安邮电大学')">西安邮电大学</span>
              <span class="options" style="width: 65px" @click="setSchoolAndPull('西安医学院')">西安医学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安财经大学')">西安财经大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西北政法大学')">西北政法大学</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安体育学院')">西安体育学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安美术学院')">西安美术学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安音乐学院')">西安音乐学院</span>
              <span class="options" style="width: 55px" @click="setSchoolAndPull('西京学院')">西京学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安文理学院')">西安文理学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安培华学院')">西安培华学院</span>
              <span class="options" style="width: 80px" @click="setSchoolAndPull('西安欧亚学院')">西安欧亚学院</span>
              
            </div>
            <div class="row" style="font-size: x-small; margin-top: 5px; margin-left: 1px;">
              <span class="category">区域分类:</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('莲湖区')">莲湖区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('新城区')">新城区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('碑林区')">碑林区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('雁塔区')">雁塔区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('灞桥区')">灞桥区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('未央区')">未央区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('阎良区')">阎良区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('临潼区')">临潼区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('长安区')">长安区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('高陵区')">高陵区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('蓝田区')">蓝田区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('户县区')">户县区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('周至区')">周至区</span>
              <span class="options" style="width: 40px;" @click="setAreaAndPull('高新区')">高新区</span>
              <span class="options" style="width: 50px;" @click="setAreaAndPull('沛渭新区')">沛渭新区</span>
              <span class="options" style="width: 50px;" @click="setAreaAndPull('曲江新区')">曲江新区</span>
              <span class="options" style="width: 30px;" @click="setAreaAndPull('杨凌')">杨凌</span>
            </div>
            <div class="row" style="font-size: x-small; margin-top: 5px; margin-left: 1px;">
              <span class="category" style="width: 95px">教学方式分类:</span>
              <span class="options" style="width: 55px" @click="setModeAndPull('上门辅导')">上门辅导</span>
              <span class="options" style="width: 55px" @click="setModeAndPull('网络辅导')">网络辅导</span>
              <span class="options" style="width: 55px" @click="setModeAndPull('')">均可</span>
            </div>
          </div>
          
          <div class="card" style="background-color: rgb(255,215,117); border-radius: 0%">
            <span style="font-size:smaller; margin-left: 10px; font-weight: bold;">西安教员列表</span>
          </div>
          <div class="card" style="border-radius: 0%">
            <nav aria-label="Page navigation example" style="margin-top: 10px;">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <a class="page-link" href="#" @click="click_page(-2)">上一页</a>
              </li>
              <li @click="click_page(page.number)" :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number">
                <a class="page-link" href="#">{{ page.number }}</a>
              </li> 
              <li class="page-item" @click="click_page(-1)">
                <a class="page-link" href="#">下一页</a>
              </li>
            </ul>
          </nav>
          </div>
          
          <div class="card" style="border-radius: 0%;">
            <table class="table table-striped table-hover" style="font-size: x-small;">
                            <thead>
                                <tr>
                                    <th>教员编号</th>
                                    <th>目前身份/可教授课程</th>
                                    <th>自我描述</th>
                                    <th>具体要求</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="teacher in teachers" :key="teacher.id">
                                    <td style="width: 15%;">
                                      <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <span class="record-teacher-tno" style="color: red; font-weight: bold;">{{ teacher.teacher.id}}</span>
                                        <span>{{ teacher.teacher.realname.charAt(0) + "教员(" + teacher.teacher.sex + ")"}}</span>
                                      </div>
                                        <img :src="teacher.teacher.photo" alt="" class="record-teacher-photo" style="width: 80%;"/>
                                        &nbsp;
                                    </td>
                                    <td style="width: 25%;">
                                      <span>{{ teacher.teacher.school }}</span>
                                      <br>
                                      <span>{{ teacher.teacher.major }}</span>
                                      <br>
                                      <span>{{ teacher.teacher.education }}</span>
                                      <br>
                                      <span>{{ teacher.subjects }}</span>
                                    </td>
                                    <td style="width: 25%;">{{ teacher.description }}</td>
                                    <td style="width: 25%;">
                                      <span>{{ teacher.mode }}</span>
                                      <br>
                                      <span>{{ "授课区域:" + teacher.areas }}</span>
                                      <br>
                                      <span>{{ "薪水要求:" + teacher.salary}}</span>
                                      <br><br>
                                      <button type="button" @click="lookDetail(teacher.teacher.id)" class="btn btn-info" style="font-size: x-small; height: 30px;">查看详情</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
            <nav aria-label="Page navigation example" style="margin-top: 10px;">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <a class="page-link" href="#" @click="click_page(-2)">上一页</a>
              </li>
              <li @click="click_page(page.number)" :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number">
                <a class="page-link" href="#">{{ page.number }}</a>
              </li> 
              <li class="page-item" @click="click_page(-1)">
                <a class="page-link" href="#">下一页</a>
              </li>
            </ul>
          </nav>
          </div>
          
          </div>
          <!-- 右侧内容 -->
        </div>
      </div>
    </div>
  </ContentField>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import ContentField from "../components/ContentField.vue"
import { useStore } from "vuex";
import { onMounted, ref } from "vue";
import $ from "jquery"
import { useRoute } from 'vue-router';

export default {
  components: {
    NavBar,
    ContentField,
  },

  setup() {
    const store = useStore();
    const route = useRoute();
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      const role = localStorage.getItem("role");
      if ( role === "STUDENT") {
        store.commit("updateStudentToken", jwt_token);
        store.dispatch("getStudentInfo", {
          success() {
            store.commit("updateStudentPullingInfo", false);
          },
          error() {
            store.commit("updateStudentPullingInfo", false);
          }
        })
      } else if (role === "TEACHER"){
        store.commit("updateTeacherToken", jwt_token);
        store.dispatch("getTeacherInfo", {
          success() {
            store.commit("updateTeacherPullingInfo", false);
          },
          error() {
            store.commit("updateTeacherPullingInfo", false);
          }
        })
      }
    } else {
      store.commit("updateStudentPullingInfo", false);
      store.commit("updateTeacherPullingInfo", false);
    }
    let id = ref("");
    let area = ref("");
    let sex = ref("");
    let school = ref("");
    let education = ref("");
    let subject = ref("");
    let mode = ref("");
    let current_page = 1;
    let teachers = ref([]);
    let total_teachers = 0;
    let pages = ref([]);

    onMounted(() => {
      id.value = route.query.id == null ? "" : route.query.id;
      subject.value = route.query.subject == null ? "" : route.query.subject;
      area.value =  route.query.area == null ? "" : route.query.area;
      sex.value = route.query.sex == null ? "" : route.query.sex;
      school.value = route.query.school == null ? "" : route.query.school;
      education.value = route.query.education == null ? "" : route.query.education;
      console.log(id.value);
      pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, current_page);
    })
    //console.log(teachers, total_teachers);

    const click_page = page => {
            if (page === -2) page = current_page - 1;
            if (page === -1) page = current_page + 1;
            let max_pages = parseInt(Math.ceil(total_teachers / 10));

            if (page >= 1 && page <= max_pages) {
              pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, page);
            }
        }

        const update_pages = () => {
            let max_pages = parseInt(Math.ceil(total_teachers / 10));//返回大于等于参数x的最小整数,即对浮点数向上取整.
            let new_pages = [];
            for (let i = current_page - 2; i <= current_page + 2; i ++) {
                if (i >= 1 && i <= max_pages) {
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : "",
                    });
                }
                pages.value = new_pages;
            }
        }

    const pull_page = (id_x, subject_x, area_x, school_x, education_x, sex_x, mode_x, page) => {
      current_page = page;
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/getTeacherList/" + current_page + "/",
        type: "post",
        contentType: "application/json", 
        dataType: "json", 
        data: JSON.stringify({
          id: id_x,
          subject: subject_x,
          area: area_x,
          school: school_x,
          education: education_x,
          sex: sex_x,
          mode: mode_x,
        }),
        headers: {
          Authorization: "Bearer " + (store.state.student.token == "" ? store.state.teacher.token : store.state.student.token),
        },
        success(resp) {
          teachers.value = resp.data.teachers;
          total_teachers = resp.data.total_teachers;
          update_pages();
          // console.log(resp);
          // console.log(teachers.value);
          // console.log(total_teachers);
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    const setSubjectAndPull = (newSubject) => {
      subject.value = newSubject
      pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, current_page)
    }
    const setSchoolAndPull = (newSchool) => {
      school.value = newSchool,
      pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, current_page)
    }
    const setAreaAndPull = (newArea) => {
      area.value = newArea
      pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, current_page)
    }
    const setModeAndPull = (newMode) => {
      mode.value = newMode
      pull_page(id.value, subject.value, area.value, school.value, education.value, sex.value, mode.value, current_page)
    }

    const lookDetail = (id) => {
      window.open('/tutor/detail/?id='+ id, '_blank');
    };

    return {
      id,
      subject,
      area,
      sex,
      school,
      education,
      mode,
      current_page,
      click_page,
      update_pages,
      teachers,
      pull_page,
      pages,
      setSubjectAndPull,
      setSchoolAndPull,
      setModeAndPull,
      setAreaAndPull,
      lookDetail,
    }
  }
};
</script>

<style scoped>
.options{  
      white-space: nowrap;  /*强制span不换行*/
      display: inline-block;  /*将span当做块级元素对待*/
      width: 30px;  
      cursor: pointer;
      padding-left: 2px;
      /* overflow: hidden;  超出宽度部分隐藏 */
      /* text-overflow: ellipsis;  超出部分以点号代替 */
      /* line-height: 0.9;  数字与之前的文字对齐 */
}
.options:hover{
  background-color: coral;
}
.category {
  white-space: nowrap;
  display: inline-block;
  width:70px;
  color: red;
  font-weight: bold;
}
.pagination .page-link {
      font-size: 0.8rem; /* 调整字体大小 */
    }
</style>

