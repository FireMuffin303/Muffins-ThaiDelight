package net.firemuffin303.thaidelight.integration.toughasnail;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.ThaiDelightFabric;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.temperature.IPlayerTemperatureModifier;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;

public class ToughAsNailIntegration {

    public static void toughAsNailIntegration(){
        if(ThaiDelightFabric.IS_TOUGH_AS_NAIL_INSTALLED){
            TemperatureHelper.registerPlayerTemperatureModifier(new IPlayerTemperatureModifier() {
                @Override
                public TemperatureLevel modify(Player player, TemperatureLevel temperatureLevel) {
                    DurianHeatComponent durianHeatAttachment = ModCardinalComponents.DURIAN_HEAT.get(player);
                    if(durianHeatAttachment.isHeatedUp){
                        temperatureLevel = temperatureLevel.increment(1);
                    }


                    return temperatureLevel;
                }
            });
        }
    }
}
