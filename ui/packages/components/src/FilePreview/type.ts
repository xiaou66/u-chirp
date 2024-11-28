/**
 * 文件预览 key
 */
export interface FilePreviewProps {
  /**
   * 文件列表
   */
  fileList: File[] | object[];
  /**
   * 文件预览 key
   */
  fileUrlKey?: string;
  /**
   * 文件类型 key
   */
  fileTypeKey?: string;
}
