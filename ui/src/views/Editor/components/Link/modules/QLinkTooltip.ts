import Quill from 'quill'
import type { QLinkInstance } from '../types'
import type { VNode } from 'vue'
import { isSelectText } from '../../../utils'
import Tooltip from 'quill/ui/tooltip'
import type Toolbar from 'quill/modules/toolbar'
import Emitter from 'quill/core/emitter'
import type { Range } from 'quill'
import Link from '../index'


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
      this.edit()
    }
  }

  listen() {
    this.root.addEventListener('mouseleave', () => {
      this.save();
    });

    // this.boundsContainer.addEventListener('click', () => {
    //   if (this.__selection) {
    //     this.save()
    //   }
    // });
    this.quill.on('selection-change', (range, oldRange, source) => {
      console.log(range, oldRange, source);
      debugger
      if (!range) {
        return;
      }
      let link = '';
      const leaf = this.quill.getLine(range.index);
      console.log(leaf)
      console.log(this.quill.getIndex(leaf[0]!))
      // const [blot, index] = leaf;
      // if (blot && blot.parent) {
      //   console.log('aaa')
      // }
      const contents = this.quill.getContents(range.index, 1);

      if (contents.length()) {
        const ops = contents
          .filter(item => !!item.attributes && item.attributes.hasOwnProperty('link'));
        if (ops.length) {
          link = ops[0].attributes &&  ops[0].attributes.link as string || '';
        }
      }
      console.log(contents);
     if (link) {
       this.edit(link);
     }
    });
  }

  edit(link?: string) {
    const selection = this.quill.getSelection()
    if (selection) {
      this.position(this.quill.getBounds(selection.index)!)
      super.show()
      console.log(this.quill.getText(selection))
      this.__selection = selection;
      this.__qLinkInstance.show(link || this.quill.getText(selection))
    }
  }


  save() {
    super.hide();
    if (this.__selection) {
      const { href } = this.__qLinkInstance;
      console.log(href)
      if (href.value) {
        this.quill.formatText(this.__selection, 'link' , href.value,  Emitter.sources.USER);
      } else {
        this.quill.formatText(this.__selection.index, this.__selection.length, { link: false });
      }
      delete this.__selection;
    }
  }
}
