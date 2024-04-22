import { createStore } from 'vuex'
import ModuleStudent from '@/store/student'
import ModuleTeacher from '@/store/teacher'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    student: ModuleStudent,
    teacher: ModuleTeacher,
  }
})
