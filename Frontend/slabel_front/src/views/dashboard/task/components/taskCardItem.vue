<template>
  <a-card :style="{ width: '350px' }">
    <template #actions>
      <span class="icon-hover"> <IconDelete /> </span>
      <span class="icon-hover"> <IconEdit /> </span>
      <span class="icon-hover"> <IconMore /> </span>
    </template>
    <template #cover>
      <div
        :style="{
          height: '100px',
          overflow: 'hidden',
        }"
      >
        <img
          :style="{ width: '100%', transform: 'translateY(-20px)' }"
          alt="dessert"
          src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp"
        />
      </div>
    </template>
    <a-card-meta :title="task.title" :description="task.description">
      <template #avatar>
        <div
          :style="{ display: 'flex', alignItems: 'center', color: '#1D2129' }"
        >
          <a-avatar :size="24" :style="{ marginRight: '8px' }">
            {{ $t(task.status[0]) }}
          </a-avatar>
          <a-typography-text>{{ $t(task.status) }}</a-typography-text>
        </div>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script>
  import { IconDelete, IconEdit, IconMore } from '@arco-design/web-vue/es/icon';

  export default {
    components: { IconDelete, IconEdit, IconMore },
    props: {
      task: {
        type: Object,
        required: true,
      },
    },
  };
</script>
<style scoped>
  .icon-hover {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    transition: all 0.1s;
  }
  .icon-hover:hover {
    background-color: rgb(var(--gray-2));
  }
</style>
