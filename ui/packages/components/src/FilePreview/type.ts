/**
 * 文件预览 key
 */
export interface FilePreviewProps {
  /**
   * 文件预览 key
   */
  fileUrlKey?: string;
  /**
   * 文件类型 key
   */
  fileTypeKey?: string;
}

/**
 * 文件预览实例
 */
export interface FilePreviewInstance {
  /**
   * 打开预览文件
   * @param fileList 文件列表
   * @param index 打开默认选择文件
   */
  previewFile: (fileList: (File | object)[], index?: number) => void;
}
