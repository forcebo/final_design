import $ from 'jquery'

export default ({
  state: {
    id: "",
    username: "",
    photo: "",
    realname: "",
    sex: "",
    phone: "",
    address: "",
    token: "",
    role: "",
    is_login: false,
    pulling_info: true, // 是否正在拉取信息
  },
  getters: {
  },
  mutations: {
    updateStudent(state, student) {
        state.id = student.id;
        state.username = student.username;
        state.photo = student.photo;
        state.realname = student.realname;
        state.sex = student.sex;
        state.phone = student.phone;
        state.address = student.address;
        state.role = student.role;
        state.is_login = true;
    },
    updateStudentToken(state, token) {
        state.token = token;
    },
    studentLogout(state) {
        state.id = "";
        state.username = "";
        state.photo = "";
        state.realname = "";
        state.sex = "";
        state.phone = "";
        state.address = "";
        state.token = "";
        state.role = "";
        state.is_login = false;
    },
    updateStudentPullingInfo(state, pulling_info) {
        state.pulling_info = pulling_info;
    }
  },
  actions: {
    studentLogin(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/student/account/token/",
            type: "post",
            data: {
                username: data.username,
                password: data.password,
            },
            success(resp) {
                if (resp.success == true) {
                    localStorage.setItem("jwt_token", resp.data);
                    context.commit("updateStudentToken", resp.data);
                }
                data.success(resp);
            },
            error(resp) {
                data.error(resp);
            }
        });    
    },
    getStudentInfo(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/student/account/info/",
            type: "get",
            headers: {
                Authorization: "Bearer " + context.state.token,
            },
            success(resp) {
                if (resp.success == true) {
                    localStorage.setItem("role", resp.data.role);
                    context.commit("updateStudent", {
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
    studentLogout(context) {
        context.commit("studentLogout");
        localStorage.removeItem("jwt_token");
        localStorage.removeItem("role");
    }
  },
  modules: {
  }
})
