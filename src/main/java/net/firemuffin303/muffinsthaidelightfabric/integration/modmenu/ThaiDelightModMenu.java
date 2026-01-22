package net.firemuffin303.muffinsthaidelightfabric.integration.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;

public class ThaiDelightModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, ThaiDelight.MOD_ID);
    }
}
