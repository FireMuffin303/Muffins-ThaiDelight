package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.firemuffin303.muffinsthaidelightfabric.common.block.entity.SauceBowlBlockEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SauceBowlBlock extends BaseEntityBlock {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level",0,3);

    public SauceBowlBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL,1));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if(blockState.getValue(LEVEL) > 0 && itemStack.isEdible() && !ModComponents.FLAVOR.get(itemStack).isFlavored() && level.getBlockEntity(blockPos) instanceof SauceBowlBlockEntity sauceBowlBlockEntity){
            if(!level.isClientSide){
                FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(itemStack);
                flavorItemComponent.setSourLevel(sauceBowlBlockEntity.getSour());
                flavorItemComponent.setSpicyLevel(sauceBowlBlockEntity.getSpicy());
                flavorItemComponent.setSaltyLevel(sauceBowlBlockEntity.getSalt());
                flavorItemComponent.setSweetLevel(sauceBowlBlockEntity.getSweet());
                level.setBlockAndUpdate(blockPos,blockState.setValue(LEVEL,blockState.getValue(LEVEL) -1));
                level.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.PLAYERS,0.8f,1f);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);

        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        level.getBlockEntity(blockPos, ModBlocks.SAUCE_BOWL_BLOCK_ENTITY).ifPresent(sauceBowlBlockEntity -> {
            sauceBowlBlockEntity.fromItem(itemStack);
        });
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SauceBowlBlockEntity(blockPos,blockState);
    }
}
