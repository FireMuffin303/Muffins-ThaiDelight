package net.firemuffin303.thaidelight.forge.common.registry;

import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.block.SpicyMincedPorkSaladFeastBlock;
import net.firemuffin303.thaidelight.forge.common.block.SomtamFeastBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class ModBlocksForge {
    public static final Block SOMTAM_BLOCK = new SomtamFeastBlock();
    public static final Block SPICY_MINCED_PORK_SALAD_BLOCK = new SpicyMincedPorkSaladFeastBlock();
    public static final Block CRAB_FRIED_RICE = new FeastBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItemsForge.CRAB_FRIED_RICE, true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 8.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    };

    public static void init(){
    }

    private static RegistryObject<Block> register(String id,Supplier<Block> blockSupplier){
        RegistryObject<Block> blockRegistryObject = ThaiDelightForge.BLOCK.register(id,blockSupplier);
        //var item = new BlockItem(blockSupplier.get(),new Item.Properties());
        //ThaiDelightForge.ITEMS.register(id,()-> item);
        return blockRegistryObject;
    }
}
