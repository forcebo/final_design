import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView'
import TutorSearchView from '@/views/TutorSearchView'
import TutorDetailView from '@/views/TutorDetailView'
import CourseListenView from '@/views/CourseListenView'
import ReleaseRequirementView from '@/views/ReleaseRequirementView'
import NotFoundView from '@/views/NotFoundView'
import StudentInfoView from '@/views/student/StudentInfoView'
import UserAccountLoginView from '@/views/user/account/UserAccountLoginView'
import UserAccountRegisterMidiumView from '@/views/user/account/UserAccountRegisterMidiumView'
import store from '@/store/index'
import StudentAccountRegisterView from '@/views/user/account/student/StudentAccountRegisterView'
import TeacherAccountRegisterView from '@/views/user/account/teacher/TeacherAccountRegisterView'
import StudentInfoUpdateView from '@/views/student/StudentInfoUpdateView'
import StudentPasswordUpdateView from '@/views/student/StudentPasswordUpdateView'
import ReleaseJobInfomationView from '@/views/ReleaseJobInformationView'
import TeacherInfoView from '@/views/teacher/TeacherInfoView'
import TeacherInfoUpdateView from '@/views/teacher/TeacherInfoUpdateView'
import TeacherPasswordUpdateView from '@/views/teacher/TeacherPasswordUpdateView'
import TutorReserveView from '@/views/TutorReserveView'
import TutorCourseView from '@/views/TutorCourseView'
import StudentReservesView from '@/views/student/StudentReservesView'
import StudentCourseOrdersView from '@/views/student/StudentCourseOrdersView'
import AdminHomeVuew from '@/views/admin/AdminHomeView.vue'
import AdminLoginView from '@/views/admin/AdminLoginView'
import CommentAuditView from '@/views/admin/CommentAuditView'
import CourseManagementView from '@/views/admin/CourseManagementView'
import JobInfomationAuditView from '@/views/admin/JobInfomationAuditView'
import OrderManagementView from '@/views/admin/OrderManagementView'
import TutorRequirementAuditView from '@/views/admin/TutorRequirementAuditView'
import UserInfoStatisticsAuditView from '@/views/admin/UserInfoStatisticsAuditView'
import UserManagementView from '@/views/admin/UserManagementView'
import TeacherCommentsView from '@/views/teacher/TeacherCommentsView'
import TeacherReservesView from '@/views/teacher/TeacherReservesView'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/home/",
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/home/",
    name: "home_index",
    component: HomeView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/tutor/search/",
    name: "tutor_search_index",
    component: TutorSearchView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/tutor/detail/",
    name: "tutor_detail_index",
    component: TutorDetailView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/tutor/reserve/",
    name: "tutor_reserve_index",
    component: TutorReserveView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/tutor/course/",
    name: "tutor_course_index",
    component: TutorCourseView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/tutor/course/listen/",
    name: "tutor_course_listen_index",
    component: CourseListenView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/release/requirement/",
    name: "release_requirement_index",
    component: ReleaseRequirementView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/student/info/",
    name: "student_info_index",
    component: StudentInfoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/student/info/update/",
    name: "studnet_info_update_index",
    component: StudentInfoUpdateView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/student/info/password/update/",
    name: "studnet_password_update_index",
    component: StudentPasswordUpdateView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterMidiumView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/student/account/register/",
    name: "student_account_register",
    component: StudentAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/student/info/course/orders/",
    name: "student_info_course_orders_reserves",
    component: StudentCourseOrdersView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/student/info/reserves/",
    name: "student_info_reserves",
    component: StudentReservesView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/teacher/account/register/",
    name: "teacher_account_register",
    component: TeacherAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  
  {
    path: "/teacher/release/job/information/",
    name: "teacher_release_job_information_index",
    component: ReleaseJobInfomationView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/teacher/info/",
    name: "teacher_info_index",
    component: TeacherInfoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/teacher/info/update/",
    name: "teacher_info_update_index",
    component: TeacherInfoUpdateView,
    meta: {
      requestAuth: true,
    }
  },
  
  {
    path: "/teacher/info/password/update/",
    name: "teacher_info_password_index",
    component: TeacherPasswordUpdateView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/teacher/info/comments/",
    name: "teacher_info_comments_index",
    component: TeacherCommentsView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/teacher/info/reserves/",
    name: "teacher_info_reserves_index",
    component: TeacherReservesView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/login/",
    name: "admin_login_index",
    component: AdminLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/",
    name: "首页",
    component: AdminHomeVuew,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/user/management/",
    name: "用户管理",
    component: UserManagementView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/tutor/requirement/audit/",
    name: "家教需求信息审核",
    component: TutorRequirementAuditView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/job.infomation/audit/",
    name: "求职信息审核",
    component: JobInfomationAuditView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/course/management/",
    name: "课程管理",
    component: CourseManagementView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/order/mangement/",
    name: "订单管理",
    component: OrderManagementView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/comment/audit/",
    name: "评价审核",
    component: CommentAuditView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/admin/user/info/statistics/audit/",
    name: "用户信息统计分析",
    component: UserInfoStatisticsAuditView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFoundView,
    meta: {
      requestAuth: false,
    }
  },
  
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.student.is_login) {
    if (store.state.teacher.is_login) {
      next();
    } else {
      next({name: "user_account_login"});
    }
  } else {
    next();
  }
})

export default router
