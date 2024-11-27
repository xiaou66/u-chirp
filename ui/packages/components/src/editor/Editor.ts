import type Quill from "quill";

export interface UserEditorInstance {
  getEditor: () => Quill;
  getFileList: () => File[];
}
