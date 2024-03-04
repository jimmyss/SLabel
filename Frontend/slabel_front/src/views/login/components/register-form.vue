<template>
    <div class="register-form-wrapper">
      <div class="register-form-title">{{ $t('register.form.title') }}</div>
      <div class="register-form-sub-title">{{ $t('register.form.sub.title') }}</div>
      <div class="register-form-error-msg">{{ errorMessage }}</div>
      <a-form
        ref="registerForm"
        :model="userInfo"
        class="register-form"
        layout="vertical"
        @submit="handleSubmit"
      >
        <a-form-item
          field="username"
          :rules="[{ required: true, message: $t('register.form.userName.errMsg') }]"
          :validate-trigger="['change', 'blur']"
          hide-label
        >
          <a-input
            v-model="userInfo.username"
            placeholder="输入用户名"
            allowClear
          >
            <template #prefix>
              <icon-user />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item
          field="password"
          :rules="[{ required: true, message: $t('register.form.password.errMsg') }]"
          :validate-trigger="['change', 'blur']"
          hide-label
        >
          <a-input-password
            v-model="userInfo.password"
            placeholder="输入密码"
            allow-clear
          >
            <template #prefix>
              <icon-lock />
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item
          field="password"
          :rules="[{ required: true, message: $t('register.form.password.errMsg') }]"
          :validate-trigger="['change', 'blur']"
          hide-label
        >
          <a-input-password
            v-model="userInfo.confirmPassword"
            placeholder="确认密码"
            allow-clear
          >
            <template #prefix>
              <icon-lock />
            </template>
          </a-input-password>
        </a-form-item>
        <a-space :size="16" direction="vertical">
          <a-button type="primary" long html-type="submit" :loading="loading">
            {{ $t('register.form.register') }}
          </a-button>
          <a-button type="text" long @click="$emit('switchForm')">
            {{ $t('register.form.login') }}
          </a-button>
        </a-space>
      </a-form>
    </div>
  </template>
  
  <script lang="ts" setup>
    import { ref, reactive } from 'vue';
    import { useRouter } from 'vue-router';
    import { Message } from '@arco-design/web-vue';
    import { ValidatedError } from '@arco-design/web-vue/es/form/interface';
    import { useI18n } from 'vue-i18n';
    import { useStorage } from '@vueuse/core';
    import { useUserStore } from '@/store';
    import useLoading from '@/hooks/loading';
    import type { RegisterData } from '@/api/user';
  
    const router = useRouter();
    const { t } = useI18n();
    const errorMessage = ref('');
    const { loading, setLoading } = useLoading();
    const userStore = useUserStore();
  
    const loginConfig = useStorage('login-config', {
      username: 'test', // 演示默认值
      password: 'qewxs132', // demo default value
    });
    const userInfo = reactive({
      username: "",
      password: "",
      confirmPassword: "",
    });
  
    const handleSubmit = async ({
      errors,
      values,
    }: {
      errors: Record<string, ValidatedError> | undefined;
      values: Record<string, any>;
    }) => {
      if (loading.value) return;
      if (!errors) {
        setLoading(true);
        try {
          await userStore.register(values as RegisterData);
          const { redirect, ...othersQuery } = router.currentRoute.value.query;
          router.push({
            name: (redirect as string) || 'home',
            query: {
              ...othersQuery,
            },
          });
          Message.success(t('register.form.register.success'));
          const { username, password } = values;
          // 实际生产环境需要进行加密存储。
          // The actual production environment requires encrypted storage.
        } catch (err) {
          errorMessage.value = (err as Error).message;
        } finally {
          setLoading(false);
        }
      }
    };
  </script>
  
  <style lang="less" scoped>
    .register-form {
      &-wrapper {
        width: 320px;
      }
  
      &-title {
        color: var(--color-text-1);
        font-weight: 500;
        font-size: 24px;
        line-height: 32px;
      }
  
      &-sub-title {
        color: var(--color-text-3);
        font-size: 16px;
        line-height: 24px;
      }
  
      &-error-msg {
        height: 32px;
        color: rgb(var(--red-6));
        line-height: 32px;
      }
  
      &-password-actions {
        display: flex;
        justify-content: space-between;
      }
  
      &-register-btn {
        color: var(--color-text-3) !important;
      }
    }
  </style>
  