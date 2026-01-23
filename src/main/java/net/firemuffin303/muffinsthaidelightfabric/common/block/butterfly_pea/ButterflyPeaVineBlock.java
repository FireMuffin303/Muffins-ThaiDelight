package net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModLootTables;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;

public class ButterflyPeaVineBlock extends CropBlock {
    public static final IntegerProperty VINE_AGE = BlockStateProperties.AGE_2;
    public static final BooleanProperty ROPELOGGED = BooleanProperty.create("ropelogged");
    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public ButterflyPeaVineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(getAgeProperty(), 0).setValue(ROPELOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int age = state.getValue(getAgeProperty());
        boolean isMature = age == getMaxAge();
        if (!isMature && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (isMature) {
            ItemStack itemStack = player.getItemInHand(hand);
            if(!level.isClientSide){
                if(itemStack.is(Tags.Items.SHEARS)) {
                    this.drop(ModLootTables.BUTTERFLY_PEA_SHEARS, (ServerLevel) level,itemStack,state,pos);
                }
                else {
                    this.drop(ModLootTables.BUTTERFLY_PEA_HARVEST, (ServerLevel) level,itemStack,state,pos);
                }
            }


            level.playSound(null, pos, ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(getAgeProperty(), 0), 2);
            return InteractionResult.SUCCESS;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    private void drop(ResourceLocation resourceLocation, ServerLevel serverLevel, ItemStack itemStack, BlockState blockState, BlockPos blockPos){
        LootTable lootTable = serverLevel.getServer().getLootData().getLootTable(resourceLocation);
        LootParams params = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.BLOCK_STATE,blockState)
                .withParameter(LootContextParams.ORIGIN,blockPos.getCenter())
                .withParameter(LootContextParams.TOOL,itemStack)
                .create(LootContextParamSets.BLOCK);

        List<ItemStack> list = lootTable.getRandomItems(params);

        for (ItemStack dropStack : list) {
            ItemEntity itemEntity = new ItemEntity(serverLevel, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), dropStack);
            itemEntity.setDefaultPickUpDelay();
            serverLevel.addFreshEntity(itemEntity);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float speed = CommonEvents.getGrowthSpeed(this, level, pos);
                if (random.nextInt((int) (25.0F / speed) + 1) == 0) {
                    level.setBlock(pos, state.setValue(getAgeProperty(), age + 1), 2);
                }
            }
            attemptRopeClimb(level, pos, random);
        }
    }

    public void attemptRopeClimb(ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.3F) {
            BlockPos posAbove = pos.above();
            BlockState stateAbove = level.getBlockState(posAbove);
            boolean canClimb = Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get() ? stateAbove.is(ModTags.ROPES) : stateAbove.is(ModBlocks.ROPE.get());
            if (canClimb) {
                int vineHeight;
                for (vineHeight = 1; level.getBlockState(pos.below(vineHeight)).is(this); ++vineHeight) {
                }
                if (vineHeight < 3) {
                    level.setBlockAndUpdate(posAbove, defaultBlockState().setValue(ROPELOGGED, true));
                }
            }
        }

    }

    @Override
    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return VINE_AGE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BUTTERFLY_PEA_SEEDS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(VINE_AGE, ROPELOGGED);
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level) / 2;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (newAge > maxAge) {
            newAge = maxAge;
        }

        level.setBlockAndUpdate(pos, state.setValue(getAgeProperty(), newAge));
        attemptRopeClimb(level, pos, random);
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);

        if (state.getValue(TomatoVineBlock.ROPELOGGED)) {
            return belowState.is(net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks.BUTTERFLY_PEA_BLOCK) && hasGoodCropConditions(level, pos);
        }

        return super.canSurvive(state, level, pos);
    }

    public boolean hasGoodCropConditions(LevelReader level, BlockPos pos) {
        return level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        boolean isRopelogged = state.getValue(TomatoVineBlock.ROPELOGGED);
        super.playerDestroy(level, player, pos, state, blockEntity, stack);

        if (isRopelogged) {
            destroyAndPlaceRope(level, pos);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }

        return state;
    }

    public static void destroyAndPlaceRope(Level level, BlockPos pos) {
        var configuredRopeBlock = BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(Configuration.DEFAULT_TOMATO_VINE_ROPE.get()));
        Block finalRopeBlock = configuredRopeBlock.orElseGet(ModBlocks.ROPE);

        level.setBlockAndUpdate(pos, finalRopeBlock.defaultBlockState());
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
            if (state.getValue(TomatoVineBlock.ROPELOGGED)) {
                destroyAndPlaceRope(level, pos);
            }
        }
    }
}
