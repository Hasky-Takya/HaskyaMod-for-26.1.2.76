package com.example.examplemod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class QinghuishiItem extends Item {
    private static final int EFFECT_DURATION_TICKS = 2400;
    private static final float ARONA_BLESSING_CHANCE = 0.10F;
    private static final float JIULAN_CURSE_CHANCE = 0.10F;

    public QinghuishiItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide()) {
            float roll = entity.getRandom().nextFloat();

            if (roll < ARONA_BLESSING_CHANCE) {
                entity.addEffect(new MobEffectInstance(HaskyaMod.ARONA_BLESSING_EFFECT, EFFECT_DURATION_TICKS, 0));
            } else if (roll < ARONA_BLESSING_CHANCE + JIULAN_CURSE_CHANCE) {
                entity.addEffect(new MobEffectInstance(HaskyaMod.JIULAN_CURSE_EFFECT, EFFECT_DURATION_TICKS, 0));
            }
        }

        return result;
    }
}
