import { AppRouteRecordRaw } from '../types';

const DATASET: AppRouteRecordRaw = {
  path: '/dataset',
  name: 'dataset',
  component: () => import('@/views/dataset/index.vue'),
  meta: {
    locale: 'menu.dataset',
    requiresAuth: true,
    icon: 'icon-storage',
    order: 2,
  },
};

export default DATASET;
