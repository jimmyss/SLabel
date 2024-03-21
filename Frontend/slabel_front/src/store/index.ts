import { createPinia } from 'pinia';
import useAppStore from './modules/app';
import useUserStore from './modules/user';
import useTabBarStore from './modules/tab-bar';
import useTaskStore from './modules/task';

const pinia = createPinia();

export { useAppStore, useUserStore, useTabBarStore, useTaskStore };
export default pinia;
