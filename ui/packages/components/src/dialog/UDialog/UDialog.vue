<script setup lang="ts">
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogFooter,
  Drawer,
  DrawerContent,
  DrawerDescription,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
  DrawerFooter,
} from '@u-chirp/shadcn'
import { useMediaQuery } from '@vueuse/core'
import { ref } from 'vue'
import type {UDialogInstance, UDialogProps} from './type'

defineProps<UDialogProps>()

const isDesktop = useMediaQuery('(min-width: 768px)')

const show = ref(false);


function handlePointerDownOutside(e: MouseEvent) {
  console.log(e.target)
  if (e.target instanceof HTMLElement) {
    if (e.target.hasAttribute('vaul-drawer-visible') || e.target.hasAttribute('data-state')) {
      handleClose()
    }
  }
}
function handleClose() {
  show.value = false
}
function handleTrigger() {
  show.value = true
}

defineExpose<UDialogInstance>({
  close: handleClose,
  show: handleTrigger,
})
</script>

<template>
  <Dialog v-if="isDesktop" :open="show">
    <DialogTrigger v-if="!hideTrigger" @click="handleTrigger">
      <slot name="trigger"></slot>
    </DialogTrigger>
    <DialogContent
      class="!max-w-2xl"
      @pointerDownOutside="handlePointerDownOutside"
      @escapeKeyDown="() => handleClose()"
      @close="() => handleClose()"
    >
      <DialogHeader>
        <DialogTitle>
          <slot name="title"></slot>
        </DialogTitle>
        <DialogDescription>
          <slot name="description"></slot>
        </DialogDescription>
      </DialogHeader>
      <div>
        <slot name="default"></slot>
      </div>
      <DialogFooter>
        <slot name="footer"></slot>
      </DialogFooter>
    </DialogContent>
  </Dialog>
  <Drawer v-else :open="show">
    <DrawerTrigger v-if="!hideTrigger"
                   as-child
                   @click="handleTrigger">
      <slot name="trigger"></slot>
    </DrawerTrigger>
    <DrawerContent
      class="min-h-[52%] p-2"
      @pointerDownOutside="handlePointerDownOutside"
      @escapeKeyDown="() => handleClose()"
    >
      <DrawerHeader class="text-left">
        <DrawerTitle>
          <slot name="title"></slot>
        </DrawerTitle>
        <DrawerDescription>
          <slot name="description"></slot>
        </DrawerDescription>
      </DrawerHeader>
      <div>
        <slot name="default"></slot>
      </div>
      <DrawerFooter>
        <slot name="footer"></slot>
      </DrawerFooter>
    </DrawerContent>
  </Drawer>
</template>

<style scoped></style>
