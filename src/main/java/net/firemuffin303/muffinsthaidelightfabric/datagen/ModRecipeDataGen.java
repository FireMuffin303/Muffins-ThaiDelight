package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.muffinsthaidelightfabric.datagen.builder.CookingPotRecipeBuilder;
import net.firemuffin303.muffinsthaidelightfabric.datagen.builder.CuttingBoardRecipeBuilder;
import net.firemuffin303.muffinsthaidelightfabric.datagen.builder.MortarRecipeBuilder;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;

import java.util.function.Consumer;

public class ModRecipeDataGen extends FabricRecipeProvider {
    public ModRecipeDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        craft(exporter);
        furnace(exporter);
        smithing(exporter);
        cook(ModItems.CRAB_MEAT, ModItems.COOKED_CRAB_MEAT, 0.35f, 200, exporter);
        cook(ModItems.DRAGONFLY, ModItems.COOKED_DRAGONFLY, 0.35f, 200, exporter);
        cook(ModItems.DURIAN_PULP,ModItems.FRIED_DURIAN,0.35f,150,exporter);

        mortar(exporter);
        cookingPot(exporter);
        cuttingBoard(exporter);
    }

    private void cook(ItemLike ingredient, Item result, float exp, int cookTicks, Consumer<FinishedRecipe> exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelight.modid( "smelting/"+getItemName(result) + "_from_smelting" ));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks / 2)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelight.modid("cooking/"+getItemName(result) + "_from_cooking"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks * 3)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelight.modid("campfire/"+getItemName(result) + "_from_campfire"));
    }

    private void craft(Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.MORTAR)
                .define('A',Items.BRICK)
                .define('B',Items.STICK)
                .pattern("ABA")
                .pattern("AAA")
                .unlockedBy(getHasName(Items.STICK),has(Items.STICK))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModBlocks.MORTAR)));


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.SACK)
                .define('S', Items.STRING)
                .define('C', vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get())
                .pattern(" S ")
                .pattern("C C")
                .pattern("CCC")
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()),has(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModItems.SACK)));

        bigPackingCraft(ModItems.LIME_CRATE,1,ModItems.LIME,exporter);
        bigPackingCraft(ModItems.PEPPER_CRATE,1,ModItems.PEPPER,exporter);
        bigPackingCraft(ModItems.RAW_PAPAYA_CRATE,1,ModItems.RAW_PAPAYA,exporter);
        bigPackingCraft(ModItems.PAPAYA_CRATE,1,ModItems.PAPAYA,exporter);
        bigPackingCraft(ModItems.MANGO_CRATE,1,ModItems.MANGO,exporter);
        bigPackingCraft(ModItems.HOLY_BASIL_CRATE,1,ModItems.HOLY_BASIL,exporter);
        bigPackingCraft(ModItems.BASIL_CRATE,1,ModItems.BASIL,exporter);
        bigPackingCraft(ModItems.BAMBOO_SHOOT_CRATE,1,ModItems.BAMBOO_SHOOT,exporter);
        bigPackingCraft(ModItems.BUTTERFLY_PEA_CRATE,1,ModItems.BUTTERFLY_PEA,exporter);

        bigPackingCraft(ModItems.DURIAN_PEEL_BLOCK,1,ModItems.DURIAN_PEEL,exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DURIAN_HELMET)
                .define('L',Items.LEATHER_HELMET)
                .define('D', ModItems.DURIAN_PEEL)
                .pattern(" D ")
                .pattern("DLD")
                .unlockedBy(getHasName(ModItems.DURIAN_PEEL),has(ModItems.DURIAN_PEEL))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModItems.DURIAN_HELMET)));

        craftWoodFamily(
                ModelDataGen.DURIAN_PLANKS,
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

        bigPackingCraft(ModItems.COCONUT_LEAF_BLOCK,1,ModItems.COCONUT_LEAF,exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.COCONUT_LEAF_MAT,3)
                .define('L', ModItems.COCONUT_LEAF)
                .pattern("LL")
                .unlockedBy(getHasName(ModItems.COCONUT_LEAF),has(ModItems.COCONUT_LEAF))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModItems.COCONUT_LEAF_MAT)));

        craftWoodFamily(
                ModelDataGen.COCONUT_PLANKS,
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
                ModelDataGen.MANGO_PLANKS,
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

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PAPAYA_WOOD, 3)
                .define('#', ModItems.PAPAYA_LOG)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(ModItems.PAPAYA_LOG))
                .save(exporter,"crafting/"+getItemName(ModItems.PAPAYA_WOOD));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.STRIPPED_PAPAYA_WOOD, 3)
                .define('#', ModItems.STRIPPED_PAPAYA_LOG)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(ModItems.STRIPPED_PAPAYA_LOG))
                .save(exporter,"crafting/"+getItemName(ModItems.STRIPPED_PAPAYA_WOOD));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.MANGO_STICKY_RICE_FEAST,1)
                .requires(ModTags.MANGO)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.BOWL)
                .unlockedBy("has_mango",RecipeProvider.has(ModTags.MANGO))
                .save(exporter,"crafting/"+RecipeProvider.getItemName(ModItems.MANGO_STICKY_RICE_FEAST));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STIR_FRIED_NOODLE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get())
                .requires(Items.SUGAR).requires(Items.BOWL).requires(ModTags.LIME)
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()),has(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModItems.STIR_FRIED_NOODLE)));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DURIAN_CAKE)
                .define('D', ModItems.DURIAN_PULP)
                .define('E', Items.EGG)
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("MMM")
                .pattern("SES")
                .pattern("DDD")
                .unlockedBy("has_milk",RecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.DURIAN_CAKE)));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD,ModItems.MANGO_CHEESECAKE)
                .define('M',ModTags.MANGO)
                .define('C',ModTags.COMMON_MILKS)
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("CPC")
                .unlockedBy("has_milk",RecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE)));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MANGO_CHEESECAKE)
                .define('C', ModItems.MANGO_CHEESECAKE_SLICE)
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_mango_cheesecake_slice",RecipeProvider.has(ModItems.MANGO_CHEESECAKE_SLICE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE)+"_from_slice"));


        //--------- Coconut Pie ------------
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE)
                .define('C', ModItems.COCONUT_SLICE)
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("CCC")
                .pattern("MMM")
                .pattern("SPS")
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_PIE)));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE)
                .define('C', ModItems.COCONUT_PIE_SLICE)
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_coconut_pie_slice",RecipeProvider.has(ModItems.COCONUT_PIE_SLICE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_PIE)+"_from_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE)
                .requires(ModItems.COCONUT_PIE).requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_coconut_pie",RecipeProvider.has(ModItems.COCONUT_PIE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE)));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE)
                .define('C', ModItems.HONEY_COCONUT_PIE_SLICE)
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_honey_coconut_pie_slice",RecipeProvider.has(ModItems.HONEY_COCONUT_PIE_SLICE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE)+"_from_slice"));
        //--------------------------------

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.COCONUT_MILK_ICE_CREAM)
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .requires(Items.ICE).requires(Items.ICE)
                .requires(Items.BOWL)
                .unlockedBy("has_coconut_milk_bottle",RecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE))
                .save(exporter,ThaiDelight.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_MILK_ICE_CREAM)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUTTERFLY_PEA_SEEDS,3).requires(ModItems.BUTTERFLY_PEA)
                .unlockedBy(getHasName(ModItems.BUTTERFLY_PEA),has(ModItems.BUTTERFLY_PEA))
                .save(exporter,ThaiDelight.modid("crafting/butterfly_seeds_from_butterfly_pea"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.YELLOW_DYE).requires(ModBlocks.DURIAN_FLOWER)
                .unlockedBy(getHasName(ModBlocks.DURIAN_FLOWER),has(ModBlocks.DURIAN_FLOWER))
                .save(exporter,ThaiDelight.modid("crafting/yellow_dye_from_durian_flower"));

        unPacking(ModItems.LIME,ModItems.LIME_CRATE,exporter);
        unPacking(ModItems.PEPPER,ModItems.PEPPER_CRATE,exporter);
        unPacking(ModItems.RAW_PAPAYA,ModItems.RAW_PAPAYA_CRATE,exporter);
        unPacking(ModItems.PAPAYA,ModItems.PAPAYA_CRATE,exporter);
        unPacking(ModItems.MANGO,ModItems.MANGO_CRATE,exporter);
        unPacking(ModItems.HOLY_BASIL,ModItems.HOLY_BASIL_CRATE,exporter);
        unPacking(ModItems.BASIL,ModItems.BASIL_CRATE,exporter);
        unPacking(ModItems.BAMBOO_SHOOT,ModItems.BAMBOO_SHOOT_CRATE,exporter);
        unPacking(ModItems.BUTTERFLY_PEA,ModItems.BUTTERFLY_PEA_CRATE,exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEPPER_SEED,4)
                .requires(ModItems.PEPPER)
                .unlockedBy(getHasName(ModItems.PEPPER),has(ModItems.PEPPER))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModItems.PEPPER_SEED)+"_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,4)
                .requires(ModItems.PAPAYA)
                .unlockedBy(getHasName(ModItems.PAPAYA),has(ModItems.PAPAYA))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,4).requires(ModItems.RAW_PAPAYA)
                .unlockedBy(getHasName(ModItems.RAW_PAPAYA),has(ModItems.RAW_PAPAYA))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_unripe_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,2).requires(ModItems.SLICED_PAPAYA)
                .unlockedBy(getHasName(ModItems.SLICED_PAPAYA),has(ModItems.SLICED_PAPAYA))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_sliced_papaya_from_crafting"));


        //Salad
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.BOWL),has(Items.BOWL))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())+"by_raw_papaya"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())
                .requires(ModTags.MANGO)
                .requires(Items.MELON)
                .requires(Items.MELON)
                .requires(ItemTags.FOX_FOOD)
                .requires(ItemTags.FOX_FOOD)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.BOWL),has(Items.BOWL))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())+"_by_mango"));

    }

    private void furnace(Consumer<FinishedRecipe> exporter){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.DURIAN_PEEL),RecipeCategory.MISC,Items.CHARCOAL,0.15f,200)
                .unlockedBy("has_durian_peel",RecipeProvider.has(ModItems.DURIAN_PEEL))
                .save(exporter,ThaiDelight.modid("smelting/charcoal_from_durian_peel"));
    }

    private void smithing(Consumer<FinishedRecipe> exporter){
        //SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),Ingredient.of(ModItems.DIAMOND_PASTLE),Ingredient.of(Items.NETHERITE_INGOT),RecipeCategory.TOOLS,ModItems.NETHERITE_PASTLE).unlocks(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(exporter,"smithing/"+getItemName(ModItems.NETHERITE_PASTLE)+"_from_smithing");
    }

    private void bigPackingCraft(Item result,int resultAmount,ItemLike ingredient,Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount)
                .define('A',ingredient)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(result)+"_from_crafting"));
    }

    private void unPacking(Item result,Item unpackedItem,Consumer<FinishedRecipe> exporter){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,result,9)
                .requires(unpackedItem).unlockedBy(getHasName(unpackedItem),has(unpackedItem))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(result)+"_from_crafting"));
    }

    private void craftWoodFamily(BlockFamily blockFamily,
                                 Item log,
                                 Item wood,
                                 Item stripped_log,
                                 Item stripped_wood,
                                 Item hanging_sign,
                                 Item boat,
                                 Item chest_boat,
                                 Item cabinet,
                                 TagKey<Item> log_item_tag,
                                 Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(log)).save(exporter,"crafting/"+getItemName(wood));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stripped_wood, 3)
                .define('#', stripped_log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(stripped_log)).save(exporter,"crafting/"+getItemName(stripped_wood));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, blockFamily.getBaseBlock(), 4)
                .requires(log_item_tag)
                .group("planks")
                .unlockedBy("has_log", RecipeProvider.has(log_item_tag))
                .save(exporter,"crafting/"+getItemName(blockFamily.getBaseBlock()));


        RecipeProvider.stairBuilder(blockFamily.get(BlockFamily.Variant.STAIRS),
                Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.STAIRS))));

        RecipeProvider.slabBuilder(RecipeCategory.BUILDING_BLOCKS,blockFamily.get(BlockFamily.Variant.SLAB),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.SLAB))));

        RecipeProvider.fenceBuilder(blockFamily.get(BlockFamily.Variant.FENCE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.FENCE)));

        RecipeProvider.fenceGateBuilder(blockFamily.get(BlockFamily.Variant.FENCE_GATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.FENCE_GATE)));

        RecipeProvider.doorBuilder(blockFamily.get(BlockFamily.Variant.DOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.DOOR)));

        RecipeProvider.trapdoorBuilder(blockFamily.get(BlockFamily.Variant.TRAPDOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.TRAPDOOR)));

        RecipeProvider.pressurePlateBuilder(RecipeCategory.REDSTONE, blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE)));

        RecipeProvider.buttonBuilder(blockFamily.get(BlockFamily.Variant.BUTTON),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.BUTTON)));

        RecipeProvider.signBuilder(blockFamily.get(BlockFamily.Variant.SIGN),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,"crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.SIGN)));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, hanging_sign, 6)
                .group("hanging_sign")
                .define('#', stripped_log)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", RecipeProvider.has(stripped_log))
                .save(exporter,"crafting/"+getItemName(hanging_sign));



        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat)
                .define('#', blockFamily.getBaseBlock())
                .pattern("# #").pattern("###")
                .group("boat")
                .unlockedBy("in_water", RecipeProvider.insideOf(Blocks.WATER))
                .save(exporter,"crafting/"+getItemName(boat));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chest_boat)
                .requires(Blocks.CHEST)
                .requires(boat)
                .group("chest_boat")
                .unlockedBy("has_boat", RecipeProvider.has(ItemTags.BOATS))
                .save(exporter,"crafting/"+getItemName(chest_boat));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,cabinet)
                .pattern("WWW")
                .pattern("S S")
                .pattern("WWW")
                .define('W',blockFamily.getBaseBlock())
                .define('S',blockFamily.get(BlockFamily.Variant.SLAB))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelight.modid("crafting/"+getItemName(cabinet)));
    }

    private void mortar(Consumer<FinishedRecipe> exporter){
        MortarRecipeBuilder.mortar(ModBlocks.SOMTAM_FEAST)
                .requires(ModItems.PEPPER)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(ModItems.FERMENTED_FISH)
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(getHasName(ModItems.FERMENTED_FISH),has(ModItems.FERMENTED_FISH))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(ModBlocks.SOMTAM_FEAST)));

        MortarRecipeBuilder.mortar(ModItems.PESTO_SAUCE)
                .requires(ModItems.BASIL)
                .requires(ModItems.BASIL)
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(getHasName(ModItems.BASIL),has(ModItems.BASIL))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(ModItems.PESTO_SAUCE)));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,4).requires(Items.BONE,1)
                .unlockedBy(getHasName(Items.BONE),has(Items.BONE))
                .group("bone_meal")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BONE_MEAL)+"_by_bone"));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,2)
                .requires(Items.NAUTILUS_SHELL,1)
                .group("bone_meal")
                .unlockedBy(getHasName(Items.NAUTILUS_SHELL),has(Items.NAUTILUS_SHELL))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BONE_MEAL)+"_by_nautilus_shell"));

        MortarRecipeBuilder.mortar(Items.BLAZE_POWDER,3)
                .requires(Items.BLAZE_ROD,1)
                .unlockedBy(getHasName(Items.BLAZE_ROD),has(Items.BLAZE_ROD))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLAZE_POWDER)+"_by_blaze_rod"));

        MortarRecipeBuilder.mortar(Items.SUGAR,2)
                .requires(Items.SUGAR_CANE,1)
                .unlockedBy(getHasName(Items.SUGAR_CANE),has(Items.SUGAR_CANE))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.SUGAR)+"_by_sugar_cane"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.COAL,1)
                .unlockedBy(getHasName(Items.COAL),has(Items.COAL))
                .group("black_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_coal"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.CHARCOAL,1)
                .unlockedBy(getHasName(Items.CHARCOAL),has(Items.CHARCOAL))
                .group("black_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_charcoal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2)
                .requires(Items.BONE_MEAL,1)
                .unlockedBy(getHasName(Items.BONE_MEAL),has(Items.BONE_MEAL))
                .group("white_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.WHITE_DYE)+"_by_bone_meal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2).requires(Items.LILY_OF_THE_VALLEY,1)
                .unlockedBy(getHasName(Items.LILY_OF_THE_VALLEY),has(Items.LILY_OF_THE_VALLEY))
                .group("white_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.WHITE_DYE)+"_by_lily_of_the_valley"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.OXEYE_DAISY,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.OXEYE_DAISY),has(Items.OXEYE_DAISY))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_oxeye_daisy"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.AZURE_BLUET,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.AZURE_BLUET),has(Items.AZURE_BLUET))
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_azure_bluet"));
        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.WHITE_TULIP,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.WHITE_TULIP),has(Items.WHITE_TULIP))
                .save(exporter, ThaiDelight.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_white_tulip"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.INK_SAC,1)
                .unlockedBy(getHasName(Items.INK_SAC),has(Items.INK_SAC))
                .group("black_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_ink_sac"));
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.WITHER_ROSE,1)
                .unlockedBy(getHasName(Items.WITHER_ROSE),has(Items.WITHER_ROSE))
                .group("black_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_wither_rose"));

        MortarRecipeBuilder.mortar(Items.BROWN_DYE,2).requires(Items.COCOA_BEANS,1)
                .unlockedBy(getHasName(Items.COCOA_BEANS),has(Items.COCOA_BEANS))
                .group("brown_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BROWN_DYE)+"_by_cocoa"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.POPPY,1)
                .unlockedBy(getHasName(Items.POPPY),has(Items.POPPY))
                .group("red_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_poppy"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.BEETROOT,1)
                .unlockedBy(getHasName(Items.BEETROOT),has(Items.BEETROOT))
                .group("red_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_beetroot"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.RED_TULIP,1)
                .unlockedBy(getHasName(Items.RED_TULIP),has(Items.RED_TULIP))
                .group("red_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_red_tulip"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.ROSE_BUSH,1)
                .unlockedBy(getHasName(Items.ROSE_BUSH),has(Items.ROSE_BUSH))
                .group("red_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_rose_bush"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.TORCHFLOWER,1)
                .unlockedBy(getHasName(Items.TORCHFLOWER),has(Items.TORCHFLOWER))
                .group("orange_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.ORANGE_DYE)+"_by_torchflower"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.ORANGE_TULIP,1)
                .unlockedBy(getHasName(Items.ORANGE_TULIP),has(Items.ORANGE_TULIP))
                .group("orange_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.ORANGE_DYE)+"_by_orange_tulip"));

        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,2).requires(Items.DANDELION,1)
                .unlockedBy(getHasName(Items.DANDELION),has(Items.DANDELION))
                .group("yellow_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.YELLOW_DYE)+"_by_dandelion"));
        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,3).requires(Items.SUNFLOWER,1)
                .unlockedBy(getHasName(Items.SUNFLOWER),has(Items.SUNFLOWER))
                .group("yellow_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.YELLOW_DYE)+"_by_sunflower"));

        MortarRecipeBuilder.mortar(Items.CYAN_DYE,3).requires(Items.PITCHER_PLANT,1)
                .unlockedBy(getHasName(Items.PITCHER_PLANT),has(Items.PITCHER_PLANT))
                .group("cyan_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.CYAN_DYE)+"_by_pitcher_plant"));

        MortarRecipeBuilder.mortar(Items.LIGHT_BLUE_DYE,2).requires(Items.BLUE_ORCHID,1)
                .unlockedBy(getHasName(Items.BLUE_ORCHID),has(Items.BLUE_ORCHID))
                .group("light_blue_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.LIGHT_BLUE_DYE)+"_by_blue_orchid"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.CORNFLOWER,1)
                .unlockedBy(getHasName(Items.CORNFLOWER),has(Items.CORNFLOWER))
                .group("blue_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_by_cornflower"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.LAPIS_LAZULI,1)
                .unlockedBy(getHasName(Items.LAPIS_LAZULI),has(Items.LAPIS_LAZULI))
                .group("blue_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_by_lapis_lazuli"));

        MortarRecipeBuilder.mortar(Items.PURPLE_DYE,1).requires(Items.CHORUS_FRUIT,1)
                .unlockedBy(getHasName(Items.CHORUS_FRUIT),has(Items.CHORUS_FRUIT))
                .group("purple_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.PURPLE_DYE)+"_by_chorus_fruit"));

        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,2).requires(Items.ALLIUM,1)
                .unlockedBy(getHasName(Items.ALLIUM),has(Items.ALLIUM))
                .group("magenta_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_allium"));
        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,3).requires(Items.LILAC,1)
                .unlockedBy(getHasName(Items.LILAC),has(Items.LILAC))
                .group("magenta_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_lilac"));

        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_PETALS,1)
                .unlockedBy(getHasName(Items.PINK_PETALS),has(Items.PINK_PETALS))
                .group("pink_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_petals"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_TULIP,1)
                .group("pink_dye")
                .unlockedBy(getHasName(Items.PINK_TULIP),has(Items.PINK_TULIP))
                .group("pink_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_tulip"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,3).requires(Items.PEONY,1)
                .unlockedBy(getHasName(Items.PEONY),has(Items.PEONY))
                .group("pink_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_peony"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,3)
                .requires(ModItems.BUTTERFLY_PEA)
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.BUTTERFLY_PEA),has(ModItems.BUTTERFLY_PEA))
                .group("blue_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_from_butterfly_pea"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,2)
                .requires(ModItems.LIME)
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.LIME),has(ModItems.LIME))
                .group("lime_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.LIME_DYE)+"_from_lime"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,1)
                .requires(ModItems.SLICED_LIME)
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.SLICED_LIME),has(ModItems.SLICED_LIME))
                .group("lime_dye")
                .save(exporter,ThaiDelight.modid("mortar/"+getItemName(Items.LIME_DYE)+"_from_lime_slice"));
    }

    private void cookingPot(Consumer<FinishedRecipe> exporter){
        CookingPotRecipeBuilder.cookingPot(ModItems.CRAB_FRIED_RICE_FEAST,1,200,0.35f)
                .requires(ModTags.FLOWER_CRAB_MEAT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(ModTags.COMMON_EGGS)
                .requires(Items.CARROT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .container(Items.BOWL)
                .group("crab_fried_rice_feast")
                .unlockedBy("has_rice",RecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .save(exporter,ThaiDelight.modid("cooking_pot/crab_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.CRAB_FRIED_RICE_FEAST,1,100,0.35f)
                .requires(ModTags.FLOWER_CRAB_MEAT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get())
                .container(Items.BOWL)
                .group("crab_fried_rice_feast")
                .unlockedBy("has_rice",RecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .save(exporter,ThaiDelight.modid("cooking_pot/crab_fried_rice_feast_from_rice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.LARB_FEAST,1,200,0.35f)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModItems.PEPPER)
                .requires(Items.SUGAR)
                .requires(ModItems.FISH_SAUCE_BOTTLE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .container(Items.BOWL)
                .unlockedBy("has_pepper",RecipeProvider.has(ModItems.PEPPER))
                .save(exporter,ThaiDelight.modid("cooking_pot/larb_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.FISH_SAUCE_BOTTLE,1,100,0.35f)
                .requires(ModTags.COMMON_RAW_FISHES)
                .container(Items.GLASS_BOTTLE)
                .unlockedBy("has_raw_fishes",RecipeProvider.has(ModTags.COMMON_RAW_FISHES))
                .save(exporter,ThaiDelight.modid("cooking_pot/fish_sauce_bottle"));

        CookingPotRecipeBuilder.cookingPot(ModItems.PHAT_KAPHRAO_FEAST,1)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModItems.HOLY_BASIL)
                .requires(ModItems.PEPPER)
                .requires(ModItems.FISH_SAUCE_BOTTLE)
                .requires(Items.EGG)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_holy_basil",RecipeProvider.has(ModItems.HOLY_BASIL))
                .save(exporter,ThaiDelight.modid("cooking_pot/phat_khaphrao_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.DURIAN_CURRY,1)
                .requires(ModTags.DURIAN)
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .requires(ModItems.PEPPER)
                .requires(ModItems.FISH_SAUCE_BOTTLE)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_durian",RecipeProvider.has(ModTags.DURIAN))
                .save(exporter,ThaiDelight.modid("cooking_pot/durian_curry_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.COCONUT_JELLY,4)
                .requires(ModItems.COCONUT_SLICE)
                .requires(Items.SLIME_BALL)
                .requires(Items.SUGAR)
                .requires(ModItems.COCONUT_WATER)
                .recipeTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE))
                .save(exporter,ThaiDelight.modid("cooking_pot/coconut_jelly"));

        CookingPotRecipeBuilder.cookingPot(ModItems.KHANOM_BABIN,4)
                .requires(ModItems.COCONUT_SLICE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(Items.SUGAR)
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE))
                .save(exporter,ThaiDelight.modid("cooking_pot/khanom_babin"));

        CookingPotRecipeBuilder.cookingPot(ModItems.OMELETTE_FEAST,1)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(ModItems.FISH_SAUCE_BOTTLE)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg",RecipeProvider.has(Items.EGG))
                .save(exporter,ThaiDelight.modid("cooking_pot/omelette_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BASIL_OMELETTE_FEAST,1)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(ModItems.FISH_SAUCE_BOTTLE)
                .requires(ModItems.BASIL)
                .requires(ModItems.PEPPER)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg",RecipeProvider.has(Items.EGG))
                .save(exporter,ThaiDelight.modid("cooking_pot/basil_omelette_feast"));


        CookingPotRecipeBuilder.cookingPot(ModItems.PINEAPPLE_FRIED_RICE_FEAST,1)
                .requires(ModTags.PINEAPPLE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(Items.EGG)
                .requires(Items.CARROT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .conditions(DefaultResourceConditions.tagsPopulated(ModTags.PINEAPPLE))
                .unlockedBy("has_pineapple",RecipeProvider.has(ModTags.PINEAPPLE))
                .save(exporter,ThaiDelight.modid("cooking_pot/pineapple_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BAMBOO_SHOOT_SOUP,1)
                .requires(ModItems.BAMBOO_SHOOT)
                .requires(ModItems.PEPPER)
                .requires(ModItems.FERMENTED_FISH)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .requires(ModItems.BASIL)
                .requires(Items.BROWN_MUSHROOM)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_bamboo_shoot",RecipeProvider.has(ModItems.BAMBOO_SHOOT))
                .save(exporter,ThaiDelight.modid("cooking_pot/bamboo_shoot_soup"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BANANA_IN_COCONUT_MILK,1)
                .requires(ModTags.BANANA)
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .requires(Items.SUGAR)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .conditions(DefaultResourceConditions.tagsPopulated(ModTags.BANANA))
                .unlockedBy("has_banana",RecipeProvider.has(ModTags.BANANA))
                .save(exporter,ThaiDelight.modid("cooking_pot/banana_in_coconut_milk"));

        CookingPotRecipeBuilder.cookingPot(ModItems.KHANOM_CHAN,4)
                .requires(ModTags.COMMON_MILKS)
                .requires(Items.SUGAR)
                .requires(Items.WHEAT)
                .requires(ModItems.COCONUT_MILK_BOTTLE)
                .unlockedBy("has_coconut_milk_bottle",RecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE))
                .save(exporter,ThaiDelight.modid("cooking_pot/khanom_chan"));


        CookingPotRecipeBuilder.cookingPot(ModItems.LIME_JUICE,1,200,0.35f)
                .requires(ModTags.LIME)
                .requires(ModTags.LIME)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME))
                .save(exporter,ThaiDelight.modid("cooking_pot/lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.HONEY_LIME_JUICE,1,200,0.35f)
                .requires(ModTags.LIME)
                .requires(ModTags.LIME)
                .requires(Items.SUGAR)
                .requires(Items.HONEY_BOTTLE)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .group("honey_lime_juice")
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME))
                .save(exporter,ThaiDelight.modid("cooking_pot/honey_lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.HONEY_LIME_JUICE,1,100,0.35f)
                .requires(ModItems.LIME_JUICE)
                .requires(Items.HONEY_BOTTLE)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .group("honey_lime_juice")
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME))
                .save(exporter,ThaiDelight.modid("cooking_pot/honey_lime_juice_from_lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.PAPAYA_JUICE,1,200,0.35f)
                .requires(ModTags.RIPE_PAPAYA)
                .requires(ModTags.RIPE_PAPAYA)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_papaya",RecipeProvider.has(ModTags.PAPAYA))
                .save(exporter,ThaiDelight.modid("cooking_pot/papaya_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.COCONUT_WATER,1,200,0.35f)
                .requires(ModTags.COCONUT)
                .requires(ModTags.COCONUT)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_coconut",RecipeProvider.has(ModTags.COCONUT))
                .save(exporter,ThaiDelight.modid("cooking_pot/coconut_water"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BUTTERFLY_PEA_TEA,1,200,0.35f)
                .requires(ModItems.BUTTERFLY_PEA)
                .requires(ModItems.BUTTERFLY_PEA)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_butterfly_pea",RecipeProvider.has(ModItems.BUTTERFLY_PEA))
                .save(exporter,ThaiDelight.modid("cooking_pot/butterfly_pea_tea"));
    }

    private void cuttingBoard(Consumer<FinishedRecipe> exporter){
        Ingredient knivesTag = Ingredient.of(ModTags.KNIVES);

        CuttingBoardRecipeBuilder.cutting(ModItems.SLICED_LIME,2,Ingredient.of(ModItems.LIME),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/lime_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.RAW_PAPAYA_SLICE,4,Ingredient.of(ModItems.RAW_PAPAYA),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/raw_papaya_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.SLICED_PAPAYA,2,Ingredient.of(ModItems.PAPAYA),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/papaya_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_PULP,1,Ingredient.of(ModItems.SMALL_DURIAN),knivesTag)
                .addResults(new ItemStack(ModItems.DURIAN_PEEL,1))
                .save(exporter,ThaiDelight.modid("cutting/durian_pulp_from_small_durian"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_PULP,2,Ingredient.of(ModItems.DURIAN),knivesTag)
                .addResults(new ItemStack(ModItems.DURIAN_PEEL,2))
                .save(exporter,ThaiDelight.modid("cutting/durian_pulp_from_durian"));

        CuttingBoardRecipeBuilder.cutting(ModItems.MANGO_SLICE,2,Ingredient.of(ModItems.MANGO),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/mango_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.STRIPPED_COCONUT,1,Ingredient.of(ModItems.COCONUT),Ingredient.of(ItemTags.AXES))
                .addResults(new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.TREE_BARK.get(),2))
                .save(exporter,ThaiDelight.modid("cutting/stripped_coconut_from_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_SLICE,2,Ingredient.of(ModItems.COCONUT), knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/coconut_slice_from_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_SLICE,2,Ingredient.of(ModItems.STRIPPED_COCONUT), knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/coconut_slice_from_stripped_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_CAKE_SLICE,7,Ingredient.of(ModItems.DURIAN_CAKE),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/durian_cake_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.MANGO_CHEESECAKE_SLICE,4,Ingredient.of(ModItems.MANGO_CHEESECAKE),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/mango_pudding_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_PIE_SLICE,4,Ingredient.of(ModItems.COCONUT_PIE),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/coconut_pie_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.HONEY_COCONUT_PIE_SLICE,4,Ingredient.of(ModItems.HONEY_COCONUT_PIE),knivesTag)
                .save(exporter,ThaiDelight.modid("cutting/honey_coconut_pie_slice"));
    }

}

