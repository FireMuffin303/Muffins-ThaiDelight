package net.firemuffin303.thaidelight.common.registry.fabric;

import com.mojang.serialization.Codec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Supplier;

public class ModTreeDecoratorTypesImpl {
    public static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecorator(String id, Codec<T> codec) {
        TreeDecoratorType<T> registeredTreeDecorator = Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, ThaiDelightCommon.modid(id),new TreeDecoratorType<>(codec));
        return () -> registeredTreeDecorator;
    }
}
