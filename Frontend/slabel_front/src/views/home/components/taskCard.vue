<template>
    <a-card title="Arco Card" :style="{ width: '600px' }">
      <a-grid :cols="24" :col-gap="16" :row-gap="16" style="margin-top: 16px">
        <a-grid-item
          :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
        >
          <TaskCardItem />
        </a-grid-item>
        <a-grid-item
        :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
        >
          <TaskCardItem />
        </a-grid-item>
      </a-grid>
    </a-card>
</template>

<script lang="ts" setup>
  import TaskCardItem from './taskCardItem.vue';

</script>

