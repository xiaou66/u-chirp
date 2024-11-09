// 提取所有非必填属性的类型
type NonRequiredProps<T> = {
  [K in keyof T]-?: undefined extends T[K] ? K : never
}[keyof T];

// 将非必填属性转换为必填属性
type RequiredNonRequiredProps<T> = {
  [K in NonRequiredProps<T>]-?: T[K];
};


export function createDefaultProps<T>(defaultValue: RequiredNonRequiredProps<T>) {
  return defaultValue;
}
