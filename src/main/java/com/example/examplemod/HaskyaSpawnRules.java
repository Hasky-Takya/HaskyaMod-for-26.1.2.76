package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;

public final class HaskyaSpawnRules {
    private HaskyaSpawnRules() {
    }

    public static boolean checkAnimalLikeSpawnRules(
            EntityType<? extends Mob> entityType,
            LevelAccessor level,
            EntitySpawnReason spawnReason,
            BlockPos pos,
            RandomSource random
    ) {
        boolean brightEnough = EntitySpawnReason.ignoresLightRequirements(spawnReason) || level.getRawBrightness(pos, 0) > 8;
        return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && brightEnough && random.nextInt(200) == 0;
    }
}
