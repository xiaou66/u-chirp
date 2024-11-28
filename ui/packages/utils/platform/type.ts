export interface IFileOps {
  /**
   * 获取用户选择文件
   */
  openSelectFile(): Promise<File[]>;
}
