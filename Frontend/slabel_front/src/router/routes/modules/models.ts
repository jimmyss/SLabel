import { AppRouteRecordRaw } from '../types';

const MODELS: AppRouteRecordRaw = {
  path: '/models',
  name: 'models',
  component: () => import('@/views/models/index.vue'),
  meta: {
    locale: 'menu.models',
    requiresAuth: true,
    icon: 'icon-experiment',
    order: 3,
  },
};

export default MODELS;
