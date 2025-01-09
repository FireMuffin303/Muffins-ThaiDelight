package net.firemuffin303.muffinsthaidelightfabric.mixin.spicy;

import net.firemuffin303.muffinsthaidelightfabric.client.ModHudRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Inject(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;lerp(FFF)F",ordinal = 1))
    public void muffinsThaiDelight$renderSpicyOverlay(GuiGraphics guiGraphics, float f, CallbackInfo ci){
        ModHudRenderer.spicyOutline(guiGraphics,f);
    }
}
