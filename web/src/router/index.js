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
    path: "/course/listen/",
    name: "course_listen_index",
    component: CourseListenView,
    meta: {
      requestAuth: true,
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
