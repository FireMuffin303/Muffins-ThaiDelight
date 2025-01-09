package net.firemuffin303.muffinsthaidelightfabric.mixin.spicy;

import net.firemuffin303.muffinsthaidelightfabric.common.data.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements SpicyData.SpicyAccessor {

    @Unique
    public final SpicyData muffins_thaidelight$spicyData = new SpicyData(((LivingEntity)(Object)this));


    @Inject(method = "eat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEatEffect(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V"))
    public void muffins_thaidelight$eat(Level level, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir){
        if(itemStack.is(ModTags.SPICY_LEVEL_5)){
            this.muffins_thaidelight$spicyData.addSpicyLevel(5);
            this.muffins_thaidelight$spicyData.setSpicyCooldownLevel(15*20);
        }else if(itemStack.is(ModTags.SPICY_LEVEL_10)){
            this.muffins_thaidelight$spicyData.addSpicyLevel(10);
            this.muffins_thaidelight$spicyData.setSpicyCooldownLevel(15*20);
        } else if (itemStack.is(ModTags.SPICY_LEVEL_50)) {
            this.muffins_thaidelight$spicyData.addSpicyLevel(50);
            this.muffins_thaidelight$spicyData.setSpicyCooldownLevel(15*20);
        }
    }



    @Inject(method = "baseTick",at = @At("HEAD"))
    public void muffins_thaidelight$baseTick(CallbackInfo ci){
        muffins_thaidelight$spicyData.mobTick();
    }

    @Inject(method = "canFreeze",at = @At("HEAD"), cancellable = true)
    public void muffins_thaidelight$canFreeze(CallbackInfoReturnable<Boolean> cir){
        if(this.muffins_thaidelight$spicyData.getSpicyLevel() > 0){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "readAdditionalSaveData",at = @At("TAIL"))
    public void muffins_thaidelight$load(CompoundTag compoundTag, CallbackInfo ci){
        muffins_thaidelight$spicyData.load(compoundTag);
    }

    @Inject(method = "addAdditionalSaveData",at = @At("TAIL"))
    public void muffins_thaidelight$save(CompoundTag compoundTag, CallbackInfo ci){
        muffins_thaidelight$spicyData.save(compoundTag);
    }

    @ModifyVariable(method = "hurt", at = @At(value = "HEAD"),argsOnly = true)
    public float muffins_thaiDelight$getDamageAfterArmorAbsorb(float f, DamageSource damageSource){
        if(SpicyData.getSpicyData((LivingEntity) (Object)this).getSpicyLevel() > 0  && damageSource.is(DamageTypeTags.IS_FIRE)){
            float spicyMultiplier = 1.5f;
            return f * spicyMultiplier;
        }

        return f;
    }


    @Override
    public SpicyData muffinsThaiDelight$access() {
        return this.muffins_thaidelight$spicyData;
    }
}
