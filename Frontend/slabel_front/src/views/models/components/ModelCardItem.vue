<template>
  <a-card
   :style="{ width: '400px' }"
   hoverable
   class="card-style"
   :loading="loading"
  >
    <template #actions>
      <span class="icon-hover"> <IconDelete @click="deleteClick($event)"/> </span>
      <a-modal v-model:visible="delVisible" :title="$t('model.form.delete.title')" @cancel="handleCancel" @before-ok="handleBeforeOk">
        <a-form :model="form">
          <a-form-item field="confirmStr" :label="$t('model.form.delete.confirm')">
            <a-input v-model="form.confirmStr" :error="delStatus"/>
          </a-form-item>
        </a-form>
      </a-modal>
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
  import { ref, reactive, defineProps } from 'vue';
  import { IconDelete, IconEdit, IconMore } from '@arco-design/web-vue/es/icon';
  import { ValidatedError } from '@arco-design/web-vue/es/form/interface';
  import { useRouter, useRoute } from 'vue-router';
  import type { deleteModelForm } from '@/api/model';
  import useModelStore from '@/store/modules/model';
  import useLoading from '@/hooks/loading';

  const { loading, setLoading } = useLoading();
  const router = useRouter();
  const route = useRoute();
  const modelStore=useModelStore();
  const delVisible=ref(false);
  const delStatus=ref(false);
  const form=reactive({
    confirmStr:''
  })
  const delForm=reactive({
    modelId: 0
  })
  const props=defineProps({
    model: {
      type: Object,
      required: true,
    },
  })
  const emit=defineEmits(['refreshParent']);
  const handleBeforeOk = async() => {
    return new Promise((resolve, reject) => {
    if (form.confirmStr === props.model.modelName) {
      delStatus.value = false;
      setLoading(true);
      delForm.modelId=props.model.id;
      modelStore.deleteModel(delForm as deleteModelForm)
        .then(() => {
          delVisible.value = false; // 成功后关闭模态框
          resolve();
          emit('refreshParent');
        })
        .catch(error => {
          console.error('删除模型失败:', error);
          reject(new Error('删除失败')); // 出错时也阻止模态框关闭
        })
        .finally(() => {
          setLoading(false);
        });
    } else {
      delStatus.value = true;
      // 条件不满足时，阻止模态框关闭
      reject(new Error('确认字符串不匹配'));
    }
  });
  };
  function deleteClick(event){
    event.stopPropagation();
    console.log("delete")
    delVisible.value = true;
  }
  async function editClick(event){
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
