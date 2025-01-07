package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.feature.PapayaDecorator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ModTreeDecoratorTypes {
    public static final TreeDecoratorType<PapayaDecorator> TREE_DECORATOR_PAPAYA = registerTreeDecorator("papaya",PapayaDecorator.CODEC);

    public static <T extends TreeDecorator> TreeDecoratorType<T> registerTreeDecorator(String id, Codec<T> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,id),new TreeDecoratorType<>(codec));
    }

    public static void init(){}
}
