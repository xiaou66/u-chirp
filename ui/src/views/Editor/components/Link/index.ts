import Quill from 'quill'
import LinkBlot from './formats/link'
import Tooltip from './modules/tooltip'
import SnowTheme from 'quill/themes/snow'
const Module = Quill.import('core/module')

class Link extends Module {
  constructor(quill: Quill, options: any) {
    super(quill, options);
    Quill.register('formats/link', LinkBlot, true);
    new Tooltip(quill, document.createElement('div'));
    console.log('link--constructor')
    document.getElementById('u-link')!
      .addEventListener('click', () => {
        console.log('13213123123')
      })
  }

  static register() {
    console.log('link-register')
    Quill.register('blots/link', LinkBlot, true)
  }
}

export default Link;

