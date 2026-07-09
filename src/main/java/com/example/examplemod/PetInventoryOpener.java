package com.example.examplemod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;

public final class PetInventoryOpener {
    private PetInventoryOpener() {
    }

    public static void open(PathfinderMob mob, Player player, SimpleContainer inventory) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, owner) -> new PetInventoryMenu(containerId, playerInventory, inventory, mob),
                    Component.translatable("container.haskyamod.pet_inventory", mob.getDisplayName())
            ));
        }
    }
}
