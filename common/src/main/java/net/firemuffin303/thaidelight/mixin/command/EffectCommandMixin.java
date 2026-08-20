package net.firemuffin303.thaidelight.mixin.command;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.server.commands.EffectCommands;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EffectCommands.class)
public abstract class EffectCommandMixin {

    @WrapOperation(method = "clearEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"))
    private static boolean muffins$clearEffect(LivingEntity instance, Operation<Boolean> original, @Local LocalIntRef i){
        boolean flag = false;

        if(PlatformUtil.getSpicyTime(instance) > 0){
            PlatformUtil.setSpicyTime(0,instance);
            PlatformUtil.updateSpicy(instance);
            flag = true;
        }

        if(PlatformUtil.getDurianHeatComponent(instance).timer() > 0){
            PlatformUtil.setDurianHeatTime(0,instance);
            PlatformUtil.updateDurianHeat(instance);
            flag = true;
        }

        if(flag){
            i.set(i.get() + 1);
        }

        return original.call(instance);
    }
}
