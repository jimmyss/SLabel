<template>
  <a-card
   :style="{ width: '400px' }"
   hoverable
   class="card-style"
   :loading="loading"
  >
    <template #actions>
      <span class="icon-hover"> <IconDelete @click="deleteClick($event)"/> </span>
        <span class="icon-hover"> <IconEdit @click="editClick($event)"/> </span>
        <span class="icon-hover"> <IconMore @click="moreClick($event)"/> </span>
    </template>
    <template #cover>
      <div
        :style="{
          height: '500px',
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
    <a-card-meta :title="model.modelName" :description="model.description">
      <template #avatar>
        <div
          :style="{ display: 'flex', alignItems: 'center', color: '#1D2129' }"
        >
          <a-avatar :size="24" :style="{ marginRight: '8px' }"> A </a-avatar>
          <a-typography-text>{{ $t(model.modelName) }}</a-typography-text>
        </div>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script lang="ts" setup>
  import { IconDelete, IconEdit, IconMore } from '@arco-design/web-vue/es/icon';
  import { ValidatedError } from '@arco-design/web-vue/es/form/interface';
  import { useRouter, useRoute } from 'vue-router';
  import useLoading from '@/hooks/loading';

  const { loading, setLoading } = useLoading();
  const router = useRouter();
  const route = useRoute();
  function deleteClick(event){
    event.stopPropagation();
    console.log("delete")
  }
  function editClick(event){
    event.stopPropagation();
    console.log("edit")
  }
  function moreClick(event){
    event.stopPropagation();
    console.log("more")
  }
</script>
<script lang="ts">
export default {
  components: { IconDelete, IconEdit, IconMore },
  props: {
    model: {
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
  .card-style {
    transition-property: all;
  }
  .card-style:hover {
    transform: translateY(-4px);
  }
</style>
