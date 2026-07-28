package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModDispenserBehavior {

    public static void init() {
        DefaultDispenseItemBehavior fishBehavior = new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
                Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
                BlockState blockState = blockSource.level().getBlockState(blockSource.pos().relative(direction));
                if (blockState.is(Blocks.WATER_CAULDRON) && blockState.getValue(LayeredCauldronBlock.LEVEL) == 3) {
                    blockSource.level().setBlock(blockSource.pos().relative(direction), ModBlocks.FERMENTED_FISH_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.LEVEL, 3), 3);
                    itemStack.shrink(1);
                    return itemStack;
                }


                return super.execute(blockSource, itemStack);
            }
        };

        DispenserBlock.registerBehavior(Items.COD, fishBehavior);
        DispenserBlock.registerBehavior(Items.SALMON, fishBehavior);
        DispenserBlock.registerBehavior(Items.TROPICAL_FISH, fishBehavior);

        /*
        DispenserBlock.registerBehavior(ModItems.COCONUT_SLICE.get(),new DefaultDispenseItemBehavior(){
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
            @Override
            protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
                Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
                BlockState blockState = blockSource.getLevel().getBlockState(blockSource.getPos().relative(direction));
                if(blockState.is(Blocks.CAULDRON) || blockState.is(ModBlocks.COCONUT_CAULDRON.get()) || blockState.is(ModBlocks.COCONUT_MILK_CAULDRON.get())){
                    if( (blockState.is(ModBlocks.COCONUT_CAULDRON.get()) && blockState.getValue(CoconutCauldron.LEVEL) >=3) || blockState.is(ModBlocks.COCONUT_MILK_CAULDRON.get())){
                        return itemStack;
                    }

                    int level = blockState.is(ModBlocks.COCONUT_CAULDRON.get()) ? blockState.getValue(CoconutCauldron.LEVEL) + 1 : 1;
                    level = Mth.clamp(level,1,3);
                    blockSource.getLevel().setBlockAndUpdate(blockSource.getPos().relative(direction), ModBlocks.COCONUT_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.LEVEL,level));
                    itemStack.shrink(1);
                    if(itemStack.isEmpty()){
                        return ModUtils.getCraftRemainder(itemStack.copy());
                    }
                    this.defaultDispenseItemBehavior.dispense(blockSource, ModUtils.getCraftRemainder(itemStack.copy()));
                    return itemStack;
                }

                return super.execute(blockSource, itemStack);
            }
        });

         */
    }
}
