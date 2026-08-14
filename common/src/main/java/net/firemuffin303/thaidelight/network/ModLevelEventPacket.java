package net.firemuffin303.thaidelight.network;

import io.netty.buffer.ByteBuf;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public record ModLevelEventPacket(byte id, BlockPos blockPos) implements CustomPacketPayload {
    public static final Type<ModLevelEventPacket> TYPE = new Type<>(ThaiDelightCommon.modid("mod_level_event"));
    public static final StreamCodec<ByteBuf,ModLevelEventPacket> STREAM_CODEC = new StreamCodec<ByteBuf, ModLevelEventPacket>() {
        @Override
        public ModLevelEventPacket decode(ByteBuf object) {
            return new ModLevelEventPacket(object.readByte(), FriendlyByteBuf.readBlockPos(object));
        }

        @Override
        public void encode(ByteBuf object, ModLevelEventPacket object2) {
            object.writeByte(object2.id());
            FriendlyByteBuf.writeBlockPos(object,object2.blockPos());
        }
    };

    public void receive() {
        if (this.id == 1) {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            level.playLocalSound(this.blockPos, ModSoundEvents.SACK_CATCHING_DURIAN.get(), SoundSource.BLOCKS, 0.6f, level.random.nextFloat() * 0.1f + 0.9f, false);
            level.playSound(null, this.blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
