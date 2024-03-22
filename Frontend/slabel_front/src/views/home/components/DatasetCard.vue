<template>
    <a-card title="Arco Card" :style="{ width: '600px' }">
      <a-grid :cols="24" :col-gap="16" :row-gap="16" style="margin-top: 16px">
        <a-grid-item v-for="(dataset, index) in datasetStore.datasetList" :key="index" :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }">
          <DatasetCardItem :dataset="dataset"/>
        </a-grid-item>
      </a-grid>
    </a-card>
</template>

<script lang="ts" setup>
  import { onMounted } from 'vue';
  import useDatasetStore from '@/store/modules/dataset';
  import DatasetCardItem from './DatasetCardItem.vue';

  const datasetStore=useDatasetStore();

  onMounted(async()=>{
    try{
      await datasetStore.getDatasetList(10);
    }catch(error){
      console.error("获取数据集失败:", error);
    }
  })
</script>

