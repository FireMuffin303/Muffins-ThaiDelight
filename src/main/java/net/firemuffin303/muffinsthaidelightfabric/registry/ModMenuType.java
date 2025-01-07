package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.menu.MortarMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
    public static MenuType<MortarMenu> MORTAR = register("mortar",new MenuType<>(MortarMenu::new, FeatureFlags.VANILLA_SET));

    public static <T extends AbstractContainerMenu> MenuType<T> register(String id, MenuType<T> menuType){
        return Registry.register(BuiltInRegistries.MENU,new ResourceLocation(ThaiDelight.MOD_ID,id),menuType);
    }

    public static void init() {
        
    }
}
