import { computed, type ComputedRef, type Ref, ref } from 'vue'

/**
 * 判断函数是否是异步的
 * @param fn
 * @return 是否异步结果
 */
function isAsyncFunction(fn: any) {
  return fn instanceof Function && fn.constructor.name === 'AsyncFunction';
}

type ProxyFunction = (...any: any[]) => Promise<any | void>;

/**
 * 单步加载，适合在单个加载按钮使用
 * @param func 加载函数
 * @return [被代理后的函数, 加载状态]
 */
export const useSingleLoading = (func: Function):  [ProxyFunction, Ref<boolean>] =>  {
  const loadStatus = ref(false);
  const proxyFunc =  (...args: any[]) => {
    loadStatus.value = true;
    return new Promise((resolve, reject) => {
      setTimeout(async () => {
        try {
          if (isAsyncFunction(func)) {
            return await func(...args);
          } else {
            resolve(func(...args));
          }
        }catch (e) {
          console.log('useSingleLoading', e);
          reject(e);
        } finally {
          loadStatus.value = false;
        }
      }, 50);
    });
  }
  return [proxyFunc, loadStatus];
}


type getLoadUniqueKey<T> = (...args: any[]) => T;
type loadStatusCompute<T> = ComputedRef<(uniqueKey: T) => boolean>;

/**
 * 适用列表或多个需要加载的状态的函数
 * @param func 加载函数
 * @param getLoadUniqueKey 获取判断加载的唯一标识
 * @return [被代理后的函数, 加载状态(唯一键), 所有加载中的唯一标识集合]
 */
export const useMultiLoading = <T=any> (func: Function, getLoadUniqueKey: getLoadUniqueKey<T>): [ProxyFunction, loadStatusCompute<T>, Ref<T[]>] =>  {
  const loadStatusList = ref<any[]>([]);
  const proxyFunc = async (...args: any[]) => {
    const loadUniqueKey = getLoadUniqueKey(...args)
    loadStatusList.value.push(loadUniqueKey);
    try {
      if (isAsyncFunction(func)) {
        return await func(...args);
      } else {
        return func(...args);
      }
    }catch (e) {
      console.error('useLoading', e);
    } finally {
      const index = loadStatusList.value.findIndex(item => item === loadUniqueKey);
      if (index !== -1) {
        loadStatusList.value.splice(index, 1);
      }
    }
  }

  const loadStatus = computed(() =>
    (uniqueKey: T) => loadStatusList.value.includes(uniqueKey));

  return [proxyFunc, loadStatus, loadStatusList];
}
