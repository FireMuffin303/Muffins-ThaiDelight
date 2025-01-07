package net.firemuffin303.muffinsthaidelightfabric.common.item;

import net.firemuffin303.muffinsthaidelightfabric.common.Bottleable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class MobBottleItem extends Item {
    private final EntityType<?> entityType;
    private final SoundEvent emptySound;
    public MobBottleItem(EntityType<?> entityType,SoundEvent emptySound,Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.emptySound = emptySound;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        ItemStack itemStack = useOnContext.getItemInHand();
        Player user = useOnContext.getPlayer();
        BlockPos blockPos = useOnContext.getClickedPos();
        Direction direction = useOnContext.getClickedFace();
        BlockPos blockPos2 = blockPos.offset(direction.getNormal());
        this.checkExtraContent(user, useOnContext.getLevel(),itemStack, blockPos2);
        user.setItemInHand(useOnContext.getHand(),getEmptySuccessItem(itemStack,user));
        return InteractionResult.SUCCESS;
    }

    public static ItemStack getEmptySuccessItem(ItemStack itemStack, Player player) {
        return !player.getAbilities().instabuild ? new ItemStack(Items.GLASS_BOTTLE) : itemStack;
    }

    public void checkExtraContent(@Nullable Player player, Level level, ItemStack itemStack, BlockPos blockPos) {
        if (level instanceof ServerLevel) {
            this.spawn((ServerLevel)level, itemStack, blockPos);
            this.playEmptySound(player,level,blockPos);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, blockPos);
        }

    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos) {
        levelAccessor.playSound(player, blockPos, this.emptySound, SoundSource.NEUTRAL, 1.0F, 1.0F);
    }

    private void spawn(ServerLevel serverLevel, ItemStack itemStack, BlockPos blockPos) {
        Entity entity = this.entityType.spawn(serverLevel, itemStack, (Player)null, blockPos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof Bottleable bottleable) {
            bottleable.copyDataFromNbt(itemStack.getOrCreateTag());
            bottleable.setFromBottle(true);
        }

    }
}
