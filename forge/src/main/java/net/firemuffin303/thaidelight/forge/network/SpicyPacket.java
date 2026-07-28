package net.firemuffin303.thaidelight.forge.network;

import io.netty.buffer.ByteBuf;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.function.Supplier;

public record SpicyPacket(int timer) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SpicyPacket> TYPE = new CustomPacketPayload.Type<>(ThaiDelightCommon.modid("spicy"));
    public static final StreamCodec<ByteBuf,SpicyPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT,SpicyPacket::timer,SpicyPacket::new);


    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeInt(this.timer);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT,() -> () -> {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                localPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                    spicy.setTimer(this.timer,localPlayer);
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
