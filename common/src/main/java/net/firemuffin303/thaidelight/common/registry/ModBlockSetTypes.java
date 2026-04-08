package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.mixin.accessor.BlockSetTypeAccessor;
import net.firemuffin303.thaidelight.mixin.accessor.WoodSetTypeAccessor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModBlockSetTypes {
    public static final BlockSetType DURIAN_BLOCK_SET = BlockSetTypeAccessor.register(new BlockSetType("durian"));
    public static final BlockSetType MANGO_BLOCK_SET = BlockSetTypeAccessor.register(new BlockSetType("mango"));
    public static final BlockSetType COCONUT_BLOCK_SET = BlockSetTypeAccessor.register(new BlockSetType("coconut"));

    public static final WoodType DURIAN_WOOD_TYPE = WoodSetTypeAccessor.register(new WoodType("durian",DURIAN_BLOCK_SET));
    public static final WoodType MANGO_WOOD_TYPE = WoodSetTypeAccessor.register(new WoodType("mango",MANGO_BLOCK_SET));
    public static final WoodType COCONUT_WOOD_TYPE = WoodSetTypeAccessor.register(new WoodType("coconut",COCONUT_BLOCK_SET));

    public static void init(){}
}
