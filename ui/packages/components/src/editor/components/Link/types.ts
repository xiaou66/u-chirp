import type { Ref } from 'vue'
export interface QLinkInstance {
  edit: Ref<boolean>;
  link: Ref<string>;
  /**
   * 显示 link Tooltip
   * @param text 文本
   * @param edit 是否直接进入编辑
   */
  show: (text?: string, edit?: boolean) => void;
  hide: () => void;
}
