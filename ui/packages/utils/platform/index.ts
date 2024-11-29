// 平台相关性代码
import type { IFileOps } from "./type";
export * from './type';

// @ts-ignore
window.platform = {};


function toCamelCase(pascalCase: string) {
  if (!pascalCase) return '';
  return pascalCase.charAt(0).toLowerCase() + pascalCase.slice(1);
}

function installBrowserModule() {
  // @ts-ignore
  import('./browser').then(moduleObj => {
    for (let key of Object.keys(moduleObj)) {
      // @ts-ignore
      window.platform[toCamelCase(key)] = new moduleObj[key]();
    }
  })
}


export function installPlatformModule() {
  installBrowserModule()
}

declare global {
  interface Window {
    platform: {
      fileOps: IFileOps
    }
  }
}
