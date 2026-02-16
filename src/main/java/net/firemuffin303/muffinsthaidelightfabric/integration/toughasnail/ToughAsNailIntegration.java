package net.firemuffin303.muffinsthaidelightfabric.integration.toughasnail;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.temperature.IPlayerTemperatureModifier;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;

public class ToughAsNailIntegration {

    public static void toughAsNailIntegration(){
        if(ThaiDelight.IS_TOUGH_AS_NAIL_INSTALLED){
            TemperatureHelper.registerPlayerTemperatureModifier(new IPlayerTemperatureModifier() {
                @Override
                public TemperatureLevel modify(Player player, TemperatureLevel temperatureLevel) {
                    DurianHeatAttachment durianHeatAttachment = player.getAttached(ModAttachments.DURIAN_HEAT);
                    if(durianHeatAttachment != null && durianHeatAttachment.isHeatedUp){
                        temperatureLevel = temperatureLevel.increment(1);
                    }


                    return temperatureLevel;
                }
            });
        }
    }
}
