package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpecialEffectAttachment;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpicyAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class StatusEffectRenderer {
    public static final ApiProviderMap<AttachmentType<? extends SpecialEffectAttachment>,SpecialEffectRenderer<? extends SpecialEffectAttachment>> SPECIAL_EFFECTS = ApiProviderMap.create();
    public static final List<AttachmentType<? extends SpecialEffectAttachment>> ATTACHMENTS = new ArrayList<>();

    public static final ResourceLocation MOD_STATUS_GUI = ThaiDelight.modid("textures/gui/special_effect/special_effect_toast.png");
    public static final ResourceLocation DURIAN_CONSUMED_ICON = ThaiDelight.modid("textures/gui/special_effect/durian_consumed.png");
    public static final ResourceLocation HEATED_UP_ICON = ThaiDelight.modid("textures/gui/special_effect/heated_up.png");
    public static final ResourceLocation SPICY_ICON = ThaiDelight.modid("textures/gui/special_effect/spicy.png");

    public static <T extends SpecialEffectAttachment> void register(AttachmentType<T> attachmentType,SpecialEffectRenderer<T> specialEffectRenderer){
        SpecialEffectRenderer<SpecialEffectAttachment> specialEffectAttachment = (SpecialEffectRenderer<SpecialEffectAttachment>) SPECIAL_EFFECTS.get(attachmentType);
        if(specialEffectAttachment != null){
            return;
        }

        SPECIAL_EFFECTS.putIfAbsent(attachmentType, specialEffectRenderer);
        ATTACHMENTS.add(attachmentType);
    }

    public static void init(){
        register(ModAttachments.DURIAN_HEAT, new SpecialEffectRenderer<DurianHeatAttachment>() {
            @Override
            public void render(GuiGraphics guiGraphics, int x, int y, SpecialEffectAttachment specialEffectAttachment, boolean bl, int textureHeight) {
                if(specialEffectAttachment.getTimer() <= 0){
                    return;
                }

                Font font = Minecraft.getInstance().font;
                boolean heatedUp = ((DurianHeatAttachment) specialEffectAttachment).isHeatedUp;
                if(bl){
                    guiGraphics.blit(MOD_STATUS_GUI,x,y,0,0,120,32);

                    Component name = heatedUp ? Component.literal("Heated Up") : Component.literal("Durian Consumed");

                    guiGraphics.drawString(font, name,x + 10 + 18, y + 6, 16777215);
                    guiGraphics.drawString(font, Component.literal(StringUtil.formatTickDuration(specialEffectAttachment.getTimer())), x + 10 + 18, y + 6 + 10, 8355711);
                }else {
                    guiGraphics.blit(MOD_STATUS_GUI,x,y,0,32,32,32);
                }

                guiGraphics.blit(heatedUp ? HEATED_UP_ICON : DURIAN_CONSUMED_ICON,x + (bl ? 6 : 7), y + 7, 0,0,18, 18,18,18);

            }
        });

        register(ModAttachments.SPICY, new SpecialEffectRenderer<SpicyAttachment>() {
            @Override
            public void render(GuiGraphics guiGraphics, int x, int y, SpecialEffectAttachment specialAttachment, boolean bl, int textureHeight) {
                if(specialAttachment.getTimer() <= 0){
                    return;
                }

                Font font = Minecraft.getInstance().font;

                if(bl){
                    guiGraphics.blit(MOD_STATUS_GUI,x,y,0,0,120,32);

                    Component name = Component.literal("Spicy");

                    guiGraphics.drawString(font, name,x + 10 + 18, y + 6, 16777215);
                    guiGraphics.drawString(font, Component.literal(StringUtil.formatTickDuration(specialAttachment.getTimer())), x + 10 + 18, y + 6 + 10, 8355711);
                }else {
                    guiGraphics.blit(MOD_STATUS_GUI,x,y,0,32,32,32);
                }

                guiGraphics.blit(SPICY_ICON,x + (bl ? 6 : 7), y + 7, 0,0,18, 18,18,18);
            }
        });
    }

    public static void renderDurianHeat(GuiGraphics guiGraphics, int i, int j, DurianHeatAttachment durianHeatAttachment, boolean bl,int topPos){
        Font font = Minecraft.getInstance().font;
        boolean heatedUp = durianHeatAttachment.isHeatedUp;
        if(bl){
            guiGraphics.blit(MOD_STATUS_GUI,i,topPos,0,0,120,32);

            Component name = heatedUp ? Component.literal("Heated Up") : Component.literal("Durian Consumed");

            guiGraphics.drawString(font, name,i + 10 + 18, topPos + 6, 16777215);
            guiGraphics.drawString(font, Component.literal(StringUtil.formatTickDuration(durianHeatAttachment.timer)), i + 10 + 18, topPos + 6 + 10, 8355711);
        }else {
            guiGraphics.blit(MOD_STATUS_GUI,i,topPos,0,32,32,32);
        }

        guiGraphics.blit(heatedUp ? HEATED_UP_ICON : DURIAN_CONSUMED_ICON,i + (bl ? 6 : 7), topPos + 7, 0,0,18, 18,18,18);
    }

    public static void renderSpicy(GuiGraphics guiGraphics, int i, int j, SpicyAttachment spicyAttachment, boolean bl, int topPos){
        Font font = Minecraft.getInstance().font;

        if(bl){
            guiGraphics.blit(MOD_STATUS_GUI,i,topPos,0,0,120,32);

            Component name = Component.literal("Spicy");

            guiGraphics.drawString(font, name,i + 10 + 18, topPos + 6, 16777215);
            guiGraphics.drawString(font, Component.literal(StringUtil.formatTickDuration(spicyAttachment.timer)), i + 10 + 18, topPos + 6 + 10, 8355711);
        }else {
            guiGraphics.blit(MOD_STATUS_GUI,i,topPos,0,32,32,32);
        }
    }


    public interface SpecialEffectRenderer<M extends SpecialEffectAttachment>{
        public void render(GuiGraphics guiGraphics, int x, int y, SpecialEffectAttachment specialAttachment, boolean bl, int textureHeight);
    }
}
