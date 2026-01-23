package net.firemuffin303.muffinsthaidelightfabric.network.packet.configPacket;

import net.minecraft.network.FriendlyByteBuf;

public abstract class AbstractCompatConfigPacket {

    abstract void write(FriendlyByteBuf friendlyByteBuf);

    abstract AbstractCompatConfigPacket read(FriendlyByteBuf friendlyByteBuf);
}
