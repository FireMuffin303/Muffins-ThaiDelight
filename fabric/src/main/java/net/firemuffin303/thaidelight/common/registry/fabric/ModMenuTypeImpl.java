package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class ModMenuTypeImpl {


    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, ModMenuType.MenuTypeSupplier<T> menuType) {
        MenuType<T> registeredMenuType = Registry.register(BuiltInRegistries.MENU,ThaiDelightCommon.modid(id),new MenuType<>(menuType::create, FeatureFlags.VANILLA_SET));
        return () -> registeredMenuType;
    }
}
