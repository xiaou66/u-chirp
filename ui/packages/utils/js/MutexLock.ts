/**
 * 互斥锁 <br/>
 * 实现了轻量锁, 可以直接锁 id 而不是全部锁住
 */
export class MutexLock {
  private readonly locks: {
    [lockKey: string]: {
      locked: boolean;
      queue: (() => void)[]
    }
  };

  constructor() {
    this.locks = {};
  }

  private getLock(lockKey: string) {
    if (!this.locks[lockKey]) {
      this.locks[lockKey] = { locked: false, queue: [] };
    }
    return this.locks[lockKey];
  }

  /**
   * 获取锁-永久等待锁
   * @param lockKey 锁 id
   */
  public async acquire(lockKey: string): Promise<() => void> {
    return this.tryAcquire(lockKey, -1);
  }

  /**
   * 获取锁
   * @param lockKey 锁 id
   * @param timeout 超时时间默认 5000
   */
  public async tryAcquire(lockKey: string, timeout: number = 5000): Promise<() => void> {
    const lock = this.getLock(lockKey);
    return new Promise((resolve, reject) => {
      // 定义获取锁函数
      const tryAcquire = () => {
        if (!lock.locked) {
          lock.locked = true;
          resolve(this.release.bind(this, lockKey));
        } else {
          lock.queue.push(tryAcquire);
        }
      };

      if (timeout > 0) {
        // 设置超时处理
        setTimeout(() => {
          // 如果超时，拒绝 Promise，并清空队列
          reject(new Error(`Timeout while trying to acquire lock for ID: ${lockKey}`));
          // 清理队列
          lock.queue = [];
        }, timeout);
      }

      // 尝试获取锁
      tryAcquire();
    });
  }

  public release(lockKey: string): void {
    const lock = this.getLock(lockKey);
    lock.locked = false;
    if (lock.queue.length > 0) {
      const next = lock.queue.shift();
      if (next) {
        next();
      }
    }
  }
}
