package net.firemuffin303.thaidelight.mixin.fabric;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow @Final private Map<Holder<MobEffect>, MobEffectInstance> activeEffects;

    @Inject(method = "completeUsingItem",at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;finishUsingItem(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;"))
    public void muffins$afterItemDone(CallbackInfo ci, @Local ItemStack itemStack){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if(itemStack.is(ModTags.FERMENTED_DRINKS)){
            ModUtils.onDrinkFermentedDrinks(livingEntity.level(), itemStack,livingEntity);
        }
    }
}
