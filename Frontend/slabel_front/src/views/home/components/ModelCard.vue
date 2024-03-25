<template>
  <a-card :title="$t('home.model.title')" :style="{ width: '1250px' }">
    <a-space direction="horizontal">
      <!-- <ModelCardItem width= 33.33%></ModelCardItem>
        <ModelCardItem width= 33.33%></ModelCardItem>
        <ModelCardItem width= 33.33%></ModelCardItem> -->
      <a-grid :cols="24" :col-gap="16" :row-gap="16" style="margin-top: 16px">
        <a-grid-item
          v-for="(model, index) in modelStore.modelList"
          :key="index"
          :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
        >
          <ModelCardItem :model="model" />
        </a-grid-item>
      </a-grid>
    </a-space>
  </a-card>
</template>

<script lang="ts" setup>
  import { onMounted } from 'vue';
  import useModelStore from '@/store/modules/model';
  import ModelCardItem from './ModelCardItem.vue';

  const modelStore = useModelStore();

  onMounted(async () => {
    try {
      await modelStore.getModels();
    } catch (error) {
      console.error('获取模型失败:', error);
    }
  });
</script>
