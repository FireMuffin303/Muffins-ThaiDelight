package net.firemuffin303.thaidelight.client.renderer.customEffectRender;

import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;

public class SpicyEffectRenderer implements CustomEffectRenderer {
    private static final ResourceLocation ICON = ThaiDelightCommon.modid("textures/gui/special_effect/spicy.png");

    @Override
    public boolean shouldRender(LocalPlayer localPlayer) {
        SpicyAttachment spicyAttachment = localPlayer.getAttached(ModAttachments.SPICY);
        if(spicyAttachment != null){
            return spicyAttachment.timer > 0;
        }
        return false;
    }

    @Override
    public Component getName(LocalPlayer localPlayer) {
        return Component.translatable("muffins_thaidelight.custom_effect_render.spicy");
    }

    @Override
    public Component getDetail(LocalPlayer localPlayer) {
        SpicyAttachment spicyAttachment = localPlayer.getAttached(ModAttachments.SPICY);
        if(spicyAttachment != null){
            return Component.literal(StringUtil.formatTickDuration(spicyAttachment.timer));
        }

        return null;
    }

    @Override
    public ResourceLocation backgroundTextureWide(LocalPlayer localPlayer) {
        return null;
    }

    @Override
    public ResourceLocation backgroundTextureShort(LocalPlayer localPlayer) {
        return null;
    }

    @Override
    public ResourceLocation backgroundTextureHUD(LocalPlayer localPlayer) {
        return null;
    }

    @Override
    public ResourceLocation iconTexture(LocalPlayer localPlayer) {
        return ICON;
    }

    @Override
    public int color(LocalPlayer player) {
        return 0xfcea67;
    }
}
