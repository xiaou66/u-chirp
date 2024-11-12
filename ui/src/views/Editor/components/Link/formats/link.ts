import type TypeInline from 'quill/blots/inline'
import Quill from 'quill'
const Inline = Quill.import('blots/inline') as typeof TypeInline

export default class Link extends Inline {
  static blotName: string
  static tagName: string
  static SANITIZED_URL: string
  static PROTOCOL_WHITELIST: string[]
  static className: string
  static autoProtocol: string = ''

  static create(value: string): HTMLElement {
    console.log('dsndjasjkdaskdjaskdjaskd')
    const node = super.create()
    let href: string = value

    // 如果链接没有协议且定义了自动协议，则添加协议
    if (href.includes('//') && this.autoProtocol) {
      href = `${this.autoProtocol}://${value}`
    }

    href = Link.sanitize(href)

    // 设置超链接基本属性
    node.setAttribute('href', href)
    node.setAttribute('target', '_blank')
    return node
  }

  /**
   * 检查链接是否合法
   * @param url 待检查的链接
   */
  static sanitize(url: string) {
    // TODO 暂时认为用户链接都是安全的, 后续可以调用备案接口或者使用白名单控制
    return url
  }

  static formats(domNode: HTMLElement): any {
    return domNode.getAttribute('href')
  }

  /**
   * 格式化链接
   * @param name 是当前 blot 的名称或者值为 false/null
   * @param value 值
   */
  format(name: string, value: any) {
    if (name !== this.statics.blotName || [false, null].includes(value)) {
      super.format(name, value)
    } else {
      this.domNode.setAttribute('href', Link.sanitize(value));
    }
  }
}

Link.blotName = 'link';
Link.tagName = 'A';
Link.SANITIZED_URL = 'about:blank';
Link.PROTOCOL_WHITELIST = [];
Link.className = 'u-link';
