package net.firemuffin303.thaidelight.client.renderer.customEffectRender;


import net.firemuffin303.muffinsmcapi.impl.customEffect.CustomEffectRenderer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffectUtil;

public class DurianHeatEffectRenderer implements CustomEffectRenderer {
    private static final ResourceLocation DURIAN_ICON = ThaiDelightCommon.modid("textures/gui/special_effect/durian_consumed.png");
    private static final ResourceLocation HEATED_UP_ICON = ThaiDelightCommon.modid("textures/gui/special_effect/heated_up.png");


    @Override
    public boolean shouldRender(LocalPlayer localPlayer) {
        DurianHeatAttachment durianHeatAttachment = localPlayer.getAttached(ModAttachments.DURIAN_HEAT);
        if(durianHeatAttachment != null){
            return durianHeatAttachment.timer > 0;
        }

        return false;
    }

    @Override
    public Component getName(LocalPlayer localPlayer) {
        DurianHeatAttachment durianHeatAttachment = localPlayer.getAttached(ModAttachments.DURIAN_HEAT);
        if(durianHeatAttachment != null){
            return durianHeatAttachment.isHeatedUp ?
                    Component.translatable("muffins_thaidelight.custom_effect_render.heated_up") :
                    Component.translatable("muffins_thaidelight.custom_effect_render.durian_consumed");
        }

        return Component.translatable("muffins_thaidelight.custom_effect_render.durian_consumed");
    }

    @Override
    public Component getDetail(LocalPlayer localPlayer) {
        DurianHeatAttachment durianHeatAttachment = localPlayer.getAttached(ModAttachments.DURIAN_HEAT);
        if(durianHeatAttachment != null){
            return Component.literal(StringUtil.formatTickDuration(durianHeatAttachment.timer));
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
        DurianHeatAttachment durianHeatAttachment = localPlayer.getAttached(ModAttachments.DURIAN_HEAT);
        if(durianHeatAttachment != null){
            if(durianHeatAttachment.isHeatedUp){
                return HEATED_UP_ICON;
            }
        }

        return DURIAN_ICON;
    }

    @Override
    public int color(LocalPlayer player) {
        return 0xfcea67;
    }
}
