<!-- LessonManage.vue -->

<template>
    <div>
      <el-container>
        <el-main>
          <el-button type="primary" @click="openLessonManageDialog(null)">新增课程</el-button>
  
          <!-- 表格 -->
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="课程编号" width="180"></el-table-column>
            <el-table-column prop="faculty" label="归属院系" width="180"></el-table-column>
            <el-table-column prop="courseName" label="课程名称"></el-table-column>
            <el-table-column prop="classname" label="上课班级"></el-table-column>
            <el-table-column prop="studentsNumber" label="上课人数"></el-table-column>
            <el-table-column prop="termName" label="开课专业学期"></el-table-column>
            <el-table-column prop="environment" label="软件环境"></el-table-column>
            <el-table-column prop="subject" label="所属学科"></el-table-column>
            <el-table-column prop="teacherName" label="任课教师"></el-table-column>
            <el-table-column prop="courseNature" label="课程性质"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="openLessonManageDialog(scope.row.id)">编辑</el-button>
                <el-button type="danger" @click="deleteLesson(scope.row.id)">删除</el-button>
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
  
      <!-- 课程管理对话框 -->
      <el-dialog :visible="lessonManageDialogVisible" @close="closeLessonManageDialog" title="课程管理">
  <el-form :model="lessonForm" label-width="80px">
    <el-form-item label="课程编号">
  <el-input v-model="lessonForm.id" :disabled="lessonForm.id"></el-input>
</el-form-item>

    <el-form-item label="归属院系">
      <el-input v-model="lessonForm.faculty"></el-input>
    </el-form-item>
    <el-form-item label="课程名称">
      <el-input v-model="lessonForm.courseName"></el-input>
    </el-form-item>
    <el-form-item label="上课班级">
      <el-input v-model="lessonForm.classname"></el-input>
    </el-form-item>
    <el-form-item label="上课人数">
      <el-input v-model="lessonForm.studentsNumber"></el-input>
    </el-form-item>
    <el-form-item label="开课专业学期">
      <el-input v-model="lessonForm.termName"></el-input>
    </el-form-item>
    <el-form-item label="软件环境">
      <el-input v-model="lessonForm.environment"></el-input>
    </el-form-item>
    <el-form-item label="所属学科">
      <el-input v-model="lessonForm.subject"></el-input>
    </el-form-item>
    <el-form-item label="任课教师">
      <el-input v-model="lessonForm.teacherName"></el-input>
    </el-form-item>
    <el-form-item label="课程性质">
      <el-input v-model="lessonForm.courseNature"></el-input>
    </el-form-item>
    <!-- 其他表单项 -->

    <el-form-item>
      <el-button type="primary" @click="saveOrUpdateLesson">保存</el-button>
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
        lessonManageDialogVisible: false,
        lessonForm: {
          faculty: '',
          courseName: '',
          classname: '',
          studentsNumber: '',
          termName: '',
          environment: '',
          subject: '',
          teacherName: '',
          courseNature: '',
        },
        selectedLessonId: null,
      };
    },
  
    methods: {
      // 获取课程列表
      fetchLessons() {
        axios.get('http://localhost:8088/lesson').then((response) => {
          this.tableData = response.data;
        });
      },
      openLessonManageDialog(lessonId) {
        this.selectedLessonId = lessonId;
  
        if (lessonId) {
          // 编辑模式，根据课程ID获取课程信息填充表单
          const selectedLesson = this.tableData.find((lesson) => lesson.id === lessonId);
          this.lessonForm = { ...selectedLesson };
        } else {
          // 新增模式，清空表单
          this.lessonForm = {
            faculty: '',
            courseName: '',
            classname: '',
            studentsNumber: '',
            termName: '',
            environment: '',
            subject: '',
            teacherName: '',
            courseNature: '',
            // 其他字段
          };
        }
  
        this.lessonManageDialogVisible = true;
      },
  
      // 关闭课程管理对话框
      closeLessonManageDialog() {
        this.lessonManageDialogVisible = false;
      },
      // 保存或更新课程
      saveOrUpdateLesson() {
        // 判断是新增还是编辑
        const isEditMode = !!this.selectedLessonId;
  
        const apiUrl = isEditMode
          ? `http://localhost:8088/lesson/${this.selectedLessonId}`
          : 'http://localhost:8088/lesson';
  
        const httpMethod = isEditMode ? 'put' : 'post';
  
        // 发送请求
        axios[httpMethod](apiUrl, this.lessonForm).then((response) => {
          console.log(response.data);
          this.closeLessonManageDialog();
          this.fetchLessons(); // 刷新课程列表
        });
      },
  
      // 删除课程
      deleteLesson(lessonId) {
        // 发送删除请求
        axios
          .delete(`http://localhost:8088/lesson/${lessonId}`)
          .then((response) => {
            console.log(response.data);
            this.fetchLessons(); // 刷新课程列表
          })
          .catch((error) => {
            console.error(`Delete lesson ${lessonId} failed:`, error);
          });
      },
  
      // 分页大小变化
      handleSizeChange(val) {
        alert('每页记录变化' + val);
      },
    },
    created() {
      this.fetchLessons();
    },
  };
  </script>
  