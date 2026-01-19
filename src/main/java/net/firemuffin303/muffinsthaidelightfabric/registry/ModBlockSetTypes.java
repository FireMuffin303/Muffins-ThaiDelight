package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.mixin.blockSetType.BlockSetTypeAccessor;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {

    public static final BlockSetType DURIAN = BlockSetTypeAccessor.register(new BlockSetType("durian"));
    public static final BlockSetType MANGO = BlockSetTypeAccessor.register(new BlockSetType("mango"));
    public static final BlockSetType COCONUT = BlockSetTypeAccessor.register(new BlockSetType("coconut"));

    public static void init(){}
}
