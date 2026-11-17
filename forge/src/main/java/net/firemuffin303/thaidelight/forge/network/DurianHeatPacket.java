package net.firemuffin303.thaidelight.forge.network;

import net.firemuffin303.thaidelight.forge.common.capabilities.DurianHeatProvider;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DurianHeatPacket {
    int timer;
    boolean isHeatUp;

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

    public static void handle(DurianHeatPacket durianHeatPacket,Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,() -> () -> ClientPacketHandler.handleDurianHeat(durianHeatPacket.timer,durianHeatPacket.isHeatUp));
        });
        context.setPacketHandled(true);
    }
}
