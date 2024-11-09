import { createDefaultProps } from '@u-chirp/utils'


export interface MenuItem {
  key: string | number;
  /**
   * 菜单标题
   */
  title: string;
  /**
   * 仅支持二级菜单
   */
  children?: MenuItem[];
}


export interface TopMenuProps {
  /**
   * 菜单位置
   */
  menuPosition?: 'left' | 'center';

  menuItems: MenuItem[];
}

export const topMenuDefaultProps = createDefaultProps<TopMenuProps>({
  menuPosition: 'left',
});

export interface TopMenuEmits {
  (event: 'menuItemClick', key: string): void;
}
