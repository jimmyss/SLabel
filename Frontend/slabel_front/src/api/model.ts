import axios from 'axios';

export interface GetModelRes<T = unknown> {
  code: number;
  msg: string;
  data: T;
}

export interface createModelForm {
  modelName: string;
  datasetId: number;
  description: string;
}

export interface deleteModelForm{
  modelId: number;
}

export function getModelList() {
  const token = localStorage.getItem('token');
  return axios.get<GetModelRes>('/v1/user/dataset/model', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function createModel(modelForm: createModelForm) {
  const token = localStorage.getItem('token');
  console.log(modelForm);
  return axios.post<GetModelRes>('/v1/user/dataset/model', modelForm, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function deleteModel(modelForm: deleteModelForm){
  const token=localStorage.getItem('token');
  return axios.delete<GetModelRes>('/v1/user/dataset/model', {
    headers:{
      Authorization: `Bearer ${token}`,
    },
    data: modelForm,
  });
}
