<template>
  <!-- <a-layout class="layout" :class="{ mobile: appStore.hideMenu }">
    <div v-if="navbar" class="layout-navbar">
      <NavBar />
    </div>
    <a-layout>
      <a-layout>
        <a-layout-sider
          v-if="renderMenu"
          v-show="!hideMenu"
          class="layout-sider"
          breakpoint="xl"
          :collapsed="collapsed"
          :collapsible="true"
          :width="menuWidth"
          :style="{ paddingTop: navbar ? '60px' : '' }"
          :hide-trigger="true"
          @collapse="setCollapsed"
        >
          <div class="menu-wrapper">
            <Menu />
          </div>
        </a-layout-sider>
        <a-drawer
          v-if="hideMenu"
          :visible="drawerVisible"
          placement="left"
          :footer="false"
          mask-closable
          :closable="false"
          @cancel="drawerCancel"
        >
          <Menu />
        </a-drawer>

        <a-layout class="layout-content" :style="paddingStyle">
          <TabBar v-if="appStore.tabBar" />
          <a-layout-content>
            <div class="container">
              <a-space direction="vertical">
                <a-button
                  @click="openForm"
                  type="primary"
                  size="large"
                  width="100%"
                  >{{ $t('task.button.create') }}</a-button
                >
                <a-modal
                  v-model:visible="visible"
                  title=""
                  @cancel="handleCancel"
                  @before-ok="handleBeforeOk"
                >
                  <a-form :model="form">
                    <a-form-item field="title" :label="$t('task.form.title')">
                      <a-input v-model="form.title" />
                    </a-form-item>
                    <a-form-item
                      field="description"
                      :label="$t('task.form.description')"
                    >
                      <a-input v-model="form.description" />
                    </a-form-item>
                    <a-form-item
                      field="direction"
                      :label="$t('task.form.direction')"
                    >
                      <a-input v-model="form.direction" />
                    </a-form-item>
                    <a-form-item field="date" :label="$t('task.form.deadline')">
                      <a-date-picker
                        v-model="form.deadline"
                        :placeholder="$t('task.form.dateRemind')"
                      />
                    </a-form-item>
                  </a-form>
                </a-modal>
                <a-grid
                  :cols="24"
                  :col-gap="16"
                  :row-gap="16"
                  style="margin-top: 16px"
                >
                  <a-grid-item
                    v-for="(task, index) in taskStore.taskList"
                    :key="index"
                    :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
                  >
                    <TaskCardItem :task="task" />
                  </a-grid-item>
                </a-grid>
              </a-space>
            </div>
          </a-layout-content>
          <Footer v-if="footer" />
        </a-layout>
      </a-layout>
    </a-layout>
  </a-layout> -->
  <div class="container">
    <a-space direction="vertical">
      <a-button
        @click="openForm"
        type="primary"
        size="large"
        width="100%"
        >{{ $t('task.button.create') }}</a-button
      >
      <a-modal
        v-model:visible="visible"
        title=""
        @cancel="handleCancel"
        @before-ok="handleBeforeOk"
      >
        <a-form :model="form">
          <a-form-item field="title" :label="$t('task.form.title')">
            <a-input v-model="form.title" />
          </a-form-item>
          <a-form-item
            field="description"
            :label="$t('task.form.description')"
          >
            <a-input v-model="form.description" />
          </a-form-item>
          <a-form-item
            field="direction"
            :label="$t('task.form.direction')"
          >
            <a-input v-model="form.direction" />
          </a-form-item>
          <a-form-item field="date" :label="$t('task.form.deadline')">
            <a-date-picker
              v-model="form.deadline"
              :placeholder="$t('task.form.dateRemind')"
            />
          </a-form-item>
        </a-form>
      </a-modal>
      <a-grid
        :cols="24"
        :col-gap="16"
        :row-gap="16"
        style="margin-top: 16px"
      >
        <a-grid-item
          v-for="(task, index) in taskStore.taskList"
          :key="index"
          :span="{ xs: 24, sm: 24, md: 24, lg: 12, xl: 12, xxl: 12 }"
        >
          <TaskCardItem :task="task" />
        </a-grid-item>
      </a-grid>
    </a-space>
  </div>
</template>

<script lang="ts" setup>
  import { ref, computed, watch, provide, onMounted, reactive } from 'vue';
  import { useRouter, useRoute } from 'vue-router';
  import { useAppStore, useUserStore } from '@/store';
  import type { createTaskForm } from '@/api/task';

  import NavBar from '@/components/navbar/index.vue';
  import Menu from '@/components/menu/index.vue';
  import Footer from '@/components/footer/index.vue';
  import TabBar from '@/components/tab-bar/index.vue';
  import usePermission from '@/hooks/permission';
  import useResponsive from '@/hooks/responsive';
  import useTaskStore from '@/store/modules/task';
  import TaskCardItem from './components/taskCardItem.vue';

  const isInit = ref(false);
  const appStore = useAppStore();
  const userStore = useUserStore();
  const router = useRouter();
  const route = useRoute();
  const permission = usePermission();
  useResponsive(true);
  const navbarHeight = `60px`;
  const navbar = computed(() => appStore.navbar);
  const renderMenu = computed(() => appStore.menu && !appStore.topMenu);
  const hideMenu = computed(() => appStore.hideMenu);
  const footer = computed(() => appStore.footer);
  const taskStore = useTaskStore();
  const visible = ref(false);
  const form = reactive({
    title: '',
    description: '',
    direction: '',
    deadline: new Date(),
  });

  const menuWidth = computed(() => {
    return appStore.menuCollapse ? 48 : appStore.menuWidth;
  });
  const collapsed = computed(() => {
    return appStore.menuCollapse;
  });
  const paddingStyle = computed(() => {
    const paddingLeft =
      renderMenu.value && !hideMenu.value
        ? { paddingLeft: `${menuWidth.value}px` }
        : {};
    const paddingTop = navbar.value ? { paddingTop: navbarHeight } : {};
    return { ...paddingLeft, ...paddingTop };
  });
  const setCollapsed = (val: boolean) => {
    if (!isInit.value) return; // for page initialization menu state problem
    appStore.updateSettings({ menuCollapse: val });
  };
  watch(
    () => userStore.role,
    (roleValue) => {
      if (roleValue && !permission.accessRouter(route))
        router.push({ name: 'notFound' });
    }
  );
  const drawerVisible = ref(false);
  const drawerCancel = () => {
    drawerVisible.value = false;
  };
  provide('toggleDrawerMenu', () => {
    drawerVisible.value = !drawerVisible.value;
  });
  onMounted(async () => {
    try {
      await taskStore.getLabelTasks(10);
    } catch (error) {
      console.error('获取标注任务失败:', error);
    }
    isInit.value = true;
  });
  const openForm = () => {
    visible.value = true;
  };
  const handleCancel = () => {
    visible.value = false;
  };
  const handleBeforeOk = async () => {
    try {
      console.log(form);
      await taskStore.createLabelTask(form as createTaskForm);
      await taskStore.getLabelTasks(10);
    } catch (error) {
      console.error(error);
    }
  };
</script>

<script lang="ts">
  export default {
    name: 'TaskInfo', // If you want the include property of keep-alive to take effect, you must name the component
  };
</script>

<style lang="less" scoped>
  @nav-size-height: 60px;
  @layout-max-width: 1100px;

  .layout {
    width: 100%;
    height: 100%;
  }

  .layout-navbar {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 100;
    width: 100%;
    height: @nav-size-height;
  }

  .layout-sider {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 99;
    height: 100%;
    transition: all 0.2s cubic-bezier(0.34, 0.69, 0.1, 1);
    &::after {
      position: absolute;
      top: 0;
      right: -1px;
      display: block;
      width: 1px;
      height: 100%;
      background-color: var(--color-border);
      content: '';
    }

    > :deep(.arco-layout-sider-children) {
      overflow-y: hidden;
    }
  }

  .menu-wrapper {
    height: 100%;
    overflow: auto;
    overflow-x: hidden;
    :deep(.arco-menu) {
      ::-webkit-scrollbar {
        width: 12px;
        height: 4px;
      }

      ::-webkit-scrollbar-thumb {
        border: 4px solid transparent;
        background-clip: padding-box;
        border-radius: 7px;
        background-color: var(--color-text-4);
      }

      ::-webkit-scrollbar-thumb:hover {
        background-color: var(--color-text-3);
      }
    }
  }

  .layout-content {
    min-height: 100vh;
    overflow-y: hidden;
    background-color: var(--color-fill-2);
    transition: padding 0.2s cubic-bezier(0.34, 0.69, 0.1, 1);
  }

  .container {
    background-color: var(--color-fill-2);
    padding: 16px 20px;
    padding-bottom: 0;
    display: flex;
  }

  .left-side {
    flex: 1;
    overflow: auto;
  }

  .right-side {
    width: 280px;
    margin-left: 16px;
  }

  .panel {
    background-color: var(--color-bg-2);
    border-radius: 4px;
    overflow: auto;
  }
  :deep(.panel-border) {
    margin-bottom: 0;
    border-bottom: 1px solid rgb(var(--gray-2));
  }
  .moduler-wrap {
    border-radius: 4px;
    background-color: var(--color-bg-2);
    :deep(.text) {
      font-size: 12px;
      text-align: center;
      color: rgb(var(--gray-8));
    }

    :deep(.wrapper) {
      margin-bottom: 8px;
      text-align: center;
      cursor: pointer;

      &:last-child {
        .text {
          margin-bottom: 0;
        }
      }
      &:hover {
        .icon {
          color: rgb(var(--arcoblue-6));
          background-color: #e8f3ff;
        }
        .text {
          color: rgb(var(--arcoblue-6));
        }
      }
    }

    :deep(.icon) {
      display: inline-block;
      width: 32px;
      height: 32px;
      margin-bottom: 4px;
      color: rgb(var(--dark-gray-1));
      line-height: 32px;
      font-size: 16px;
      text-align: center;
      background-color: rgb(var(--gray-1));
      border-radius: 4px;
    }
  }
</style>
