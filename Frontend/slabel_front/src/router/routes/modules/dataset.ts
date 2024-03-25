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
  children:[
    {
      path: 'datasetList',
      name: 'datasetList',
      component: ()=>import('@/views/dataset/index.vue'),
      meta:{
        locale: 'menu.dataset.list',
        noAffix: true,
        requiresAuth: true,
        hideInMenu: false,
      }
    }
  ]
};

export default DATASET;
