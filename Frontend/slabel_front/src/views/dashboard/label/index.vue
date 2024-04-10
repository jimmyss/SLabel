<template>
  <div class="container">
    <a-space direction="vertical" size="mini" style="flex-grow: 1;">
      <!-- 顶部工具栏 -->
      <a-card>
        <TopBar/>
      </a-card>
      <a-space direction="horizontal" size="mini">
        <!-- 左边Canvas+dialogBox -->
        <a-grid :cols="24" :cols-gap="30" :row-gap="16" style="margin-top: 4px" class="left-side">
          <a-grid-item :span="24">
            <a-card class="canvas-container">
              <Canvas/>
            </a-card>
          </a-grid-item>
          <a-grid-item :span="24">
            <a-card class="dialogBox-container">
              <DialogBox/>
            </a-card>
          </a-grid-item>
        </a-grid>
        <!-- 右边collection -->
        <a-card class="collection-container">
          <Collection/>
        </a-card>
      </a-space>
    </a-space>
  </div>
</template>

<script lang="ts" setup>
  import { ref, computed, watch, provide, onMounted } from 'vue';
  import { useAppStore } from '@/store';
  import { useRouter, useRoute } from 'vue-router';

  import usePermission from '@/hooks/permission';
  import useResponsive from '@/hooks/responsive';
  import NavBar from '@/components/navbar/index.vue';
  import Menu from '@/components/menu/index.vue';
  import Footer from '@/components/footer/index.vue';
  import TabBar from '@/components/tab-bar/index.vue';
  import TopBar from './components/topBar.vue';
  import Canvas from './components/canvas.vue';
  import Collection from './components/collection.vue';
  import DialogBox from './components/dialogBox.vue';

  const isInit=ref(false);
  const appStore=useAppStore();
  const router = useRouter();
  const route = useRoute();
  const permission = usePermission();
  useResponsive(true);
  const navbarHeight = `60px`;
  const navbar = computed(() => appStore.navbar);
  const renderMenu = computed(() => appStore.menu && !appStore.topMenu);
  const hideMenu = computed(() => appStore.hideMenu);
  const footer = computed(() => appStore.footer);
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
  const drawerVisible = ref(false);
  const drawerCancel = () => {
    drawerVisible.value = false;
  };
  provide('toggleDrawerMenu', () => {
    drawerVisible.value = !drawerVisible.value;
  });
  onMounted(async () => {
    isInit.value = true;
  });
</script>

<script lang="ts">
  export default {
    name: 'LabelPanel', // If you want the include property of keep-alive to take effect, you must name the component
  };
</script>

<style lang="less" scoped>
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
    height: 60px;
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
    display: flex;
    flex-direction: column;
    flex: 1;
  }
  .canvas-container {
    flex: 8; /* Canvas 占据的空间比例 */
  }

  .dialogbox-container {
    flex: 2; /* DialogBox 占据的空间比例 */
  }

  .collection-container {
    width: 400px; /* 或根据需要调整 */
    height: 735px;
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
  .down-right{
    display: flex;
    flex-direction: column;
    align-items: stretch;
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
