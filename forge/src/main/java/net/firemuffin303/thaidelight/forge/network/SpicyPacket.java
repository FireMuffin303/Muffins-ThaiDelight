package net.firemuffin303.thaidelight.forge.network;

import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpicyPacket  {
    int timer;


    public SpicyPacket(FriendlyByteBuf friendlyByteBuf){
        this.timer = friendlyByteBuf.readInt();
    }

    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeInt(this.timer);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT,() -> () -> {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                localPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> spicy.setTimer(this.timer));
                return null;
            });
        });
    }
}
