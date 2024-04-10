<template>
  <div id="canvas"></div>
</template>
  
<script lang="ts" setup>
  import Konva from 'konva';
  import { reactive, toRefs, onMounted } from 'vue';
  import { useRoute, useRouter } from 'vue-router'

  let stage: Konva.Stage;
  let layer: Konva.Layer;

  onMounted(() => {
    // 初始化舞台（stage）和图层（layer）
    stage = new Konva.Stage({
      container: 'canvas', // id of container <div>
      width: window.innerWidth,
      height: window.innerHeight,
    });

    layer = new Konva.Layer();
    stage.add(layer);

    // 示例：在图层上添加一个矩形
    const rect = new Konva.Rect({
      x: 400,
      y: 200,
      width: 100,
      height: 50,
      fill: 'red',
      stroke: 'black',
      strokeWidth: 4,
    });

    // 将矩形添加到图层
    layer.add(rect);

    // 绘制图层
    layer.draw();
  });
</script>
<style>
  #canvas {
    overflow: hidden;
    position: relative;
    height: 500px;
    width: 800px;
    margin: 0 auto;
    border: 1px dashed #ccc;
  }
</style>