package com.example.examplemod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class FuyuMomoiRenderer extends HaskyaHumanoidRenderer<FuyuMomoiEntity> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "textures/entity/fuyu_momoi.png");

    public FuyuMomoiRenderer(EntityRendererProvider.Context context) {
        super(context, TEXTURE);
    }
}
