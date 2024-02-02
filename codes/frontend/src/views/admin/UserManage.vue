<!-- UserManage.vue -->

<template>
  <div>
    <el-container>
      <el-main>
        <el-button type="primary" @click="openUserManageDialog(null)">新增用户</el-button>

        <!-- 表格 -->
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="id" label="工号" width="100"></el-table-column>
          <el-table-column prop="username" label="姓名" width="100"></el-table-column>
          <el-table-column prop="idCard" label="身份证号" width="120"></el-table-column>
          <el-table-column prop="admin" label="管理员">
            <template slot-scope="scope">
              <div>
                {{ scope.row.admin ? '是' : '否' }}
              </div>

            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="openUserManageDialog(scope.row.id)">编辑</el-button>
              <el-button type="danger" @click="deleteUser(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          :background="true"
          layout="prev, pager, next, jumper"
          @size-change="handleSizeChange"
          :total="1000"
        ></el-pagination>
      </el-main>
    </el-container>

  
   <!-- 用户管理对话框 -->
<el-dialog :visible="userManageDialogVisible" @close="closeUserManageDialog" title="用户管理">
  <el-form :model="userForm" label-width="80px">
    <el-form-item label="姓名">
      <el-input v-model="userForm.username"></el-input>
    </el-form-item>
    <el-form-item label="身份证号">
      <el-input v-model="userForm.idCard"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="userForm.password" type="password"></el-input>
    </el-form-item>
    <el-form-item label="管理员">
      <el-switch v-model="userForm.admin" active-text="是" inactive-text="否"></el-switch>
    </el-form-item>

    <!-- 其他字段 -->

    <el-form-item>
      <el-button type="primary" @click="saveOrUpdateUser">保存</el-button>
    </el-form-item>
  </el-form>
</el-dialog>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      tableData: [],
      userManageDialogVisible: false,
      userForm: {
        username: '',
        idCard: '',
        password: '',
        admin: false,
      },
      selectedUserId: null,
    };
  },

  methods: {
    // 获取用户列表
    fetchUsers() {
      axios.get('http://localhost:8088/user').then((response) => {
        this.tableData = response.data;
      });
    },
    openUserManageDialog(userId) {
  this.selectedUserId = userId;

  if (userId) {
    // 编辑模式，根据用户ID获取用户信息填充表单
    const selectedUser = this.tableData.find((user) => user.id === userId);
    this.userForm = { ...selectedUser };
  } else {
    // 新增模式，清空表单
    this.userForm = {
      username: '',
      idCard: '',
      password: '',
      isAdmin: false,
      // 其他字段
    };
  }

  this.userManageDialogVisible = true;
},


    // 关闭用户管理对话框
    closeUserManageDialog() {
      this.userManageDialogVisible = false;
    },
    // 保存或更新用户
    saveOrUpdateUser() {
      // 判断是新增还是编辑
      const isEditMode = !!this.selectedUserId;

      const apiUrl = isEditMode
        ? `http://localhost:8088/user/${this.selectedUserId}`
        : 'http://localhost:8088/user';

      const httpMethod = isEditMode ? 'put' : 'post';

      // 发送请求
      axios[httpMethod](apiUrl, this.userForm).then((response) => {
        console.log(response.data);
        this.closeUserManageDialog();
        this.fetchUsers(); // 刷新用户列表
      });
    },

    // 删除用户
    deleteUser(userId) {
      // 发送删除请求
      axios
        .delete(`http://localhost:8088/user/${userId}`)
        .then((response) => {
          console.log(response.data);
          this.fetchUsers(); // 刷新用户列表
        })
        .catch((error) => {
          console.error(`Delete user ${userId} failed:`, error);
        });
    },

    // 分页大小变化
    handleSizeChange(val) {
      alert('每页记录变化' + val);
    },
  },
  created() {
    this.fetchUsers();
  },
};
</script>

<style scoped>
  
  .el-input input {
    color: black;
  }
</style>