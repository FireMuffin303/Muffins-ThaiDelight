package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpicyAttachment;

public class ModAttachments {
    public static final AttachmentType<DurianHeatAttachment> DURIAN_HEAT = AttachmentRegistry.createPersistent(ThaiDelight.modid("durian_heat"),DurianHeatAttachment.CODEC);
    public static final AttachmentType<SpicyAttachment> SPICY = AttachmentRegistry.createPersistent(ThaiDelight.modid("spicy"),SpicyAttachment.CODEC);

    public static void init(){}

}
