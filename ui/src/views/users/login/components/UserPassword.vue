<script lang="ts" setup>
import { ref } from "vue";

const loginInfo = ref({
  email: '',
  password: ''
});

</script>
<template>
  <div>
    <el-form>
      <el-form-item :label="$t('user.email')">
        <el-input size="large" class="input-info"
                  v-model:model-value="loginInfo.email"></el-input>
      </el-form-item>
      <el-form-item :label="$t('user.password')">
        <el-input size="large" type="password"
                  v-model:model-value="loginInfo.password"></el-input>
      </el-form-item>
    </el-form>
    <div class="form-control mt-6">
      <button class="btn btn-primary">{{ $t('user.login') }}</button>
    </div>
  </div>
</template>
