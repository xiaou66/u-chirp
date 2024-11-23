<script lang="ts" setup>
import { ref } from 'vue'
import { Input } from '@u-chirp/shadcn'
import { userPasswordLogin } from '../../../../api'
import { useRouter } from 'vue-router'
import { useUserStore } from '../../../../stores/userStore'

const loginInfo = ref({
  email: '',
  password: '',
})
const error = ref<boolean>(false)

const router = useRouter()
const { setToken } = useUserStore()
async function handleLogin(e: Event) {
  e.stopPropagation()
  e.preventDefault()
  if (loginInfo.value.email && loginInfo.value.password && !loginInfo.value.email.includes('@')) {
    error.value = true
    return
  }
  try {
    const data = await userPasswordLogin(loginInfo.value)
    error.value = false
    setToken(data).then(() => {});
    router.replace({ name: 'product' })
      .then(() => {});
    console.log(data)
  } catch {
    console.log('error')
    error.value = true
  }
}
</script>
<template>
  <div>
    <el-form>
      <div v-show="error" class="text-sm text-orange-400 text-center mb-2">
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
      <button class="btn btn-primary" @click="handleLogin">
        {{ $t('user.login') }}
      </button>
    </div>
  </div>
</template>
