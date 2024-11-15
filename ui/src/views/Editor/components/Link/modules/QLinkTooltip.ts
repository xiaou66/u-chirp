import Quill from 'quill'
import type { QLinkInstance } from '../types'
import type { VNode } from 'vue'
import Tooltip from 'quill/ui/tooltip'
import type Toolbar from 'quill/modules/toolbar'
import Emitter  from 'quill/core/emitter'
import  { Range } from 'quill'
import { isSelectText } from '../../../utils'


export default class QLinkTooltip extends Tooltip {
  private readonly __qLinkInstance: QLinkInstance

  private __selectRange?: Range;

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
      if (isSelectText(this.quill)) {
        this.edit();
      }
    }
  }

  listen() {
    this.quill.on('selection-change', (range, oldRange, source) => {
      if (source === Emitter.sources.API) {
        return;
      }
      console.log(range, oldRange, source);
      debugger
      if (!range) {
        return;
      }
      let link = '';
      const contents = this.quill.getContents(range.index, 1);

      if (contents.length()) {
        const ops = contents
          .filter(item => !!item.attributes && item.attributes.hasOwnProperty('link'));
        if (ops.length) {
          link = ops[0].attributes &&  ops[0].attributes.link as string || '';
        }
      }
     if (link) {
       const [leafBlot, index] = this.quill.getLeaf(range.index);
       const editRange = leafBlot && new Range(range.index - index, leafBlot!.value().toString().length);
       this.edit(link, editRange);
     }
    });
  }

  edit(link?: string, range? : Range | null) {
     range = range || this.quill.getSelection()!;
    if (range) {
      this.position(this.quill.getBounds(range.index)!)
      super.show()
      console.log(this.quill.getText(range))
      this.__selectRange = range;
      this.__qLinkInstance.show(link || this.quill.getText(range))
    }
  }


  save() {
    super.hide();
    if (this.__selectRange) {
      const { href } = this.__qLinkInstance;
      console.log(href)
      if (href.value) {
        this.quill.formatText(this.__selectRange, 'link' , href.value,  Emitter.sources.USER);
      } else {
        this.quill.formatText(this.__selectRange.index, this.__selectRange.length, { link: false });
      }
      delete this.__selectRange;
    }
  }
}
