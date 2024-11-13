import { BaseTooltip } from 'quill/themes/base';
import Quill, { Range } from 'quill'


export default class QLinkTooltip {
  constructor(quill: Quill, boundsContainer?: HTMLElement) {
    console.log(quill, boundsContainer)
    document.getElementById('u-link')!.addEventListener('click', () => {
        console.log('13213123123')
      })
  }
}
