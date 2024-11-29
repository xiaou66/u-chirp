import { request } from '../../appService';
import type {FileInfoItem} from "./type";
/**
 * 上传文件
 * @param file
 */
export async function uploadFile(file: File): Promise<FileInfoItem> {
  const formData = new FormData();
  formData.append("file", file);
  return await request<FileInfoItem>('POST', '/local/uploadFile', {
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data; boundary={boundary}'
    }
  });
}
