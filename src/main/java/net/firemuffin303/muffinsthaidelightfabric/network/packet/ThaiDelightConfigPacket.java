package net.firemuffin303.muffinsthaidelightfabric.network.packet;

import eu.midnightdust.lib.util.PlatformFunctions;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.integration.midnightLib.ThaiDelightConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;


public record ThaiDelightConfigPacket(
        int encode
        ) implements FabricPacket {
    public static final PacketType<ThaiDelightConfigPacket> TYPE = PacketType.create(ThaiDelight.modid("thai_delight_config_packet"),ThaiDelightConfigPacket::new);

    private static final Component DISCONNECT_TEXT = Component.literal("The server you are attempting to connect to has ")
            .append(Component.literal("Muffin's Thai's Delight").withStyle(ChatFormatting.GREEN))
            .append(" installed, but your configuration file does not match the server's.\n\n")
            .append(Component.literal("Please make sure your configuration file matches the server's.\n").withStyle(ChatFormatting.RED))
            .append(Component.literal("Your configuration file is located at ").withStyle(ChatFormatting.RED))
            .append(Component.literal(PlatformFunctions.getConfigDirectory().resolve(ThaiDelight.MOD_ID + ".json").toString()).withStyle(ChatFormatting.BLUE))
            .append(Component.literal(".\n\n").withStyle(ChatFormatting.RED))
            .append(Component.literal("This is not a bug, do not report it.").withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD))
            .append(Component.literal("Server/Client config sync is work in progress.").withStyle(ChatFormatting.GOLD,ChatFormatting.BOLD));

    public ThaiDelightConfigPacket(FriendlyByteBuf friendlyByteBuf){
        this(friendlyByteBuf.readInt());
    }


    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(this.encode);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public static void receive(ThaiDelightConfigPacket packet, LocalPlayer player, PacketSender responseSender){
        if(ThaiDelightConfig.encode() != packet.encode){
            player.connection.getConnection().disconnect(DISCONNECT_TEXT);
        }
    }

}
