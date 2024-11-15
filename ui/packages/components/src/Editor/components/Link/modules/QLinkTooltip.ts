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

  private __show: boolean = false;

  private __bind: any;

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
        this.edit()
      }
    }
  }

  listen() {
    this.quill.on('selection-change', (range, oldRange, source) => {
      console.log('selection-change', range, oldRange, source, this.__show)
      if (source === Emitter.sources.API || !range) {
        console.log('selection-change-return', range, oldRange, source)
        return
      }
      console.log('selection-change-01', range, oldRange, source)
      let link = ''
      const contents = this.quill.getContents(range.index, 1)

      if (contents.length()) {
        const ops = contents.filter(
          (item) => !!item.attributes && item.attributes.hasOwnProperty('link'),
        )
        if (ops.length) {
          link = (ops[0].attributes && (ops[0].attributes.link as string)) || ''
        }
      }
      if (link) {
        console.log('link')
        const [leafBlot, index] = this.quill.getLeaf(range.index)
        const editRange =
          leafBlot && new Range(range.index - index, leafBlot!.value().toString().length)
        this.edit(link, editRange, false);
      } else {
        if (this.__show) {
          this.save();
          console.log('link...........')
        }
      }
    })
  }

  edit(link?: string, range?: Range | null, edit?: boolean) {
    range = range || this.quill.getSelection()!
    console.log('edit....', range)
    if (range) {
      this.position(this.quill.getBounds(range.index)!)
      setTimeout(() => {
        this.show();
      });
      console.log(this.quill.getText(range))
      this.__selectRange = range
      this.__qLinkInstance.show(link || this.quill.getText(range), edit);
    }
  }

  save() {
    this.hide()
    if (this.__selectRange && this.__qLinkInstance.edit.value) {
      const { link } = this.__qLinkInstance;
      if (link.value) {
        this.quill.formatText(this.__selectRange, 'link', link.value, Emitter.sources.USER);
      } else {
        this.quill.formatText(this.__selectRange.index, this.__selectRange.length, { link: false });
      }
    }
    this.__selectRange = undefined;
  }

  show() {
    super.show();
    this.__show = true;
  }

  hide() {
    super.hide();
    this.__show = false;
  }
}
