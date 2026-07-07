package com.example.examplemod;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.examplemod.HaskyaMod.ARONA_BLESSING_EFFECT;
import static com.example.examplemod.HaskyaMod.JIULAN_CURSE_EFFECT;

@EventBusSubscriber(modid = HaskyaMod.MODID)
public class LootEventHandler {
    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {
        if (event.getBreaker() instanceof Player player && hasAronaBlessing(player) && hasJiulanCurse(player)) {
            normalDrops(event.getDrops());
        }
        else if (event.getBreaker() instanceof Player player && hasAronaBlessing(player)) {
            doubleDrops(event.getDrops());
        }
        else if (event.getBreaker() instanceof Player player && hasJiulanCurse(player)) {
            noDrops(event.getDrops());
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player && hasAronaBlessing(player) && hasJiulanCurse(player)) {
            normalDrops(event.getDrops());
        }
        else if (event.getSource().getEntity() instanceof Player player && hasAronaBlessing(player)) {
            doubleDrops(event.getDrops());
        }
        else if (event.getSource().getEntity() instanceof Player player && hasJiulanCurse(player)) {
            noDrops(event.getDrops());
        }
    }

    private static boolean hasAronaBlessing(Player player) {
        return player.hasEffect(ARONA_BLESSING_EFFECT);
    }

    private static boolean hasJiulanCurse(Player player) {
        return player.hasEffect(JIULAN_CURSE_EFFECT);
    }

    private static void doubleDrops(Collection<ItemEntity> drops) {
        List<ItemEntity> extraDrops = new ArrayList<>();

        for (ItemEntity drop : drops) {
            if (!drop.getItem().isEmpty()) {
                extraDrops.add(new ItemEntity(
                        drop.level(),
                        drop.getX(),
                        drop.getY(),
                        drop.getZ(),
                        drop.getItem().copy()
                ));
            }
        }

        drops.addAll(extraDrops);
    }

    private static void normalDrops(Collection<ItemEntity> drops) {
        for (ItemEntity drop : drops) {}
    }

    private static void noDrops(Collection<ItemEntity> drops) {
        drops.clear();
    }
}
