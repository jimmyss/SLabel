import { defineStore } from 'pinia';
import {
  GetModelRes,
  getModelList,
} from '@/api/model';
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
    // getLabelTasks
    async getModels(getModelNum: number) {
      try{
        const res = await getModelList(getModelNum);
        this.modelList=res.data.modelList;
      }catch{
        console.error('Fail to fetch models');
      }
    },
  },
});

export default useModelStore;
