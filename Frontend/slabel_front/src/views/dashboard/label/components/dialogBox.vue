<template>
    <a-list :max-height="120" @reach-bottom="fetchData" :scrollbar="scrollbar">
        <template #scroll-loading>
            <div v-if="bottom">No more data</div>
            <a-spin v-else />
        </template>
        <a-list-item v-for="item of data" :key="item">{{item}}</a-list-item>
    </a-list>
    <a-input-search :style="{width:'800px'}" placeholder="please enter prompt" search-button>
        <template #button-icon>
            <icon-send/>
        </template>
    </a-input-search>
</template>

<script lang="ts">
import { reactive, ref } from 'vue';

export default {
  setup() {
    const current = ref(1);
    const bottom = ref(false);
    const data = reactive([]);
    const scrollbar = ref(true);

    const fetchData = () => {
      if (current.value <= 5) {
        window.setTimeout(() => {
          const index = data.length;
          data.push(
            `Beijing Bytedance Technology Co., Ltd. ${index + 1}`,
            `Bytedance Technology Co., Ltd. ${index + 2}`,
            `Beijing Toutiao Technology Co., Ltd. ${index + 3}`,
            `Beijing Volcengine Technology Co., Ltd. ${index + 4}`,
            `China Beijing Bytedance Technology Co., Ltd. ${index + 5}`
          );
          current.value += 1
        }, 2000)
      } else {
        bottom.value = true
      }
    }

    return {
      current,
      bottom,
      data,
      fetchData,
      scrollbar
    }
  },
}
</script>

<style scoped>
    .action {
    display: inline-block;
    padding: 0 4px;
    color: var(--color-text-1);
    line-height: 24px;
    background: transparent;
    border-radius: 2px;
    cursor: pointer;
    transition: all 0.1s ease;
    }
    .action:hover {
    background: var(--color-fill-3);
    }
</style>
