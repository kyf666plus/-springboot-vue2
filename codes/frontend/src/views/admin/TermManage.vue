<!-- TermManage.vue -->

<template>
  <div>
    <el-container>
      <el-main>
        <el-button type="primary" @click="openTermManageDialog(null)">新增学期</el-button>

        <!-- 表格 -->
        <el-table :data="tableData" style="width: 100%">
  <el-table-column prop="termname" label="学期名称" width="180"></el-table-column>
  <el-table-column prop="date" label="日期"></el-table-column>
  <el-table-column prop="week" label="周数"></el-table-column>
  <el-table-column prop="courseload" label="课程负载"></el-table-column>
  
  <el-table-column label="操作">
    <template slot-scope="scope">
      <el-button type="primary" @click="openTermManageDialog(scope.row.termname)">编辑</el-button>
      <el-button type="danger" @click="deleteTerm(scope.row.termname)">删除</el-button>
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

    <!-- 学期管理对话框 -->
    <el-dialog :visible="termManageDialogVisible" @close="closeTermManageDialog" title="学期管理">
      <el-form :model="termForm" label-width="80px">
        <el-form-item label="学期名称">
          <el-input v-model="termForm.termname"></el-input>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker v-model="termForm.date" type="date" format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="周数">
          <el-input v-model="termForm.week"></el-input>
        </el-form-item>
        <el-form-item label="课程负载">
  <el-input v-model="termForm.courseload"></el-input>
        </el-form-item>
       

        <!-- 其他字段 -->

        <el-form-item>
          <el-button type="primary" @click="saveOrUpdateTerm">保存</el-button>
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
      termManageDialogVisible: false,
      termForm: {
        termname: '',
        date: '',
        week: '',
        courseload: '',
        
      },
      selectedTermName: null,
    };
  },

  methods: {
    // 获取学期列表
    fetchTerms() {
      axios.get('http://localhost:8088/term').then((response) => {
        this.tableData = response.data;
        // 将日期时间字符串转换为日期对象
        
      });
    },
    openTermManageDialog(termName) {
  this.selectedTermName = termName;

  if (termName) {
    // 编辑模式，根据学期名称获取学期信息填充表单
    const selectedTerm = this.tableData.find((term) => term.termname === termName);
    this.termForm = { ...selectedTerm };
  } else {
    // 新增模式，清空表单
    this.termForm = {
      termname: '',
      date: '',
      week: '',
      courseload: '',
      // 其他字段
    };
  }

  this.termManageDialogVisible = true;
},



    // 关闭学期管理对话框
    closeTermManageDialog() {
      this.termManageDialogVisible = false;
    },
    // 保存或更新学期
    saveOrUpdateTerm() {
      const isEditMode = !!this.selectedTermName;

      const apiUrl = isEditMode
        ? `http://localhost:8088/term/${this.selectedTermName}`
        : 'http://localhost:8088/term';

      const httpMethod = isEditMode ? 'put' : 'post';

      

      axios[httpMethod](apiUrl, this.termForm).then((response) => {
        console.log(response.data);
        this.closeTermManageDialog();
        this.fetchTerms(); // 刷新学期列表
      });
    },

    // 删除学期
    deleteTerm(termName) {
      axios
        .delete(`http://localhost:8088/term/${termName}`)
        .then((response) => {
          console.log(response.data);
          this.fetchTerms(); // 刷新学期列表
        })
        .catch((error) => {
          console.error(`Delete term ${termName} failed:`, error);
        });
    },

    // 分页大小变化
    handleSizeChange(val) {
      alert('每页记录变化' + val);
    },
  },
  created() {
    this.fetchTerms();
  },
};
</script>
