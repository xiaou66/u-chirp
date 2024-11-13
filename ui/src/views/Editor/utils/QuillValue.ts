import type Quill from 'quill'

/**
 * 获取选择文本
 * @param quill
 */
export function getSelectText(quill: Quill) {
  const range = quill.getSelection();
  if (range) {
    const selectedText = quill.getText(range.index, range.length);
    if (selectedText) {
      return selectedText;
    } else {
      return '';
    }
  } else {
    return '';
  }
}

/**
 * 是否选择文本
 */
export function isSelectText(quill: Quill) {
  return !!getSelectText(quill);
}
