import { request } from '../../appService';
/**
 * 上传文件
 * @param file
 */
export async function uploadFile(file: File) {
  return await request('POST', '/local/uploadFile', {
    data: file,
    headers: {
      'Content-Type': 'multipart/form-data; boundary={boundary}'
    }
  });
}
