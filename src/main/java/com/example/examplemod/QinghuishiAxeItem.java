package com.example.examplemod;

import net.minecraft.world.item.AxeItem;

/**
 * Qinghuishi axe implementation and extension point for axe-specific behavior.
 */
public class QinghuishiAxeItem extends AxeItem {
    private static final float ATTACK_DAMAGE_BASELINE = 10.0F;
    private static final float ATTACK_SPEED_BASELINE = -3.0F;

    public QinghuishiAxeItem(Properties properties) {
        super(
                ModToolMaterials.QINGHUISHI,
                ATTACK_DAMAGE_BASELINE,
                ATTACK_SPEED_BASELINE,
                properties
        );
    }

    // TODO: Add Qinghuishi axe-specific behavior below this line.
}
