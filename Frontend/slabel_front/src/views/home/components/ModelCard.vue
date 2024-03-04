<template>
    <a-card title="Model Card" :style="{ width: '1250px' }">
      <a-space direction="horizontal">
        <ModelCardItem width= 33.33%></ModelCardItem>
        <ModelCardItem width= 33.33%></ModelCardItem>
        <ModelCardItem width= 33.33%></ModelCardItem>
      </a-space>
    </a-card>
</template>

<script lang="ts" setup>
  import ModelCardItem from './ModelCardItem.vue';
</script>

