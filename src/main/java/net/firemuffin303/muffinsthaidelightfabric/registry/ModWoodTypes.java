package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.mixin.blockSetType.WoodSetTypeAccessor;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType DURIAN = WoodSetTypeAccessor.register(new WoodType("durian",ModBlockSetTypes.DURIAN));
    public static final WoodType MANGO = WoodSetTypeAccessor.register(new WoodType("mango",ModBlockSetTypes.MANGO));
    public static final WoodType COCONUT = WoodSetTypeAccessor.register(new WoodType("coconut",ModBlockSetTypes.COCONUT));

    public static void init(){}
}
