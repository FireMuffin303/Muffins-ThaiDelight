package net.firemuffin303.thaidelight.common.registry.forge;

import com.mojang.serialization.Codec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTreeDecoratorTypesImpl {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPE = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, ThaiDelightCommon.MOD_ID);

    public static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecorator(String id, Codec<T> codec) {
        return TREE_DECORATOR_TYPE.register(id,() -> new TreeDecoratorType<>(codec));
    }
}
