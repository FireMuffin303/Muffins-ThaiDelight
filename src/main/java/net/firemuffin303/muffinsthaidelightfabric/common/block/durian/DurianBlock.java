package net.firemuffin303.muffinsthaidelightfabric.common.block.durian;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DurianBlock extends FallingBlock {
    private static final VoxelShape BOX = Block.box(1.0,0.0,1.0,15.0,16.0,15.0);
    public DurianBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return BOX;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        //cancelParticle
    }

    @Override
    protected void falling(FallingBlockEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(1.5f,8);
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().source(ModDamageTypes.FALLING_DURIAN);
    }
}
