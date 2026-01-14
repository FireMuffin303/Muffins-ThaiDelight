package net.firemuffin303.muffinsthaidelightfabric.client.packet;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public record ModLevelEventPacket(byte id, BlockPos blockPos) implements FabricPacket {
    public static final PacketType<ModLevelEventPacket> TYPE = PacketType.create(ThaiDelight.modid("mod_level_event"),ModLevelEventPacket::new);

    public ModLevelEventPacket(FriendlyByteBuf friendlyByteBuf){
        this(friendlyByteBuf.readByte(),friendlyByteBuf.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeByte(this.id);
        friendlyByteBuf.writeBlockPos(this.blockPos);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void receive(ModLevelEventPacket packet, LocalPlayer player, PacketSender responseSender) {
        switch (packet.id){
            case 1:{
                Level level = Minecraft.getInstance().level;
                if(level == null) break;
                level.playLocalSound(packet.blockPos, ModSoundEvents.SACK_CATCHING_DURIAN, SoundSource.BLOCKS,1.0f,level.random.nextFloat() * 0.1f + 0.9f,false);
                level.playSound(null,packet.blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
                break;
            }
        }
    }
}
