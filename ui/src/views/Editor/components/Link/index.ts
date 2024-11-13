import Quill from 'quill'
import LinkBlot from './formats/link'
import Tooltip from './modules/QLinkTooltip'
import SnowTheme from 'quill/themes/snow'
const Module = Quill.import('core/module')
import { render, h, ref } from 'vue'
import QLink from "./QLink.vue";

class Link extends Module {
  constructor(quill: Quill, options: any) {
    super(quill, options);
    Quill.register('formats/link', LinkBlot, true);


    const divElement = document.createElement('div');
    const qlinkRef = ref(null);
    const vNode = h(QLink, { quill });
    console.log('vNode', vNode)
    render(vNode, divElement);
    // 加入容器
    quill.addContainer(divElement);
    new Tooltip(quill, divElement);
    setTimeout(() => {
      console.log(qlinkRef.value)
    }, 3000)
    // document.getElementById('u-link')!
    //   .addEventListener('click', () => {
    //     console.log('13213123123')
    //   })
  }

  static register() {
    console.log('link-register')
    Quill.register('blots/link', LinkBlot, true)
  }
}

export default Link;

