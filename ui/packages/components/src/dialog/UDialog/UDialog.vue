<script setup lang="ts">
import {
  Dialog, DialogContent, DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger, DialogFooter,
  Drawer, DrawerContent, DrawerDescription,
  DrawerHeader,
  DrawerTitle, DrawerTrigger, DrawerFooter
} from '@u-chirp/shadcn'
import { useMediaQuery } from '@vueuse/core'

const isDesktop = useMediaQuery('(min-width: 768px)')
</script>

<template>
  <Dialog v-if="isDesktop">
    <DialogHeader>
      <DialogTrigger>
        <slot name="trigger"></slot>
      </DialogTrigger>
    </DialogHeader>
    <DialogContent class="!max-w-2xl">
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
  <Drawer v-else>
    <DrawerTrigger as-child>
      <slot name="trigger"></slot>
    </DrawerTrigger>
    <DrawerContent class="min-h-[60%] p-2">
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

<style scoped>

</style>
