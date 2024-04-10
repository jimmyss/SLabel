import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const TASK: AppRouteRecordRaw = {
  path: '/dashboard',
  name: 'dashboard',
  component: DEFAULT_LAYOUT,
  meta: {
    locale: 'menu.dashboard',
    requiresAuth: true,
    icon: 'icon-bookmark',
    order: 1,
    roles: ['*'],
  },
  children: [
    {
      path: 'taskInfo',
      name: 'taskInfo',
      component: () => import('@/views/dashboard/task/index.vue'),
      meta: {
        locale: 'menu.dashboard.taskInfo',
        noAffix: true,
        requiresAuth: true,
        hideInMenu: false,
      },
    },
    {
      path: 'labelPanel',
      name: 'labelPanel',
      component: () => import('@/views/dashboard/label/index.vue'),
      meta: {
        locale: 'menu.dashboard.labelPanel',
        noAffix: true,
        requiresAuth: true,
        hideInMenu: false,
      },
    },
  ],
};

export default TASK;
