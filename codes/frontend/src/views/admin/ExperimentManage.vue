<!-- ExperimentManage.vue -->

<template>
    <div style="color:black">
      <el-container>
        <el-main>
          <el-button type="primary" @click="openExperimentManageDialog(null)">新增实验</el-button>
  
          <!-- 表格 -->
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="experimentName" label="实验名称" width="180"></el-table-column>
            <el-table-column prop="courseName" label="所属课程" width="180"></el-table-column>
            <el-table-column prop="labUpdate" label="是否更新、新增"></el-table-column>
            <el-table-column prop="labType" label="实验类别"></el-table-column>
            <el-table-column prop="nature" label="实验项目性质"></el-table-column>
            <el-table-column prop="requirement" label="实验要求"></el-table-column>
            <el-table-column prop="groupsNumber" label="组人数"></el-table-column>
            <el-table-column prop="labLocation" label="实验室地点"></el-table-column>
            <el-table-column prop="labTime" label="实验时间"></el-table-column>
            <el-table-column prop="labTeacher" label="实验辅导教师"></el-table-column>
            <el-table-column prop="week" label="周数"></el-table-column>
            <el-table-column prop="workday" label="工作日"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="openExperimentManageDialog(scope.row.experimentName)">编辑</el-button>
                <el-button type="danger" @click="deleteExperiment(scope.row.experimentName)">删除</el-button>
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
  
      <!-- 实验管理对话框 -->
      <el-dialog :visible="experimentManageDialogVisible" @close="closeExperimentManageDialog" title="实验管理">
  <el-form :model="experimentForm" label-width="80px">
    <el-form-item label="所属课程">
      <el-input v-model="experimentForm.courseName"></el-input>
    </el-form-item>
    <el-form-item label="实验名称">
      <el-input v-model="experimentForm.experimentName"></el-input>
    </el-form-item>
    <el-form-item label="是否更新、新增">
      <el-input v-model="experimentForm.labUpdate"></el-input>
    </el-form-item>
    <el-form-item label="实验类别">
      <el-input v-model="experimentForm.labType"></el-input>
    </el-form-item>
    <el-form-item label="实验项目性质">
      <el-input v-model="experimentForm.nature"></el-input>
    </el-form-item>
    <el-form-item label="实验要求">
      <el-input v-model="experimentForm.requirement"></el-input>
    </el-form-item>
    <el-form-item label="组人数">
      <el-input v-model="experimentForm.groupsNumber"></el-input>
    </el-form-item>
    <el-form-item label="实验室地点">
      <el-input v-model="experimentForm.labLocation"></el-input>
    </el-form-item>
    <el-form-item label="实验时间">
      <el-input v-model="experimentForm.labTime"></el-input>
    </el-form-item>
    <el-form-item label="实验辅导教师">
      <el-input v-model="experimentForm.labTeacher"></el-input>
    </el-form-item>
    <el-form-item label="周数">
        <el-input v-model="experimentForm.week" ></el-input>
        </el-form-item>
        <el-form-item label="工作日">
          <el-input v-model="experimentForm.workday" ></el-input>
        </el-form-item>

    <!-- 其他字段 -->

    <el-form-item>
      <el-button type="primary" @click="saveOrUpdateExperiment">保存</el-button>
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
        experimentManageDialogVisible: false,
        experimentForm: {
          courseName: '',
          experimentName: '',
          labUpdate: '',
          labType: '',
          nature: '',
          requirement: '',
          groupsNumber: '',
          labLocation: '',
          labTime: '',
          labTeacher: '',
          week:'',
          workday:''
          // 其他字段
        },
        selectedExperimentName: null,
      };
    },
  
    methods: {
      // 获取实验列表
      fetchExperiments() {
        axios.get('http://localhost:8088/experiment').then((response) => {
          this.tableData = response.data;
        });
      },
      openExperimentManageDialog(experimentName) {
        this.selectedExperimentName = experimentName;
  
        if (experimentName) {
          // 编辑模式，根据实验名称获取实验信息填充表单
          const selectedExperiment = this.tableData.find((experiment) => experiment.experimentName === experimentName);
          this.experimentForm = { ...selectedExperiment };
        } else {
          // 新增模式，清空表单
          this.experimentForm = {
            courseName: '',
            experimentName: '',
            labUpdate: '',
            labType: '',
            nature: '',
            requirement: '',
            groupsNumber: '',
            labLocation: '',
            labTime: '',
            labTeacher: '',
            week:'',
            workday:''
            // 其他字段
          };
        }
  
        this.experimentManageDialogVisible = true;
      },
  
      // 关闭实验管理对话框
      closeExperimentManageDialog() {
        this.experimentManageDialogVisible = false;
      },
      // 保存或更新实验
      saveOrUpdateExperiment() {
        // 判断是新增还是编辑
        const isEditMode = !!this.selectedExperimentName;
  
        const apiUrl = isEditMode
          ? `http://localhost:8088/experiment/${this.selectedExperimentName}`
          : 'http://localhost:8088/experiment';
  
        const httpMethod = isEditMode ? 'put' : 'post';
  
        // 发送请求
        axios[httpMethod](apiUrl, this.experimentForm).then((response) => {
          console.log(response.data);
          this.closeExperimentManageDialog();
          this.fetchExperiments(); // 刷新实验列表
        });
      },
  
      // 删除实验
      deleteExperiment(experimentName) {
        // 发送删除请求
        axios
          .delete(`http://localhost:8088/experiment/${experimentName}`)
          .then((response) => {
            console.log(response.data);
            this.fetchExperiments(); // 刷新实验列表
          })
          .catch((error) => {
            console.error(`Delete experiment ${experimentName} failed:`, error);
          });
      },
  
      // 分页大小变化
      handleSizeChange(val) {
        alert('每页记录变化' + val);
      },
    },
    created() {
      this.fetchExperiments();
    },
  };
  </script>
  
