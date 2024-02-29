<template>
    <h1>Test</h1>
  </template>
  
  <script lang="ts" setup>

  </script>
  
  <script lang="ts">
    export default {
      name: 'Test',
    };
  </script>
  
  <style scoped lang="less">
    .container {
      padding: 0 20px 20px 20px;
    }
  
    .layout {
      display: flex;
  
      &-left-side {
        flex-basis: 300px;
      }
  
      &-content {
        flex: 1;
        padding: 0 16px;
      }
  
      &-right-side {
        flex-basis: 280px;
      }
    }
  </style>
  
  <style lang="less" scoped>
    // responsive
    @media (max-width: @screen-lg) {
      .layout {
        flex-wrap: wrap;
        &-left-side {
          flex: 1;
          flex-basis: 100%;
          margin-bottom: 16px;
        }
  
        &-content {
          flex: none;
          flex-basis: 100%;
          padding: 0;
          order: -1;
          margin-bottom: 16px;
        }
  
        &-right-side {
          flex-basis: 100%;
        }
      }
    }
  </style>
  