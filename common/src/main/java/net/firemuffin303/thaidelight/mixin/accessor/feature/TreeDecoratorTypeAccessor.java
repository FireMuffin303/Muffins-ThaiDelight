package net.firemuffin303.thaidelight.mixin.accessor.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeAccessor {

    @Invoker("<init>")
    static <T extends TreeDecorator> TreeDecoratorType<T> init(MapCodec<T> codec){
        throw new AssertionError();
    }
}
