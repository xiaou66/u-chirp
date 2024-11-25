import {request} from "../appService";


/**
 * 获取多语言
 */
export async function getLanguageConfig(language: string) {
  return await request<any>('GET', '/system/getLanguageConfig', {
    params: {
      language
    }
  });
}
