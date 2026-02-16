package net.firemuffin303.muffinsthaidelightfabric.network.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpicyAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;

public record SpicyPacket(int timer) implements FabricPacket {
    public static final PacketType<SpicyPacket> TYPE = PacketType.create(ThaiDelight.modid("spicy_sync"), SpicyPacket::new);

    public SpicyPacket(FriendlyByteBuf friendlyByteBuf){
        this(friendlyByteBuf.readInt());
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(this.timer);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void recieve(SpicyPacket packet, LocalPlayer player, PacketSender respondSender){
        player.setAttached(ModAttachments.SPICY,new SpicyAttachment(packet.timer));
    }
}
