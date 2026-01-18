package net.firemuffin303.muffinsthaidelightfabric.mixin.anorexia;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    @ModifyExpressionValue(method = "applyEatTransform",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration()I"))
    public int muffins$hasStinkyEffect(int original){
        if(this.minecraft.player.hasEffect(ModMobEffects.ANOREXIA)){
            return CommonEvents.calculateEatingWithAnorexiaEffect(this.minecraft.player,original);
        }

        return original;
    }
}
