package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.impl.resource.conditions.DefaultResourceConditionTypes;
import net.fabricmc.fabric.impl.resource.conditions.conditions.TagsPopulatedResourceCondition;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.datagen.builder.CookingPotRecipeBuilder;
import net.firemuffin303.thaidelight.datagen.builder.CuttingBoardRecipeBuilder;
import net.firemuffin303.thaidelight.datagen.builder.MortarRecipeBuilder;
import net.minecraft.core.HolderLookup;
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

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeDataGen extends FabricRecipeProvider {
    public ModRecipeDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {

        craft(exporter);
        furnace(exporter);
        smithing(exporter);
        cook(ModItems.CRAB_MEAT.get(), ModItems.COOKED_CRAB_MEAT.get(), 0.35f, 200, exporter);
        cook(ModItems.DRAGONFLY.get(), ModItems.COOKED_DRAGONFLY.get(), 0.35f, 200, exporter);
        cook(ModItems.DURIAN_PULP.get(),ModItems.FRIED_DURIAN.get(),0.35f,150,exporter);

        mortar(exporter);
        cookingPot(exporter);
        cuttingBoard(exporter);
    }

    private void cook(ItemLike ingredient, Item result, float exp, int cookTicks, RecipeOutput exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelightCommon.modid( "smelting/"+getItemName(result) + "_from_smelting" ));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks / 2)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelightCommon.modid("cooking/"+getItemName(result) + "_from_cooking"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks * 3)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(exporter, ThaiDelightCommon.modid("campfire/"+getItemName(result) + "_from_campfire"));
    }

    private void craft(RecipeOutput exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.MORTAR.get())
                .define('A',Items.BRICK)
                .define('B',Items.STICK)
                .pattern("ABA")
                .pattern("AAA")
                .unlockedBy(getHasName(Items.STICK),has(Items.STICK))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModBlocks.MORTAR.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.SACK.get())
                .define('C', vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get())
                .pattern("CCC")
                .pattern("C C")
                .pattern("CCC")
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()),has(vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.SACK.get())));

        bigPackingCraft(ModItems.LIME_CRATE.get(),1,ModItems.LIME.get(),exporter);
        bigPackingCraft(ModItems.PEPPER_CRATE.get(),1,ModItems.PEPPER.get(),exporter);
        bigPackingCraft(ModItems.RAW_PAPAYA_CRATE.get(),1,ModItems.RAW_PAPAYA.get(),exporter);
        bigPackingCraft(ModItems.PAPAYA_CRATE.get(),1,ModItems.PAPAYA.get(),exporter);
        bigPackingCraft(ModItems.MANGO_CRATE.get(),1,ModItems.MANGO.get(),exporter);
        bigPackingCraft(ModItems.BASIL_CRATE.get(),1,ModItems.BASIL.get(),exporter);
        bigPackingCraft(ModItems.BAMBOO_SHOOT_CRATE.get(),1,ModItems.BAMBOO_SHOOT.get(),exporter);
        bigPackingCraft(ModItems.BUTTERFLY_PEA_CRATE.get(),1,ModItems.BUTTERFLY_PEA.get(),exporter);

        bigPackingCraft(ModItems.DURIAN_PEEL_BLOCK.get(),1,ModItems.DURIAN_PEEL.get(),exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DURIAN_HELMET.get())
                .define('L',Items.LEATHER_HELMET)
                .define('D', ModItems.DURIAN_PEEL.get())
                .pattern(" D ")
                .pattern("DLD")
                .unlockedBy(getHasName(ModItems.DURIAN_PEEL.get()),has(ModItems.DURIAN_PEEL.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.DURIAN_HELMET.get())));

        craftWoodFamily(
                ModelDataGen.DURIAN_PLANKS,
                ModItems.DURIAN_LOG.get(),
                ModItems.DURIAN_WOOD.get(),
                ModItems.STRIPPED_DURIAN_LOG.get(),
                ModItems.STRIPPED_DURIAN_WOOD.get(),
                ModItems.DURIAN_HANGING_SIGN.get(),
                ModItems.DURIAN_BOAT.get(),
                ModItems.DURIAN_CHEST_BOAT.get(),
                ModItems.DURIAN_CABINET.get(),
                ModTags.DURIAN_LOGS_ITEM,
                exporter
        );

        bigPackingCraft(ModItems.COCONUT_LEAF_BLOCK.get(),1,ModItems.COCONUT_LEAF.get(),exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.COCONUT_LEAF_MAT.get(),3)
                .define('L', ModItems.COCONUT_LEAF.get())
                .pattern("LL")
                .unlockedBy(getHasName(ModItems.COCONUT_LEAF.get()),has(ModItems.COCONUT_LEAF.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.COCONUT_LEAF_MAT.get())));

        craftWoodFamily(
                ModelDataGen.COCONUT_PLANKS,
                ModItems.COCONUT_LOG.get(),
                ModItems.COCONUT_WOOD.get(),
                ModItems.STRIPPED_COCONUT_LOG.get(),
                ModItems.STRIPPED_COCONUT_WOOD.get(),
                ModItems.COCONUT_HANGING_SIGN.get(),
                ModItems.COCONUT_BOAT.get(),
                ModItems.COCONUT_CHEST_BOAT.get(),
                ModItems.COCONUT_CABINET.get(),
                ModTags.COCONUT_LOGS_ITEM,
                exporter
        );

        craftWoodFamily(
                ModelDataGen.MANGO_PLANKS,
                ModItems.MANGO_LOG.get(),
                ModItems.MANGO_WOOD.get(),
                ModItems.STRIPPED_MANGO_LOG.get(),
                ModItems.STRIPPED_MANGO_WOOD.get(),
                ModItems.MANGO_HANGING_SIGN.get(),
                ModItems.MANGO_BOAT.get(),
                ModItems.MANGO_CHEST_BOAT.get(),
                ModItems.MANGO_CABINET.get(),
                ModTags.MANGO_LOGS_ITEM,
                exporter
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PAPAYA_WOOD.get(), 3)
                .define('#', ModItems.PAPAYA_LOG.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(ModItems.PAPAYA_LOG.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.PAPAYA_WOOD.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.STRIPPED_PAPAYA_WOOD.get(), 3)
                .define('#', ModItems.STRIPPED_PAPAYA_LOG.get())
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(ModItems.STRIPPED_PAPAYA_LOG.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.STRIPPED_PAPAYA_WOOD.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.MANGO_STICKY_RICE_FEAST.get(),1)
                .requires(ModTags.MANGO)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.BOWL)
                .unlockedBy("has_mango",RecipeProvider.has(ModTags.MANGO))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.MANGO_STICKY_RICE_FEAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STIR_FRIED_NOODLE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get())
                .requires(Items.SUGAR).requires(Items.BOWL).requires(ModTags.LIME)
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()),has(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.STIR_FRIED_NOODLE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DURIAN_CAKE.get())
                .define('D', ModItems.DURIAN_PULP.get())
                .define('E', Items.EGG)
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("MMM")
                .pattern("SES")
                .pattern("DDD")
                .unlockedBy("has_milk",RecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.DURIAN_CAKE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD,ModItems.MANGO_CHEESECAKE.get())
                .define('M',ModTags.MANGO)
                .define('C',ModTags.COMMON_MILKS)
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("CPC")
                .unlockedBy("has_milk",RecipeProvider.has(ModTags.COMMON_MILKS))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MANGO_CHEESECAKE.get())
                .define('C', ModItems.MANGO_CHEESECAKE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_mango_cheesecake_slice",RecipeProvider.has(ModItems.MANGO_CHEESECAKE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.MANGO_CHEESECAKE.get())+"_from_slice"));


        //--------- Coconut Pie ------------
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE.get())
                .define('C', ModItems.COCONUT_SLICE.get())
                .define('P', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .define('S', Items.SUGAR)
                .define('M', ModTags.COMMON_MILKS)
                .pattern("CCC")
                .pattern("MMM")
                .pattern("SPS")
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_PIE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.COCONUT_PIE.get())
                .define('C', ModItems.COCONUT_PIE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_coconut_pie_slice",RecipeProvider.has(ModItems.COCONUT_PIE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_PIE.get())+"_from_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE.get())
                .requires(ModItems.COCONUT_PIE.get()).requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_coconut_pie",RecipeProvider.has(ModItems.COCONUT_PIE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HONEY_COCONUT_PIE.get())
                .define('C', ModItems.HONEY_COCONUT_PIE_SLICE.get())
                .pattern("CC")
                .pattern("CC")
                .unlockedBy("has_honey_coconut_pie_slice",RecipeProvider.has(ModItems.HONEY_COCONUT_PIE_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.HONEY_COCONUT_PIE.get())+"_from_slice"));
        //--------------------------------

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.COCONUT_MILK_ICE_CREAM.get())
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(Items.ICE).requires(Items.ICE)
                .requires(Items.BOWL)
                .unlockedBy("has_coconut_milk_bottle",RecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+RecipeProvider.getItemName(ModItems.COCONUT_MILK_ICE_CREAM.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUTTERFLY_PEA_SEEDS.get(),3).requires(ModItems.BUTTERFLY_PEA.get())
                .unlockedBy(getHasName(ModItems.BUTTERFLY_PEA.get()),has(ModItems.BUTTERFLY_PEA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/butterfly_seeds_from_butterfly_pea"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.YELLOW_DYE).requires(ModBlocks.DURIAN_FLOWER.get())
                .unlockedBy(getHasName(ModBlocks.DURIAN_FLOWER.get()),has(ModBlocks.DURIAN_FLOWER.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/yellow_dye_from_durian_flower"));

        unPacking(ModItems.LIME.get(),ModItems.LIME_CRATE.get(),exporter);
        unPacking(ModItems.PEPPER.get(),ModItems.PEPPER_CRATE.get(),exporter);
        unPacking(ModItems.RAW_PAPAYA.get(),ModItems.RAW_PAPAYA_CRATE.get(),exporter);
        unPacking(ModItems.PAPAYA.get(),ModItems.PAPAYA_CRATE.get(),exporter);
        unPacking(ModItems.MANGO.get(),ModItems.MANGO_CRATE.get(),exporter);
        unPacking(ModItems.BASIL.get(),ModItems.BASIL_CRATE.get(),exporter);
        unPacking(ModItems.BAMBOO_SHOOT.get(),ModItems.BAMBOO_SHOOT_CRATE.get(),exporter);
        unPacking(ModItems.BUTTERFLY_PEA.get(),ModItems.BUTTERFLY_PEA_CRATE.get(),exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEPPER_SEED.get(),4)
                .requires(ModItems.PEPPER.get())
                .unlockedBy(getHasName(ModItems.PEPPER.get()),has(ModItems.PEPPER.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModItems.PEPPER_SEED.get())+"_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),4)
                .requires(ModItems.PAPAYA.get())
                .unlockedBy(getHasName(ModItems.PAPAYA.get()),has(ModItems.PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),4).requires(ModItems.RAW_PAPAYA.get())
                .unlockedBy(getHasName(ModItems.RAW_PAPAYA.get()),has(ModItems.RAW_PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_unripe_papaya_from_crafting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS.get(),2).requires(ModItems.SLICED_PAPAYA.get())
                .unlockedBy(getHasName(ModItems.SLICED_PAPAYA.get()),has(ModItems.SLICED_PAPAYA.get()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING.get())+"_by_sliced_papaya_from_crafting"));


        //Salad
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.BOWL),has(Items.BOWL))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())+"by_raw_papaya"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())
                .requires(ModTags.MANGO)
                .requires(Items.MELON)
                .requires(Items.MELON)
                .requires(ItemTags.FOX_FOOD)
                .requires(ItemTags.FOX_FOOD)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.BOWL),has(Items.BOWL))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(vectorwing.farmersdelight.common.registry.ModItems.FRUIT_SALAD.get())+"_by_mango"));

    }

    private void furnace(RecipeOutput exporter){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.DURIAN_PEEL.get()),RecipeCategory.MISC,Items.CHARCOAL,0.15f,200)
                .unlockedBy("has_durian_peel",RecipeProvider.has(ModItems.DURIAN_PEEL.get()))
                .save(exporter,ThaiDelightCommon.modid("smelting/charcoal_from_durian_peel"));
    }

    private void smithing(RecipeOutput exporter){
        //SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),Ingredient.of(ModItems.DIAMOND_PASTLE),Ingredient.of(Items.NETHERITE_INGOT),RecipeCategory.TOOLS,ModItems.NETHERITE_PASTLE).unlocks(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(exporter,"smithing/"+getItemName(ModItems.NETHERITE_PASTLE)+"_from_smithing");
    }

    private void bigPackingCraft(Item result,int resultAmount,ItemLike ingredient,RecipeOutput exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount)
                .define('A',ingredient)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(result)+"_from_crafting"));
    }

    private void unPacking(Item result,Item unpackedItem,RecipeOutput exporter){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,result,9)
                .requires(unpackedItem).unlockedBy(getHasName(unpackedItem),has(unpackedItem))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(result)+"_from_crafting"));
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
                                 RecipeOutput exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(log))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(wood)));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stripped_wood, 3)
                .define('#', stripped_log)
                .pattern("##")
                .pattern("##")
                .group("bark")
                .unlockedBy("has_log", RecipeProvider.has(stripped_log))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(stripped_wood)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, blockFamily.getBaseBlock(), 4)
                .requires(log_item_tag)
                .group("planks")
                .unlockedBy("has_log", RecipeProvider.has(log_item_tag))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.getBaseBlock())));


        RecipeProvider.stairBuilder(blockFamily.get(BlockFamily.Variant.STAIRS),
                Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.STAIRS))));

        RecipeProvider.slabBuilder(RecipeCategory.BUILDING_BLOCKS,blockFamily.get(BlockFamily.Variant.SLAB),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.SLAB))));

        RecipeProvider.fenceBuilder(blockFamily.get(BlockFamily.Variant.FENCE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.FENCE))));

        RecipeProvider.fenceGateBuilder(blockFamily.get(BlockFamily.Variant.FENCE_GATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.FENCE_GATE))));

        RecipeProvider.doorBuilder(blockFamily.get(BlockFamily.Variant.DOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.DOOR))));

        RecipeProvider.trapdoorBuilder(blockFamily.get(BlockFamily.Variant.TRAPDOOR),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.TRAPDOOR))));

        RecipeProvider.pressurePlateBuilder(RecipeCategory.REDSTONE, blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.PRESSURE_PLATE))));

        RecipeProvider.buttonBuilder(blockFamily.get(BlockFamily.Variant.BUTTON),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.BUTTON))));

        RecipeProvider.signBuilder(blockFamily.get(BlockFamily.Variant.SIGN),Ingredient.of(blockFamily.getBaseBlock()))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(blockFamily.get(BlockFamily.Variant.SIGN))));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, hanging_sign, 6)
                .group("hanging_sign")
                .define('#', stripped_log)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", RecipeProvider.has(stripped_log))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(hanging_sign)));



        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat)
                .define('#', blockFamily.getBaseBlock())
                .pattern("# #").pattern("###")
                .group("boat")
                .unlockedBy("in_water", RecipeProvider.insideOf(Blocks.WATER))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(boat)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chest_boat)
                .requires(Blocks.CHEST)
                .requires(boat)
                .group("chest_boat")
                .unlockedBy("has_boat", RecipeProvider.has(ItemTags.BOATS))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(chest_boat)));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,cabinet)
                .pattern("WWW")
                .pattern("S S")
                .pattern("WWW")
                .define('W',blockFamily.getBaseBlock())
                .define('S',blockFamily.get(BlockFamily.Variant.SLAB))
                .unlockedBy(getHasName(blockFamily.getBaseBlock()),has(blockFamily.getBaseBlock()))
                .save(exporter,ThaiDelightCommon.modid("crafting/"+getItemName(cabinet)));
    }

    private void mortar(RecipeOutput exporter){
        MortarRecipeBuilder.mortar(ModBlocks.SOMTAM_FEAST.get())
                .requires(ModTags.PEPPER)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(ModItems.FERMENTED_FISH.get())
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(getHasName(ModItems.FERMENTED_FISH.get()),has(ModItems.FERMENTED_FISH.get()))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(ModBlocks.SOMTAM_FEAST.get())));

        MortarRecipeBuilder.mortar(ModItems.PESTO_SAUCE.get())
                .requires(ModItems.BASIL.get())
                .requires(ModItems.BASIL.get())
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(getHasName(ModItems.BASIL.get()),has(ModItems.BASIL.get()))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(ModItems.PESTO_SAUCE.get())));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,4).requires(Items.BONE,1)
                .unlockedBy(getHasName(Items.BONE),has(Items.BONE))
                .group("bone_meal")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BONE_MEAL)+"_by_bone"));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,2)
                .requires(Items.NAUTILUS_SHELL,1)
                .group("bone_meal")
                .unlockedBy(getHasName(Items.NAUTILUS_SHELL),has(Items.NAUTILUS_SHELL))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BONE_MEAL)+"_by_nautilus_shell"));

        MortarRecipeBuilder.mortar(Items.BLAZE_POWDER,3)
                .requires(Items.BLAZE_ROD,1)
                .unlockedBy(getHasName(Items.BLAZE_ROD),has(Items.BLAZE_ROD))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLAZE_POWDER)+"_by_blaze_rod"));

        MortarRecipeBuilder.mortar(Items.SUGAR,2)
                .requires(Items.SUGAR_CANE,1)
                .unlockedBy(getHasName(Items.SUGAR_CANE),has(Items.SUGAR_CANE))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.SUGAR)+"_by_sugar_cane"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.COAL,1)
                .unlockedBy(getHasName(Items.COAL),has(Items.COAL))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_coal"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.CHARCOAL,1)
                .unlockedBy(getHasName(Items.CHARCOAL),has(Items.CHARCOAL))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_charcoal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2)
                .requires(Items.BONE_MEAL,1)
                .unlockedBy(getHasName(Items.BONE_MEAL),has(Items.BONE_MEAL))
                .group("white_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.WHITE_DYE)+"_by_bone_meal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2).requires(Items.LILY_OF_THE_VALLEY,1)
                .unlockedBy(getHasName(Items.LILY_OF_THE_VALLEY),has(Items.LILY_OF_THE_VALLEY))
                .group("white_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.WHITE_DYE)+"_by_lily_of_the_valley"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.OXEYE_DAISY,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.OXEYE_DAISY),has(Items.OXEYE_DAISY))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_oxeye_daisy"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.AZURE_BLUET,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.AZURE_BLUET),has(Items.AZURE_BLUET))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_azure_bluet"));
        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.WHITE_TULIP,1)
                .group("light_gray_dye")
                .unlockedBy(getHasName(Items.WHITE_TULIP),has(Items.WHITE_TULIP))
                .save(exporter, ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_white_tulip"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.INK_SAC,1)
                .unlockedBy(getHasName(Items.INK_SAC),has(Items.INK_SAC))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_ink_sac"));
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.WITHER_ROSE,1)
                .unlockedBy(getHasName(Items.WITHER_ROSE),has(Items.WITHER_ROSE))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLACK_DYE)+"_by_wither_rose"));

        MortarRecipeBuilder.mortar(Items.BROWN_DYE,2).requires(Items.COCOA_BEANS,1)
                .unlockedBy(getHasName(Items.COCOA_BEANS),has(Items.COCOA_BEANS))
                .group("brown_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BROWN_DYE)+"_by_cocoa"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.POPPY,1)
                .unlockedBy(getHasName(Items.POPPY),has(Items.POPPY))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_poppy"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.BEETROOT,1)
                .unlockedBy(getHasName(Items.BEETROOT),has(Items.BEETROOT))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_beetroot"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.RED_TULIP,1)
                .unlockedBy(getHasName(Items.RED_TULIP),has(Items.RED_TULIP))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_red_tulip"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.ROSE_BUSH,1)
                .unlockedBy(getHasName(Items.ROSE_BUSH),has(Items.ROSE_BUSH))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.RED_DYE)+"_by_rose_bush"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.TORCHFLOWER,1)
                .unlockedBy(getHasName(Items.TORCHFLOWER),has(Items.TORCHFLOWER))
                .group("orange_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.ORANGE_DYE)+"_by_torchflower"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.ORANGE_TULIP,1)
                .unlockedBy(getHasName(Items.ORANGE_TULIP),has(Items.ORANGE_TULIP))
                .group("orange_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.ORANGE_DYE)+"_by_orange_tulip"));

        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,2).requires(Items.DANDELION,1)
                .unlockedBy(getHasName(Items.DANDELION),has(Items.DANDELION))
                .group("yellow_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.YELLOW_DYE)+"_by_dandelion"));
        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,3).requires(Items.SUNFLOWER,1)
                .unlockedBy(getHasName(Items.SUNFLOWER),has(Items.SUNFLOWER))
                .group("yellow_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.YELLOW_DYE)+"_by_sunflower"));

        MortarRecipeBuilder.mortar(Items.CYAN_DYE,3).requires(Items.PITCHER_PLANT,1)
                .unlockedBy(getHasName(Items.PITCHER_PLANT),has(Items.PITCHER_PLANT))
                .group("cyan_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.CYAN_DYE)+"_by_pitcher_plant"));

        MortarRecipeBuilder.mortar(Items.LIGHT_BLUE_DYE,2).requires(Items.BLUE_ORCHID,1)
                .unlockedBy(getHasName(Items.BLUE_ORCHID),has(Items.BLUE_ORCHID))
                .group("light_blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIGHT_BLUE_DYE)+"_by_blue_orchid"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.CORNFLOWER,1)
                .unlockedBy(getHasName(Items.CORNFLOWER),has(Items.CORNFLOWER))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_by_cornflower"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.LAPIS_LAZULI,1)
                .unlockedBy(getHasName(Items.LAPIS_LAZULI),has(Items.LAPIS_LAZULI))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_by_lapis_lazuli"));

        MortarRecipeBuilder.mortar(Items.PURPLE_DYE,1).requires(Items.CHORUS_FRUIT,1)
                .unlockedBy(getHasName(Items.CHORUS_FRUIT),has(Items.CHORUS_FRUIT))
                .group("purple_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.PURPLE_DYE)+"_by_chorus_fruit"));

        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,2).requires(Items.ALLIUM,1)
                .unlockedBy(getHasName(Items.ALLIUM),has(Items.ALLIUM))
                .group("magenta_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_allium"));
        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,3).requires(Items.LILAC,1)
                .unlockedBy(getHasName(Items.LILAC),has(Items.LILAC))
                .group("magenta_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_lilac"));

        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_PETALS,1)
                .unlockedBy(getHasName(Items.PINK_PETALS),has(Items.PINK_PETALS))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_petals"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_TULIP,1)
                .group("pink_dye")
                .unlockedBy(getHasName(Items.PINK_TULIP),has(Items.PINK_TULIP))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_tulip"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,3).requires(Items.PEONY,1)
                .unlockedBy(getHasName(Items.PEONY),has(Items.PEONY))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.PINK_DYE)+"_by_peony"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,3)
                .requires(ModItems.BUTTERFLY_PEA.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.BUTTERFLY_PEA.get()),has(ModItems.BUTTERFLY_PEA.get()))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.BLUE_DYE)+"_from_butterfly_pea"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,2)
                .requires(ModItems.LIME.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.LIME.get()),has(ModItems.LIME.get()))
                .group("lime_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIME_DYE)+"_from_lime"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,1)
                .requires(ModItems.SLICED_LIME.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(getHasName(ModItems.SLICED_LIME.get()),has(ModItems.SLICED_LIME.get()))
                .group("lime_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+getItemName(Items.LIME_DYE)+"_from_lime_slice"));

        MortarRecipeBuilder.mortar(ModItems.STEAMED_BAMBOO_SHOOT.get(),1)
                .requires(ModItems.BAMBOO_SHOOT.get())
                .requires(ModTags.PEPPER)
                .requires(ModItems.FERMENTED_FISH.get())
                .requires(ModTags.COMMON_COOKED_MEATS)
                .container(Items.BOWL)
                .unlockedBy(getHasName(ModItems.BAMBOO_SHOOT.get()),has(ModItems.BAMBOO_SHOOT.get()))
                .save(exporter,ThaiDelightCommon.modid("mortar/steamed_bamboo_shoot"));
    }

    private void cookingPot(RecipeOutput exporter){
        CookingPotRecipeBuilder.cookingPot(ModItems.CRAB_FRIED_RICE_FEAST.get(),1,200,0.35f)
                .requires(ModTags.FLOWER_CRAB_MEAT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(ModTags.COMMON_EGGS)
                .requires(Items.CARROT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .container(Items.BOWL)
                .group("crab_fried_rice_feast")
                .unlockedBy("has_rice",RecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/crab_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.CRAB_FRIED_RICE_FEAST.get(),1,100,0.35f)
                .requires(ModTags.FLOWER_CRAB_MEAT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get())
                .container(Items.BOWL)
                .group("crab_fried_rice_feast")
                .unlockedBy("has_rice",RecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/crab_fried_rice_feast_from_rice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.LARB_FEAST.get(),1,200,0.35f)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModTags.PEPPER)
                .requires(Items.SUGAR)
                .requires(ModItems.FISH_SAUCE_BOTTLE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .container(Items.BOWL)
                .unlockedBy("has_pepper",RecipeProvider.has(ModTags.PEPPER))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/larb_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.FISH_SAUCE_BOTTLE.get(),1,100,0.35f)
                .requires(ModTags.COMMON_RAW_FISHES)
                .container(Items.GLASS_BOTTLE)
                .unlockedBy("has_raw_fishes",RecipeProvider.has(ModTags.COMMON_RAW_FISHES))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/fish_sauce_bottle"));

        CookingPotRecipeBuilder.cookingPot(ModItems.PHAT_KAPHRAO_FEAST.get(),1)
                .requires(ModTags.COMMON_COOKED_MEATS)
                .requires(ModItems.BASIL.get())
                .requires(ModTags.PEPPER)
                .requires(ModItems.FISH_SAUCE_BOTTLE.get())
                .requires(Items.EGG)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_basil",RecipeProvider.has(ModItems.BASIL.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/phat_khaphrao_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.DURIAN_CURRY.get(),1)
                .requires(ModTags.DURIAN)
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(ModTags.PEPPER)
                .requires(ModItems.FISH_SAUCE_BOTTLE.get())
                .requires(ModTags.COMMON_COOKED_MEATS)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_durian",RecipeProvider.has(ModTags.DURIAN))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/durian_curry_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.COCONUT_JELLY.get(),4)
                .requires(ModItems.COCONUT_SLICE.get())
                .requires(Items.SLIME_BALL)
                .requires(Items.SUGAR)
                .requires(ModItems.COCONUT_WATER.get())
                .recipeTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/coconut_jelly"));

        CookingPotRecipeBuilder.cookingPot(ModItems.KHANOM_BABIN.get(),4)
                .requires(ModItems.COCONUT_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(Items.SUGAR)
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .recipeTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice",RecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/khanom_babin"));

        CookingPotRecipeBuilder.cookingPot(ModItems.OMELETTE_FEAST.get(),1)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(ModItems.FISH_SAUCE_BOTTLE.get())
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg",RecipeProvider.has(Items.EGG))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/omelette_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BASIL_OMELETTE_FEAST.get(),1)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(ModItems.FISH_SAUCE_BOTTLE.get())
                .requires(ModItems.BASIL.get())
                .requires(ModTags.PEPPER)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg",RecipeProvider.has(Items.EGG))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/basil_omelette_feast"));


        CookingPotRecipeBuilder.cookingPot(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get(),1)
                .requires(ModTags.PINEAPPLE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .requires(Items.EGG)
                .requires(Items.CARROT)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .conditions(new TagsPopulatedResourceCondition(ModTags.PINEAPPLE))
                .unlockedBy("has_pineapple",RecipeProvider.has(ModTags.PINEAPPLE))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/pineapple_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BAMBOO_SHOOT_SOUP.get(),1)
                .requires(ModItems.BAMBOO_SHOOT.get())
                .requires(ModTags.PEPPER)
                .requires(ModItems.FERMENTED_FISH.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .requires(ModItems.BASIL.get())
                .requires(Items.BROWN_MUSHROOM)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_bamboo_shoot",RecipeProvider.has(ModItems.BAMBOO_SHOOT.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/bamboo_shoot_soup"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BANANA_IN_COCONUT_MILK.get(),1)
                .requires(ModTags.BANANA)
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .requires(Items.SUGAR)
                .container(Items.BOWL)
                .recipeTab(CookingPotRecipeBookTab.MEALS)
                .conditions(new TagsPopulatedResourceCondition(ModTags.BANANA))
                .unlockedBy("has_banana",RecipeProvider.has(ModTags.BANANA))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/banana_in_coconut_milk"));

        CookingPotRecipeBuilder.cookingPot(ModItems.KHANOM_CHAN.get(),4)
                .requires(ModTags.COMMON_MILKS)
                .requires(Items.SUGAR)
                .requires(Items.WHEAT)
                .requires(ModItems.COCONUT_MILK_BOTTLE.get())
                .unlockedBy("has_coconut_milk_bottle",RecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/khanom_chan"));


        CookingPotRecipeBuilder.cookingPot(ModItems.LIME_JUICE.get(),1,200,0.35f)
                .requires(ModTags.LIME)
                .requires(ModTags.LIME)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.HONEY_LIME_JUICE.get(),1,200,0.35f)
                .requires(ModTags.LIME)
                .requires(ModTags.LIME)
                .requires(Items.SUGAR)
                .requires(Items.HONEY_BOTTLE)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .group("honey_lime_juice")
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/honey_lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.HONEY_LIME_JUICE.get(),1,100,0.35f)
                .requires(ModItems.LIME_JUICE.get())
                .requires(Items.HONEY_BOTTLE)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .group("honey_lime_juice")
                .unlockedBy("has_lime",RecipeProvider.has(ModItems.LIME.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/honey_lime_juice_from_lime_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.PAPAYA_JUICE.get(),1,200,0.35f)
                .requires(ModTags.RIPE_PAPAYA)
                .requires(ModTags.RIPE_PAPAYA)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_papaya",RecipeProvider.has(ModTags.PAPAYA))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/papaya_juice"));

        CookingPotRecipeBuilder.cookingPot(ModItems.COCONUT_WATER.get(),1,200,0.35f)
                .requires(ModTags.COCONUT)
                .requires(ModTags.COCONUT)
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_coconut",RecipeProvider.has(ModTags.COCONUT))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/coconut_water"));

        CookingPotRecipeBuilder.cookingPot(ModItems.BUTTERFLY_PEA_TEA.get(),1,200,0.35f)
                .requires(ModItems.BUTTERFLY_PEA.get())
                .requires(ModItems.BUTTERFLY_PEA.get())
                .requires(Items.SUGAR)
                .container(Items.GLASS_BOTTLE)
                .recipeTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_butterfly_pea",RecipeProvider.has(ModItems.BUTTERFLY_PEA.get()))
                .save(exporter,ThaiDelightCommon.modid("cooking_pot/butterfly_pea_tea"));
    }

    private void cuttingBoard(RecipeOutput exporter){
        Ingredient knivesTag = Ingredient.of(ModTags.KNIVES);

        CuttingBoardRecipeBuilder.cutting(ModItems.SLICED_LIME.get(),2,Ingredient.of(ModItems.LIME.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/lime_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.RAW_PAPAYA_SLICE.get(),4,Ingredient.of(ModItems.RAW_PAPAYA.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/raw_papaya_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.SLICED_PAPAYA.get(),2,Ingredient.of(ModItems.PAPAYA.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/papaya_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_PULP.get(),2,Ingredient.of(ModItems.SMALL_DURIAN.get()),knivesTag)
                .addResults(new ItemStack(ModItems.DURIAN_PEEL.get(),2))
                .save(exporter,ThaiDelightCommon.modid("cutting/durian_pulp_from_small_durian"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_SLICE_FEAST.get(),1,Ingredient.of(ModItems.DURIAN.get()),knivesTag)
                .addResults(new ItemStack(ModItems.DURIAN_PEEL.get(),3))
                .save(exporter,ThaiDelightCommon.modid("cutting/durian_feast_from_durian"));

        CuttingBoardRecipeBuilder.cutting(ModItems.MANGO_SLICE.get(),2,Ingredient.of(ModItems.MANGO.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/mango_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.STRIPPED_COCONUT.get(),1,Ingredient.of(ModItems.COCONUT.get()),Ingredient.of(ItemTags.AXES))
                .addResults(new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.TREE_BARK.get(),2))
                .save(exporter, ThaiDelightCommon.modid("cutting/stripped_coconut_from_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_SLICE.get(),2,Ingredient.of(ModItems.COCONUT.get()), knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/coconut_slice_from_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_SLICE.get(),2,Ingredient.of(ModItems.STRIPPED_COCONUT.get()), knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/coconut_slice_from_stripped_coconut"));

        CuttingBoardRecipeBuilder.cutting(ModItems.DURIAN_CAKE_SLICE.get(),7,Ingredient.of(ModItems.DURIAN_CAKE.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/durian_cake_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.MANGO_CHEESECAKE_SLICE.get(),4,Ingredient.of(ModItems.MANGO_CHEESECAKE.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/mango_pudding_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.COCONUT_PIE_SLICE.get(),4,Ingredient.of(ModItems.COCONUT_PIE.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/coconut_pie_slice"));

        CuttingBoardRecipeBuilder.cutting(ModItems.HONEY_COCONUT_PIE_SLICE.get(),4,Ingredient.of(ModItems.HONEY_COCONUT_PIE.get()),knivesTag)
                .save(exporter,ThaiDelightCommon.modid("cutting/honey_coconut_pie_slice"));
    }

}

