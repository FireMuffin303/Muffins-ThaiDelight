package net.firemuffin303.muffinsthaidelightfabric.mixin.integration.emi;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.firemuffin303.muffinsthaidelightfabric.client.ThaiDelightClient;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.StatusEffectRenderer;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpecialEffectAttachment;
import net.firemuffin303.muffinsthaidelightfabric.mixin.foodEffect.gui.AbstractContainerScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.stream.Collectors;

import static net.firemuffin303.muffinsthaidelightfabric.client.renderer.StatusEffectRenderer.ATTACHMENTS;

@Pseudo
@Mixin(value = EffectRenderingInventoryScreen.class,priority = 1500)
public class EmiEffectRenderingMixin {
    /*
    @Unique
    private int specialEffectAmount = 0;

    @TargetHandler(mixin = "dev.emi.emi.mixin.AbstractInventoryScreenMixin",name = "emi$drawCenteredEffects")
    @Inject(method = "@MixinSquared:Handler",at = @At(value = "INVOKE", target = "Ljava/util/Collection;size()I"))
    public void muffins$emiRenderSpecialEffect(GuiGraphics raw, int mouseX, int mouseY, CallbackInfo ci,
                                               @Local Collection<MobEffectInstance> effects){

        if(ThaiDelightClient.isEMIInstalled){
            AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>)(Object)this;
            int imageWidth = ((AbstractContainerScreenAccessor)screen).getImageWidth();
            int leftPos = ((AbstractContainerScreenAccessor)screen).getLeftPos();

            LocalPlayer player = Minecraft.getInstance().player;
            this.specialEffectAmount = ATTACHMENTS.stream().map(attachmentType ->  player.getAttached(attachmentType)).filter(at -> at.getTimer() > 0).collect(Collectors.toSet()).size();

            int size = this.specialEffectAmount + effects.size();
            boolean wide = size == 1;

            int xOff = 34;
            if (wide) {
                xOff = 122;
            } else if (size > 5) {
                xOff = (imageWidth - 32) / (size - 1);
            }

            int width = (size - 1) * xOff + (wide ? 120 : 32);
            int x = leftPos + (imageWidth - width) / 2;

            for(AttachmentType<? extends SpecialEffectAttachment> specialEffectAttachmentType: ATTACHMENTS){
                SpecialEffectAttachment specialEffectAttachment = player.getAttached(specialEffectAttachmentType);
                if(specialEffectAttachment.getTimer() > 0){
                    StatusEffectRenderer.SPECIAL_EFFECTS.get(specialEffectAttachmentType).render(raw,x,32,specialEffectAttachment,wide,0);
                    x += xOff;
                }
            }
        }

    }

     */
}
