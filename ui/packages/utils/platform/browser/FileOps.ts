import type {IFileOps} from "../type";

export class FileOps implements IFileOps {
  openSelectFile(): Promise<File[]> {
    return new Promise((resolve, reject) => {
      const input = document.createElement('input');
      input.type = 'file';
      input.multiple = true;
      input.click();
      input.addEventListener('change', (e: Event) => {
        resolve(Array.from((e.target as any).files as FileList));
        input.remove();
      });
      input.addEventListener('cancel', (e: Event) => {
        resolve([])
        input.remove()
      })
    });
  }
}

