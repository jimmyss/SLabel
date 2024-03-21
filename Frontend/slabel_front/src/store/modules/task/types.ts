export type Status = '' | 'Proceeding' | 'Finished';
export interface Task {
  id?: number;
  status: Status;
  title?: string;
  description?: string;
  direction?: string;
  deadline?: Date;
  createdDate?: Date;
  datasetId?: number;
  modelDir?: string;
}
export interface TaskListState {
  taskList: [];
}
