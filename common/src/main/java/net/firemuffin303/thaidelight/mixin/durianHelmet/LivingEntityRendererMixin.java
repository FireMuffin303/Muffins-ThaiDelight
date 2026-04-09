package net.firemuffin303.thaidelight.mixin.durianHelmet;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

    @ModifyReturnValue(method = "shouldShowName(Lnet/minecraft/world/entity/LivingEntity;)Z",at = @At(value = "RETURN",ordinal = 6))
    public <T extends LivingEntity> boolean muffins$checkIfWearDurianHelmet(boolean original, @Local(argsOnly = true) T livingEntity){
        return original && !livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.DURIAN_HELMET.get());
    }
}
