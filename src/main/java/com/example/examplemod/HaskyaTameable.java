package com.example.examplemod;

import net.minecraft.world.entity.player.Player;

public interface HaskyaTameable {
    boolean isTamed();

    Player getOwner();
}
