package net.firemuffin303.muffinsthaidelightfabric.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static net.firemuffin303.muffinsthaidelightfabric.registry.ModTags.*;

public class ModBlockTagDataGen extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.DURIAN_PLANKS,ModBlocks.MANGO_PLANKS,ModBlocks.COCONUT_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.DURIAN_STAIRS,ModBlocks.MANGO_STAIRS,ModBlocks.COCONUT_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.DURIAN_SLAB,ModBlocks.MANGO_SLAB,ModBlocks.COCONUT_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.DURIAN_FENCE,ModBlocks.MANGO_FENCE,ModBlocks.COCONUT_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DURIAN_FENCE_GATE,ModBlocks.MANGO_FENCE_GATE,ModBlocks.COCONUT_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.DURIAN_DOOR,ModBlocks.MANGO_DOOR,ModBlocks.COCONUT_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.DURIAN_TRAPDOOR,ModBlocks.MANGO_TRAPDOOR,ModBlocks.COCONUT_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.DURIAN_PRESSURE_PLATE,ModBlocks.MANGO_PRESSURE_PLATE,ModBlocks.COCONUT_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.DURIAN_BUTTON,ModBlocks.MANGO_BUTTON,ModBlocks.COCONUT_BUTTON);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.DURIAN_SIGN,ModBlocks.MANGO_SIGN,ModBlocks.COCONUT_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.DURIAN_WALL_SIGN,ModBlocks.MANGO_WALL_SIGN,ModBlocks.COCONUT_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.DURIAN_HANGING_SIGN,ModBlocks.MANGO_HANGING_SIGN,ModBlocks.COCONUT_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.DURIAN_WALL_HANGING_SIGN,ModBlocks.MANGO_WALL_HANGING_SIGN,ModBlocks.COCONUT_WALL_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.CROPS).add(
                ModBlocks.BUDDING_PEPPER_CROP,
                ModBlocks.PEPPER_CROP,
                ModBlocks.LIME_PLANT,
                ModBlocks.BUDDING_PAPAYA_FLOWER,
                ModBlocks.PAPAYA,
                ModBlocks.HANGING_DURIAN,
                ModBlocks.HANGING_MANGO_BLOCK,
                ModBlocks.BUDDING_COCONUT_LEAF,
                ModBlocks.HOLY_BASIL,
                ModBlocks.BASIL,
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
                ModBlocks.BUTTERFLY_PEA_BLOCK
        );

        getOrCreateTagBuilder(BlockTags.CAULDRONS)
                .add(ModBlocks.FERMENTED_FISH_CAULDRON);

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.PEPPER_CRATE,
                ModBlocks.PAPAYA_CRATE,
                ModBlocks.LIME_CRATE,
                ModBlocks.RAW_PAPAYA_CRATE,
                ModBlocks.MANGO_CRATE,
                ModBlocks.HOLY_BASIL_CRATE,
                ModBlocks.BASIL_CRATE,
                ModBlocks.BAMBOO_SHOOT_CRATE,
                ModBlocks.BUTTERFLY_PEA_CRATE,
                ModBlocks.SMALL_DURIAN_BLOCK,
                ModBlocks.DURIAN_BLOCK,
                ModBlocks.DURIAN_PEEL_BLOCK,
                ModBlocks.COCONUT,
                ModBlocks.STRIPPED_COCONUT
        );

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MORTAR);

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.DURIAN_BLOCK,
                ModBlocks.COCONUT_LEAF_BLOCK
        );

        getOrCreateTagBuilder(DURIAN_LOGS_BLOCK).add(
                ModBlocks.DURIAN_LOG,
                ModBlocks.DURIAN_WOOD,
                ModBlocks.STRIPPED_DURIAN_LOG,
                ModBlocks.STRIPPED_DURIAN_WOOD
        );
        getOrCreateTagBuilder(MANGO_LOGS_BLOCK).add(
                ModBlocks.MANGO_LOG,
                ModBlocks.MANGO_WOOD,
                ModBlocks.STRIPPED_MANGO_LOG,
                ModBlocks.STRIPPED_MANGO_WOOD
        );
        getOrCreateTagBuilder(COCONUT_LOGS_BLOCK).add(
                ModBlocks.COCONUT_LOG,
                ModBlocks.COCONUT_WOOD,
                ModBlocks.STRIPPED_COCONUT_LOG,
                ModBlocks.STRIPPED_COCONUT_WOOD
        );
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(PAPAYA_LOGS)
                .addTag(DURIAN_LOGS_BLOCK)
                .addTag(MANGO_LOGS_BLOCK)
                .addTag(COCONUT_LOGS_BLOCK);


        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(
                        ModBlocks.PAPAYA_LEAVES,
                        ModBlocks.DURIAN_LEAVES,
                        ModBlocks.MANGO_LEAVES,
                        ModBlocks.COCONUT_LEAF,
                        ModBlocks.COCONUT_LEAF_END
                );

        getOrCreateTagBuilder(ModTags.PAPAYA_LOGS)
                .add(ModBlocks.PAPAYA_LOG)
                .add(ModBlocks.PAPAYA_WOOD)
                .add(ModBlocks.STRIPPED_PAPAYA_LOG)
                .add(ModBlocks.STRIPPED_PAPAYA_WOOD);

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.DURIAN_FLOWER,
                ModBlocks.BUTTERFLY_PEA_WALL,
                ModBlocks.BUDDING_PAPAYA_FLOWER,
                ModBlocks.PAPAYA_FLOWER,
                ModBlocks.WALL_PAPAYA_FLOWER
        );

        getOrCreateTagBuilder(FLOWER_CRAB_SPAWNABLE_ON).add(
                Blocks.GRASS_BLOCK,
                Blocks.MUD,
                Blocks.MANGROVE_ROOTS,
                Blocks.MUDDY_MANGROVE_ROOTS,
                Blocks.CLAY,
                Blocks.SAND,
                Blocks.SUSPICIOUS_SAND
        );

        getOrCreateTagBuilder(SACK_CATCHABLE).add(
                ModBlocks.SMALL_DURIAN_BLOCK,
                ModBlocks.HANGING_MANGO_BLOCK).addTag(SACK_HEAVY_CATCHABLE);

        getOrCreateTagBuilder(SACK_HEAVY_CATCHABLE).add(
                ModBlocks.DURIAN_BLOCK,
                ModBlocks.HANGING_DURIAN,
                ModBlocks.COCONUT
        );

        getOrCreateTagBuilder(BlockTags.CANDLE_CAKES).add(
                ModBlocks.CANDLE_DURIAN_CAKE,
                ModBlocks.WHITE_CANDLE_DURIAN_CAKE,
                ModBlocks.LIGHT_GRAY_CANDLE_DURIAN_CAKE,
                ModBlocks.GRAY_CANDLE_DURIAN_CAKE,
                ModBlocks.BLACK_CANDLE_DURIAN_CAKE,
                ModBlocks.BROWN_CANDLE_DURIAN_CAKE,
                ModBlocks.RED_CANDLE_DURIAN_CAKE,
                ModBlocks.ORANGE_CANDLE_DURIAN_CAKE,
                ModBlocks.YELLOW_CANDLE_DURIAN_CAKE,
                ModBlocks.LIME_CANDLE_DURIAN_CAKE,
                ModBlocks.GREEN_CANDLE_DURIAN_CAKE,
                ModBlocks.CYAN_CANDLE_DURIAN_CAKE,
                ModBlocks.LIGHT_BLUE_CANDLE_DURIAN_CAKE,
                ModBlocks.BLUE_CANDLE_DURIAN_CAKE,
                ModBlocks.MAGENTA_CANDLE_DURIAN_CAKE,
                ModBlocks.PURPLE_CANDLE_DURIAN_CAKE,
                ModBlocks.PINK_CANDLE_DURIAN_CAKE
        );

        getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS).add(
                ModBlocks.WILD_BASIL,
                ModBlocks.WILD_HOLY_BASIL,
                ModBlocks.WILD_PEPPER_CROP
        );

        getOrCreateTagBuilder(SPRING_CROPS).add(
                ModBlocks.LIME_SAPLING,
                ModBlocks.LIME_PLANT,
                ModBlocks.PEPPER_CROP,
                ModBlocks.BUDDING_PEPPER_CROP,
                ModBlocks.DURIAN_SAPLING,
                ModBlocks.PAPAYA_CROP,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.HOLY_BASIL,
                ModBlocks.BASIL,
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
                ModBlocks.BUTTERFLY_PEA_BLOCK
        );

        getOrCreateTagBuilder(SUMMER_CROPS).add(
                ModBlocks.LIME_SAPLING,
                ModBlocks.LIME_PLANT,
                ModBlocks.PEPPER_CROP,
                ModBlocks.BUDDING_PEPPER_CROP,
                ModBlocks.DURIAN_SAPLING,
                ModBlocks.MANGO_SAPLING,
                ModBlocks.HANGING_MANGO_BLOCK,
                ModBlocks.COCONUT_SAPLING,
                ModBlocks.COCONUT_SAPLING_CROP,
                ModBlocks.PAPAYA_CROP,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.HOLY_BASIL,
                ModBlocks.BASIL,
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
                ModBlocks.BUTTERFLY_PEA_BLOCK
                );

    }
}
