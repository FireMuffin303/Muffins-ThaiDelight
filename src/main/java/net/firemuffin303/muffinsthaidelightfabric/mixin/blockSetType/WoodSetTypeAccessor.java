package net.firemuffin303.muffinsthaidelightfabric.mixin.blockSetType;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodSetTypeAccessor {

    @Invoker("register")
    static WoodType register(WoodType woodType) {
        throw new AssertionError();
    }
}
