package net.firemuffin303.thaidelight.neoforge.common.attachment;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ThaiDelightCommon.MOD_ID);

    public static final Supplier<AttachmentType<SpicyAttachment>> SPICY = ATTACHMENT_TYPES.register("spicy",() -> AttachmentType.builder(SpicyAttachment::new).build());
    public static final Supplier<AttachmentType<DurianHeatAttachment>> DURIAN_HEAT = ATTACHMENT_TYPES.register("durian_heat",() -> AttachmentType.builder(DurianHeatAttachment::new).build());
}
