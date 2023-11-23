package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Optional;
import java.util.function.BiConsumer;
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
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.LOINCLOTH,1).define('A', ItemTags.WOOL).define('B', Items.WHITE_DYE).pattern("A").pattern("B").pattern("A").unlockedBy(getHasName(Items.WHITE_WOOL),has(ItemTags.WOOL)).save(exporter,"crafting/"+getItemName(ModItems.LOINCLOTH)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.SEAFOOD_BOTTLE,1).requires(Items.KELP).requires(ModItems.PEPPER).requires(Items.SUGAR).unlockedBy(getHasName(ModItems.PEPPER),has(ModItems.PEPPER)).save(exporter,"crafting/"+getItemName(ModItems.SEAFOOD_BOTTLE)+"_from_crafting");

            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.STONE_PASTLE,1).define('A', Items.COBBLESTONE).pattern("A").pattern("A").pattern("A").unlockedBy(getHasName(Items.COBBLESTONE),has(Items.COBBLESTONE)).save(exporter,"crafting/"+getItemName(ModItems.STONE_PASTLE)+"_from_crafting");
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.IRON_PASTLE,1).define('A', Items.IRON_INGOT).pattern("A").pattern("A").pattern("A").unlockedBy(getHasName(Items.IRON_INGOT),has(Items.IRON_INGOT)).save(exporter,"crafting/"+getItemName(ModItems.IRON_PASTLE)+"_from_crafting");
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.GOLDEN_PASTLE,1).define('A', Items.GOLD_INGOT).pattern("A").pattern("A").pattern("A").unlockedBy(getHasName(Items.GOLD_INGOT),has(Items.GOLD_INGOT)).save(exporter,"crafting/"+getItemName(ModItems.GOLDEN_PASTLE)+"_from_crafting");
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.DIAMOND_PASTLE,1).define('A', Items.DIAMOND).pattern("A").pattern("A").pattern("A").unlockedBy(getHasName(Items.DIAMOND),has(Items.DIAMOND)).save(exporter,"crafting/"+getItemName(ModItems.DIAMOND_PASTLE)+"_from_crafting");

            bigPackingCraft(Item.byBlock(ModBlocks.LIME_CRATE),1,ModItems.LIME,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.PEPPER_CRATE),1,ModItems.PEPPER,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.UNRIPE_PAPAYA_CRATE),1,ModItems.UNRIPE_PAPAYA,exporter);
            bigPackingCraft(Item.byBlock(ModBlocks.PAPAYA_CRATE),1,ModItems.PAPAYA,exporter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.LIME,9).requires(ModBlocks.LIME_CRATE).unlockedBy(getHasName(ModBlocks.LIME_CRATE),has(ModBlocks.LIME_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.LIME)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PEPPER,9).requires(ModBlocks.PEPPER_CRATE).unlockedBy(getHasName(ModBlocks.PEPPER_CRATE),has(ModBlocks.PEPPER_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PEPPER)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.UNRIPE_PAPAYA,9).requires(ModBlocks.UNRIPE_PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.UNRIPE_PAPAYA_CRATE),has(ModBlocks.UNRIPE_PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.UNRIPE_PAPAYA)+"_from_crafting");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PAPAYA,9).requires(ModBlocks.PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.PAPAYA_CROP),has(ModBlocks.PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PAPAYA)+"_from_crafting");

        }

        private void smithing(Consumer<FinishedRecipe> exporter){
            SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),Ingredient.of(ModItems.DIAMOND_PASTLE),Ingredient.of(Items.NETHERITE_INGOT),RecipeCategory.TOOLS,ModItems.NETHERITE_PASTLE).unlocks(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(exporter,"smithing/"+getItemName(ModItems.NETHERITE_PASTLE)+"_from_smithing");
        }

        private void bigPackingCraft(Item result,int resultAmount,ItemLike ingredient,Consumer<FinishedRecipe> exporter){
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount).define('A',ingredient).pattern("AAA").pattern("AAA").pattern("AAA").unlockedBy(getHasName(ingredient),has(ingredient)).save(exporter,"crafting/"+getItemName(result)+"_from_crafting");
        }
    }
}