package com.example.examplemod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class FuyuMidoriRenderer extends HaskyaHumanoidRenderer<FuyuMidoriEntity> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "textures/entity/fuyu_midori.png");

    public FuyuMidoriRenderer(EntityRendererProvider.Context context) {
        super(context, TEXTURE);
    }
}
