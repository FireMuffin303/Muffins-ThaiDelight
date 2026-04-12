package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.forge.mixin.BlockFamiliesAccessor;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThaiDelightCraftRecipeProvider implements IRecipeProvider {
    public static final BlockFamily DURIAN_PLANKS = BlockFamiliesAccessor.familyBuilder(ModBlocks.DURIAN_PLANKS.get())
            .button(ModBlocks.DURIAN_BUTTON.get())
            .fence(ModBlocks.DURIAN_FENCE.get())
            .fenceGate(ModBlocks.DURIAN_FENCE_GATE.get())
            .pressurePlate(ModBlocks.DURIAN_PRESSURE_PLATE.get())
            .sign(ModBlocks.DURIAN_SIGN.get(),ModBlocks.DURIAN_WALL_SIGN.get())
            .slab(ModBlocks.DURIAN_SLAB.get())
            .stairs(ModBlocks.DURIAN_STAIRS.get())
            .door(ModBlocks.DURIAN_DOOR.get())
            .trapdoor(ModBlocks.DURIAN_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily COCONUT_PLANKS = BlockFamiliesAccessor.familyBuilder(ModBlocks.COCONUT_PLANKS.get())
            .button(ModBlocks.COCONUT_BUTTON.get())
            .fence(ModBlocks.COCONUT_FENCE.get())
            .fenceGate(ModBlocks.COCONUT_FENCE_GATE.get())
            .pressurePlate(ModBlocks.COCONUT_PRESSURE_PLATE.get())
            .sign(ModBlocks.COCONUT_SIGN.get(),ModBlocks.COCONUT_WALL_SIGN.get())
            .slab(ModBlocks.COCONUT_SLAB.get())
            .stairs(ModBlocks.COCONUT_STAIRS.get())
            .door(ModBlocks.COCONUT_DOOR.get())
            .trapdoor(ModBlocks.COCONUT_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily MANGO_PLANKS = BlockFamiliesAccessor.familyBuilder(ModBlocks.MANGO_PLANKS.get())
            .button(ModBlocks.MANGO_BUTTON.get())
            .fence(ModBlocks.MANGO_FENCE.get())
            .fenceGate(ModBlocks.MANGO_FENCE_GATE.get())
            .pressurePlate(ModBlocks.MANGO_PRESSURE_PLATE.get())
            .sign(ModBlocks.MANGO_SIGN.get(),ModBlocks.MANGO_WALL_SIGN.get())
            .slab(ModBlocks.MANGO_SLAB.get())
            .stairs(ModBlocks.MANGO_STAIRS.get())
            .door(ModBlocks.MANGO_DOOR.get())
            .trapdoor(ModBlocks.MANGO_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();


    @Override
    public void generate(Consumer<FinishedRecipe> exporter) {
        craft(exporter);
    }

    private void craft(Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.MORTAR.get())
                .define('A',Items.BRICK)
                .define('B',Items.STICK)
                .pattern("ABA")
                .pattern("AAA")
                .unlockedBy(IRecipeProvider.getHasName(Items.STICK),IRecipeProvider.has(Items.STICK))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModBlocks.MORTAR.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.SACK.get())
                .define('C', vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get())
                .pattern("CCC")
                .pattern("C C")
                .pattern("CCC")
                .unlockedBy(IRecipeProvider.getHasName(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()),IRecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.SACK.get())));

        bigPackingCraft(ModItems.LIME_CRATE.get(),1,ModItems.LIME.get(),exporter);
        bigPackingCraft(ModItems.PEPPER_CRATE.get(),1,ModItems.PEPPER.get(),exporter);
        bigPackingCraft(ModItems.RAW_PAPAYA_CRATE.get(),1,ModItems.RAW_PAPAYA.get(),exporter);
        bigPackingCraft(ModItems.PAPAYA_CRATE.get(),1,ModItems.PAPAYA.get(),exporter);
        bigPackingCraft(ModItems.MANGO_CRATE.get(),1,ModItems.MANGO.get(),exporter);
        bigPackingCraft(ModItems.HOLY_BASIL_CRATE.get(),1,ModItems.HOLY_BASIL.get(),exporter);
        bigPackingCraft(ModItems.BASIL_CRATE.get(),1,ModItems.BASIL.get(),exporter);
        bigPackingCraft(ModItems.BAMBOO_SHOOT_CRATE.get(),1,ModItems.BAMBOO_SHOOT.get(),exporter);
        bigPackingCraft(ModItems.BUTTERFLY_PEA_CRATE.get(),1,ModItems.BUTTERFLY_PEA.get(),exporter);

        bigPackingCraft(ModItems.DURIAN_PEEL_BLOCK.get(),1,ModItems.DURIAN_PEEL.get(),exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DURIAN_HELMET.get())
                .define('L',Items.LEATHER_HELMET)
                .define('D', ModItems.DURIAN_PEEL.get())
                .pattern(" D ")
                .pattern("DLD")
                .unlockedBy(IRecipeProvider.getHasName(ModItems.DURIAN_PEEL.get()),IRecipeProvider.has(ModItems.DURIAN_PEEL.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.DURIAN_HELMET.get())));

        craftWoodFamily(
                DURIAN_PLANKS,
                ModItems.DURIAN_LOG,
                ModItems.DURIAN_WOOD,
                ModItems.STRIPPED_DURIAN_LOG,
                ModItems.STRIPPED_DURIAN_WOOD,
                ModItems.DURIAN_HANGING_SIGN,
                ModItems.DURIAN_BOAT,
                ModItems.DURIAN_CHEST_BOAT,
                ModItems.DURIAN_CABINET,
                ModTags.DURIAN_LOGS_ITEM,
                exporter
        );

        bigPackingCraft(ModItems.COCONUT_LEAF_BLOCK.get(),1,ModItems.COCONUT_LEAF.get(),exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.COCONUT_LEAF_MAT.get(),3)
                .define('L', ModItems.COCONUT_LEAF.get())
                .pattern("LL")
                .unlockedBy(IRecipeProvider.getHasName(ModItems.COCONUT_LEAF.get()),IRecipeProvider.has(ModItems.COCONUT_LEAF.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.COCONUT_LEAF_MAT.get())));

        craftWoodFamily(
                COCONUT_PLANKS,
                ModItems.COCONUT_LOG,
                ModItems.COCONUT_WOOD,
                ModItems.STRIPPED_COCONUT_LOG,
                ModItems.STRIPPED_COCONUT_WOOD,
                ModItems.COCONUT_HANGING_SIGN,
                ModItems.COCONUT_BOAT,
                ModItems.COCONUT_CHEST_BOAT,
                ModItems.COCONUT_CABINET,
                ModTags.COCONUT_LOGS_ITEM,
                exporter
        );

        craftWoodFamily(
                MANGO_PLANKS,
                ModItems.MANGO_LOG,
                ModItems.MANGO_WOOD,
                ModItems.STRIPPED_MANGO_LOG,
                ModItems.STRIPPED_MANGO_WOOD,
                ModItems.MANGO_HANGING_SIGN,
                ModItems.MANGO_BOAT,
                ModItems.MANGO_CHEST_BOAT,
                ModItems.MANGO_CABINET,
                ModTags.MANGO_LOGS_ITEM,
                exporter
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PAPAYA_WOOD.get(), 3)
                .define('#', ModItems.PAPAYA_LOG.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", IRecipeProvider.has(ModItems.PAPAYA_LOG.get()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(ModItems.PAPAYA_WOOD.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.STRIPPED_PAPAYA_WOOD.get(), 3)
                .define('#', ModItems.STRIPPED_PAPAYA_LOG.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", IRecipeProvider.has(ModItems.STRIPPED_PAPAYA_LOG.get()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(ModItems.STRIPPED_PAPAYA_WOOD.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.MANGO_STICKY_RICE_FEAST.get(),1)
                .requires(ModTags.MANGO)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(Items.BOWL)
                .unlockedBy("has_mango",IRecipeProvider.has(ModTags.MANGO))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(ModItems.MANGO_STICKY_RICE_FEAST.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STIR_FRIED_NOODLE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get())
                .requires(Items.SUGAR).requires(Items.BOWL).requires(ModTags.LIME)
                .unlockedBy(IRecipeProvider.getHasName(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()),IRecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.STIR_FRIED_NOODLE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DURIAN_CAKE.get())
                .define('D', ModItems.DURIAN_PULP.get())
                .define('E', Items.EGG)
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("MMM")
                .pattern("SES")
                .pattern("DDD")
                .unlockedBy("has_milk",IRecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.DURIAN_CAKE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD,ModItems.MANGO_CHEESECAKE.get())
                .define('M',ModTags.MANGO)
                .define('C',ModTags.COMMON_MILKS)
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("CPC")
                .unlockedBy("has_milk",IRecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MANGO_CHEESECAKE.get())
                .define('C', ModItems.MANGO_CHEESECAKE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_mango_cheesecake_slice",IRecipeProvider.has(ModItems.MANGO_CHEESECAKE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE.get())+"_from_slice"));


        //--------- Coconut Pie ------------
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE.get())
                .define('C', ModItems.COCONUT_SLICE.get())
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("CCC")
                .pattern("MMM")
                .pattern("SPS")
                .unlockedBy("has_coconut_slice",IRecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.COCONUT_PIE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE.get())
                .define('C', ModItems.COCONUT_PIE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_coconut_pie_slice",IRecipeProvider.has(ModItems.COCONUT_PIE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.COCONUT_PIE.get())+"_from_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE.get())
                .requires(ModItems.COCONUT_PIE.get()).requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_coconut_pie",IRecipeProvider.has(ModItems.COCONUT_PIE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE.get())
                .define('C', ModItems.HONEY_COCONUT_PIE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_honey_coconut_pie_slice",IRecipeProvider.has(ModItems.HONEY_COCONUT_PIE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE.get())+"_from_slice"));
        //--------------------------------

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.COCONUT_MILK_ICE_CREAM.get())
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(Items.ICE).requires(Items.ICE)
                .requires(Items.BOWL)
                .unlockedBy("has_coconut_milk_bottle",IRecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.COCONUT_MILK_ICE_CREAM.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUTTERFLY_PEA_SEEDS.get(),3).requires(ModItems.BUTTERFLY_PEA.get())
                .unlockedBy(IRecipeProvider.getHasName(ModItems.BUTTERFLY_PEA.get()),IRecipeProvider.has(ModItems.BUTTERFLY_PEA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/butterfly_seeds_from_butterfly_pea"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.YELLOW_DYE).requires(ModBlocks.DURIAN_FLOWER.get())
                .unlockedBy(IRecipeProvider.getHasName(ModBlocks.DURIAN_FLOWER.get()),IRecipeProvider.has(ModBlocks.DURIAN_FLOWER.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/yellow_dye_from_durian_flower"));

        unPacking(ModItems.LIME,ModItems.LIME_CRATE,exporter);
        unPacking(ModItems.PEPPER,ModItems.PEPPER_CRATE,exporter);
        unPacking(ModItems.RAW_PAPAYA,ModItems.RAW_PAPAYA_CRATE,exporter);
        unPacking(ModItems.PAPAYA,ModItems.PAPAYA_CRATE,exporter);
        unPacking(ModItems.MANGO,ModItems.MANGO_CRATE,exporter);
        unPacking(ModItems.HOLY_BASIL,ModItems.HOLY_BASIL_CRATE,exporter);
        unPacking(ModItems.BASIL,ModItems.BASIL_CRATE,exporter);
        unPacking(ModItems.BAMBOO_SHOOT,ModItems.BAMBOO_SHOOT_CRATE,exporter);
        unPacking(ModItems.BUTTERFLY_PEA,ModItems.BUTTERFLY_PEA_CRATE,exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEPPER_SEED.get(),4)
                .requires(ModItems.PEPPER.get())
                .unlockedBy(IRecipeProvider.getHasName(ModItems.PEPPER.get()),IRecipeProvider.has(ModItems.PEPPER.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModItems.PEPPER_SEED.get())+"_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),4)
                .requires(ModItems.PAPAYA.get())
                .unlockedBy(IRecipeProvider.getHasName(ModItems.PAPAYA.get()),IRecipeProvider.has(ModItems.PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),4).requires(ModItems.RAW_PAPAYA.get())
                .unlockedBy(IRecipeProvider.getHasName(ModItems.RAW_PAPAYA.get()),IRecipeProvider.has(ModItems.RAW_PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_unripe_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),2).requires(ModItems.SLICED_PAPAYA.get())
                .unlockedBy(IRecipeProvider.getHasName(ModItems.SLICED_PAPAYA.get()),IRecipeProvider.has(ModItems.SLICED_PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_sliced_papaya_from_crafting"));


        //Salad
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .unlockedBy(IRecipeProvider.getHasName(Items.BOWL),IRecipeProvider.has(Items.BOWL))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())+"by_raw_papaya"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())
                .requires(ModTags.MANGO)
                .requires(Items.MELON)
                .requires(Items.MELON)
                .requires(ItemTags.FOX_FOOD)
                .requires(ItemTags.FOX_FOOD)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .requires(Items.BOWL)
                .unlockedBy(IRecipeProvider.getHasName(Items.BOWL),IRecipeProvider.has(Items.BOWL))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())+"_by_mango"));

    }


    private void bigPackingCraft(Item result, int resultAmount, ItemLike ingredient, Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount)
                .define('A',ingredient)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(IRecipeProvider.getHasName(ingredient),IRecipeProvider.has(ingredient))
                .save(exporter, ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(result)+"_from_crafting"));
    }

    private void unPacking(Supplier<Item> result, Supplier<Item> unpackedItem, Consumer<FinishedRecipe> exporter){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,result.get(),9)
                .requires(unpackedItem.get()).unlockedBy(IRecipeProvider.getHasName(unpackedItem.get()),IRecipeProvider.has(unpackedItem.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(result.get())+"_from_crafting"));
    }

    private void craftWoodFamily(BlockFamily blockFamily,
                                 Supplier<Item> log,
                                 Supplier<Item> wood,
                                 Supplier<Item> stripped_log,
                                 Supplier<Item> stripped_wood,
                                 Supplier<Item> hanging_sign,
                                 Supplier<Item> boat,
                                 Supplier<Item> chest_boat,
                                 Supplier<Item> cabinet,
                                 TagKey<Item> log_item_tag,
                                 Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood.get(), 3)
                .define('#', log.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", IRecipeProvider.has(log.get())).save(exporter,"crafting/"+IRecipeProvider.getItemName(wood.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stripped_wood.get(), 3)
                .define('#', stripped_log.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", IRecipeProvider.has(stripped_log.get())).save(exporter,"crafting/"+IRecipeProvider.getItemName(stripped_wood.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, blockFamily.getBaseBlock(), 4)
                .requires(log_item_tag)
                .group("planks")
                .unlockedBy("has_log", IRecipeProvider.has(log_item_tag))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.getBaseBlock()));


        IRecipeProvider.stairBuilder(blockFamily.get(BlockFamily.Variant.STAIRS),
                        Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.STAIRS))));

        IRecipeProvider.slabBuilder(RecipeCategory.BUILDING_BLOCKS,blockFamily.get(BlockFamily.Variant.SLAB),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.SLAB))));

        IRecipeProvider.fenceBuilder(blockFamily.get(BlockFamily.Variant.FENCE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.FENCE)));

        IRecipeProvider.fenceGateBuilder(blockFamily.get(BlockFamily.Variant.FENCE_GATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.FENCE_GATE)));

        IRecipeProvider.doorBuilder(blockFamily.get(BlockFamily.Variant.DOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.DOOR)));

        IRecipeProvider.trapdoorBuilder(blockFamily.get(BlockFamily.Variant.TRAPDOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.TRAPDOOR)));

        IRecipeProvider.pressurePlateBuilder(RecipeCategory.REDSTONE, blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE)));

        IRecipeProvider.buttonBuilder(blockFamily.get(BlockFamily.Variant.BUTTON),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.BUTTON)));

        IRecipeProvider.signBuilder(blockFamily.get(BlockFamily.Variant.SIGN),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(blockFamily.get(BlockFamily.Variant.SIGN)));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, hanging_sign.get(), 6)
                .group("hanging_sign")
                .define('#', stripped_log.get())
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", IRecipeProvider.has(stripped_log.get()))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(hanging_sign.get()));



        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat.get())
                .define('#', blockFamily.getBaseBlock())
                .pattern("# #").pattern("###")
                .group("boat")
                .unlockedBy("in_water", IRecipeProvider.insideOf(Blocks.WATER))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(boat.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chest_boat.get())
                .requires(Blocks.CHEST)
                .requires(boat.get())
                .group("chest_boat")
                .unlockedBy("has_boat", IRecipeProvider.has(ItemTags.BOATS))
                .save(exporter,"crafting/"+IRecipeProvider.getItemName(chest_boat.get()));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,cabinet.get())
                .pattern("WWW")
                .pattern("S S")
                .pattern("WWW")
                .define('W',blockFamily.getBaseBlock())
                .define('S',blockFamily.get(BlockFamily.Variant.SLAB))
                .unlockedBy(IRecipeProvider.getHasName(blockFamily.getBaseBlock()),IRecipeProvider.has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+IRecipeProvider.getItemName(cabinet.get())));
    }


}
