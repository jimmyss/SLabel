import axios from 'axios';

export interface GetDatasetRes<T=unknown> {
  code: number;
  msg: string;
  data: T;
}

export interface uploadDatasetForm{
  datasetName: string;
  description: string;
}

export function getDatasets(datasetNum: number) {
  const token = localStorage.getItem('token');
  return axios.get<GetDatasetRes>('/v1/user/dataset', {
    params: {
      num: datasetNum,
    },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

export function uploadDataset(uploadForm: uploadDatasetForm){
  const token=localStorage.getItem('token');
  return axios.post<GetDatasetRes>('/v1/user/dataset',uploadForm,{
      headers: {
        Authorizatioin: `Bearer ${token}`,
      }
  });
}
