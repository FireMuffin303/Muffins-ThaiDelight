package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

public class FermentedFishCauldronBlock extends AbstractCauldronBlock {
    public static final IntegerProperty FERMENT;
    public static final IntegerProperty LEVEL;
    public static final int MAX_FERMENT_LEVEL = 2;
    private static final int MAX_FERMENTING_TIME = 6000; // 5 Min
    private static final int MIN_FERMENTING_TIME = 2400; // 5 Min

    public FermentedFishCauldronBlock(Properties properties, Map<Item, CauldronInteraction> map) {
        super(properties, map);
        this.registerDefaultState((BlockState) this.stateDefinition.any().setValue(FERMENT,0));
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL,1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{FERMENT,LEVEL});
    }


    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        level.gameEvent(GameEvent.BLOCK_CHANGE,blockPos, GameEvent.Context.of(blockState));
        level.scheduleTick(blockPos,this, level.random.nextInt(MIN_FERMENTING_TIME,MAX_FERMENTING_TIME));
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(blockState.getValue(FERMENT) < MAX_FERMENT_LEVEL) {
            this.fermenting(serverLevel,blockPos,blockState,randomSource);
        }
    }

    private void fermenting(ServerLevel serverLevel,BlockPos blockPos,BlockState blockState,RandomSource randomSource){
        serverLevel.playSound(null, blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.7f, 0.9f + randomSource.nextFloat() * 0.2f);
        serverLevel.gameEvent(GameEvent.BLOCK_CHANGE,blockPos,GameEvent.Context.of(blockState));
        serverLevel.setBlock(blockPos, blockState.setValue(FERMENT, blockState.getValue(FERMENT) + 1), 2);
        serverLevel.scheduleTick(blockPos,this, randomSource.nextInt(MIN_FERMENTING_TIME,MAX_FERMENTING_TIME));
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (this.isEntityInsideContent(blockState,blockPos,entity) && !level.isClientSide){
            if(entity instanceof LivingEntity livingEntity){
                int duration = 10*20;
                switch (blockState.getValue(FERMENT)){
                    case 1 -> duration = 15*20;
                    case 2 -> duration = 30*20;
                }

                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.STINKY, duration));
            }

            if(entity.isOnFire()){
                entity.clearFire();
                if (entity.mayInteract(level, blockPos)) {
                    this.handleEntityOnFireInside(blockState, level, blockPos);
                }
            }
        }
    }

    protected void handleEntityOnFireInside(BlockState blockState, Level level, BlockPos blockPos) {
        lowerFillLevel(blockState, level, blockPos);
    }

    public static void lowerFillLevel(BlockState blockState, Level level, BlockPos blockPos) {
        int i = (Integer)blockState.getValue(LEVEL) - 1;
        BlockState blockState2 = i == 0 ? Blocks.CAULDRON.defaultBlockState() : (BlockState)blockState.setValue(LEVEL, i);
        level.setBlockAndUpdate(blockPos, blockState2);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState2));
    }

    @Override
    protected double getContentHeight(BlockState blockState) {
        return (6.0 + (double) blockState.getValue(LEVEL) * 3.0) / 16.0;
    }

    @Override
    public boolean isFull(BlockState blockState) {
        return blockState.getValue(LEVEL) == 3;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return Mth.ceil(blockState.getValue(FERMENT) * 7.5f);
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(FERMENT) == 2) {
            double d = (double)blockPos.getX() + 0.5;
            double e = (double)blockPos.getY() + 0.8;
            double f = (double)blockPos.getZ() + 0.5;

            double[] color = {0.596078431372549f,0.3607843137254902f,0.2705882352941176f};
            level.addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT,d, e,f,color[0],color[1],color[2]);
        }
    }

    static {
        FERMENT = IntegerProperty.create("fermented",0,MAX_FERMENT_LEVEL);
        LEVEL = BlockStateProperties.LEVEL_CAULDRON;
    }

}
