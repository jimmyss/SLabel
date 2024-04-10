<template>
  <a-table :columns="columns" :data="data">
    <template #columns>
        <a-table-column title="ID" data-intdex="id"></a-table-column>
        <a-table-column title="Category Name" data-intdex="category"></a-table-column>
        <a-table-column title="Optional">
            <a-table-column title="Delete">
                <template #cell="{ record}">
                    <a-button type="primary" @click="deleteItem(record)" shape="circle" size="mini">
                        <template #icon>
                            <icon-delete size="16"/>
                        </template>
                    </a-button>
                </template>
            </a-table-column>
            <a-table-column title="Edit">
                <template #cell="{record}">
                    <a-button type="primary" @click="editItem(record)" shape="circle" size="mini">
                        <template #icon>
                            <icon-edit size="16" />
                        </template>
                    </a-button>
                </template>
            </a-table-column>
        </a-table-column>
    </template>
  </a-table>
</template>

<script setup>
import { ref } from 'vue';

const data = ref([
{ id: 1, category: 'Category 1' },
{ id: 2, category: 'Category 2' },
// 更多数据...
]);

const columns = ref([
{ title: 'ID', dataIndex: 'id' },
{ title: 'Category Name', dataIndex: 'category' },
{
    title: 'Operation',
    key: 'operation',
    slots: { customRender: 'operation' },
},
]);

function editItem(record) {
    console.log('Editing item', record);
    // 编辑操作逻辑
}

function deleteItem(record) {
console.log('Deleting item', record);
// 删除操作逻辑
}
</script>
<style scoped>

</style>
