import axios from 'axios';
import type { RouteRecordNormalized } from 'vue-router';
import { UserState } from '@/store/modules/user/types';

export interface LoginData {
  username: string;
  password: string;
}

export interface RegisterData {
  username: string;
  password: string;
  confirmPassword: string;
}

export interface LoginRes<T = unknown> {
  msg: string;
  code: number;
  data: T;
  token: string;
}

export interface RegisterRes<T = unknown> {
  code: number;
  msg: string;
  data: T;
  token: string;
}

export function login(data: LoginData) {
  return axios.post<LoginRes>('/v1/users/login', data);
}

export function register(data: RegisterData) {
  return axios.post<RegisterRes>('/v1/users/register', data);
}

export function logout() {
  return axios.post<LoginRes>('/v1/users/logout');
}

export function getUserInfo() {
  return axios.post<UserState>('/v1/users/info');
}

export function getMenuList() {
  return axios.post<RouteRecordNormalized[]>('/api/user/menu');
}
