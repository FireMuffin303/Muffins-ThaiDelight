package net.firemuffin303.thaidelight.forge.mixin.foodEffect.spicy;

import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.item.MilkBottleItem;

@Mixin(MilkBottleItem.class)
public abstract class MilkBottleItemMixin {

    @Inject(method = "affectConsumer",at = @At("TAIL"))
    public void muffins$clearSpicy(ItemStack stack, Level level, LivingEntity consumer, CallbackInfo ci){
        if(consumer instanceof Player player){
            player.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy ->{
                spicy.setTimer(0,player);
            });

            if(!PlatformUtil.getDurianHeatComponent(consumer).isHeatUp()){
                PlatformUtil.setDurianHeatTime(0,consumer);
            }
        }
    }
}
