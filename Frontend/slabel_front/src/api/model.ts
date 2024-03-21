import axios from 'axios';

export interface GetModelRes<T=unknown> {
  code: number;
  msg: string;
  data: T;
}

export function getModelList(modelNum: number) {
  const token = localStorage.getItem('token');
  return axios.get<GetModelRes>('/v1/user/dataset/models', {
    params: {
      num: modelNum,
    },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}
