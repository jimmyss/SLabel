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
};

export default MODELS;
