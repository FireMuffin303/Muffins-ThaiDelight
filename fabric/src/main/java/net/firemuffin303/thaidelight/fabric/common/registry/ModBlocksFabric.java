package net.firemuffin303.thaidelight.fabric.common.registry;

import com.nhoryzon.mc.farmersdelight.block.FeastBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.block.SomtamFeastBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModBlocksFabric {
    public static Block SOMTAM_FEAST = new SomtamFeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItemsFabric.SOMTAM, true);
    public static Block SPICY_MINCED_PORK_SALAD_FEAST = new FeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItemsFabric.SPICY_MINCED_MEAT_SALAD, true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 7.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    };
    public static Block CRAB_FRIED_RICE = new FeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE),ModItemsFabric.CRAB_FRIED_RICE,true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 8.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    };
    public static void init(){
    //    register("somtam_block",SOMTAM_FEAST);
    //    register("spicy_minced_pork_salad_block",SPICY_MINCED_PORK_SALAD_FEAST);
    }

    private static void register(String id,Block block){
        Registry.register(BuiltInRegistries.BLOCK,new ResourceLocation(ThaiDelight.MOD_ID,id),block);
        Item item = new BlockItem(block,new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
        ModItems.ITEMS.add(item);

    }
}
