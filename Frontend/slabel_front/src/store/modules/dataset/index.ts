import { defineStore } from 'pinia';
import {
  getDatasets,
  uploadDataset as upload,
  uploadDatasetForm,
} from '@/api/dataset';
import {
  getDatasetByTaskId as getDatasetById
} from '@/api/task';
import { DatasetListState } from './types';

const useDatasetStore = defineStore('dataset', {
  state: (): DatasetListState => ({
    datasetList: [],
    chosenId: 0 as number,
  }),

  getters: {
    // taskInfo(state: TaskListState): TaskListState {
    //   return { ...state };
    // },
    // taskListArray: (state) => Array.from(state.taskList)
  },

  actions: {
    // get Dataset list
    async getDatasetList(getDatasetNum: number) {
      try {
        const res = await getDatasets(getDatasetNum);
        this.datasetList = res.data.datasets;
      } catch {
        console.error('Fail to fetch datasets');
      }
    },

    async uploadDataset(uploadForm: uploadDatasetForm) {
      try {
        const res = await upload(uploadForm);
        console.log(res);
      } catch {
        console.error('Fail to create tasks');
      }
    },

    async getDatasetByLabelTask(taskId: number){
      try{
        const res=await getDatasetById(taskId);
        if(res.data!==-1){
          this.chosenId=res.data;
        }
      }catch{
        console.error('Fail to get dataset by taskId');
      }
    }
  },
});

export default useDatasetStore;
