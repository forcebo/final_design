import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView'
import TutorSearchView from '@/views/TutorSearchView'
import ReserveView from '@/views/ReserveView'
import CourseListenView from '@/views/CourseListenView'
import ReleaseRequirementView from '@/views/ReleaseRequirementView'
import NotFoundView from '@/views/NotFoundView'
import UserInfoView from '@/views/UserInfoView'
import UserAccountLoginView from '@/views/user/account/UserAccountLoginView'
import UserAccountRegisterMidiumView from '@/views/user/account/UserAccountRegisterMidiumView'
import store from '@/store/index'
import StudentAccountRegisterView from '@/views/user/account/student/StudentAccountRegisterView'
import TeacherAccountRegisterView from '@/views/user/account/teacher/TeacherAccountRegisterView'

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
    path: "/reserve/",
    name: "reserve_index",
    component: ReserveView,
    meta: {
      requestAuth: true,
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
    path: "/user/info/",
    name: "user_info_index",
    component: UserInfoView,
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
    next({name: "user_account_login"});
  } else {
    next();
  }
})

export default router
