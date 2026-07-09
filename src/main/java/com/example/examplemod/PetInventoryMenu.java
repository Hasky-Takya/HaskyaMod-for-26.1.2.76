package com.example.examplemod;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class PetInventoryMenu extends ChestMenu {
    private static final double MAX_INTERACTION_DISTANCE_SQR = 64.0D;

    private final PathfinderMob mob;

    public PetInventoryMenu(int containerId, Inventory playerInventory, SimpleContainer petInventory, PathfinderMob mob) {
        this(containerId, playerInventory, petInventory, mob, rowsFor(petInventory));
    }

    private PetInventoryMenu(int containerId, Inventory playerInventory, SimpleContainer petInventory, PathfinderMob mob, int rows) {
        super(menuTypeFor(rows), containerId, playerInventory, new DisplayContainer(petInventory, rows * 9), rows);
        this.mob = mob;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.mob.isAlive()
                && this.mob.distanceToSqr(player) <= MAX_INTERACTION_DISTANCE_SQR
                && (!(this.mob instanceof HaskyaTameable tameable) || tameable.getOwner() == player);
    }

    private static int rowsFor(SimpleContainer inventory) {
        return Math.clamp((inventory.getContainerSize() + 8) / 9, 1, 6);
    }

    private static MenuType<ChestMenu> menuTypeFor(int rows) {
        return switch (rows) {
            case 1 -> MenuType.GENERIC_9x1;
            case 2 -> MenuType.GENERIC_9x2;
            case 3 -> MenuType.GENERIC_9x3;
            case 4 -> MenuType.GENERIC_9x4;
            case 5 -> MenuType.GENERIC_9x5;
            default -> MenuType.GENERIC_9x6;
        };
    }

    private static final class DisplayContainer implements Container {
        private final SimpleContainer inventory;
        private final int displaySize;

        private DisplayContainer(SimpleContainer inventory, int displaySize) {
            this.inventory = inventory;
            this.displaySize = displaySize;
        }

        @Override
        public int getContainerSize() {
            return this.displaySize;
        }

        @Override
        public boolean isEmpty() {
            return this.inventory.isEmpty();
        }

        @Override
        public ItemStack getItem(int slot) {
            return this.isRealSlot(slot) ? this.inventory.getItem(slot) : ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItem(int slot, int count) {
            return this.isRealSlot(slot) ? this.inventory.removeItem(slot, count) : ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            return this.isRealSlot(slot) ? this.inventory.removeItemNoUpdate(slot) : ItemStack.EMPTY;
        }

        @Override
        public void setItem(int slot, ItemStack itemStack) {
            if (this.isRealSlot(slot)) {
                this.inventory.setItem(slot, itemStack);
            }
        }

        @Override
        public void setChanged() {
            this.inventory.setChanged();
        }

        @Override
        public boolean stillValid(Player player) {
            return true;
        }

        @Override
        public boolean canPlaceItem(int slot, ItemStack itemStack) {
            return this.isRealSlot(slot);
        }

        @Override
        public void clearContent() {
            this.inventory.clearContent();
        }

        private boolean isRealSlot(int slot) {
            return slot >= 0 && slot < this.inventory.getContainerSize();
        }
    }
}
