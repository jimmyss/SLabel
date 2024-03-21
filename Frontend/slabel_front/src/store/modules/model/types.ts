export type Status = '' | 'Created' | 'Proceeding' | 'Paused' | 'Stopped';
export interface Model {
  id?: number;
  status: Status;
  modelName?: string;
  description?: string;
  direction?: string;
  modelIou?: number;
  epoch?: number;
  trainTime?: number;
  createdDate?: Date;
  saveDir?: string;
}
export interface ModelListState {
  modelList: [];
}
