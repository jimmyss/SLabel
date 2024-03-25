import localeMessageBox from '@/components/message-box/locale/zh-CN';
import localeLogin from '@/views/login/locale/zh-CN';

import localeHome from '@/views/home/local/zh-CN';
import localeTask from '@/views/dashboard/task/local/zh-CN';
import localeDataset from '@/views/dataset/local/zh-CN';
import localeModel from '@/views/models/local/zh-CN';

import localeSettings from './zh-CN/settings';

export default {
  'menu.home': '首页',
  'menu.dataset': '我的数据集',
  'menu.dataset.list': '数据集列表',
  'menu.models': '我的模型',
  'menu.models.list':'模型列表',
  'menu.dashboard': '我的标注任务',
  'menu.dashboard.taskInfo': '标注管理',
  'menu.dashboard.labelPanel': '标注工作台',
  'menu.server.dashboard': '仪表盘-服务端',
  'menu.server.workplace': '工作台-服务端',
  'menu.server.monitor': '实时监控-服务端',
  'menu.list': '列表页',
  'menu.result': '结果页',
  'menu.exception': '异常页',
  'menu.form': '表单页',
  'menu.profile': '详情页',
  'menu.visualization': '数据可视化',
  'menu.user': '个人中心',
  'menu.arcoWebsite': 'Arco Design',
  'menu.faq': '常见问题',
  'navbar.docs': '文档中心',
  'navbar.action.locale': '切换为中文',
  ...localeSettings,
  ...localeMessageBox,
  ...localeLogin,

  ...localeHome,
  ...localeTask,
  ...localeDataset,
  ...localeModel,
};
