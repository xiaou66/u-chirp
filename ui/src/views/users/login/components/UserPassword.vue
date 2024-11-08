<script lang="ts" setup>
import { ref } from "vue";

const loginInfo = ref({
  username: '',
  password: ''
});

</script>
<template>
  <div>
    <el-form>
      <el-form-item label="邮箱">
        <el-input size="large" class="input-info"
                  v-model:model-value="loginInfo.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input size="large" type="password"
                  v-model:model-value="loginInfo.password"></el-input>
      </el-form-item>
    </el-form>
    <div class="form-control mt-6">
      <button class="btn btn-primary">登录</button>
    </div>
  </div>
</template>
