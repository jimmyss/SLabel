<template>
    <a-space direction="horizontal" size="large" style="width: 100%; justify-content: space-between;">
      <a-select v-model="datasetName" :style="{ width: '320px' }" :placeholder="$t('dashboard.label.topBar.select.prompt')">
        <a-option
            v-for="dataset in datasetList"
            :key="dataset.id"
            :value="dataset.id"
        >
          {{ dataset.datasetName }}
        </a-option>
      </a-select>
  
      <a-space>
        <!-- 图片按钮 -->
        <a-divider direction="vertical" />
        <a-button type="primary">
          <template #icon>
            <icon-font type="icon-yidong" size="28"/>
          </template>
        </a-button>
        <!-- <a-divider direction="vertical" />
        <a-button type="primary">
          <template #icon>
            <icon-font type="icon-biaozhugongju_juxingbiaozhu" size="28"/>
          </template>
        </a-button> -->
        <a-divider direction="vertical" />
        <a-button type="primary">
          <template #icon>
            <icon-font type="icon-biaozhugongju_duobianxingbiaozhu" size="28"/>
          </template>
        </a-button>
        <a-divider direction="vertical" />
        <a-button type="primary">
          <template #icon>
            <icon-font type="icon-shezhi-xianxing" size="28"/>
          </template>
        </a-button>
        <a-divider direction="vertical" />
  
        <!-- 普通按钮 -->
        <a-button type="primary">{{$t('dashboard.label.topBar.download')}}</a-button>
        <a-divider direction="vertical" />
        <a-button type="primary">{{$t('dashboard.label.topBar.choose')}}</a-button>
        <a-divider direction="vertical" />
        <a-button type="primary">{{$t('dashboard.label.topBar.start')}}</a-button>
      </a-space>
    </a-space>
  </template>

<script lang="ts" setup>
  import { onMounted, defineProps, ref } from 'vue';
  import { computed } from 'vue';
  import { Icon } from '@arco-design/web-vue';
  import useDatasetStore from '@/store/modules/dataset';
  import { useTaskStore } from '@/store';

  const datasetStore=useDatasetStore();
  const datasetList = computed(() => useDatasetStore().datasetList);
  const taskStore=useTaskStore();
  const datasetName=ref("");

  onMounted(async ()=>{
    try{
      await datasetStore.getDatasetList(10);
      await datasetStore.getDatasetByLabelTask(taskStore.chosenTask);
      if(datasetStore.chosenId!==0){
        const chosenDataset=datasetList.value.find(dataset=>dataset.id===datasetStore.chosenId)
        datasetName.value=chosenDataset.datasetname;
      }else{
        datasetName.value="";
      }
    }catch(error){
      console.error('获取数据集失败', error);
    }
  })
</script>

<script lang="ts">
  const IconFont=Icon.addFromIconFontCn({src:'https://at.alicdn.com/t/c/font_4120967_nbw2u437dnm.js'});
  export default{
    components:{
      IconFont
    }
  }
</script>

<style lang="less">
  
</style>