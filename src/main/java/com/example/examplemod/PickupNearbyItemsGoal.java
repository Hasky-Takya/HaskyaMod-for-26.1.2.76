package com.example.examplemod;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class PickupNearbyItemsGoal extends Goal {
    private static final double SEARCH_RANGE = 18.0D;
    private static final double PICKUP_DISTANCE_SQR = 2.25D;

    private final PathfinderMob mob;
    private final SimpleContainer inventory;
    private final Predicate<ItemStack> targetItemPredicate;
    private final double speedModifier;
    private ItemEntity targetItem;

    public PickupNearbyItemsGoal(PathfinderMob mob, SimpleContainer inventory, Predicate<ItemStack> targetItemPredicate, double speedModifier) {
        this.mob = mob;
        this.inventory = inventory;
        this.targetItemPredicate = targetItemPredicate;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.mob.level().isClientSide() || this.inventory == null) {
            return false;
        }

        List<ItemEntity> items = this.mob.level().getEntitiesOfClass(
                ItemEntity.class,
                this.mob.getBoundingBox().inflate(SEARCH_RANGE),
                item -> item.isAlive()
                        && !item.hasPickUpDelay()
                        && this.targetItemPredicate.test(item.getItem())
                        && this.inventory.canAddItem(item.getItem())
        );

        this.targetItem = items.stream()
                .min(Comparator.comparingDouble(this.mob::distanceToSqr))
                .orElse(null);

        return this.targetItem != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetItem != null
                && this.targetItem.isAlive()
                && this.targetItemPredicate.test(this.targetItem.getItem())
                && this.inventory.canAddItem(this.targetItem.getItem())
                && this.mob.distanceToSqr(this.targetItem) <= SEARCH_RANGE * SEARCH_RANGE;
    }

    @Override
    public void stop() {
        this.targetItem = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.targetItem == null) {
            return;
        }

        this.mob.getNavigation().moveTo(this.targetItem, this.speedModifier);
        if (this.mob.distanceToSqr(this.targetItem) > PICKUP_DISTANCE_SQR) {
            return;
        }

        ItemStack leftover = this.inventory.addItem(this.targetItem.getItem().copy());
        if (leftover.isEmpty()) {
            this.targetItem.discard();
        } else {
            this.targetItem.setItem(leftover);
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
