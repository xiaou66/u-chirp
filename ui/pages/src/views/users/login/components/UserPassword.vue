<script lang="ts" setup>
import { ref } from "vue";
import {Input} from "@u-chirp/shadcn";
import { userPasswordLogin } from "../../../../api";

const loginInfo = ref({
  email: '',
  password: ''
});

async function handleLogin(e: Event) {
  e.stopPropagation();
  e.preventDefault();
  if (loginInfo.value.email && loginInfo.value.password
    && loginInfo.value.email.includes('@')) {
    return;
  }
  try {
    const data = await userPasswordLogin(loginInfo.value);
    console.log(data)
  }catch (e) {
    console.log(e);
  }
}
</script>
<template>
  <div>
    <el-form>
      <div>
        登录信息填写存在错误
      </div>
      <el-form-item :label="$t('user.email')">
        <Input v-model:model-value="loginInfo.email"></Input>
      </el-form-item>
      <el-form-item :label="$t('user.password')">
        <Input v-model:model-value="loginInfo.password" type="password"></Input>
      </el-form-item>
    </el-form>
    <div class="form-control mt-6">
      <button class="btn btn-primary"
              @click="handleLogin">
        {{ $t('user.login') }}
      </button>
    </div>
  </div>
</template>
