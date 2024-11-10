import { createDefaultProps } from '@u-chirp/utils'


type SubMenuItem = Omit<MenuItem, 'children'>

export interface MenuItem {
  key: string | number;
  /**
   * 菜单标题
   */
  title: string;
  /**
   * 仅支持二级菜单
   */
  children?: SubMenuItem[];
}


export interface TopMenuProps {
  /**
   * 菜单位置
   */
  menuPosition?: 'left' | 'center';

  /**
   * 菜单项
   */
  menuItems: MenuItem[];
}

export const topMenuDefaultProps = createDefaultProps<TopMenuProps>({
  menuPosition: 'left',
});

export interface TopMenuEmits {
  (event: 'menuItemClick', key: string | number): void;
}
