import { AppRouteRecordRaw } from '../types';

const TASK: AppRouteRecordRaw = {
  path: '/task',
  name: 'task',
  component: () => import('@/views/task/index.vue'),
  meta: {
    locale: 'menu.task',
    requiresAuth: true,
    icon: 'icon-bookmark',
    order: 1,
    roles: ['*'],
  },
};

export default TASK;
