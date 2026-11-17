package net.firemuffin303.thaidelight.forge.network;

import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.common.registry.ModSoundEvents;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ModLevelPacket {
    byte id;
    BlockPos blockPos;

    public ModLevelPacket(byte id,BlockPos blockPos){
        this.id = id;
        this.blockPos = blockPos;
    }

    public ModLevelPacket(FriendlyByteBuf friendlyByteBuf){
        this.id = friendlyByteBuf.readByte();
        this.blockPos = friendlyByteBuf.readBlockPos();
    }

    public void encode(FriendlyByteBuf friendlyByteBuf){
        friendlyByteBuf.writeByte(this.id);
        friendlyByteBuf.writeBlockPos(this.blockPos);
    }

    public static void handle(ModLevelPacket modLevelPacket,Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,() -> () -> ClientPacketHandler.handlerLevelEvent(modLevelPacket.id,modLevelPacket.blockPos));
        });
        context.setPacketHandled(true);
    }
}
