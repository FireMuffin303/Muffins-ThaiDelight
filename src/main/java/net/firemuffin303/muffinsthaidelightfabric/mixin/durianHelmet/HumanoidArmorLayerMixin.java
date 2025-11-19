package net.firemuffin303.muffinsthaidelightfabric.mixin.durianHelmet;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.ClientModelRegistry;
import net.firemuffin303.muffinsthaidelightfabric.client.model.armor.DurianHelmetModel;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin <T extends LivingEntity, A extends HumanoidModel<T>> {

    @Shadow @Final private static Map<String, ResourceLocation> ARMOR_LOCATION_CACHE;

    @Inject(method = "renderArmorPiece",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;getParentModel()Lnet/minecraft/client/model/EntityModel;"))
    public void muffins$getArmorModel(PoseStack poseStack,
                                      MultiBufferSource multiBufferSource,
                                      T livingEntity,
                                      EquipmentSlot equipmentSlot,
                                      int i,
                                      A humanoidModel,
                                      CallbackInfo ci,
                                      @Local ItemStack itemStack,
                                      @Local(argsOnly = true) LocalRef<HumanoidModel<T>> armorModel
    ){
        if(itemStack.is(ModItems.DURIAN_HELMET)){
            ModelPart modelPart = Minecraft.getInstance().getEntityModels().bakeLayer(ClientModelRegistry.DURIAN_HELMET);
            armorModel.set(new DurianHelmetModel<>(modelPart));
        }

    }


    @ModifyReturnValue(method = "getArmorLocation",at = @At(value = "RETURN"))
    public ResourceLocation muffins$redirectTexture(ResourceLocation original, @Local(argsOnly = true)ArmorItem armorItem){
        if(armorItem == ModItems.DURIAN_HELMET){
            return ARMOR_LOCATION_CACHE.computeIfAbsent("textures/models/armor/durian.png", ThaiDelight::modid);
        }
        return original;
    }
}
