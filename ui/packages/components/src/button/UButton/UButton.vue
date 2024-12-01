<script setup lang="ts">
import { Button } from "@u-chirp/shadcn";
import { SvgIcon} from "@u-chirp/components";
import { useSingleLoading } from "@u-chirp/utils";
import type { UButtonProps } from "./UButton";
import { defaultUButtonProps } from "./UButton";

const props = withDefaults(defineProps<UButtonProps>(), {
  ...defaultUButtonProps
});

const [proxyClick, loadStatus] = useSingleLoading(props.handleClick as any);


</script>

<template>
  <Button :class="size === 'auto' ? 'w-full' : ''"
          @click="proxyClick"
          :disabled="loadStatus || props.disable">
    <svg-icon v-if="loadStatus" color="hsl(var(--primary-foreground))"
              svg-class="animate-spin"
              name="default-loading" />
    <slot v-else name="icon"></slot>
    <slot>
    </slot>
  </Button>
</template>

<style scoped>

</style>
