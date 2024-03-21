import axios from 'axios';

export interface GetTaskRes<T=unknown> {
  code: number;
  msg: string;
  data: T;
}

export interface createTaskForm{
  title: string;
  description: string;
  direction: string;
  deadline: Date;
}

export function getTasks(taskNum: number) {
  const token = localStorage.getItem('token');
  return axios.get<GetTaskRes>('/v1/annotation-tasks', {
    params: {
      num: taskNum,
    },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function createTask(taskForm: createTaskForm){
  const token=localStorage.getItem('token');
  return axios.post<GetTaskRes>('/v1/annotation-tasks',taskForm,{
      headers: {
        Authorizatioin: `Bearer ${token}`,
      }
  });
}
