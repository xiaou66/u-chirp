import Quill from 'quill'
import { h, render } from 'vue'
import LinkPlus from './LinkPlus.vue'

const Inline = Quill.import('blots/inline')  as any;

class LinkBlot extends Inline {
  static blotName = 'link';
  static tagName = 'a';
  static create(url: string) {
    const node = super.create();
    // Sanitize url if desired
    node.setAttribute('href', url);
    // Okay to set other non-format related attributes
    node.setAttribute('target', '_blank');
    return node;
  }

  static formats(node: any) {
    return node.getAttribute('href');
  }
}
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
    render(h(LinkPlus, { quill: this.quill }), container);
  }
}

export function installLinkTool(quill: Quill) {
  new LinkTool(quill)
    .install();
}
