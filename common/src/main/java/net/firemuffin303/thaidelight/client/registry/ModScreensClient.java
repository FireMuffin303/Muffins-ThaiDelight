package net.firemuffin303.thaidelight.client.registry;

import net.firemuffin303.thaidelight.client.screens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.utils.ModPlatform;

public class ModScreensClient {
    public static void init(){
        ModPlatform.registerScreen(ModMenuType.MORTAR, MortarScreen::new);
    }
}
