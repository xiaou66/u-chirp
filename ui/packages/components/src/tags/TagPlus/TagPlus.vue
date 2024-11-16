<script setup lang="ts">
import { SvgIcon } from '@u-chirp/components';
import type { TagPlusProps } from './TagPlus';
import  { tagPlusDefaultProps } from './TagPlus';

const props = withDefaults(defineProps<TagPlusProps>(), tagPlusDefaultProps as any);

const tagColors = ['tag-cyan', 'tag-urple', 'tag-pinkpurple', 'tag-gold', 'tag-orangered', 'tag-lime', 'tag-magenta'];
</script>

<template>
  <el-tag :class="tagColors.includes(color) ? color?.replace('tag-', '') : ''" :type="type">
    <div class="flex gap-1 items-center">
      <slot v-if="icon?.name" name="icon">
        <svg-icon v-bind="icon" color="var(--el-tag-text-color)" />
      </slot>
      <slot name="default">
        {{ tagName }}
      </slot>
    </div>
  </el-tag>
</template>

<style scoped>
.dark {
  .el-tag.cyan {
    --el-tag-bg-color: rgba(63,212,207, .2);
    --el-tag-border-color: rgb(63,212,207);
    --el-tag-hover-color: rgb(63,212,207);
    --el-tag-text-color: rgba(63,212,207);
  }

  .el-tag.purple {
    --el-tag-bg-color: rgba(142,81,218, .2);
    --el-tag-border-color: rgb(142,81,218);
    --el-tag-hover-color: rgb(1142,81,218);
    --el-tag-text-color: rgba(142,81,218);
  }

  .el-tag.pinkpurple {
    --el-tag-bg-color: rgba(225,61,219, .2);
    --el-tag-border-color: rgb(225,61,219);
    --el-tag-hover-color: rgb(225,61,219);
    --el-tag-text-color: rgb(225,61,219);
  }

  .el-tag.gold {
    --el-tag-bg-color: rgba(249,204,68,.2);
    --el-tag-hover-color: rgb(249,204,68);
    --el-tag-text-color: rgb(249,204,68);
    --el-tag-border-color: rgb(249,204,68);
  }

  .el-tag.orangered {
    --el-tag-bg-color: rgba(249,146,90,.2);
    --el-tag-hover-color: rgb(249,146,90);
    --el-tag-text-color: rgb(249,146,90);
    --el-tag-border-color: rgb(249,146,90);
  }

  .el-tag.lime {
    --el-tag-bg-color: rgba(184,226,75,.2);
    --el-tag-hover-color: rgb(184,226,75);
    --el-tag-text-color: rgb(184,226,75);
    --el-tag-border-color: rgb(184,226,75);
  }

  .el-tag.magenta {
    --el-tag-bg-color: rgba(247,86,169, .2);
    --el-tag-hover-color: rgb(247,86,169);
    --el-tag-text-color: rgb(247,86,169);
    --el-tag-border-color: rgb(247,86,169);
  }
}





.el-tag.cyan {
  --el-tag-bg-color: rgb(232,255,251);
  --el-tag-border-color: rgb(20,201,201);
  --el-tag-hover-color: rgb(20,201,201);
  --el-tag-text-color: rgb(20,201,201);
}

.el-tag.purple {
  --el-tag-bg-color: rgb(245,232,255);
  --el-tag-border-color: rgb(114,46,209);
  --el-tag-hover-color: rgb(114,46,209);
  --el-tag-text-color: rgb(114,46,209);
}


.el-tag.pinkpurple {
  --el-tag-bg-color: rgb(245,232,255);
  --el-tag-border-color: rgb(217,26,217);
  --el-tag-hover-color: rgb(217,26,217);
  --el-tag-text-color: rgb(217,26,217);
}

.el-tag.gold {
  --el-tag-bg-color: rgb(255,252,232);
  --el-tag-hover-color: rgb(255,252,232);
  --el-tag-text-color: rgb(247,186,30);
  --el-tag-border-color: rgb(247,186,30);
}

.el-tag.orangered {
  --el-tag-bg-color: rgb(255,243,232);
  --el-tag-hover-color: rgb(255,243,232);
  --el-tag-text-color: rgb(247,114,52);
  --el-tag-border-color: rgb(247,114,52);
}

.el-tag.lime {
  --el-tag-bg-color: rgb(252,255,232);
  --el-tag-hover-color: rgb(252,255,232);
  --el-tag-text-color: rgb(159,219,29);
  --el-tag-border-color: rgb(159,219,29);
}

.el-tag.magenta {
  --el-tag-bg-color: rgba(255,232,241);
  --el-tag-hover-color: rgb(255,232,241);
  --el-tag-text-color: rgb(245,49,157);
  --el-tag-border-color: rgb(245,49,157);
}
</style>
