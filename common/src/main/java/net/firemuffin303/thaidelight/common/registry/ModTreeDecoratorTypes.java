package net.firemuffin303.thaidelight.common.registry;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.world.feature.PapayaDecorator;
import net.firemuffin303.thaidelight.mixin.accessor.feature.TreeDecoratorTypeAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Supplier;

public class ModTreeDecoratorTypes {
    public static final ResourceRegistry<TreeDecoratorType<?>> TREE_DECORATOR = ResourceRegistry.create(Registries.TREE_DECORATOR_TYPE, ThaiDelightCommon.MOD_ID);
    public static final Supplier<TreeDecoratorType<?>> TREE_DECORATOR_PAPAYA = TREE_DECORATOR.register("papaya", () -> TreeDecoratorTypeAccessor.init(PapayaDecorator.CODEC));


    public static void init(){
        TREE_DECORATOR.init();
    }
}
