<template>
  <div>
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>管理员权限
            </template>
            <el-menu-item-group title="">
              <router-link to="/admin/user">
                <el-menu-item index="1-1" @click="openUserManageDialog">用户管理</el-menu-item>
              </router-link>
              <router-link to="/admin/term">
                <el-menu-item index="2-2" @click="openTermManageDialog">学期管理</el-menu-item>
              </router-link>
              <router-link to="/admin/laboratory">
                <el-menu-item index="3-3" @click="openLaboratoryManageDialog">实验室管理</el-menu-item>
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
             
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="text" class="logout-button" @click="logout">
      <i class="el-icon-switch-button"></i> 退出
    </el-button>
        </el-header>

        <!-- 用户管理对话框 -->
        <el-dialog :visible="userManageDialogVisible" @close="closeUserManageDialog" title="用户管理">
          <!-- 在对话框中使用 UserManage 组件 -->
          <UserManage />
        </el-dialog>

        <!-- 实验室管理对话框 -->
        <el-dialog :visible="laboratoryManageDialogVisible" @close="closeLaboratoryManageDialog" title="实验室管理">
          <!-- 在对话框中使用 LaboratoryManage 组件 -->
          <LaboratoryManage />
        </el-dialog>

        <!-- 学期管理对话框 -->
        <el-dialog :visible="termManageDialogVisible" @close="closeTermManageDialog" title="学期管理">
          <!-- 在对话框中使用 TermManage 组件 -->
          <TermManage />
        </el-dialog>

        <!-- 编辑图标 -->
        <i class="el-icon-edit" @click="openTermManageDialog"></i>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import UserManage from '@/views/admin/UserManage.vue'; 
import LaboratoryManage from '@/views/admin/LaboratoryManage.vue';
import TermManage from '@/views/admin/TermManage.vue';

export default {
  data() {
    return {
      tableData: [],
      userManageDialogVisible: false,
      laboratoryManageDialogVisible: false,
      termManageDialogVisible: false,
    };
  },
  components: {
    UserManage,
    LaboratoryManage,
    TermManage,
  },
  methods: {

    openLaboratoryManageDialog() {
      this.laboratoryManageDialogVisible = true;
    },
    closeLaboratoryManageDialog() {
      this.laboratoryManageDialogVisible = false;
    },
    openTermManageDialog() {
      this.termManageDialogVisible = true;
    },
    closeTermManageDialog() {
      this.termManageDialogVisible = false;
    },
    openUserManageDialog() {
      this.userManageDialogVisible = true;
    },
    closeUserManageDialog() {
      this.userManageDialogVisible = false;
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
