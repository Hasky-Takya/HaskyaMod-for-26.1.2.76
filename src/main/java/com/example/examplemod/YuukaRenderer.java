package com.example.examplemod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class YuukaRenderer extends HaskyaHumanoidRenderer<YuukaEntity> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "textures/entity/yuuka.png");
    private boolean loggedTexture;

    public YuukaRenderer(EntityRendererProvider.Context context) {
        // Steve / classic / 4px arm
        super(context, TEXTURE);

        // 如果您的皮肤是 Alex / slim / 3px arm，改成：
        // super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER_SLIM), true), 0.5F);
    }

    @Override
    public HaskyaPlayerSkinRenderState createRenderState() {
        return new HaskyaPlayerSkinRenderState();
    }

    @Override
    public void extractRenderState(YuukaEntity entity, HaskyaPlayerSkinRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        // 打开玩家皮肤的第二层：帽子、外套、袖子、裤腿
        state.showHat = true;
        state.showJacket = true;
        state.showLeftSleeve = true;
        state.showRightSleeve = true;
        state.showLeftPants = true;
        state.showRightPants = true;
    }

    @Override
    public Identifier getTextureLocation(HaskyaPlayerSkinRenderState state) {
        if (!this.loggedTexture) {
            HaskyaMod.LOGGER.info("MomoiRenderer is using entity texture {}", TEXTURE);
            this.loggedTexture = true;
        }
        return TEXTURE;
    }
}
