package com.example.examplemod;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

public abstract class HaskyaHumanoidRenderer<T extends Mob> extends HumanoidMobRenderer<T, HaskyaPlayerSkinRenderState, HaskyaPlayerSkinModel> {
    public static final ModelLayerLocation HASKYA_HUMANOID =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "haskya_humanoid"), "main");

    private final Identifier texture;
    private boolean loggedTexture;

    protected HaskyaHumanoidRenderer(EntityRendererProvider.Context context, Identifier texture) {
        super(context, new HaskyaPlayerSkinModel(context.bakeLayer(HASKYA_HUMANOID), false), 0.5F);
        this.texture = texture;
    }

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(HaskyaPlayerSkinModel.createMesh(CubeDeformation.NONE, false), 64, 64);
    }

    @Override
    public Identifier getTextureLocation(HaskyaPlayerSkinRenderState state) {
        if (!this.loggedTexture) {
            HaskyaMod.LOGGER.info("{} is using entity texture {}", this.getClass().getSimpleName(), this.texture);
            this.loggedTexture = true;
        }
        return texture;
    }

    @Override
    public HaskyaPlayerSkinRenderState createRenderState() {
        return new HaskyaPlayerSkinRenderState();
    }

    @Override
    public void extractRenderState(T entity, HaskyaPlayerSkinRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isSpectator = false;
        state.showHat = true;
        state.showJacket = true;
        state.showLeftPants = true;
        state.showRightPants = true;
        state.showLeftSleeve = true;
        state.showRightSleeve = true;
    }
}
