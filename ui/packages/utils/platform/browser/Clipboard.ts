import type { IClipboard } from "../type";

export class Clipboard implements IClipboard {


  copyText(text: string) {
    navigator.clipboard.writeText(text);
  }
}
