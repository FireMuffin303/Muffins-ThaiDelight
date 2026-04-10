package net.firemuffin303.thaidelight.client.renderer.customEffectRender;


import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;

public class DurianHeatEffectRenderer implements CustomEffectRenderer {
    private static final ResourceLocation DURIAN_ICON = ThaiDelightCommon.modid("textures/gui/special_effect/durian_consumed.png");
    private static final ResourceLocation HEATED_UP_ICON = ThaiDelightCommon.modid("textures/gui/special_effect/heated_up.png");


    @Override
    public boolean shouldRender(LocalPlayer localPlayer) {
        return PlatformUtil.getDurianHeatComponent(localPlayer).timer() > 0;
    }

    @Override
    public Component getName(LocalPlayer localPlayer) {
        if(PlatformUtil.getDurianHeatComponent(localPlayer).isHeatUp()){
            return Component.translatable("muffins_thaidelight.custom_effect_render.heated_up");
        }
        return Component.translatable("muffins_thaidelight.custom_effect_render.durian_consumed");
    }

    @Override
    public Component getDetail(LocalPlayer localPlayer) {
        return Component.literal(StringUtil.formatTickDuration(PlatformUtil.getDurianHeatComponent(localPlayer).timer()));
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
        if(PlatformUtil.getDurianHeatComponent(localPlayer).isHeatUp()){
            return HEATED_UP_ICON;
        }
        return DURIAN_ICON;
    }

    @Override
    public int color(LocalPlayer player) {
        return 0xfcea67;
    }
}
