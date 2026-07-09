package com.example.examplemod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static com.example.examplemod.HaskyaMod.QINGHUISHI;

public class DeliverInventoryToOwnerGoal extends Goal {
    private static final int CHECK_INTERVAL_TICKS = 100;

    private final PathfinderMob mob;
    private final HaskyaTameable tameable;
    private final SimpleContainer inventory;
    private final Predicate<ItemStack> targetItemPredicate;
    private ItemEntity targetItem;
    private int cooldown;

    public DeliverInventoryToOwnerGoal(PathfinderMob mob, HaskyaTameable tameable, SimpleContainer inventory, Predicate<ItemStack> targetItemPredicate) {
        this.mob = mob;
        this.tameable = tameable;
        this.inventory = inventory;
        this.targetItemPredicate = targetItemPredicate;
    }

    @Override
    public boolean canUse() {
        return !this.mob.level().isClientSide() && this.tameable.isTamed() && this.tameable.getOwner() != null;
    }

    @Override
    public void tick() {
        if (!(this.mob.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (this.cooldown > 0) {
            this.cooldown--;
            return;
        }

        this.cooldown = CHECK_INTERVAL_TICKS;
        Player owner = this.tameable.getOwner();
        if (owner == null) {
            return;
        }

        for (int slot = 0; slot < this.inventory.getContainerSize(); slot++) {
            ItemStack stack = this.inventory.removeItemNoUpdate(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (!stack.is(QINGHUISHI)) {
                this.inventory.addItem(stack);
                continue;
            }

            ItemEntity itemEntity = this.mob.spawnAtLocation(serverLevel, stack, 0.6F);
            if (itemEntity != null) {
                Vec3 direction = owner.position().subtract(this.mob.position()).normalize().scale(0.35D);
                itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                itemEntity.setPickUpDelay(10);
            }
            return;
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
