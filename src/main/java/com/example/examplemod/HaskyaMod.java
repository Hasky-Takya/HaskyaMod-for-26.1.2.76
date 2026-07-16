package com.example.examplemod;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.component.Consumables;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.alchemy.Potion;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HaskyaMod.MODID)
public class HaskyaMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "haskyamod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "haskyamod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final Holder<MobEffect> ARONA_BLESSING_EFFECT = MOB_EFFECTS.register("arona_blessing", () -> new AronaBlessingEffect(
            //Can be either BENEFICIAL, NEUTRAL or HARMFUL. Used to determine the potion tooltip color of this effect.
            MobEffectCategory.BENEFICIAL,
            //The color of the effect particles in RGB format.
            0xffffff
    ));

    public static final Holder<MobEffect> JIULAN_CURSE_EFFECT = MOB_EFFECTS.register("jiulan_curse", () -> new JiulanCurseEffect(
            MobEffectCategory.HARMFUL,
            0x000000
    ));

    public static final Holder<MobEffect> QINGHUISHI_POTION_EFFECT = MOB_EFFECTS.register("qinghuishi_potion", () -> new QinghuishiPotionEffect(
            MobEffectCategory.NEUTRAL,
            0x66ccff
    ));

    //POTIONS is a DeferredRegister<Potion>
    public static final Holder<Potion> QINGHUISHI_POTION = POTIONS.register("qinghuishi_potion", registryName -> new Potion(
            // The suffix applied to the potion
            registryName.getPath(),
            // The effects used by the potion
            new MobEffectInstance(QINGHUISHI_POTION_EFFECT, 1)
    ));

    public static final DeferredItem<Item> QINGHUISHI = ITEMS.registerItem(
            "qinghuishi",
            QinghuishiItem::new,
            () -> new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(10).saturationModifier(10f).build())
    );
    public static final DeferredItem<Item> STRAWBERRY = ITEMS.registerSimpleItem(
            "strawberry",
            () -> new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(2f).build())
    );
    public static final DeferredItem<Item> BERRY_PIE = ITEMS.registerSimpleItem(
            "berry_pie",
            () -> new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(5f).build())
    );
    public static final DeferredItem<Item> ITIGOMILK = ITEMS.registerItem(
            "itigomilk",
            BucketDrinkItem::new,
            () -> new Item.Properties()
                    .stacksTo(1)
                    .food(
                            new FoodProperties.Builder().alwaysEdible().nutrition(5).saturationModifier(2f).build(),
                            Consumables.DEFAULT_DRINK
                    )
                    .usingConvertsTo(Items.BUCKET)
    );
    public static final DeferredItem<BlockItem> QINGHUISHI_ORE_ITEM = ITEMS.registerSimpleBlockItem(ModBlocks.QINGHUISHI_ORE, () -> new Item.Properties());
    public static final DeferredItem<SpawnEggItem> FUYU_MOMOI_SPAWN_EGG = ITEMS.registerItem(
            "fuyu_momoi_spawn_egg",
            SpawnEggItem::new,
            () -> new Item.Properties().spawnEgg(ModEntities.FUYU_MOMOI.get())
    );
    public static final DeferredItem<SpawnEggItem> FUYU_MIDORI_SPAWN_EGG = ITEMS.registerItem(
            "fuyu_midori_spawn_egg",
            SpawnEggItem::new,
            () -> new Item.Properties().spawnEgg(ModEntities.FUYU_MIDORI.get())
    );
    public static final DeferredItem<SpawnEggItem> MOMOI_SPAWN_EGG = ITEMS.registerItem(
            "momoi_spawn_egg",
            SpawnEggItem::new,
            () -> new Item.Properties().spawnEgg(ModEntities.MOMOI.get())
    );
    public static final DeferredItem<SpawnEggItem> MIDORI_SPAWN_EGG = ITEMS.registerItem(
            "midori_spawn_egg",
            SpawnEggItem::new,
            () -> new Item.Properties().spawnEgg(ModEntities.MIDORI.get())
    );
    public static final DeferredItem<SpawnEggItem> YUUKA_SPAWN_EGG = ITEMS.registerItem(
            "yuuka_spawn_egg",
            SpawnEggItem::new,
            () -> new Item.Properties().spawnEgg(ModEntities.YUUKA.get())
    );

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public HaskyaMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        POTIONS.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::registerSpawnPlacements);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addFoodCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void addFoodCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(QINGHUISHI.get());
            event.accept(STRAWBERRY.get());
            event.accept(ITIGOMILK.get());
            event.accept(BERRY_PIE.get());
        }
        else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(QINGHUISHI_ORE_ITEM.get());
        }
        else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(FUYU_MOMOI_SPAWN_EGG.get());
            event.accept(MOMOI_SPAWN_EGG.get());
            event.accept(MIDORI_SPAWN_EGG.get());
            event.accept(FUYU_MIDORI_SPAWN_EGG.get());
            event.accept(YUUKA_SPAWN_EGG.get());
        }
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FUYU_MOMOI.get(), FuyuMomoiEntity.createAttributes().build());
        event.put(ModEntities.MOMOI.get(), MomoiEntity.createAttributes().build());
        event.put(ModEntities.MIDORI.get(), MidoriEntity.createAttributes().build());
        event.put(ModEntities.FUYU_MIDORI.get(), FuyuMidoriEntity.createAttributes().build());
        event.put(ModEntities.YUUKA.get(), YuukaEntity.createAttributes().build());
    }

    private void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntities.FUYU_MOMOI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FuyuMomoiEntity::checkFuyuMomoiSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
        event.register(
                ModEntities.FUYU_MIDORI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FuyuMidoriEntity::checkFuyuMidoriSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
        event.register(
                ModEntities.MOMOI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MomoiEntity::checkMomoiSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
        event.register(
                ModEntities.MIDORI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MidoriEntity::checkMidoriSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
        event.register(
                ModEntities.YUUKA.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                YuukaEntity::checkYuukaSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    @SubscribeEvent
    public void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                QINGHUISHI.get(),
                QINGHUISHI_POTION
        );
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


}
