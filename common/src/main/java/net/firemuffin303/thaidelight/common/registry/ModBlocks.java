package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.FishSauceCauldronBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.SeafoodCauldronBlock;
import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.firemuffin303.thaidelight.common.fluid.ModLiquid;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ModBlocks {
    public static final ResourcefulRegistry<Block> BLOCK = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, ThaiDelight.MOD_ID);
    public static final RegistryEntry<Block> CRAB_EGG = BLOCK.register("crab_egg",() -> new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).noOcclusion()));
    public static final RegistryEntry<Block> SEAFOOD = BLOCK.register("seafood",() -> new ModLiquid(ModFluid.SEAFOOD.get(),BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryEntry<Block> SEAFOOD_CAULDRON = BLOCK.register("seafood_cauldron",() -> new SeafoodCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));
    public static final RegistryEntry<Block> FISH_SAUCE_CAULDRON = BLOCK.register("fish_sauce_cauldron",() -> new FishSauceCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));

    //Crops
    public static final Supplier<Block> LIME_BUSH = ModPlatform.registryBlock("lime_bush",() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryEntry<Block> MORTAR = BLOCK.register("mortar",() -> new MortarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static class ModBlockEntityTypes{
        public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPE = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ThaiDelight.MOD_ID);

        public static final RegistryEntry<BlockEntityType<MortarBlockEntity>> MORTAR_BLOCK_ENTITY = BLOCK_ENTITY_TYPE.register("mortar",() -> ModPlatform.registerBlockEntity(MortarBlockEntity::new,ModBlocks.MORTAR.get()).build(null));

        @FunctionalInterface
        public interface BlockEntitySupplier<T extends BlockEntity> {
            T create(BlockPos blockPos, BlockState blockState);
        }
    }



}
