package net.firemuffin303.thaidelight.neoforge.network;

import io.netty.buffer.ByteBuf;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.neoforge.common.attachment.DurianHeatAttachment;
import net.firemuffin303.thaidelight.neoforge.common.attachment.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record DurianHeatPacket(int timer,boolean isHeatUp) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DurianHeatPacket> TYPE = new CustomPacketPayload.Type<>(ThaiDelightCommon.modid("durian_heat"));
    public static final StreamCodec<ByteBuf,DurianHeatPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT,DurianHeatPacket::timer,ByteBufCodecs.BOOL,DurianHeatPacket::isHeatUp,DurianHeatPacket::new);

    public DurianHeatPacket(int timer,boolean isHeatUp){
        this.timer = timer;
        this.isHeatUp = isHeatUp;
    }

    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeInt(this.timer);
        friendlyByteBuf.writeBoolean(this.isHeatUp);
    }

    public static void handle(DurianHeatPacket durianHeatPacket){
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        DurianHeatAttachment durianHeatAttachment = localPlayer.getData(ModAttachments.DURIAN_HEAT);
        durianHeatAttachment.setTimer(durianHeatPacket.timer);
        durianHeatAttachment.setHeat(durianHeatPacket.isHeatUp);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
