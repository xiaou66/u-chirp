import { BaseTooltip } from 'quill/themes/base';
import Quill, { Range } from 'quill'


export default class Tooltip {
  textbox = document.createElement('input');
  constructor(quill: Quill, boundsContainer?: HTMLElement) {
    console.log(quill, boundsContainer)
    document.createElement('u-link')
      .addEventListener('click', () => {
        console.log('13213123123')
      })
  }
}
