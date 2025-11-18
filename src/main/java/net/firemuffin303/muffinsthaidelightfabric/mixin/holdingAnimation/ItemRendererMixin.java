package net.firemuffin303.muffinsthaidelightfabric.mixin.holdingAnimation;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.muffinsthaidelightfabric.client.ThaiDelightClient;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow @Final private ItemModelShaper itemModelShaper;

    @ModifyExpressionValue(method = "getModel",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    public BakedModel muffins$getModel(BakedModel original,@Local(argsOnly = true) ItemStack itemStack){
        if(itemStack.is(ModItems.SACK)){
            original = this.itemModelShaper.getModelManager().getModel(ThaiDelightClient.SACK_MODEL_IN_HAND);
        }
        return original;
    }


    @Inject(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z",ordinal = 0))
    public void muffins$setInventoryModel(ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl,
                                          PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j,
                                          BakedModel bakedModel, CallbackInfo ci,@Local(argsOnly = true) LocalRef<BakedModel> bakedModel2
    ){
        if( itemStack.is(ModItems.SACK)){
            bakedModel2.set(this.itemModelShaper.getModelManager().getModel(ThaiDelightClient.SACK_MODEL));
        }
    }
}
