<template>
  <!-- Login -->
  <div id="login">
    <div id="login-form">
      <h1>登录界面</h1>
      <label for="name"><i class="el-icon-user-solid" style="color: #c1c1c1"></i></label>
      <input type="text" placeholder="用户名" id="name" autocapitalize="off" v-model.trim="name" aria-autocomplete="off">
      <p style="visibility: hidden">用户名为必填选项</p>
      <label for="password"><i class="el-icon-right" style="color: #c1c1c1"></i></label>
      <input type="password" placeholder="密码" id="password" autocapitalize="off" v-model.trim="password">
      <p style="visibility: hidden">密码为必填选项</p> 
      <div>
        <el-button type="primary" @click="inputInfo">登录</el-button>
        <el-button type="info" @click="resetInfo">重置</el-button>
        <el-button type="default" @click="enterGuestMode">游客模式</el-button>
 
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "LoginView",
  data: function () {
    return {
      name: '',
      password: '',
    };
  },
  methods: {
    // 清空当前填写信息
    resetInfo: function () {
      this.name = "";
      this.password = "";
    },
    // 验证信息是否正确
    inputInfo: async function () {
      try {
        const response = await axios.post('http://localhost:8088/login', {
          username: this.name,
          password: this.password,
        });

        const message = response.data;

        if (message.includes('成功')) {
          // 登录成功则跳转管理员界面
          this.$router.push("/admin");
        } else {
          // 跳转教师界面
          console.error(message);
          this.$router.push("/teacher");
        }
      } catch (error) {
        console.error('Error during login:', error.message);
      }
    },
    enterGuestMode() {
   


    this.$router.push('/unlogin');
  },
  },
  computed: {

  },
  mounted() {
    // css transition 样式
    let input = document.querySelectorAll("input");
    let label = document.querySelectorAll("label")
    let is = document.querySelectorAll("i");
    for (let i = 0; i < input.length; i++) {
      input[i].addEventListener("click", function () {
        input[i].style.width = '70%';
        input[i].style.transition = '1s';
        label[i].style.width = '70%';
        label[i].style.transition = '1s';
        is[i].style.color = '#037db3';
      })
      input[i].addEventListener("blur", function () {
        input[i].style.width = '60%';
        input[i].style.transition = '1s';
        label[i].style.width = '60%';
        label[i].style.transition = '1s';
        is[i].style.color = '#c1c1c1';
      })
    }
  },
  watch: {
    // 动态监测,验证 input 中 值的输入
    name: function f() {
      let p = document.querySelectorAll("p");
      if (this.name.length < 1) {
        p[0].innerHTML = "用户名称应大于 1 ";
      }
      if (this.name.length >= 1) {
        p[0].style.visibility = "hidden";
      }
      if (this.name.length === 0) {
        p[0].style.visibility = "visible";
      }
    },
    // 仅检测用户名输入，不再检测密码长度
    password: function f() {
      let p = document.querySelectorAll("p");
      p[1].style.visibility = "hidden";
    }
  }
};
</script>

<style>
#login {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: relative;
  background-image: url("@/assets/MyPhoto.jpg"); /* 使用相对路径引用图片 */
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
}

#login-form {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50vw;
  min-width: 300px;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 15px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, .5);
}

#login-form h1 {
  width: 60%;
  margin: 50px auto 0;
  color: #c1c1c1;
  text-align: center;
}

#login-form input {
  width: 60%;
  margin: 0 auto;
  outline: none;
  border: none;
  padding: 10px;
  border-bottom: 1px solid #fff;
  background: transparent;
  color: white;
}

#login-form label {
  width: 60%;
  margin: 0 auto;
  position: relative;
  top: 30px;
  left: -15px;
}

#login-form div {
  width: 60%;
  margin: 10px auto;
  display: flex;
  justify-content: center;
  align-content: center;
}

#login-form button {
  background-color: rgba(9, 108, 144, 0.5);
  margin: 10px 25px 40px 25px;
}

#login-form p {
  width: 60%;
  margin: 8px auto;
  position: relative;
  left: -15px;
  color: #ff0000;
  font-size: 8px;
}

#login-form .el-button--default {
  color: white;
}
input {
  -webkit-text-fill-color: #ffffff !important;
  transition: background-color 5000s ease-in-out, width 1s ease-out !important;
}

</style>
