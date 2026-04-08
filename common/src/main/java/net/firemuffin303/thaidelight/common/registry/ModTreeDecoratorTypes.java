package net.firemuffin303.thaidelight.common.registry;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.common.world.feature.PapayaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Supplier;

public class ModTreeDecoratorTypes {
    public static final Supplier<TreeDecoratorType<PapayaDecorator>> TREE_DECORATOR_PAPAYA = registerTreeDecorator("papaya",PapayaDecorator.CODEC);

    @ExpectPlatform
    public static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecorator(String id, Codec<T> codec) {
        throw new AssertionError();
    }

    public static void init(){}
}
