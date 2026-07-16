package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
//    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
//            DeferredRegister.create(Registries.ENTITY_TYPE, HaskyaMod.MODID);
      public static final DeferredRegister.Entities ENTITY_TYPES =
              DeferredRegister.createEntities(HaskyaMod.MODID);

    public static final Supplier<EntityType<FuyuMomoiEntity>> FUYU_MOMOI =
            ENTITY_TYPES.registerEntityType(
                    "fuyu_momoi",
                    FuyuMomoiEntity::new,
                    ModMobCategories.haskyaStudents(),
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
                    ModMobCategories.haskyaStudents(),
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
                    ModMobCategories.haskyaStudents(),
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
                    ModMobCategories.haskyaStudents(),
                    builder -> builder
                            .sized(0.6F, 1.8F)
                            .eyeHeight(1.62F)
                            .clientTrackingRange(8)
                            .updateInterval(3)
            );


    public static final Supplier<EntityType<YuukaEntity>> YUUKA =
            ENTITY_TYPES.registerEntityType(
                    "yuuka",
                    YuukaEntity::new,
                    ModMobCategories.haskyaStudents(),
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
