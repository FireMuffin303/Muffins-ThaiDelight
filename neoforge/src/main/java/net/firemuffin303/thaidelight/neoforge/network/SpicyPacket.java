package net.firemuffin303.thaidelight.neoforge.network;

import io.netty.buffer.ByteBuf;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.neoforge.common.attachment.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SpicyPacket(int timer) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SpicyPacket> TYPE = new CustomPacketPayload.Type<>(ThaiDelightCommon.modid("spicy"));
    public static final StreamCodec<ByteBuf,SpicyPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT,SpicyPacket::timer,SpicyPacket::new);


    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeInt(this.timer);
    }

    public static void handle(SpicyPacket spicyPacket){
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        localPlayer.setData(ModAttachments.SPICY, spicyPacket.timer);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
