import axios from 'axios';

export interface GetModelRes<T=unknown> {
  code: number;
  msg: string;
  data: T;
}

export interface createModelForm{
  modelName: string;
  datasetId: string;
  description: string;
}

export function getModelList() {
  const token = localStorage.getItem('token');
  return axios.get<GetModelRes>('/v1/user/dataset/model', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function createModel(modelForm: createModelForm){
  const token=localStorage.getItem('token');
  console.log(modelForm);
  return axios.post<GetModelRes>('/v1/user/dataset/model',modelForm,{
      headers: {
        Authorizatioin: `Bearer ${token}`,
      }
  });
}
