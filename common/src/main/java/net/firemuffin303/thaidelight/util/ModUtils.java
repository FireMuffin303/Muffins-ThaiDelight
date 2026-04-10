package net.firemuffin303.thaidelight.util;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.Optional;

public class ModUtils {
    public static ItemStack getCraftRemainder(ItemStack itemStack){
        return itemStack.getItem().hasCraftingRemainingItem() ? itemStack.getItem().getCraftingRemainingItem().getDefaultInstance() : ItemStack.EMPTY;
    }

    public static boolean isAreaLoaded(ServerLevel serverLevel, BlockPos center, int range) {
        return serverLevel.hasChunksAt(center.offset(-range, -range, -range), center.offset(range, range, range));
    }

    public static Optional<BlockPos> getTopConnectedBlock(BlockGetter blockGetter, BlockPos blockPos, BlockState middleBlock, Direction direction, BlockState endBlock) {
        BlockState blockState;
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        do {
            mutableBlockPos.move(direction);
            blockState = blockGetter.getBlockState(mutableBlockPos);
        } while (blockState == middleBlock);
        if (blockState == endBlock) {
            return Optional.of(mutableBlockPos);
        }
        return Optional.empty();
    }

    public static int calculateEatingWithAnorexiaEffect(LivingEntity livingEntity, int original){
        float amp = ( Objects.requireNonNull(livingEntity.getEffect(ModMobEffects.APPETITE_LOSS.get())).getAmplifier() + 1);
        float rate = 1.2f;
        return (int) ((float)original * (rate + (rate * (0.6 * amp))  ) );
    }

    public static void playDurianCatchingSound(ServerLevel serverLevel, Vec3 vec3, BlockPos blockPos){
        for(ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()){
            if(player.level().dimension() != serverLevel.dimension() || player.position().distanceTo(vec3) > 64f) continue;
            //ServerPlayNetworking.send(player,new ModLevelEventPacket((byte) 1,blockPos));
        }
    }

    public static void durianHelmetThorns(LivingEntity victim, Entity attacker){
        ItemStack helmet = victim.getItemBySlot(EquipmentSlot.HEAD);
        if(helmet.is(ModItems.DURIAN_HELMET.get()) && EnchantmentHelper.getEnchantmentLevel(Enchantments.THORNS,victim) <= 0){
            RandomSource randomSource = victim.getRandom();
            if(ThornsEnchantment.shouldHit(1,randomSource)){
                if(attacker != null){
                    attacker.hurt(victim.damageSources().thorns(victim),ThornsEnchantment.getDamage(1,randomSource));
                }

                if (attacker != null) {
                    helmet.hurtAndBreak(2, victim, livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.HEAD));
                }
            }
        }
    }

    public static void spawnItemEntity(Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        ItemEntity entity = new ItemEntity(level, x, y, z, stack);
        entity.setDeltaMovement(xMotion, yMotion, zMotion);
        level.addFreshEntity(entity);
    }

    public static void onEatSpicyFood(ItemStack itemStack,LivingEntity livingEntity){
        livingEntity.setTicksFrozen(0);


        int i = 1200;
        if(itemStack.getItem().isEdible()){
            FoodProperties foodProperties = itemStack.getItem().getFoodProperties();
            if(foodProperties != null){
                float nutrition = foodProperties.getNutrition();
                float modifier = foodProperties.getSaturationModifier();
                i = (int)Math.max( ((nutrition + (nutrition * modifier) ) / 6f) * (60f * 20f), 1200) ;
            }
        }

        PlatformUtil.addSpicyTime(i,livingEntity);
    }

    public static void onEatDurian(ItemStack itemStack,LivingEntity livingEntity){
        int i = 2400;
        if(itemStack.getItem().isEdible()){
            FoodProperties foodProperties = itemStack.getItem().getFoodProperties();
            if(foodProperties != null){
                float nutrition = foodProperties.getNutrition();
                float modifier = foodProperties.getSaturationModifier();
                i = (int)Math.max( ((nutrition + (nutrition * modifier) ) / 4f) * (60f * 20f), 2400f) ;
            }
        }

        PlatformUtil.addDurianHeatTime(i,livingEntity);
    }

    public static void onDrinkFermentedDrinks(Level level, ItemStack itemStack, LivingEntity livingEntity){
        DurianComponentSupplier durianComponentSupplier = PlatformUtil.getDurianHeatComponent(livingEntity);
        if(durianComponentSupplier.timer > 0){
            if(livingEntity instanceof Player player){
                player.displayClientMessage(Component.translatable("muffins_thaidelight.consume.durian_fermented_drinks"),true);
            }
            PlatformUtil.setDurianHeat(true,livingEntity);
        }
    }

    public record DurianComponentSupplier(int timer,boolean isHeatUp){

    }
}
