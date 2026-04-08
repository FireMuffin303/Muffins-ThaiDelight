package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.menu.MortarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ModMenuType {
    public static Supplier<MenuType<MortarMenu>> MORTAR = register("mortar",MortarMenu::new);

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, MenuTypeSupplier<T> menuType){
        throw new AssertionError();
    }

    public static void init() {

    }

    public interface  MenuTypeSupplier<T extends AbstractContainerMenu>{
        T create(int id, Inventory inventory);
    }
}