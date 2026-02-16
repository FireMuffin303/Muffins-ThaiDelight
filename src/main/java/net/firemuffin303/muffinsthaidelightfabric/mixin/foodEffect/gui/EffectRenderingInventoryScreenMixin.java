package net.firemuffin303.muffinsthaidelightfabric.mixin.foodEffect.gui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.runtime.IIngredientListOverlay;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.firemuffin303.muffinsthaidelightfabric.client.ThaiDelightClient;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.StatusEffectRenderer;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpecialEffectAttachment;
import net.firemuffin303.muffinsthaidelightfabric.integration.jei.ThaiDelightJEIIntegration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.stream.Collectors;

import static net.firemuffin303.muffinsthaidelightfabric.client.renderer.StatusEffectRenderer.ATTACHMENTS;

@Mixin(EffectRenderingInventoryScreen.class)
public abstract class EffectRenderingInventoryScreenMixin {

    @Unique private int specialEffectAmount = 0;
    @Unique private int toastHeight = 33;

    @Inject(method = "renderEffects",at = @At(value = "INVOKE", target = "Ljava/util/Collection;isEmpty()Z"))
    public void muffins$renderDataAttachments(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci,
                                              @Local(ordinal = 2) int k, @Local(ordinal = 3) int l, @Local Collection<MobEffectInstance> collection){

        if(!ThaiDelightClient.isEMIInstalled){
            AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>)(Object)this;
            int topPos = ((AbstractContainerScreenAccessor)screen).getTopPos();

            boolean bl = l >= 120;

            LocalPlayer player = Minecraft.getInstance().player;
            this.specialEffectAmount = ATTACHMENTS.stream().map(attachmentType ->  player.getAttached(attachmentType)).filter(at -> at.getTimer() > 0).collect(Collectors.toSet()).size();
            if(this.specialEffectAmount + collection.size() > 5){
                this.toastHeight = 132 / ((collection.size() - 1) + (this.specialEffectAmount - 1) );
            }else{
                this.toastHeight = 33;
            }

            for(AttachmentType<? extends SpecialEffectAttachment> specialEffectAttachmentType: ATTACHMENTS){
                SpecialEffectAttachment specialEffectAttachment = player.getAttached(specialEffectAttachmentType);
                if(specialEffectAttachment.getTimer() > 0){
                    StatusEffectRenderer.SPECIAL_EFFECTS.get(specialEffectAttachmentType).render(guiGraphics,k,topPos,specialEffectAttachment,bl,this.toastHeight);
                    topPos += this.toastHeight;
                }
            }
        }
    }

    @ModifyExpressionValue(method = "renderBackgrounds",at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/EffectRenderingInventoryScreen;topPos:I"))
    public int muffins$modifyRenderBGPosition(int original){
        if(this.specialEffectAmount > 0){
            return original + (this.toastHeight * this.specialEffectAmount);

        }
        return original;
    }

    @ModifyExpressionValue(method = "renderIcons",at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/EffectRenderingInventoryScreen;topPos:I"))
    public int muffins$modifyRenderIconPosition(int original){
        if(this.specialEffectAmount > 0){
            return original + (this.toastHeight * this.specialEffectAmount);

        }
        return original;
    }

    @ModifyExpressionValue(method = "renderLabels",at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/EffectRenderingInventoryScreen;topPos:I"))
    public int muffins$modifyRenderLabelPosition(int original){
        if(this.specialEffectAmount > 0){
            return original + (this.toastHeight * this.specialEffectAmount);

        }
        return original;
    }

}
