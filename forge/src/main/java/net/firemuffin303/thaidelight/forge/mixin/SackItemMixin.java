package net.firemuffin303.thaidelight.forge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.forge.client.ThaiDelightForgeClient;
import net.firemuffin303.thaidelight.forge.client.renderer.SackItemRenderer;
import net.firemuffin303.thaidelight.util.ModAnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(SackItem.class)
public abstract class SackItemMixin extends Item {
    public SackItemMixin(Properties arg) {
        super(arg);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new SackItemRenderer();
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if(player.isUsingItem() && itemInHand.is(ModItems.SACK.get())){
                    ModAnimationUtils.handleUsingCatchingBag(poseStack,arm,equipProcess);
                    return true;
                }
                return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
            }

            @Override
            public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if(SackItem.isFull(itemStack)){
                    return ThaiDelightForgeClient.SACK_SHOULDER_HOLD;
                }

                return ThaiDelightForgeClient.SACK_HOLD;
            }
        });
    }
}
