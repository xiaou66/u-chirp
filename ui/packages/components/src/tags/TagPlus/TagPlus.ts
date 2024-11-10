import { createDefaultProps } from '@u-chirp/utils'
import type { TagProps } from 'element-plus'
import type { SvgIconProps } from '@u-chirp/components'


type TagColor = 'tag-cyan' | 'tag-purple' | 'tag-pinkpurple' | 'tag-gold' | 'tag-orangered' | 'tag-lime' | 'tag-magenta';
export interface TagPlusProps extends Partial<TagProps> {

  /**
   * 标签名称
   */
  tagName: string;
  icon?: SvgIconProps;
  color?: TagColor | string
}

export const tagPlusDefaultProps = createDefaultProps<Omit<TagPlusProps, keyof Partial<TagProps>>>({
  icon: {
    name: 'icon',
  }
});
