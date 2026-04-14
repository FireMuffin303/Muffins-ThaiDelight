package net.firemuffin303.thaidelight.integration.modmenu;


import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import net.firemuffin303.thaidelight.ThaiDelightCommon;

public class ThaiDelightModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, ThaiDelightCommon.MOD_ID);
    }
}
