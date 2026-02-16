package net.firemuffin303.muffinsthaidelightfabric.mixin.foodEffect.heatedup;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {

    @ModifyReturnValue(method = "canEntityWalkOnPowderSnow",at = @At(value = "RETURN",ordinal = 1))
    private static boolean muffins$shouldBeWalkOn(boolean original, @Local(argsOnly = true)Entity entity){
        boolean bl = false;
        if(entity instanceof Player player){
            DurianHeatAttachment durianHeatAttachment = player.getAttached(ModAttachments.DURIAN_HEAT);
            if(durianHeatAttachment != null){
                bl = durianHeatAttachment.isHeatedUp;
            }
        }

        return original || bl;
    }
}
