package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HaskyaMod.MODID);

    public static final DeferredBlock<Block> QINGHUISHI_ORE = BLOCKS.registerSimpleBlock(
            "qinghuishi_ore",
            () -> BlockBehaviour.Properties.of()
                    .strength(3.0f, 3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    );

    public static final DeferredBlock<Block> QINGHUISHI_BLOCK = BLOCKS.register(
            "qinghuishi_block",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .destroyTime(2.0F)
                    .lightLevel(state -> 7)
                    .sound(SoundType.IRON))
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
