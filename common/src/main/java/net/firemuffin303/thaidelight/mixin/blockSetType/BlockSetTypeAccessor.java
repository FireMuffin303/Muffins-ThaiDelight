package net.firemuffin303.thaidelight.mixin.blockSetType;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockSetType.class)
public interface BlockSetTypeAccessor {

    @Invoker("register")
    static BlockSetType register(BlockSetType woodType){
        throw new AssertionError();
    }
}
