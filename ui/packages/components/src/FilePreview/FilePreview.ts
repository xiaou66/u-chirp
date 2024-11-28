import { inject } from "vue";
import type { FilePreviewInstance } from "./type";

export function useFilePreview() {
  return inject<FilePreviewInstance>('filePreviewInstance');
}
