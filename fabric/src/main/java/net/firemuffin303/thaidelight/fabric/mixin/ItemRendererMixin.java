package net.firemuffin303.thaidelight.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.fabric.ThaiDelightModFabricClient;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.firemuffin303.thaidelight.fabric.ThaiDelightModFabricClient.*;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "render",at = @At("HEAD"),argsOnly = true)
    public BakedModel usePastleModel(BakedModel value, ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, BakedModel bakedModel){
        if(itemDisplayContext != ItemDisplayContext.GUI && itemDisplayContext != ItemDisplayContext.GROUND && itemDisplayContext !=ItemDisplayContext.FIXED){
            if(itemStack.is(ModItemsFabric.STONE_PASTLE)){
                return((ItemRendererAccessor)(Object)this).muffins$getModels().getModelManager().getModel(STONE_PASTLE_MODEL);
            }else if(itemStack.is(ModItemsFabric.IRON_PASTLE)){
                return((ItemRendererAccessor)(Object)this).muffins$getModels().getModelManager().getModel(IRON_PASTLE_MODEL);
            }else if(itemStack.is(ModItemsFabric.GOLD_PASTLE)){
                return((ItemRendererAccessor)(Object)this).muffins$getModels().getModelManager().getModel(GOLDEN_PASTLE_MODEL);
            }else if(itemStack.is(ModItemsFabric.DIAMOND_PASTLE)){
                return((ItemRendererAccessor)(Object)this).muffins$getModels().getModelManager().getModel(DIAMOND_PASTLE_MODEL);
            }else if(itemStack.is(ModItemsFabric.NETHERITE_PASTLE)){
                return((ItemRendererAccessor)(Object)this).muffins$getModels().getModelManager().getModel(NETHERITE_PASTLE_MODEL);
            }
        }



        return value;
    }


}
