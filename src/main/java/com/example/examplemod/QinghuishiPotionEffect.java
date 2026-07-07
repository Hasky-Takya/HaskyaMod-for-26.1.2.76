package com.example.examplemod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.Nullable;

public class QinghuishiPotionEffect extends InstantenousMobEffect {
    private static final int RESULT_EFFECT_DURATION_TICKS = 9600;

    public QinghuishiPotionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyInstantenousEffect(
            ServerLevel level,
            @Nullable Entity source,
            @Nullable Entity owner,
            LivingEntity entity,
            int amplifier,
            double scale
    ) {
        if (entity.getRandom().nextBoolean()) {
            entity.addEffect(new MobEffectInstance(HaskyaMod.ARONA_BLESSING_EFFECT, RESULT_EFFECT_DURATION_TICKS, amplifier), owner);
        } else {
            entity.addEffect(new MobEffectInstance(HaskyaMod.JIULAN_CURSE_EFFECT, RESULT_EFFECT_DURATION_TICKS, amplifier), owner);
        }
    }

}
