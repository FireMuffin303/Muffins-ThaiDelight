package net.firemuffin303.thaidelight;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.firemuffin303.thaidelight.network.ModLevelEventPacket;

public class ThaiDelightDedicatedServerFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        PayloadTypeRegistry.playS2C().register(ModLevelEventPacket.TYPE,ModLevelEventPacket.STREAM_CODEC);
    }
}
