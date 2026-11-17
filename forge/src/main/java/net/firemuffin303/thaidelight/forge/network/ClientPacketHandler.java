package net.firemuffin303.thaidelight.forge.network;

import net.firemuffin303.thaidelight.common.registry.ModSoundEvents;
import net.firemuffin303.thaidelight.forge.common.capabilities.DurianHeatProvider;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientPacketHandler {

    public static void handlerLevelEvent(int id, BlockPos blockPos){
        if(id == 1){
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            level.playLocalSound(blockPos, ModSoundEvents.SACK_CATCHING_DURIAN.get(), SoundSource.BLOCKS, 1.0f, level.random.nextFloat() * 0.1f + 0.9f, false);
            level.playSound(null, blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
        }
    }

    public static void handleDurianHeat(int timer,boolean isHeatUp){
        Player localPlayer = Minecraft.getInstance().player;
        localPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(spicy -> {
            spicy.setTimer(timer);
            spicy.setHeat(isHeatUp);
        });
    }

    public static void handleSpicy(int timer){
        Player localPlayer = Minecraft.getInstance().player;
        localPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
            spicy.setTimer(timer,localPlayer);
        });
    }
}
