package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.menu.MortarMenu;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
    public static MenuType<MortarMenu> MORTAR;

    public static void init(){
        MORTAR = ModPlatform.registryMenu("mortar",MortarMenu::new);
    }
}
