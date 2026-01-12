package net.firemuffin303.muffinsthaidelightfabric.mixin.integration.farmersdelight;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.integration.ModCuttingBoardRenderer;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.client.renderer.CuttingBoardRenderer;

@Debug(export = true)
@Mixin(CuttingBoardRenderer.class)
public abstract class CuttingBoardRendererMixin {

    @WrapWithCondition(method = "render(Lvectorwing/farmersdelight/common/block/entity/CuttingBoardBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/client/renderer/CuttingBoardRenderer;renderBlock(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/core/Direction;)V"))

    public boolean muffins$render(CuttingBoardRenderer instance, PoseStack matrixStackIn, Direction direction, @Local ItemStack boardStack){
        if(boardStack.is(ModItems.COCONUT) || boardStack.is(ModItems.STRIPPED_COCONUT)){
            ModCuttingBoardRenderer.render(matrixStackIn, direction);
            return false;
        }
        return true;
    }
}
