package net.firemuffin303.thaidelight.client.renderer.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.model.armor.DurianHelmetModel;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class DurianHelmetRenderer implements ArmorRenderer {
    private static final ResourceLocation TEXTURES = ThaiDelightCommon.modid("textures/models/armor/durian.png");

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        if(stack.is(ModItems.DURIAN_HELMET.get()) && slot == EquipmentSlot.HEAD){
            ModelPart modelPart = Minecraft.getInstance().getEntityModels().bakeLayer(DurianHelmetModel.DURIAN_HELMET);
            DurianHelmetModel<LivingEntity> model  = new DurianHelmetModel<>(modelPart);
            model.setAllVisible(false);
            model.head.visible = true;
            contextModel.copyPropertiesTo(model);
            ArmorRenderer.renderPart(matrices,vertexConsumers,light,stack,model,TEXTURES);
        }

    }
}
