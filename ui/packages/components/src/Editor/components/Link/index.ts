import Quill from 'quill'
import LinkBlot from './formats/link'
import Tooltip from './modules/QLinkTooltip'
const Module = Quill.import('core/module')
import { render, h } from 'vue'
import QLink from "./QLink.vue";

class Link extends Module {
  public readonly __qLinkTooltip: Tooltip;
  constructor(quill: Quill, options: any) {
    super(quill, options);
    Quill.register('formats/link', LinkBlot, true);



    const divElement = document.createElement('div');
    const vNode = h(QLink, { quill });
    render(vNode, divElement);
    // vNode.component!.exposed;
    console.log(vNode)
    // 加入容器
    quill.addContainer(divElement);
    this.__qLinkTooltip = new Tooltip(quill, vNode);
  }

  static register() {
    console.log('link-register')
    Quill.register('blots/link', LinkBlot, true)
  }
}

export default Link;

