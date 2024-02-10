package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
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

public class ModBlocks {
    //Functional Block
    public static final Block MORTAR = new MortarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion());

    //Crate
    public static final Block LIME_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block PEPPER_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block UNRIPE_PAPAYA_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block PAPAYA_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));

    //Sauce Bowl
    //public static final Block SAUCE_BOWL = new SauceBowl(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).instabreak());
    //public static final Block SEAFOOD_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.SEAFOOD);
    //public static final Block FISH_SAUCE_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.FISH_SAUCE);
    //public static final Block HONEY_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.FISH_SAUCE);

    //Eggs
    public static final Block CRAB_EGG = new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN));

    //Wild Crops
    public static final Block WILD_PEPPER_CROP = ModPlatform.getWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
    public static final Block WILD_PAPAYA_CROP = ModPlatform.getWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));

    //Crops
    public static final Block LIME_CROP = new LimeCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block LIME_SAPLING = new LimeSapling(BlockBehaviour.Properties.copy(ModBlocks.LIME_CROP).noCollission());
    public static final Block PEPPER_CROP = new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES));
    public static final Block PAPAYA_STEM = new PapayaStem(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.WOOD).ignitedByLava().pushReaction(PushReaction.DESTROY));
    public static final Block PAPAYA_TOP_STEM = new UpperPapayaBlock(BlockBehaviour.Properties.copy(PAPAYA_STEM));

    public static final Block PAPAYA = new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY));
    public static final Block PAPAYA_SAPLING = new ModSaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));

    //Feast
    public static final Block CRAB_FRIED_RICE_FEAST = ModPlatform.getCrabFriedRice();
    public static final Block SOMTAM_FEAST = ModPlatform.getSomtamBlock();
    public static final Block SPICY_MINCED_MEAT_SALAD_FEAST = ModPlatform.getSpicyMincedPorkBlock();

    //Log
    public static final Block PAPAYA_LOG = new PapayaLog(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava());
    public static final Block STRIPPED_PAPAYA_LOG = log(MapColor.COLOR_CYAN,MapColor.COLOR_CYAN);
    public static final Block PAPAYA_WOOD = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block STRIPPED_PAPAYA_WOOD = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());

    public static final Block PAPAYA_LEAVES = leaves(SoundType.AZALEA_LEAVES);

    public static void init(){
        //Functional Block
        registerWithItem("mortar",MORTAR);

        //Crate
        registerWithItem("lime_crate",LIME_CRATE);
        registerWithItem("pepper_crate",PEPPER_CRATE);
        registerWithItem("raw_papaya_crate",UNRIPE_PAPAYA_CRATE);
        registerWithItem("papaya_crate",PAPAYA_CRATE);

        //Sauce Bowl
        //registerWithItem("sauce_bowl",SAUCE_BOWL);
        //register("seafood_sauce_bowl",SEAFOOD_SAUCE_BOWL);
        //register("fish_sauce_sauce_bowl",FISH_SAUCE_SAUCE_BOWL);
        //register("honey_sauce_bowl",HONEY_SAUCE_BOWL);

        //Eggs
        registerWithItem("flower_crab_egg",CRAB_EGG);

        //Wild Crops
        registerWithItem("wild_pepper",WILD_PEPPER_CROP);
        registerWithItem("wild_papaya",WILD_PAPAYA_CROP);

        //Crops
        register("pepper",PEPPER_CROP);
        register("lime_bush", LIME_CROP);
        registerWithItem("lime_sapling", LIME_SAPLING);

        register("papaya_top_stem", PAPAYA_TOP_STEM);
        register("papaya_stem", PAPAYA_STEM);

        register("papaya",PAPAYA);
        registerWithItem("papaya_sapling",PAPAYA_SAPLING);

        //Feast
        registerWithItem("somtam_feast",SOMTAM_FEAST);
        registerWithItem("spicy_minced_meat_salad_feast", SPICY_MINCED_MEAT_SALAD_FEAST);
        registerWithItem("crab_fried_rice_feast", CRAB_FRIED_RICE_FEAST);

        //Block
        registerWithItem("papaya_log",PAPAYA_LOG);
        registerWithItem("stripped_papaya_log",STRIPPED_PAPAYA_LOG);
        registerWithItem("papaya_wood",PAPAYA_WOOD);
        registerWithItem("stripped_papaya_wood",STRIPPED_PAPAYA_WOOD);
        registerWithItem("papaya_leaves",PAPAYA_LEAVES);
    }

    public static void register(String id, Block block){
        ModPlatform.registryBlock(id,()->block);
    }

    public static void registerWithItem(String id, Block block){
        register(id, block);
        Item item = new BlockItem(block,new Item.Properties());
        ModPlatform.registryItem(id,() -> item);
        ModItems.ITEMS.add(item);
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
        //public static BlockEntityType<MortarBlockEntity> MORTAR_BLOCK_ENTITY = ModPlatform.buildBlockEntity(MortarBlockEntity::new,ModBlocks.MORTAR).build(null);

        public static void init(){
            //ModPlatform.registerBlockEntity("mortar",MORTAR_BLOCK_ENTITY);
        }


        @FunctionalInterface
        public interface BlockEntitySupplier<T extends BlockEntity> {
            T create(BlockPos blockPos, BlockState blockState);
        }
    }



}
