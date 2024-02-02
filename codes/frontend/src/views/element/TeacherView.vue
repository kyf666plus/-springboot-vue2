<template>
    <div>
      <el-container>
        <!-- 侧边栏 -->
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '3']">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-message"></i>教师权限
              </template>
              <el-menu-item-group title="">
                <router-link to="/teacher/lesson">
                  <el-menu-item index="1-1" @click="openLessonDialog">课程管理</el-menu-item>
                </router-link>
                <router-link to="/teacher/experiment">
                  <el-menu-item index="2-2" @click="openExperimentDialog">实验管理</el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
  
        <!-- 主体内容 -->
        <el-container>
          <el-header>
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px"></i>
              <el-dropdown-menu slot="dropdown">
                <!-- 可以在这里添加其他设置项 -->
              </el-dropdown-menu>
            </el-dropdown>
            <el-button type="text" class="logout-button" @click="logout">
      <i class="el-icon-switch-button"></i> 退出
    </el-button>
          </el-header>
  
          <!-- 课程管理对话框 -->
          <el-dialog :visible="lessonDialogVisible" @close="closeLessonDialog" title="课程管理">
            <!-- 在对话框中使用 LessonManage 组件 -->
            <LessonManage />
          </el-dialog>
  
          <!-- 实验管理对话框 -->
          <el-dialog :visible="experimentDialogVisible" @close="closeExperimentDialog" title="实验管理">
            <!-- 在对话框中使用 ExperimentManage 组件 -->
            <ExperimentManage />
          </el-dialog>
  
          <!-- 编辑图标 -->
          <i class="el-icon-edit" @click="openLessonDialog"></i>
        </el-container>
      </el-container>
    </div>
  </template>
  
  <script>
  import LessonManage from '@/views/admin/LessonManage.vue';
  import ExperimentManage from '@/views/admin/ExperimentManage.vue';
  
  export default {
    data() {
      return {
        lessonDialogVisible: false,
        experimentDialogVisible: false,
      };
    },
    components: {
      LessonManage,
      ExperimentManage,
    },
    methods: {
      openLessonDialog() {
        this.lessonDialogVisible = true;
      },
      closeLessonDialog() {
        this.lessonDialogVisible = false;
      },
      openExperimentDialog() {
        this.experimentDialogVisible = true;
      },
      closeExperimentDialog() {
        this.experimentDialogVisible = false;
      },
      //退出当前界面回到登录界面
      logout() {
    this.$router.push('/login');
  },
    },
  };
  </script>
  
  <style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }

  .logout-button {
  margin-right: 15px;
  color: red; 
}

.logout-button i {
  color: red; 
}
  </style>
  