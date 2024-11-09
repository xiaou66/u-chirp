import { buildProps } from "../../../../src/utils";
import type { ExtractPropTypes } from 'vue'

export const topMenuProps = buildProps({
  menuPosition: {
    values: ['left', 'center'],
    default: 'left',
  }
});

export type TopMenuProps = ExtractPropTypes<typeof topMenuProps>;
