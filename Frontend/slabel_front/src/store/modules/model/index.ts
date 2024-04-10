import { defineStore } from 'pinia';
import { createModel, getModelList, createModelForm, deleteModelForm, deleteModel } from '@/api/model';
import { responsiveArray } from '@arco-design/web-vue/es/_utils/responsive-observe';
import { Model, ModelListState } from './types';

const useModelStore = defineStore('model', {
  state: (): ModelListState => ({
    modelList: [],
  }),

  getters: {
    // taskInfo(state: TaskListState): TaskListState {
    //   return { ...state };
    // },
    // taskListArray: (state) => Array.from(state.taskList)
  },

  actions: {
    // getModels
    async getModels() {
      try {
        const res = await getModelList();
        console.log(res.data);
        this.modelList = res.data.modelList;
      } catch {
        console.error('Fail to fetch models');
      }
    },

    // createModel
    async createModel(modelForm: createModelForm) {
      try {
        const res = await createModel(modelForm);
      } catch {
        console.error('Fail to create model');
      }
    },

    // deleteModel
    async deleteModel(modelForm: deleteModelForm){
      try{
        const res = await deleteModel(modelForm);
      } catch{
        console.error('Fail to delete model');
      }
    }
  },
});

export default useModelStore;
