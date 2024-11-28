// 平台相关性代码

export * from './type';

function installBrowserModule() {
  import('./browser').then(moduleList => {
    // @ts-ignore
    console.log(new moduleList.FileOps())
    window.platform = {};
    window.platform.fileOps = new moduleList.FileOps()
  })
}
export function installPlatformModule() {
  installBrowserModule()
}
