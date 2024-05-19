import $ from 'jquery'

export default ({
  state: {
    id: "",
    username: "",
    photo: "",
    identity: "",
    realname: "",
    sex: "",
    age: "",
    education: "",
    school: "",
    major: "",
    phone: "",
    address: "",
    role: "",
    token: "",
    is_agree: "",
    is_login: false,
    pulling_info: true, // 是否正在拉取信息
  },
  getters: {
  },
  mutations: {
    updateTeacher(state, teacher) {
        state.id = teacher.id;
        state.username = teacher.username;
        state.photo = teacher.photo;
        state.identity = teacher.identity;
        state.realname = teacher.realname;
        state.sex = teacher.sex;
        state.age = teacher.age;
        state.education = teacher.education;
        state.school = teacher.school;
        state.major = teacher.major;
        state.phone = teacher.phone;
        state.address = teacher.address;
        state.role = teacher.role;
        state.is_agree = teacher.is_agree;
        state.is_login = true;
    },
    updateTeacherToken(state, token) {
        state.token = token;
    },
    teacherLogout(state) {
        state.id = "";
        state.username = "";
        state.photo = "";
        state.identity = "";
        state.realname = "";
        state.sex = "";
        state.age = "";
        state.education = "";
        state.school = "";
        state.major = "";
        state.phone = "";
        state.address = "";
        state.role = "";
        state.token = "";
        state.is_agree = "";
        state.is_login = false;
    },
    updateTeacherPullingInfo(state, pulling_info) {
        state.pulling_info = pulling_info;
    }
  },
  actions: {
    teacherLogin(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/teacher/account/token/",
            type: "post",
            data: {
                username: data.username,
                password: data.password,
            },
            success(resp) {
                if (resp.success == true) {
                    localStorage.setItem("jwt_token", resp.data);
                    context.commit("updateTeacherToken", resp.data);
                }
                data.success(resp);
            },
            error(resp) {
                data.error(resp);
            }
        });    
    },
    getTeacherInfo(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/teacher/account/info/",
            type: "get",
            headers: {
                Authorization: "Bearer " + context.state.token,
            },
            success(resp) {
                if (resp.success == true) {
                    localStorage.setItem("role", resp.data.role);
                    context.commit("updateTeacher", {
                        ...resp.data,
                        is_login: true,
                    });
                    data.success(resp);
                } else {
                    data.error(resp);
                }
            },
            error(resp) {
                data.error(resp);
            }
        })
    },
    teacherLogout(context) {
        context.commit("teacherLogout");
        localStorage.removeItem("jwt_token");
        localStorage.removeItem("role");
    }
  },
  modules: {
  }
})
