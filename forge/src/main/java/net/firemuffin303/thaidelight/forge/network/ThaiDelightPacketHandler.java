package net.firemuffin303.thaidelight.forge.network;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class ThaiDelightPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ThaiDelightCommon.modid("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );



    public static void registerSpicyPacket(){
        INSTANCE.registerMessage(1,
                SpicyPacket.class,
                SpicyPacket::encode,
                SpicyPacket::new,
                SpicyPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));

        INSTANCE.registerMessage(2,
                DurianHeatPacket.class,
                DurianHeatPacket::encode,
                DurianHeatPacket::new,
                DurianHeatPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
