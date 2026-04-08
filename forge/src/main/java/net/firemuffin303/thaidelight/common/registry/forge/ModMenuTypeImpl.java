package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypeImpl {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(Registries.MENU,ThaiDelightCommon.MOD_ID);

    public static <T extends AbstractContainerMenu> MenuType<T> register(String id, ModMenuType.MenuTypeSupplier<T> menuType) {
        RegistryObject<MenuType<T>> object = MENU.register(id,() -> new MenuType<>(menuType::create,FeatureFlags.VANILLA_SET));
        return object.get();
    }
}
