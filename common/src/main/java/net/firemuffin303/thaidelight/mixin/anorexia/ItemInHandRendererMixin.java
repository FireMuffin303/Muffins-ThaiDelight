package net.firemuffin303.thaidelight.mixin.anorexia;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    @ModifyExpressionValue(method = "applyEatTransform",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration(Lnet/minecraft/world/entity/LivingEntity;)I"))
    public int muffins$hasStinkyEffect(int original){
        if(this.minecraft.player.hasEffect(ModMobEffects.APPETITE_LOSS)){
            return ModUtils.calculateEatingWithAnorexiaEffect(this.minecraft.player,original);
        }

        return original;
    }
}
