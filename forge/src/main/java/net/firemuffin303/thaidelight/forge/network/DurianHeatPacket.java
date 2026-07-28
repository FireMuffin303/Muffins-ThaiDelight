package net.firemuffin303.thaidelight.forge.network;

import io.netty.buffer.ByteBuf;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.forge.common.capabilities.DurianHeatProvider;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.function.Supplier;

public record DurianHeatPacket(int timer,boolean isHeatUp) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DurianHeatPacket> TYPE = new CustomPacketPayload.Type<>(ThaiDelightCommon.modid("durian_heat"));
    public static final StreamCodec<ByteBuf,DurianHeatPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT,DurianHeatPacket::timer,ByteBufCodecs.BOOL,DurianHeatPacket::isHeatUp,DurianHeatPacket::new);

    public DurianHeatPacket(int timer,boolean isHeatUp){
        this.timer = timer;
        this.isHeatUp = isHeatUp;
    }

    public DurianHeatPacket(FriendlyByteBuf friendlyByteBuf){
        this.timer = friendlyByteBuf.readInt();
        this.isHeatUp = friendlyByteBuf.readBoolean();
    }

    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeInt(this.timer);
        friendlyByteBuf.writeBoolean(this.isHeatUp);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT,() -> () -> {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                localPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(spicy -> {
                    spicy.setTimer(this.timer);
                    spicy.setHeat(this.isHeatUp);
                });
                return null;
            });
        });
        context.setPacketHandled(true);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
