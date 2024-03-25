<template>
  <a-card :title="$t('home.labelTask.title')" :style="{ width: '600px' }">
    <div class="scroll-container">
      <a-grid :cols="24" :col-gap="30" :row-gap="16" style="margin-top: 16px">
        <a-grid-item
          v-for="(task, index) in taskStore.taskList"
          :key="index"
          :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
        >
          <TaskCardItem :task="task" />
        </a-grid-item>
      </a-grid>
    </div>
  </a-card>
</template>

<script lang="ts" setup>
  import { onMounted } from 'vue';
  import useTaskStore from '@/store/modules/task';
  import TaskCardItem from './taskCardItem.vue';

  const taskStore = useTaskStore();

  onMounted(async () => {
    try {
      await taskStore.getLabelTasks(10);
    } catch (error) {
      console.error('获取标注任务失败:', error);
    }
  });
</script>

<style lang="less" scoped>
  .scroll-container {
    overflow-x: auto;
    white-space: nowrap;
  }

  .a-grid-item {
    display: inline-block;
    width: calc(50% - 12px);
  }
</style>
