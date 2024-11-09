import { createDefaultProps } from '@u-chirp/utils'
export interface TopMenuProps {
  menuPosition?: 'left' | 'center';
}

export const topMenuDefaultProps = createDefaultProps<TopMenuProps>({
  menuPosition: 'left',
});
