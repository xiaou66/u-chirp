import type Quill from 'quill'
import type Toolbar from 'quill/modules/toolbar'
import { h, render } from 'vue'
import Link from './Link'

function handleLinkClick(value: string) {
  const range = quill.getSelection()!;
  console.log(quill.getBounds(range.index));
  const contents = quill.getContents(range.index);
  console.log(contents)
  return;
}
// function
export function useLinkTool(quill: Quill) {
  const toolbar = quill.getModule('toolbar') as Toolbar;
  toolbar.addHandler('link', handleLinkClick);
  const container = document.createElement('div');
  container.className = 'custom-container';
  quill.addContainer(container);
  render(h(Link, {}), container);
}
