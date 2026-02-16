package net.firemuffin303.muffinsthaidelightfabric.network.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;

public record DurianHeatPacket(DurianHeatAttachment durianHeatAttachment) implements FabricPacket {
    public static final PacketType<DurianHeatPacket> TYPE = PacketType.create(ThaiDelight.modid("durian_heat_sync"),DurianHeatPacket::new);

    public DurianHeatPacket(FriendlyByteBuf friendlyByteBuf){
        this(new DurianHeatAttachment(friendlyByteBuf.readInt(),friendlyByteBuf.readBoolean()));
    }


    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(this.durianHeatAttachment().timer);
        friendlyByteBuf.writeBoolean(this.durianHeatAttachment().isHeatedUp);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void recieve(DurianHeatPacket packet, LocalPlayer player, PacketSender respondSender){
        player.setAttached(ModAttachments.DURIAN_HEAT,packet.durianHeatAttachment());
    }
}
