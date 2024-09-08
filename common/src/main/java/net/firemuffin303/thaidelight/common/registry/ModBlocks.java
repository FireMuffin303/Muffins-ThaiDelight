package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldron;
import net.firemuffin303.thaidelight.common.block.crops.*;
import net.firemuffin303.thaidelight.common.block.grower.PapayaTreeGrower;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class ModBlocks {
    //Functional Block
    public static final Supplier<Block> MORTAR = register("mortar",() -> new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5f,6.0f).noOcclusion().sound(SoundType.DECORATED_POT)));

    //Crate
    public static final Supplier<Block> LIME_CRATE = register("lime_crate",() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Supplier<Block> PEPPER_CRATE = register("pepper_crate",() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Supplier<Block> RAW_PAPAYA_CRATE = register("raw_papaya_crate",() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Supplier<Block> PAPAYA_CRATE = register("papaya_crate",() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));

    //Eggs
    public static final Supplier<Block> CRAB_EGG = register("flower_crab_egg",() -> new CrabEggBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FROGSPAWN)));

    //Wild Crops
    public static final Supplier<Block> WILD_PEPPER_CROP = register("wild_pepper_crop",() -> ModPlatform.getWildCropBlock(MobEffects.CONFUSION.value(),6,BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    //Crops
    public static final Supplier<Block> LIME_CROP = register("lime_bush",() -> new LimeCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> LIME_SAPLING = register("lime_sapling",() -> new LimeSapling(BlockBehaviour.Properties.ofFullCopy(ModBlocks.LIME_CROP.get()).noCollission()));
    public static final Supplier<Block> PEPPER_CROP = register("pepper",() -> new PepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));


    //Feast
    public static final Supplier<Block> CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast", ModPlatform::getCrabFriedRice);
    public static final Supplier<Block> SOMTAM_FEAST = register("somtam_feast",ModPlatform::getSomtamBlock);
    public static final Supplier<Block> LARB_FEAST = register("larb_feast",ModPlatform::getSpicyMincedPorkBlock);

    //Papaya
    public static final Supplier<Block> PAPAYA_LOG = register("papaya_log", () -> new PapayaLog(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",() -> log(MapColor.COLOR_CYAN,MapColor.COLOR_CYAN));
    public static final Supplier<Block> PAPAYA_WOOD = register("papaya_wood",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> PAPAYA_LEAVES = register("papaya_leaves",() -> leaves(SoundType.AZALEA_LEAVES));

    public static final Supplier<Block> PAPAYA = register("papaya",() -> new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> PAPAYA_SAPLING = register("papaya_sapling",() -> new ModSaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> PAPAYA_CROPS = register("papaya_crop",() -> new PapayaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    //Cauldron
    public static final Supplier<Block> FERMENTED_FISH_CAULDRON = register("fermented_fish_cauldron",() -> new FermentedFishCauldron(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).randomTicks()));

    public static void init(){}

    public static Supplier<Block> register(String id, Supplier<Block> block){
        return ModPlatform.registryBlock(ThaiDelight.ModResource(id),block);
    }

    private static RotatedPillarBlock log(MapColor mapColor, MapColor mapColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> {
            return blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor2;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    private static LeavesBlock leaves(SoundType soundType) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating((blockState, blockGetter, blockPos) -> false).isViewBlocking((blockState, blockGetter, blockPos) -> false).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor((blockState, blockGetter, blockPos) -> false));
    }

    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    public static class ModBlockEntityTypes{
        public static void init(){
        }


        @FunctionalInterface
        public interface BlockEntitySupplier<T extends BlockEntity> {
            T create(BlockPos blockPos, BlockState blockState);
        }
    }



}
