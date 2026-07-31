package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypeImpl {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(Registries.MENU,ThaiDelightCommon.MOD_ID);

    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, ModMenuType.MenuTypeSupplier<T> menuType) {
        return MENU.register(id,() -> new MenuType<>(menuType::create,FeatureFlags.VANILLA_SET));
    }
}
