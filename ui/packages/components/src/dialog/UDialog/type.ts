export interface UDialogProps {
  hideTrigger?: boolean;
}

export interface UDialogInstance {
  show(): void;
  close(): void;
}
