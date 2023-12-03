package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
import net.firemuffin303.thaidelight.common.block.crops.LimeCrop;
import net.firemuffin303.thaidelight.common.block.crops.LimeSapling;
import net.firemuffin303.thaidelight.common.block.crops.PapayaCrop;
import net.firemuffin303.thaidelight.common.block.crops.PepperBlock;
import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.firemuffin303.thaidelight.common.block.saucebowl.LevelSauceBowl;
import net.firemuffin303.thaidelight.common.block.saucebowl.SauceBowl;
import net.firemuffin303.thaidelight.common.block.saucebowl.SauceBowlInteraction;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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
    public static final Block SAUCE_BOWL = new SauceBowl(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).instabreak());
    public static final Block SEAFOOD_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.SEAFOOD);
    public static final Block FISH_SAUCE_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.FISH_SAUCE);
    public static final Block HONEY_SAUCE_BOWL = new LevelSauceBowl(BlockBehaviour.Properties.copy(ModBlocks.SAUCE_BOWL),SauceBowlInteraction.FISH_SAUCE);

    //Eggs
    public static final Block CRAB_EGG = new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN));

    //Wild Crops
    public static final Block WILD_PEPPER_CROP = ModPlatform.getWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
    public static final Block WILD_PAPAYA_CROP = ModPlatform.getWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));

    //Crops
    public static final Block LIME_CROP = new LimeCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block LIME_SAPLING = new LimeSapling(BlockBehaviour.Properties.copy(ModBlocks.LIME_CROP).noCollission());
    public static final Block PEPPER_CROP = new PepperBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES));
    public static final Block PAPAYA_CROP = new PapayaCrop(BlockBehaviour.Properties.copy(Blocks.POTATOES));

    //Feast
    public static final Block CRAB_FRIED_RICE_FEAST = ModPlatform.getCrabFriedRice();
    public static final Block SOMTAM_FEAST = ModPlatform.getSomtamBlock();
    public static final Block SPICY_MINCED_MEAT_SALAD_FEAST = ModPlatform.getSpicyMincedPorkBlock();

    public static void init(){
        //Functional Block
        registerWithItem("mortar",MORTAR);

        //Crate
        registerWithItem("lime_crate",LIME_CRATE);
        registerWithItem("pepper_crate",PEPPER_CRATE);
        registerWithItem("unripe_papaya_crate",UNRIPE_PAPAYA_CRATE);
        registerWithItem("papaya_crate",PAPAYA_CRATE);

        //Sauce Bowl
        registerWithItem("sauce_bowl",SAUCE_BOWL);
        register("seafood_sauce_bowl",SEAFOOD_SAUCE_BOWL);
        register("fish_sauce_sauce_bowl",FISH_SAUCE_SAUCE_BOWL);
        register("honey_sauce_bowl",HONEY_SAUCE_BOWL);

        //Eggs
        registerWithItem("flower_crab_egg",CRAB_EGG);

        //Wild Crops
        registerWithItem("wild_pepper_crop",WILD_PEPPER_CROP);
        registerWithItem("wild_papaya_crop",WILD_PAPAYA_CROP);

        //Crops
        register("pepper_crop",PEPPER_CROP);
        register("lime_bush", LIME_CROP);
        registerWithItem("lime_sapling", LIME_SAPLING);
        register("papaya_crop",PAPAYA_CROP);

        //Feast
        registerWithItem("somtam_feast",SOMTAM_FEAST);
        registerWithItem("spicy_minced_meat_salad_feast", SPICY_MINCED_MEAT_SALAD_FEAST);
        registerWithItem("crab_fried_rice_feast", CRAB_FRIED_RICE_FEAST);
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

    public static class ModBlockEntityTypes{
        public static BlockEntityType<MortarBlockEntity> MORTAR_BLOCK_ENTITY = ModPlatform.buildBlockEntity(MortarBlockEntity::new,ModBlocks.MORTAR).build(null);

        public static void init(){
            ModPlatform.registerBlockEntity("mortar",MORTAR_BLOCK_ENTITY);
        }


        @FunctionalInterface
        public interface BlockEntitySupplier<T extends BlockEntity> {
            T create(BlockPos blockPos, BlockState blockState);
        }
    }



}
