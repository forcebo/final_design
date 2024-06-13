import $ from 'jquery'

export default ({
  state: {
    id: "",
    username: "",
    photo: "",
    token: "",
    phone: "",
    role: "",
    is_login: false,
    pulling_info: true, // 是否正在拉取信息
  },
  getters: {
  },
  mutations: {
    updateAdmin(state, admin) {
        state.id = admin.id;
        state.username = admin.username;
        state.photo = admin.photo;
        state.phone = admin.phone;
        state.role = admin.role;
        state.is_login = true;
    },
    updateAdminToken(state, token) {
        state.token = token;
    },
    adminLogout(state) {
        state.id = "";
        state.username = "";
        state.photo = "";
        state.phone = "";
        state.token = "";
        state.role = "";
        state.is_login = false;
    },
    updateAdminPullingInfo(state, pulling_info) {
        state.pulling_info = pulling_info;
    }
  },
  actions: {
    adminLogin(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/admin/account/token/",
            type: "post",
            data: {
                username: data.username,
                password: data.password,
            },
            success(resp) {
                if (resp.success == true) {
                    localStorage.setItem("jwt_token", resp.data);
                    localStorage.setItem("role", "admin");
                    context.commit("updateAdminToken", resp.data);
                }
                data.success(resp);
            },
            error(resp) {
                data.error(resp);
            }
        });    
    },
    getAdminInfo(context, data) {
        $.ajax({
            url: "http://127.0.0.1:3000/admin/account/info/",
            type: "get",
            headers: {
                Authorization: "Bearer " + context.state.token,
            },
            success(resp) {
                if (resp.success == true) {
                    context.commit("updateAdmin", {
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
    adminLogout(context) {
        context.commit("adminLogout");
        localStorage.removeItem("jwt_token");
        localStorage.removeItem("role");
    }
  },
  modules: {
  }
})
