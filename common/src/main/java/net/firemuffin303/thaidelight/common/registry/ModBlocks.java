package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
import net.firemuffin303.thaidelight.common.block.crops.LimeCrop;
import net.firemuffin303.thaidelight.common.block.crops.PapayaCrop;
import net.firemuffin303.thaidelight.common.block.crops.PepperBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.SeafoodCauldronBlock;
import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.firemuffin303.thaidelight.common.fluid.ModLiquid;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModBlocks {
    public static final Block CRAB_EGG = new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN));
    public static final Block SEAFOOD = new ModLiquid(ModFluid.SEAFOOD,BlockBehaviour.Properties.copy(Blocks.WATER));
    public static final Block SEAFOOD_CAULDRON = new SeafoodCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON));

    //Crops
    public static final Block LIME_BUSH = new LimeCrop(BlockBehaviour.Properties.copy(Blocks.POTATOES));
    public static final Block PEPPER_CROP = new PepperBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES));
    public static final Block PAPAYA_CROP = new PapayaCrop(BlockBehaviour.Properties.copy(Blocks.POTATOES));

    public static final Block MORTAR = new MortarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion());


    public static void init(){
        registerWithItem("mortar",MORTAR);
        register("pepper_crop",PEPPER_CROP);
        registerWithItem("crab_egg",CRAB_EGG);
        register("seafood",SEAFOOD);
        register("seafood_cauldron",SEAFOOD_CAULDRON);
        register("lime_bush",LIME_BUSH);
        register("papaya_crop",PAPAYA_CROP);
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
