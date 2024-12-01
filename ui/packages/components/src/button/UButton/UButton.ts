import {createDefaultProps} from "@u-chirp/utils";

export interface UButtonProps {
  handleClick?: (...args: any[]) => Promise<void> | void;
  size?: 'auto' | 'default';
  disabled?: boolean;
}

export const defaultUButtonProps = createDefaultProps<Omit<UButtonProps, keyof Partial<UButtonProps>>>({
  size: 'default',
});
