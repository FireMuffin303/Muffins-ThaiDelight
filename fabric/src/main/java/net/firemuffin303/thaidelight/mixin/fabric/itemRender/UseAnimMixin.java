package net.firemuffin303.thaidelight.mixin.fabric.itemRender;

import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(UseAnim.class)
public enum UseAnimMixin {
    MUFFINS_THAIDELIGHT_SACK_CATCH;
}
