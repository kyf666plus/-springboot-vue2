<template>
  <div>
    <el-container>
      <el-main>
        <el-button type="primary" @click="openLaboratoryManageDialog(null)">新增实验室</el-button>

        <!-- 表格 -->
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="classroomId" label="实验室编号" width="180"></el-table-column>
          <el-table-column prop="laboratoryName" label="实验室名称" width="180"></el-table-column>
          <el-table-column prop="brief" label="简介"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="openLaboratoryManageDialog(scope.row.classroomId)">编辑</el-button>
              <el-button type="danger" @click="deleteLaboratory(scope.row.classroomId)">删除</el-button>
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

    <!-- 实验室管理对话框 -->
    <el-dialog :visible="laboratoryManageDialogVisible" @close="closeLaboratoryManageDialog" title="实验室管理">
      <el-form :model="laboratoryForm" label-width="80px">
        <el-form-item label="实验室名称">
          <el-input v-model="laboratoryForm.laboratoryName"></el-input>
        </el-form-item>
        <el-form-item label="实验室编号">
          <el-input v-model="laboratoryForm.classroomId" :disabled="!!selectedClassroomId"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="laboratoryForm.brief"></el-input>
        </el-form-item>

        <!-- 其他字段 -->

        <el-form-item>
          <el-button type="primary" @click="saveOrUpdateLaboratory">保存</el-button>
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
      laboratoryManageDialogVisible: false,
      laboratoryForm: {
        classroomId: '',
        laboratoryName: '',
        brief: '',
      },
      selectedClassroomId: null,
    };
  },

  methods: {
    // 获取实验室列表
    fetchLaboratories() {
      axios.get('http://localhost:8088/laboratory').then((response) => {
        this.tableData = response.data;
      });
    },
    openLaboratoryManageDialog(classroomId) {
      this.selectedClassroomId = classroomId;

      if (classroomId) {
        // 编辑模式，根据实验室编号获取实验室信息填充表单
        const selectedLaboratory = this.tableData.find((lab) => lab.classroomId === classroomId);
        this.laboratoryForm = { ...selectedLaboratory };
      } else {
        // 新增模式，清空表单
        this.laboratoryForm = {
          classroomId: '',
          laboratoryName: '',
          brief: '',
          // 其他字段
        };
      }

      this.laboratoryManageDialogVisible = true;
    },

    // 关闭实验室管理对话框
    closeLaboratoryManageDialog() {
      this.laboratoryManageDialogVisible = false;
    },
    // 保存或更新实验室
    saveOrUpdateLaboratory() {
      // 判断是新增还是编辑
      const isEditMode = !!this.selectedClassroomId;

      const apiUrl = isEditMode
        ? `http://localhost:8088/laboratory/${this.selectedClassroomId}`
        : 'http://localhost:8088/laboratory';

      const httpMethod = isEditMode ? 'put' : 'post';

      // 发送请求
      axios[httpMethod](apiUrl, this.laboratoryForm).then((response) => {
        console.log(response.data);
        this.closeLaboratoryManageDialog();
        this.fetchLaboratories(); // 刷新实验室列表
      });
    },

    // 删除实验室
    deleteLaboratory(classroomId) {
      // 发送删除请求
      axios
        .delete(`http://localhost:8088/laboratory/${classroomId}`)
        .then((response) => {
          console.log(response.data);
          this.fetchLaboratories(); // 刷新实验室列表
        })
        .catch((error) => {
          console.error(`Delete laboratory ${classroomId} failed:`, error);
        });
    },

    // 分页大小变化
    handleSizeChange(val) {
      alert('每页记录变化' + val);
    },
  },
  created() {
    this.fetchLaboratories();
  },
};
</script>
