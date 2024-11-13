import type { Ref } from 'vue'
export interface QLinkInstance {
  href: Ref<string>;
  show: (text?: string) => void;
}
