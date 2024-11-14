import Quill from 'quill'
import type { QLinkInstance } from '../types'
import type { VNode } from 'vue'
import { isSelectText } from '../../../utils'
import Tooltip from 'quill/ui/tooltip'
import type Toolbar from 'quill/modules/toolbar'
import Emitter from 'quill/core/emitter'
import type { Range } from 'quill'


export default class QLinkTooltip extends Tooltip {
  private readonly __qLinkInstance: QLinkInstance

  private __selection?: Range;

  constructor(quill: Quill, vNode: VNode) {
    super(quill, quill.root)
    console.log('vNode.el', vNode.el, this.root)
    this.root = vNode.el as HTMLDivElement
    this.__qLinkInstance = vNode.component!.exposed as QLinkInstance
    this.hide()
    this.listen()
    const toolbar = quill.getModule('toolbar') as Toolbar
    toolbar.addHandler('link', this.handleToolBarClick.bind(this))
  }

  handleToolBarClick(value: string) {
    if (value) {
      this.show()
    }
  }

  listen() {
    this.root.addEventListener('mouseleave', () => {
      // this.save();
    });

    this.boundsContainer.addEventListener('click', () => {
      // this.save()
    });
    this.quill.on('selection-change', (range, oldRange, source) => {
      console.log(range, oldRange, source);
      this.show();
    });
  }

  show() {
    if (!isSelectText(this.quill)) {
      this.hide()
      return
    }
    console.log('show........')
    const selection = this.quill.getSelection()
    console.log(selection)
    if (selection) {
      this.position(this.quill.getBounds(selection.index)!)
      super.show()
      console.log(this.quill.getText(selection))
      this.__selection = selection;
      this.__qLinkInstance.show(this.quill.getText(selection))
    }
  }


  save() {
    super.hide();
    if (this.__selection) {
      const { href } = this.__qLinkInstance;
      if (href.value) {
        this.quill.formatText(this.__selection, 'link' , href.value,  Emitter.sources.USER);
      } else {
        this.quill.formatText(this.__selection, '' , href.value,  Emitter.sources.USER);
      }
    }
  }
}
