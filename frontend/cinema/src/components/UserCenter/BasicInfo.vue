<template>
  <div class="user-info">
    <el-form :model="userInfo" :rules="rules" label-width="100px" class="form">
      <el-form-item label="昵称">
        <el-input v-model="userInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="称呼方式">
        <el-radio-group v-model="userInfo.gender">
          <el-radio :label="0">先生</el-radio>
          <el-radio :label="1">女士</el-radio>
          <el-radio :label="2">朋友</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker v-model="userInfo.birthday" type="date">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="从事行业">
        <el-input v-model="userInfo.profession"></el-input>
      </el-form-item>
      <el-form-item label="个性签名" prop="signature">
        <el-input
          v-model="userInfo.signature"
          placeholder="请不要超过20字"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {userInfo, updateInfo} from "@/lib/userInfo.js"
export default {
  name: "BasicInfo",
  data() {
    return {
      userInfo,
      rules: {
        signature: [
          { min: 0, max: 20, message: "请不要超过20字", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    onSubmit() {
        console.log(this.userInfo)
    }
  }
};
</script>

<style scoped>
.user-info {
  width: 100%;
}
.user-info .form {
  width: 50%;
  margin-left: 30px;
}
</style>
