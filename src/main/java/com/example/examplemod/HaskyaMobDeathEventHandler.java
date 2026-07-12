package com.example.examplemod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import static com.example.examplemod.HaskyaMod.*;

@EventBusSubscriber(modid = HaskyaMod.MODID)
public final class HaskyaMobDeathEventHandler {
    private HaskyaMobDeathEventHandler() {
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity().level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (event.getEntity() instanceof FuyuMomoiEntity entity) {
            onFuyuMomoiDeath(entity, serverLevel, event);
            onAnyHaskyaStudentDeath(entity, serverLevel, event);
        } else if (event.getEntity() instanceof FuyuMidoriEntity entity) {
            onFuyuMidoriDeath(entity, serverLevel, event);
            onAnyHaskyaStudentDeath(entity, serverLevel, event);
        } else if (event.getEntity() instanceof MomoiEntity entity) {
            onMomoiDeath(entity, serverLevel, event);
            onAnyHaskyaStudentDeath(entity, serverLevel, event);
        } else if (event.getEntity() instanceof MidoriEntity entity) {
            onMidoriDeath(entity, serverLevel, event);
            onAnyHaskyaStudentDeath(entity, serverLevel, event);
        }
    }

    private static void onFuyuMomoiDeath(FuyuMomoiEntity entity, ServerLevel level, LivingDeathEvent event) {
        // Fuyu Momoi-specific death logic goes here.
        if (!entity.isTamed()) {
            return;
        }
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        SimpleContainer inventory = entity.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.removeItemNoUpdate(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT) && getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                ItemStack copiedStack = itemEntity.getItem().copy();
                ItemEntity copiedItemEntity = entity.spawnAtLocation(serverLevel, copiedStack, 0.6F);
                if (copiedItemEntity != null) {
                    Vec3 direction = entity.position().subtract(copiedItemEntity.getX(), copiedItemEntity.getY(), copiedItemEntity.getZ());
                    copiedItemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                return;
            }
            else {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
        }
    }

    private static void onFuyuMidoriDeath(FuyuMidoriEntity entity, ServerLevel level, LivingDeathEvent event) {
        // Fuyu Midori-specific death logic goes here.
        if (!entity.isTamed()) {
            return;
        }
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        SimpleContainer inventory = entity.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.removeItemNoUpdate(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT) && getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                ItemStack copiedStack = itemEntity.getItem().copy();
                ItemEntity copiedItemEntity = entity.spawnAtLocation(serverLevel, copiedStack, 0.6F);
                if (copiedItemEntity != null) {
                    Vec3 direction = entity.position().subtract(copiedItemEntity.getX(), copiedItemEntity.getY(), copiedItemEntity.getZ());
                    copiedItemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                return;
            }
            else {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
        }
    }

    private static void onMomoiDeath(MomoiEntity entity, ServerLevel level, LivingDeathEvent event) {
        // Momoi-specific death logic goes here.
        if (!entity.isTamed()) {
            return;
        }
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        SimpleContainer inventory = entity.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.removeItemNoUpdate(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT) && getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                ItemStack copiedStack = itemEntity.getItem().copy();
                ItemEntity copiedItemEntity = entity.spawnAtLocation(serverLevel, copiedStack, 0.6F);
                if (copiedItemEntity != null) {
                    Vec3 direction = entity.position().subtract(copiedItemEntity.getX(), copiedItemEntity.getY(), copiedItemEntity.getZ());
                    copiedItemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                return;
            }
            else {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
        }
    }

    private static void onMidoriDeath(MidoriEntity entity, ServerLevel level, LivingDeathEvent event) {
        // Midori-specific death logic goes here.
        if (!entity.isTamed()) {
            return;
        }
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        SimpleContainer inventory = entity.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.removeItemNoUpdate(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT) && getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(ARONA_BLESSING_EFFECT)) {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                ItemStack copiedStack = itemEntity.getItem().copy();
                ItemEntity copiedItemEntity = entity.spawnAtLocation(serverLevel, copiedStack, 0.6F);
                if (copiedItemEntity != null) {
                    Vec3 direction = entity.position().subtract(copiedItemEntity.getX(), copiedItemEntity.getY(), copiedItemEntity.getZ());
                    copiedItemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
            else if (getKiller(event).hasEffect(JIULAN_CURSE_EFFECT)) {
                return;
            }
            else {
                ItemEntity itemEntity = entity.spawnAtLocation(serverLevel, stack, 0.6F);
                if (itemEntity != null) {
                    Vec3 direction = entity.position().subtract(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
                    itemEntity.setDeltaMovement(direction.x, 0.25D, direction.z);
                }
            }
        }
    }

    private static void onAnyHaskyaStudentDeath(LivingEntity entity, ServerLevel level, LivingDeathEvent event) {
        Player killer = getKiller(event);
        if (killer == null) {
            return;
        }

        // Shared logic for all four custom mobs killed by a player goes here.
    }

    private static Player getKiller(LivingDeathEvent event) {
        return event.getSource().getEntity() instanceof Player player ? player : null;
    }
}
