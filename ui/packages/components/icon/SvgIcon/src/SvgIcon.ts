import { createDefaultProps } from '@u-chirp/utils'

export interface SvgIconProps {
  name: string;
  color?: string;
  prefix?: string;
  size?: number;
}

export const svgIconDefaultProps = createDefaultProps<SvgIconProps>({
  color: '#333',
  prefix: 'icon',
  size: 14,
});
