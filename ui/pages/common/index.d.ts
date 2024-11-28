import type { FileOps } from "@u-chirp/utils";

declare global {
  interface Window {
    platform: {
      fileOps: FileOps
    }
  }
}
