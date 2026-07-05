package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
//    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
//            DeferredRegister.create(Registries.ENTITY_TYPE, HaskyaMod.MODID);
      public static final DeferredRegister.Entities ENTITY_TYPES =
              DeferredRegister.createEntities(HaskyaMod.MODID);

//    public static final DeferredHolder<EntityType<?>, EntityType<FuyuMomoiEntity>> FUYU_MOMOI =
//            ENTITY_TYPES.register("fuyu_momoi", () -> EntityType.Builder
//                    .of(FuyuMomoiEntity::new, MobCategory.CREATURE)
//                    .sized(0.6F, 1.8F)
//                    .eyeHeight(1.62F)
//                    .clientTrackingRange(8)
//                    .build(entityKey("fuyu_momoi")));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<FuyuMidoriEntity>> FUYU_MIDORI =
//            ENTITY_TYPES.register("fuyu_midori", () -> EntityType.Builder
//                    .of(FuyuMidoriEntity::new, MobCategory.CREATURE)
//                    .sized(0.6F, 1.8F)
//                    .eyeHeight(1.62F)
//                    .clientTrackingRange(8)
//                    .build(entityKey("fuyu_midori")));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<MomoiEntity>> MOMOI =
//            ENTITY_TYPES.register("momoi", () -> EntityType.Builder
//                    .of(MomoiEntity::new, MobCategory.CREATURE)
//                    .sized(0.6F, 1.8F)
//                    .eyeHeight(1.62F)
//                    .clientTrackingRange(8)
//                    .build(entityKey("momoi")));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<MidoriEntity>> MIDORI =
//            ENTITY_TYPES.register("midori", () -> EntityType.Builder
//                    .of(MidoriEntity::new, MobCategory.CREATURE)
//                    .sized(0.6F, 1.8F)
//                    .eyeHeight(1.62F)
//                    .clientTrackingRange(8)
//                    .build(entityKey("midori")));

    public static final Supplier<EntityType<FuyuMomoiEntity>> FUYU_MOMOI =
            ENTITY_TYPES.registerEntityType(
                    "fuyu_momoi",
                    FuyuMomoiEntity::new,
                    MobCategory.CREATURE,
                    builder -> builder
                            .sized(0.6F, 1.8F)
                            .eyeHeight(1.62F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
            );

    public static final Supplier<EntityType<FuyuMidoriEntity>> FUYU_MIDORI =
            ENTITY_TYPES.registerEntityType(
                    "fuyu_midori",
                    FuyuMidoriEntity::new,
                    MobCategory.CREATURE,
                    builder -> builder
                            .sized(0.6F, 1.8F)
                            .eyeHeight(1.62F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
            );

    public static final Supplier<EntityType<MomoiEntity>> MOMOI =
            ENTITY_TYPES.registerEntityType(
                    "momoi",
                    MomoiEntity::new,
                    MobCategory.CREATURE,
                    builder -> builder
                            .sized(0.6F, 1.8F)
                            .eyeHeight(1.62F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
            );

    public static final Supplier<EntityType<MidoriEntity>> MIDORI =
            ENTITY_TYPES.registerEntityType(
                    "midori",
                    MidoriEntity::new,
                    MobCategory.CREATURE,
                    builder -> builder
                            .sized(0.6F, 1.8F)
                            .eyeHeight(1.62F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    private static ResourceKey<EntityType<?>> entityKey(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HaskyaMod.MODID, name));
    }
}
