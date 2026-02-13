package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;

public class ModAttachments {
    public static final AttachmentType<DurianHeatAttachment> DURIAN_HEAT = AttachmentRegistry.createPersistent(ThaiDelight.modid("durian_heat"),DurianHeatAttachment.CODEC);

    public static void init(){}

}
