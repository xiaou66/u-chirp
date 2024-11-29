/**
 * 文件上传响应
 */
export interface FileInfoItem {
  /**
   * 文件 id，使用 string 来表示 Long 类型
   */
  fileId: string;

  /**
   * 缩略图
   */
  thumbnailUrl: string;

  /**
   * 预览图
   */
  previewUrl: string;

  /**
   * 文件类型
   */
  fileType: string;
}
