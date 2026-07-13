package com.example.examplemod;

import net.minecraft.world.entity.MobCategory;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public final class ModMobCategories {
    public static final EnumProxy<MobCategory> HASKYA_STUDENTS = new EnumProxy<>(
            MobCategory.class,
            "haskyamod:students",
            1,
            true,
            false,
            128
    );

    private ModMobCategories() {
    }

    public static MobCategory haskyaStudents() {
        return HASKYA_STUDENTS.getValue();
    }
}
