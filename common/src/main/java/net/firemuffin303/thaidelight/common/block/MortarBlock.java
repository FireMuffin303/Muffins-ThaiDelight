package net.firemuffin303.thaidelight.common.block;

import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MortarBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE;
    public MortarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        ItemStack mainStack = player.getItemInHand(interactionHand);
        if(blockEntity instanceof MortarBlockEntity mortarBlockEntity){
            if(!mainStack.isEmpty()){

                //If item is tools
                if(mainStack.getItem() instanceof TieredItem && !mortarBlockEntity.isEmpty()){
                    if(mortarBlockEntity.process(mortarBlockEntity,player,mainStack)){
                        level.playSound(null,blockPos,SoundEvents.GRAVEL_BREAK,SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.PASS;
                }

                //add item to mortar
                if(interactionHand.equals(InteractionHand.MAIN_HAND)){
                    if(mortarBlockEntity.addItem(mainStack)){
                        level.playSound(null,blockPos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }

                return InteractionResult.PASS;

            }else{
                //bare hand remove item
                if(mainStack.isEmpty() && interactionHand.equals(InteractionHand.MAIN_HAND) && !mortarBlockEntity.isEmpty() && mortarBlockEntity.removeItem()){
                    level.playSound(null,blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
                    return InteractionResult.SUCCESS;
                }
            }


            return InteractionResult.PASS;

        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }



    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MortarBlockEntity(blockPos,blockState);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    static{
        SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);
    }
}
