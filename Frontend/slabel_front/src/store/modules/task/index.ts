import { defineStore } from 'pinia';
import {
  getTasks, 
  GetTaskRes,
  createTask,
  createTaskForm
} from '@/api/task';
import { responsiveArray } from '@arco-design/web-vue/es/_utils/responsive-observe';
import { Task, TaskListState } from './types';

const useTaskStore = defineStore('task', {
  state: (): TaskListState => ({
    taskList: [],
  }),

  getters: {
    // taskInfo(state: TaskListState): TaskListState {
    //   return { ...state };
    // },
    // taskListArray: (state) => Array.from(state.taskList)
  },

  actions: {
    // getLabelTasks
    async getLabelTasks(getTaskNum: number) {
      try{
        const res = await getTasks(getTaskNum);
        this.taskList=res.data.labelTasks;
      }catch{
        console.error('Fail to fetch tasks');
      }
    },

    async createLabelTask(taskForm: createTaskForm){
      try{
        const res=await createTask(taskForm);
        console.log(res);
      }catch{
        console.error('Fail to create tasks');
      }
    }
  },
});

export default useTaskStore;
