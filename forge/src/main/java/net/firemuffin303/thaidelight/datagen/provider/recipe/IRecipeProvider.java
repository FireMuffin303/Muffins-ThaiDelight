package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public interface IRecipeProvider {
    void generate(Consumer<FinishedRecipe> exporter);

    static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... args) {
        return new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, args);
    }

    static InventoryChangeTrigger.TriggerInstance has(ItemLike arg) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(new ItemLike[]{arg}).build());
    }

    static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> arg) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(arg).build());
    }

    static String getHasName(ItemLike arg) {
        return "has_" + getItemName(arg);
    }

    static String getItemName(ItemLike arg) {
        return ForgeRegistries.ITEMS.getKey(arg.asItem()).getPath();
    }

    static EnterBlockTrigger.TriggerInstance insideOf(Block arg) {
        return new EnterBlockTrigger.TriggerInstance(ContextAwarePredicate.ANY, arg, StatePropertiesPredicate.ANY);
    }

    static RecipeBuilder stairBuilder(ItemLike arg, Ingredient arg2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, arg, 4).define('#', arg2).pattern("#  ").pattern("## ").pattern("###");
    }

    static RecipeBuilder trapdoorBuilder(ItemLike arg, Ingredient arg2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, arg, 2).define('#', arg2).pattern("###").pattern("###");
    }

    static RecipeBuilder signBuilder(ItemLike arg, Ingredient arg2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, arg, 3).group("sign").define('#', arg2).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ");
    }

    static void hangingSign(Consumer<FinishedRecipe> consumer, ItemLike arg, ItemLike arg2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, arg, 6).group("hanging_sign").define('#', arg2).define('X', Items.CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_logs", has(arg2)).save(consumer);
    }

    static RecipeBuilder buttonBuilder(ItemLike arg, Ingredient arg2) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, arg).requires(arg2);
    }

    static RecipeBuilder doorBuilder(ItemLike arg, Ingredient arg2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, arg, 3).define('#', arg2).pattern("##").pattern("##").pattern("##");
    }

    static RecipeBuilder fenceBuilder(ItemLike arg, Ingredient arg2) {
        int i = arg == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = arg == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : Items.STICK;
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, arg, i).define('W', arg2).define('#', item).pattern("W#W").pattern("W#W");
    }

    static RecipeBuilder fenceGateBuilder(ItemLike arg, Ingredient arg2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, arg).define('#', Items.STICK).define('W', arg2).pattern("#W#").pattern("#W#");
    }

    static RecipeBuilder slabBuilder(RecipeCategory arg, ItemLike arg2, Ingredient arg3) {
        return ShapedRecipeBuilder.shaped(arg, arg2, 6).define('#', arg3).pattern("###");
    }

    static RecipeBuilder pressurePlateBuilder(RecipeCategory arg, ItemLike arg2, Ingredient arg3) {
        return ShapedRecipeBuilder.shaped(arg, arg2).define('#', arg3).pattern("##");
    }
}
