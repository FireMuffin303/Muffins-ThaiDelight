package net.firemuffin303.thaidelight.common.block.vegetations.basil;

import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class BasilCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private final ResourceLocation harvestLoot;
    private final ResourceLocation shearsLoot;

    public BasilCropBlock(Properties properties,ResourceLocation harvestLoot,ResourceLocation shearsLoot) {
        super(properties);
        this.harvestLoot = harvestLoot;
        this.shearsLoot = shearsLoot;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide && blockState.getValue(AGE) == this.getMaxAge()){
            if(itemStack.is(PlatformUtil.shearTag())) {
                this.drop(shearsLoot, (ServerLevel) level,itemStack,blockState,blockPos);
                return ItemInteractionResult.SUCCESS;
            }
            this.drop(this.harvestLoot, (ServerLevel) level,itemStack,blockState,blockPos);
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }


    private void drop(ResourceLocation resourceLocation, ServerLevel serverLevel, ItemStack itemStack, BlockState blockState, BlockPos blockPos){
        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE,resourceLocation));
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

        serverLevel.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
        serverLevel.setBlock(blockPos,blockState.setValue(AGE,2),3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level) / 3;
    }

}
