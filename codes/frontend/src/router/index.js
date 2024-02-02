// router.js

import Vue from 'vue';
import VueRouter from 'vue-router';
import ElementComponent from '../views/element/ElementView.vue'; // Update the path
import UserManage from '../views/admin/UserManage.vue';
import TermManage from '../views/admin/TermManage.vue';
import LaboratoryManage from '../views/admin/LaboratoryManage.vue';
import TeacherViewComponent from '../views/element/TeacherView.vue';
import LessonManage from '../views/admin/LessonManage.vue';
import ExperimentManage from '../views/admin/ExperimentManage.vue';

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    {
      path: '/admin',
      component: ElementComponent,
      children: [
        { path: 'user', component: UserManage },
        { path: 'term', component: TermManage },
        { path: 'laboratory', component: LaboratoryManage },
      ],
    },
    {
      path: '/teacher',
      component: TeacherViewComponent,
      children: [
        { path: 'lesson', component: LessonManage },
        { path: 'experiment', component: ExperimentManage },
      ],
    },
    {
      path:'/',
      redirect: '/login'
    },
    {
      path:'/login',
      component:() => import('../views/login/LoginView.vue')
    },
    {
      path:'/unlogin',
      component:() => import('../views/login/UnloginView.vue')
    }
  ],
});
// 导航守卫，用于检查身份验证和用户类型
router.beforeEach((to, from, next) => {
  const isLoggedIn = localStorage.getItem('token') !== null;
  const user = JSON.parse(localStorage.getItem('user')); // 从本地存储中获取用户信息
  const isAdmin = user && user.isAdmin;

  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 需要身份验证的路由
    if (!isLoggedIn) {
      // 用户未登录，重定向到登录页面
      next('/login');
    } else {
      // 用户已登录，检查用户是否具有所需类型
      if ((to.matched[0].meta.isAdmin && isAdmin) || (!to.matched[0].meta.isAdmin && !isAdmin)) {
        next();  // 继续到下一个路由
      } else {
        // 用户类型不匹配，重定向到登录页面
        next('/login');
      }
    }
  } else {
    // 不需要身份验证的路由，继续到下一个路由
    next();
  }
});

export default router;
