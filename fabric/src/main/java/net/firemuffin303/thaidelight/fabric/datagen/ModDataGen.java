package net.firemuffin303.thaidelight.fabric.datagen;

import com.nhoryzon.mc.farmersdelight.item.ModItemSettings;
import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeDataGen::new);
        pack.addProvider(AdvancementDataGen::new);
        pack.addProvider(BlockLootTableDataGen::new);
        pack.addProvider(LangDataGen::new);
        pack.addProvider(LangDataGen.ThaiLangDataGen::new);
        pack.addProvider(ModelDataGen::new);
        pack.addProvider(LootTableDataGen::new);
        pack.addProvider(LootTableDataGen.ChestDataGen::new);
        pack.addProvider(ItemTagDataGen::new);
        pack.addProvider(BlockTagDataGen::new);
        pack.addProvider(EntityTypeTagDataGen::new);
    }

    private static class RecipeDataGen extends FabricRecipeProvider {

        public RecipeDataGen(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            craft(exporter);
            smithing(exporter);
            cook(ModItems.CRAB_MEAT, ModItems.COOKED_CRAB_MEAT, 0.35f, 200, exporter);
            cook(ModItems.DRAGONFLY, ModItems.COOKED_DRAGONFLY, 0.35f, 200, exporter);
        }

        private void cook(ItemLike ingredient, Item result, float exp, int cookTicks, Consumer<FinishedRecipe> exporter) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("smelting/")+getItemName(result) + ("_from_smelting"));
            SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks / 2).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("cooking/")+getItemName(result) + ("_from_cooking"));
            SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks * 3).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("campfire/")+getItemName(result) + ("_from_campfire"));
        }

        private void craft(Consumer<FinishedRecipe> exporter){
            bigPackingCraft(Item.byBlock(ModBlocks.LIME_CRATE),1,ModItems.LIME,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.PEPPER_CRATE),1,ModItems.PEPPER,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.RAW_PAPAYA_CRATE),1,ModItems.RAW_PAPAYA,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.PAPAYA_CRATE),1,ModItems.PAPAYA,exporter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.LIME,9).requires(ModBlocks.LIME_CRATE).unlockedBy(getHasName(ModBlocks.LIME_CRATE),has(ModBlocks.LIME_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.LIME)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PEPPER,9).requires(ModBlocks.PEPPER_CRATE).unlockedBy(getHasName(ModBlocks.PEPPER_CRATE),has(ModBlocks.PEPPER_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PEPPER)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.RAW_PAPAYA,9).requires(ModBlocks.RAW_PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.RAW_PAPAYA_CRATE),has(ModBlocks.RAW_PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.RAW_PAPAYA)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PAPAYA,9).requires(ModBlocks.PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.PAPAYA_CRATE),has(ModBlocks.PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PAPAYA)+"_from_crafting");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEPPER_SEED,4).requires(ModItems.PEPPER).unlockedBy(getHasName(ModItems.PEPPER),has(ModItems.PEPPER)).save(exporter,"crafting/"+getItemName(ModItems.PEPPER_SEED)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModBlocks.PAPAYA_SEEDS,4).requires(ModItems.PAPAYA).unlockedBy(getHasName(ModItems.PAPAYA),has(ModItems.PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SEEDS)+"_by_papaya_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModBlocks.PAPAYA_SEEDS,4).requires(ModItems.RAW_PAPAYA).unlockedBy(getHasName(ModItems.RAW_PAPAYA),has(ModItems.RAW_PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SEEDS)+"_by_unripe_papaya_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModBlocks.PAPAYA_SEEDS,2).requires(ModItems.SLICED_PAPAYA).unlockedBy(getHasName(ModItems.SLICED_PAPAYA),has(ModItems.SLICED_PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SEEDS)+"_by_sliced_papaya_from_crafting");

            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsFabric.STIR_FRIED_NOODLE).requires(ItemsRegistry.ONION.get()).requires(ItemsRegistry.RAW_PASTA.get()).requires(Items.SUGAR).requires(Items.BOWL).requires(ModTags.LIME).unlockedBy(getHasName(ItemsRegistry.RAW_PASTA.get()),has(ItemsRegistry.RAW_PASTA.get())).save(exporter,"crafting/"+getItemName(ModItemsFabric.STIR_FRIED_NOODLE));

            MortarRecipeBuilder.mortar(ModBlocks.SOMTAM_FEAST).requires(ModItems.PEPPER,2).requires(ModTags.RAW_PAPAYA).requires(ModItems.CRAB_MEAT).unlockedBy(getHasName(ModItems.RAW_PAPAYA),has(ModItems.RAW_PAPAYA)).save(exporter,"mortar/"+getItemName(ModBlocks.SOMTAM_FEAST));
        }

        private void smithing(Consumer<FinishedRecipe> exporter){
            //SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),Ingredient.of(ModItems.DIAMOND_PASTLE),Ingredient.of(Items.NETHERITE_INGOT),RecipeCategory.TOOLS,ModItems.NETHERITE_PASTLE).unlocks(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(exporter,"smithing/"+getItemName(ModItems.NETHERITE_PASTLE)+"_from_smithing");
        }

        private void bigPackingCraft(Item result,int resultAmount,ItemLike ingredient,Consumer<FinishedRecipe> exporter){
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount).define('A',ingredient).pattern("AAA").pattern("AAA").pattern("AAA").unlockedBy(getHasName(ingredient),has(ingredient)).save(exporter,"crafting/"+getItemName(result)+"_from_crafting");
        }
    }
}