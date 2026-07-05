package com.example.examplemod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MidoriRenderer extends HaskyaHumanoidRenderer<MidoriEntity> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "textures/entity/midori.png");

    public MidoriRenderer(EntityRendererProvider.Context context) {
        super(context, TEXTURE);
    }
}
