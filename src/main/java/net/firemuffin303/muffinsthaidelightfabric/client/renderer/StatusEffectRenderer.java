package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap;
import net.firemuffin303.muffinseffectrenderapi.api.CustomEffectRegistry;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.customEffectRender.DurianHeatEffectRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.customEffectRender.SpicyEffectRenderer;
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

    public static void init(){
        CustomEffectRegistry.register(new DurianHeatEffectRenderer());
        CustomEffectRegistry.register(new SpicyEffectRenderer());
    }

}
