package net.firemuffin303.thaidelight.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static net.firemuffin303.thaidelight.common.registry.ModTags.*;


public class ModBlockTagDataGen extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.DURIAN_PLANKS.get(),ModBlocks.MANGO_PLANKS.get(),ModBlocks.COCONUT_PLANKS.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.DURIAN_STAIRS.get(),ModBlocks.MANGO_STAIRS.get(),ModBlocks.COCONUT_STAIRS.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.DURIAN_SLAB.get(),ModBlocks.MANGO_SLAB.get(),ModBlocks.COCONUT_SLAB.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.DURIAN_FENCE.get(),ModBlocks.MANGO_FENCE.get(),ModBlocks.COCONUT_FENCE.get());
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DURIAN_FENCE_GATE.get(),ModBlocks.MANGO_FENCE_GATE.get(),ModBlocks.COCONUT_FENCE_GATE.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.DURIAN_DOOR.get(),ModBlocks.MANGO_DOOR.get(),ModBlocks.COCONUT_DOOR.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.DURIAN_TRAPDOOR.get(),ModBlocks.MANGO_TRAPDOOR.get(),ModBlocks.COCONUT_TRAPDOOR.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.DURIAN_PRESSURE_PLATE.get(),ModBlocks.MANGO_PRESSURE_PLATE.get(),ModBlocks.COCONUT_PRESSURE_PLATE.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.DURIAN_BUTTON.get(),ModBlocks.MANGO_BUTTON.get(),ModBlocks.COCONUT_BUTTON.get());

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.DURIAN_SIGN.get(),ModBlocks.MANGO_SIGN.get(),ModBlocks.COCONUT_SIGN.get());
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.DURIAN_WALL_SIGN.get(),ModBlocks.MANGO_WALL_SIGN.get(),ModBlocks.COCONUT_WALL_SIGN.get());
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.DURIAN_HANGING_SIGN.get(),ModBlocks.MANGO_HANGING_SIGN.get(),ModBlocks.COCONUT_HANGING_SIGN.get());
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.DURIAN_WALL_HANGING_SIGN.get(),ModBlocks.MANGO_WALL_HANGING_SIGN.get(),ModBlocks.COCONUT_WALL_HANGING_SIGN.get());

        getOrCreateTagBuilder(BlockTags.CROPS).add(
                ModBlocks.BUDDING_PEPPER_CROP.get(),
                ModBlocks.PEPPER_CROP.get(),
                ModBlocks.LIME_PLANT.get(),
                ModBlocks.BUDDING_PAPAYA_FLOWER.get(),
                ModBlocks.PAPAYA.get(),
                ModBlocks.HANGING_DURIAN.get(),
                ModBlocks.HANGING_MANGO_BLOCK.get(),
                ModBlocks.BUDDING_COCONUT_LEAF.get(),
                ModBlocks.BASIL.get(),
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get(),
                ModBlocks.BUTTERFLY_PEA_BLOCK.get()
        );

        getOrCreateTagBuilder(BlockTags.CAULDRONS)
                .add(ModBlocks.FERMENTED_FISH_CAULDRON.get());

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.PEPPER_CRATE.get(),
                ModBlocks.PAPAYA_CRATE.get(),
                ModBlocks.LIME_CRATE.get(),
                ModBlocks.RAW_PAPAYA_CRATE.get(),
                ModBlocks.MANGO_CRATE.get(),
                ModBlocks.BASIL_CRATE.get(),
                ModBlocks.BAMBOO_SHOOT_CRATE.get(),
                ModBlocks.BUTTERFLY_PEA_CRATE.get(),
                ModBlocks.SMALL_DURIAN_BLOCK.get(),
                ModBlocks.DURIAN_BLOCK.get(),
                ModBlocks.DURIAN_PEEL_BLOCK.get(),
                ModBlocks.COCONUT.get(),
                ModBlocks.STRIPPED_COCONUT.get()
        );

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MORTAR.get());

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.DURIAN_BLOCK.get(),
                ModBlocks.COCONUT_LEAF_BLOCK.get()
        );

        getOrCreateTagBuilder(DURIAN_LOGS_BLOCK).add(
                ModBlocks.DURIAN_LOG.get(),
                ModBlocks.DURIAN_WOOD.get(),
                ModBlocks.STRIPPED_DURIAN_LOG.get(),
                ModBlocks.STRIPPED_DURIAN_WOOD.get()
        );
        getOrCreateTagBuilder(MANGO_LOGS_BLOCK).add(
                ModBlocks.MANGO_LOG.get(),
                ModBlocks.MANGO_WOOD.get(),
                ModBlocks.STRIPPED_MANGO_LOG.get(),
                ModBlocks.STRIPPED_MANGO_WOOD.get()
        );
        getOrCreateTagBuilder(COCONUT_LOGS_BLOCK).add(
                ModBlocks.COCONUT_LOG.get(),
                ModBlocks.COCONUT_WOOD.get(),
                ModBlocks.STRIPPED_COCONUT_LOG.get(),
                ModBlocks.STRIPPED_COCONUT_WOOD.get()
        );
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(PAPAYA_LOGS)
                .addTag(DURIAN_LOGS_BLOCK)
                .addTag(MANGO_LOGS_BLOCK)
                .addTag(COCONUT_LOGS_BLOCK);


        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(
                        ModBlocks.PAPAYA_LEAVES.get(),
                        ModBlocks.DURIAN_LEAVES.get(),
                        ModBlocks.MANGO_LEAVES.get(),
                        ModBlocks.COCONUT_LEAF.get(),
                        ModBlocks.COCONUT_LEAF_END.get()
                );

        getOrCreateTagBuilder(ModTags.PAPAYA_LOGS)
                .add(ModBlocks.PAPAYA_LOG.get())
                .add(ModBlocks.PAPAYA_WOOD.get())
                .add(ModBlocks.STRIPPED_PAPAYA_LOG.get())
                .add(ModBlocks.STRIPPED_PAPAYA_WOOD.get());

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.DURIAN_FLOWER.get(),
                ModBlocks.BUTTERFLY_PEA_WALL.get(),
                ModBlocks.BUDDING_PAPAYA_FLOWER.get(),
                ModBlocks.PAPAYA_FLOWER.get(),
                ModBlocks.WALL_PAPAYA_FLOWER.get()
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
                ModBlocks.SMALL_DURIAN_BLOCK.get(),
                ModBlocks.HANGING_MANGO_BLOCK.get()).addTag(SACK_HEAVY_CATCHABLE);

        getOrCreateTagBuilder(SACK_HEAVY_CATCHABLE).add(
                ModBlocks.DURIAN_BLOCK.get(),
                ModBlocks.HANGING_DURIAN.get(),
                ModBlocks.COCONUT.get()
        );

        getOrCreateTagBuilder(BlockTags.CANDLE_CAKES).add(
                ModBlocks.CANDLE_DURIAN_CAKE.get(),
                ModBlocks.WHITE_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.LIGHT_GRAY_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.GRAY_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.BLACK_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.BROWN_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.RED_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.ORANGE_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.YELLOW_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.LIME_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.GREEN_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.CYAN_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.LIGHT_BLUE_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.BLUE_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.MAGENTA_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.PURPLE_CANDLE_DURIAN_CAKE.get(),
                ModBlocks.PINK_CANDLE_DURIAN_CAKE.get()
        );

        /*
        getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS).add(
                ModBlocks.WILD_BASIL.get(),
                ModBlocks.WILD_PEPPER_CROP.get()
        );

         */

        getOrCreateTagBuilder(SPRING_CROPS).add(
                ModBlocks.LIME_SAPLING.get(),
                ModBlocks.LIME_PLANT.get(),
                ModBlocks.PEPPER_CROP.get(),
                ModBlocks.BUDDING_PEPPER_CROP.get(),
                ModBlocks.DURIAN_SAPLING.get(),
                ModBlocks.PAPAYA_CROP.get(),
                ModBlocks.PAPAYA_SAPLING.get(),
                ModBlocks.BASIL.get(),
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get(),
                ModBlocks.BUTTERFLY_PEA_BLOCK.get()
        );

        getOrCreateTagBuilder(SUMMER_CROPS).add(
                ModBlocks.LIME_SAPLING.get(),
                ModBlocks.LIME_PLANT.get(),
                ModBlocks.PEPPER_CROP.get(),
                ModBlocks.BUDDING_PEPPER_CROP.get(),
                ModBlocks.DURIAN_SAPLING.get(),
                ModBlocks.MANGO_SAPLING.get(),
                ModBlocks.HANGING_MANGO_BLOCK.get(),
                ModBlocks.COCONUT_SAPLING.get(),
                ModBlocks.COCONUT_SAPLING_CROP.get(),
                ModBlocks.PAPAYA_CROP.get(),
                ModBlocks.PAPAYA_SAPLING.get(),
                ModBlocks.BASIL.get(),
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get(),
                ModBlocks.BUTTERFLY_PEA_BLOCK.get()
                );

    }
}
