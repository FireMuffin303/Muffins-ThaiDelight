package net.firemuffin303.thaidelight.client.renderer.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.Direction;

public class ModCuttingBoardRenderer {

    public static void render(PoseStack matrixStackIn, Direction direction){
        matrixStackIn.translate(0.5D, 0.55D, 0.5D);
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
        matrixStackIn.scale(2F, 2F, 2F);
    }

    public static void renderDurian(PoseStack matrixStackIn, Direction direction){
        matrixStackIn.translate(0.5D, 0.45D, 0.5D);
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
        matrixStackIn.scale(1.5F, 1.5F, 1.5F);
    }
}
