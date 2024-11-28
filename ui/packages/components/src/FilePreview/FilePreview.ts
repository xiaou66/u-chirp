import { inject } from "vue";
import {FilePreviewInstance} from "./type";

export function useFilePreview() {
  return inject<FilePreviewInstance>('filePreviewInstance');
}
