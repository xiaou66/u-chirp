import type Quill from 'quill'
import type Toolbar from 'quill/modules/toolbar'
import { h, render } from 'vue'
import Link from './Link.vue'



class LinkTool {
  __quill: Quill;
  constructor(quill: Quill) {
    this.__quill = quill;
  }

  get quill() {
    return this.__quill;
  }

  public install() {
    const container = document.createElement('div');
    container.className = 'custom-container';
    this.quill.addContainer(container);
    render(h(Link, { quill: this.quill }), container);
  }
}

export function installLinkTool(quill: Quill) {
  new LinkTool(quill)
    .install();
}
