export interface IFileOps {
  /**
   * 获取用户选择文件
   */
  openSelectFile(): Promise<File[]>;
}

export interface IClipboard {
  /**
   * 复制文本
   * @param text 文本
   */
  copyText(text: string);
}
