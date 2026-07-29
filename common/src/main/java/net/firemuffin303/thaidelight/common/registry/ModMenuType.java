package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.menu.MortarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ModMenuType {
    public static final ResourceRegistry<MenuType<?>> MENU_TYPE = ResourceRegistry.create(Registries.MENU,ThaiDelightCommon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static Supplier<MenuType<MortarMenu>> MORTAR = (Supplier<MenuType<MortarMenu>>)(Supplier<?>) MENU_TYPE.register("mortar",() -> new MenuType<>(MortarMenu::new, FeatureFlags.VANILLA_SET));

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, MenuTypeSupplier<T> menuType){
        throw new AssertionError();
    }

    public static void init() {
        MENU_TYPE.init();
    }

    public interface  MenuTypeSupplier<T extends AbstractContainerMenu>{
        T create(int id, Inventory inventory);
    }
}