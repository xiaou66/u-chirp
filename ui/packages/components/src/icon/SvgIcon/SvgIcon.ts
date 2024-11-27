import { createDefaultProps } from '@u-chirp/utils'

export interface SvgIconProps {
  /**
   * svg 路径名称多层目录使用 - 连接
   */
  name: string;
  /**
   * 图标颜色
   */
  color?: string;
  /**
   * 鼠标移动上去颜色
   */
  hoverColor?: string;
  /**
   * 图标前缀
   */
  prefix?: string;
  /**
   * 字体大小
   */
  size?: number;
  /**
   * svg className 样式 <br/>
   * 推荐: 动画使用
   */
  svgClass?: string;
}

export const svgIconDefaultProps = createDefaultProps<SvgIconProps>({
  color: '#333',
  prefix: 'icon',
  size: 14,
  svgClass: '',
  hoverColor: '',
});
