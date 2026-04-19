package net.firemuffin303.thaidelight.forge.network;

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

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeCallWhenOn(Dist.CLIENT,() -> () -> {
                if(this.id == 1){
                    Level level = Minecraft.getInstance().level;
                    if (level == null) return null;
                    level.playLocalSound(this.blockPos, ModSoundEvents.SACK_CATCHING_DURIAN.get(), SoundSource.BLOCKS, 1.0f, level.random.nextFloat() * 0.1f + 0.9f, false);
                    level.playSound(null, this.blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
                }


                return null;
            });
        });
        context.setPacketHandled(true);
    }
}
