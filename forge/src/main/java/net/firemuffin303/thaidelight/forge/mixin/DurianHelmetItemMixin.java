package net.firemuffin303.thaidelight.forge.mixin;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.model.armor.DurianHelmetModel;
import net.firemuffin303.thaidelight.common.item.equipments.DurianHelmetItem;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(DurianHelmetItem.class)
public abstract class DurianHelmetItemMixin extends Item {
    public DurianHelmetItemMixin(Properties arg) {
        super(arg);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return ThaiDelightCommon.modid("textures/models/armor/durian.png").toString();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack stack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(stack.is(ModItems.DURIAN_HELMET.get()) && equipmentSlot == EquipmentSlot.HEAD){
                    ModelPart modelPart = Minecraft.getInstance().getEntityModels().bakeLayer(DurianHelmetModel.DURIAN_HELMET);
                    DurianHelmetModel<LivingEntity> model  = new DurianHelmetModel<>(modelPart);
                    model.setAllVisible(false);
                    model.head.visible = true;
                    return model;
                }

                return original;
            }
        });
    }


}
