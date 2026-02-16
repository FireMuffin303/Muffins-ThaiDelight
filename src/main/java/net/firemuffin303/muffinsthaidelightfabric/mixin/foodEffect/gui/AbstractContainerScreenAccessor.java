package net.firemuffin303.muffinsthaidelightfabric.mixin.foodEffect.gui;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {

    @Accessor("topPos")
    int getTopPos();

    @Accessor("imageWidth")
    int getImageWidth();

    @Accessor("leftPos")
    int getLeftPos();
}
