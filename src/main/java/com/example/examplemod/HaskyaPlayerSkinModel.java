package com.example.examplemod;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.world.entity.HumanoidArm;

public class HaskyaPlayerSkinModel extends HumanoidModel<HaskyaPlayerSkinRenderState> {
    public final ModelPart leftSleeve;
    public final ModelPart rightSleeve;
    public final ModelPart leftPants;
    public final ModelPart rightPants;
    public final ModelPart jacket;
    private final boolean slim;

    public HaskyaPlayerSkinModel(ModelPart root, boolean slim) {
        super(root, RenderTypes::entityTranslucent);
        this.slim = slim;
        this.leftSleeve = this.leftArm.getChild("left_sleeve");
        this.rightSleeve = this.rightArm.getChild("right_sleeve");
        this.leftPants = this.leftLeg.getChild("left_pants");
        this.rightPants = this.rightLeg.getChild("right_pants");
        this.jacket = this.body.getChild("jacket");
    }

    public static MeshDefinition createMesh(CubeDeformation deformation, boolean slim) {
        return PlayerModel.createMesh(deformation, slim);
    }

    @Override
    public void setupAnim(HaskyaPlayerSkinRenderState state) {
        boolean showBody = !state.isSpectator;
        this.body.visible = showBody;
        this.rightArm.visible = showBody;
        this.leftArm.visible = showBody;
        this.rightLeg.visible = showBody;
        this.leftLeg.visible = showBody;
        this.hat.visible = state.showHat;
        this.jacket.visible = state.showJacket;
        this.leftPants.visible = state.showLeftPants;
        this.rightPants.visible = state.showRightPants;
        this.leftSleeve.visible = state.showLeftSleeve;
        this.rightSleeve.visible = state.showRightSleeve;
        super.setupAnim(state);
    }

    @Override
    public void translateToHand(HaskyaPlayerSkinRenderState state, HumanoidArm arm, PoseStack poseStack) {
        this.root().translateAndRotate(poseStack);
        ModelPart part = this.getArm(arm);
        if (this.slim) {
            float offset = 0.5F * (arm == HumanoidArm.RIGHT ? 1 : -1);
            part.x += offset;
            part.translateAndRotate(poseStack);
            part.x -= offset;
        } else {
            part.translateAndRotate(poseStack);
        }
    }
}
