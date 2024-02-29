import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const DATASET: AppRouteRecordRaw = {
  path: '/dataset',
  name: 'dataset',
  component: DEFAULT_LAYOUT,
  meta: {
    locale: 'menu.dataset',
    requiresAuth: true,
    icon: 'icon-storage',
    order: 2,
  },
};

export default DATASET;
