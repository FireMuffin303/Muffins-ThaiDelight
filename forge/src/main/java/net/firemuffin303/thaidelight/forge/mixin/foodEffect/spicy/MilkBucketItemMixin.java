package net.firemuffin303.thaidelight.forge.mixin.foodEffect.spicy;

import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {

    @Inject(method = "finishUsingItem",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;curePotionEffects(Lnet/minecraft/world/item/ItemStack;)Z"))
    public void muffins$removeSpicy(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir){
        if(livingEntity instanceof Player player){
            PlatformUtil.setSpicyTime(0,player);

            if(!PlatformUtil.getDurianHeatComponent(livingEntity).isHeatUp()){
                PlatformUtil.setDurianHeatTime(0,livingEntity);
            }
        }
    }
}
