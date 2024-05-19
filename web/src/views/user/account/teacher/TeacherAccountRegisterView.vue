<template>
  <NavBar />
  <ContentField>
    <div class="row justify-content-center">
      <div class="card" style="height: 1400px; width: 1000px; border-radius: 0%">
        <div
          class="card"
          style="
            margin-top: 20px;
            border-radius: 0%;
            background-color: rgb(0, 161, 239);
          "
        >
          <div class="d-flex align-items-center">
            <img
              src="@/assets/1.png"
              alt="图像"
              style="height: 150px; width: 200px"
            />
            <span
              class="denglut"
              style="margin-top: 10px; margin-left: 10px; font-weight: 900"
              >欢迎你注册成为师生之家<span style="color: red">{{ ad }}</span
              >教员.<br />
              为了更好的找到合适您的工作和为您服务，请仔细填写以下内容</span
            >
          </div>
        </div>
        <form @submit.prevent="register" style="width: 750px; margin-top: 10px">
          <div
            class="btbg1"
            style="
              background-color: rgb(255, 243, 207);
              height: 30px;
              width: 975px;
            "
          >
            <img src="@/assets/images/zcimg1.png" style="margin-left: 10px" />
            <span style="margin-left: 10px; color: red; font-weight: bold"
              >请选择密码用户名、身份</span
            >
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px">
            <label
              for="teacherRegisterUsername"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold">*</span>请输入用户名
            </label>
            <div class="col-sm-7">
              <input
                v-model="username"
                type="text"
                class="form-control"
                id="teacherRegisterUsername"
                placeholder="请输入用户名"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="teacherRegisterUsername"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold">*</span>请输入密码
            </label>
            <div class="col-sm-7">
              <input
                v-model="password"
                type="password"
                class="form-control"
                id="teacherRegisterPassword"
                placeholder="请输入密码"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="teacherRegisterConfirmedPassword"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold">*</span>请确认密码
            </label>
            <div class="col-sm-7">
              <input
                v-model="confirmedPassword"
                type="password"
                class="form-control"
                id="teacherRegisterConfirmedPassword"
                placeholder="请确认密码"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="identity1" class="col-sm-5 col-form-label text-end">
              <img src="@/assets/images/zcimg4.png" width="18" height="15" />
              选择身份
            </label>
            <div class="col-sm-7">
              <div class="row align-items-center">
                <div class="col">
                  <div class="form-check">
                    <input
                      v-model="identity"
                      class="form-check-input"
                      type="radio"
                      name="identity"
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
                      v-model="identity"
                      class="form-check-input"
                      type="radio"
                      name="identity"
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
                      v-model="identity"
                      class="form-check-input"
                      type="radio"
                      name="identity"
                      value="其他(已经毕业离校的人员)"
                    />
                    <label class="form-check-label" for="identity"
                      >其他(已经毕业离校的人员)</label
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center"> 是否同意注册条款
          <input @change="checkedIsAgree" name="acceptxieyi" id="acceptxieyi" type="checkbox" :value="is_agree" />
          <strong class="red">是</strong> <a href="#registration-terms" class="green linelink">点击查看注册条款</a></div>
          <div
            class="btbg1"
            style="
              background-color: rgb(216,247,245);
              height: 30px;
              width: 975px;
            "
          >
            <img src="@/assets/images/zcimg3.png" style="margin-left: 10px" />
            <span style="margin-left: 10px; color: blue; font-weight: bold"
              >请填写基本信息</span
            >
          </div>

          <div class="zsbg" style="width: 975px; background-color: rgb(255,249,237); color: orange; font-weight:500;">注意：(1)一经填写不得修改，与证件不符将不能通过认证(除外籍人士外禁止英文或拼音)<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2)出于隐私考虑，本网将只显示您的姓，如&ldquo;王明&rdquo;只显示为&ldquo;王教员&rdquo;。 
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label
              for="teacherName"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>真实姓名
            </label>
            <div class="col-sm-7">
              <input
                v-model="name"
                type="text"
                class="form-control"
                id="teacherName"
                placeholder="请输入真实姓名"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label for="studentSex" class="col-sm-5 col-form-label text-end">
              <span style="color: red; font-weight: bold">*</span>性别
            </label>
            <div class="col-sm-7">
              <input
                v-model="sex"
                class="form-check-input"
                type="radio"
                name="gender"
                id="male"
                value="男"
              />
              <label class="form-check-label" for="male"> 男 </label>
              <input
                v-model="sex"
                class="form-check-input"
                type="radio"
                name="gender"
                id="female"
                value="女"
                style="margin-left: 10px"
              />
              <label class="form-check-label" for="female"> 女 </label>
            </div>
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label
              for="age"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>年龄
            </label>
            <div class="col-sm-7">
              <input
                v-model="age"
                type="text"
                class="form-control"
                id="age"
                placeholder="请输入真实年龄"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label
              for="education"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>目前学历
            </label>
            <div class="col-sm-7">
              <select v-model="education" class="form-select" id="education">
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
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label
              for="school"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>就读或毕业学校
            </label>
            <div class="col-sm-7">
              <input
                v-model="school"
                type="text"
                class="form-control"
                id="school"
                placeholder="请输入就读或毕业学校"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label
              for="major"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold;">*</span>专业
            </label>
            <div class="col-sm-7">
              <input
                v-model="major"
                type="text"
                class="form-control"
                id="major"
                placeholder="请输入专业"
              />
            </div>
          </div>
          <div
            class="btbg1"
            style="
              background-color: rgb(216,247,245);
              height: 30px;
              width: 975px;
            "
          >
            <img src="@/assets/images/zcimg3.png" style="margin-left: 10px" />
            <span style="margin-left: 10px; color: blue; font-weight: bold"
              >请填写联系方式</span
            >
          </div>
          <div class="mb-3 row align-items-center" style="margin-top: 10px;">
            <label for="teacherPhone" class="col-sm-5 col-form-label text-end">
              <span style="color: red; font-weight: bold">*</span>手机号
            </label>
            <div class="col-sm-7">
              <input
                v-model="phone"
                type="text"
                class="form-control"
                id="teacherPhone"
                placeholder="请输入手机号"
              />
            </div>
          </div>
          <div class="mb-3 row align-items-center">
            <label
              for="address"
              class="col-sm-5 col-form-label text-end"
            >
              <span style="color: red; font-weight: bold">*</span>通讯地址
            </label>
            <div class="col-sm-7">
              <input
                v-model="address"
                type="text"
                class="form-control"
                id="address"
                placeholder="请输入通讯地址"
              />
            </div>
          </div>
          <div class="d-flex justify-content-center" style="margin-left: 100px">
            <div class="error-message">{{ error_message }}</div>
          </div>
          <div
            class="mb-3 d-flex justify-content-center"
            style="margin-left: 100px; margin-top: 15px"
          >
            <button
              type="submit"
              class="btn btn-primary me-2"
              style="width: 100px"
            >
              注册
            </button>
            <button type="reset" class="btn btn-primary" style="width: 100px">
              重置
            </button>
          </div>
        </form>
        <div
            class="btbg1"
            style="
              background-color: rgb(255, 243, 207);
              height: 30px;
              width: 975px;
            "
          >
            <img src="@/assets/images/zcimg2.png" style="margin-left: 10px" />
            <span style="margin-left: 10px; color: red; font-weight: bold"
              >注意条款</span
            >
          </div>
          <div class="d-flex justify-content-center">
            <textarea id="registration-terms" name="textarea" cols="90" rows="11" style="font-size: small; width: 700px; margin-top: 10px;">注意事项：
1、本站只接收各类教师、 正规大学大专以上大学生、研究生和有专业特长的工作者。
  【注】对于在校大学生，本站只接受国家承认学历的正规大学生、研究生注册
2、注册和发布简历不收费，但成功一份家教教员应及时缴纳自己的信息费以及代收学员的会员费（教员第一次上课成功并且收到费用才需要汇款）。
3、保证所填信息真实有效，如因信息不实而引起的各种不良事宜，一律自行承担全部责任。
4、如果有学员家长觉得你的条件与他的要求相符，会在线预约您；在核实了家长的情况后，我们会通知您。
5、如果是女教员，从个人的安全角度出发，在初次上门试讲时，最好带上一名男性同伴，以免发生意外。
6、易教网仅负责提供家教信息，不承担家教信息以外的其它责任。
7、请分步骤一次性填写好自己的全部资料，否则易教网工作人员将删除信息不全的注册用户！
   一旦成为我们的会员保证不填写隐瞒信息，如有不实，责任完全由会员自行承担责任。
  </textarea>
          </div>
        <div>
          <router-link
            class="nav-link"
            :to="{ name: 'home' }"
            style="margin-left: auto; cursor: pointer"
            role="button"
          >
            &lt;&lt; 返回首页
          </router-link>
        </div>
      </div>
    </div>
  </ContentField>
</template>
    
    <script>
import ContentField from "@/components/ContentField.vue";
import { ref } from "vue";
//import { useStore } from "vuex"
import router from "@/router";
import $ from "jquery";

export default {
  components: {
    ContentField,
  },

  setup() {
    //const store = useStore();
    let username = ref("");
    let password = ref("");
    let confirmedPassword = ref("");
    let identity = ref("");
    let name = ref("");
    let sex = ref("");
    let age = ref("");
    let education = ref("");
    let school = ref("");
    let major = ref("");
    let phone = ref("");
    let address = ref("");
    let is_agree = ref("");
    is_agree.value = 0;
    let error_message = ref("");
    let ad = ref("");
    ad.value = "西安地区";

    const checkedIsAgree = () => {
      if (is_agree.value == 0) {
        is_agree.value = 1;
      } else if (is_agree.value == 1) {
        is_agree.value = 0;
      }
    }

    const register = () => {
      error_message.value = "";
      $.ajax({
        url: "http://127.0.0.1:3000/teacher/account/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
          identity: identity.value,
          realname: name.value,
          sex: sex.value,
          age: age.value,
          education: education.value,
          school: school.value,
          major: major.value,
          phone: phone.value,
          address: address.value,
          is_agree: is_agree.value,
        },
        success(resp) {
          if (resp.success == true) {
            router.push({ name: "user_account_login" });
          } else {
            error_message.value = resp.errorMsg;
          }
          console.log(resp);
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    return {
      username,
      password,
      confirmedPassword,
      identity,
      name,
      sex,
      age,
      education,
      school,
      major,
      phone,
      address,
      is_agree,
      error_message,
      ad,
      register,
      checkedIsAgree,
    };
  },
};
</script>
    
  <style scoped>
div.error-message {
  font-weight: 700;
  color: red;
}
</style>
    
    