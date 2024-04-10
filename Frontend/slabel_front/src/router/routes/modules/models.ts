import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const MODELS: AppRouteRecordRaw = {
  path: '/models',
  name: 'models',
  component: DEFAULT_LAYOUT,
  meta: {
    locale: 'menu.models',
    requiresAuth: true,
    icon: 'icon-experiment',
    order: 3,
  },
  children: [
    {
      path: 'modelList',
      name: 'modelList',
      component: () => import('@/views/models/index.vue'),
      meta: {
        locale: 'menu.models.list',
        noAffix: true,
        requiresAuth: true,
        hideInMenu: false,
      },
    },
  ],
};

export default MODELS;
