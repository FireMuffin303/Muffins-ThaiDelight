package net.firemuffin303.thaidelight.fabric.datagen;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeDataGen::new);
        pack.addProvider(AdvancementsDataGen::new);
    }

    private static class RecipeDataGen extends FabricRecipeProvider {

        public RecipeDataGen(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            cook(ModItems.CRAB_MEAT.get(), ModItems.COOKED_CRAB_MEAT, 0.35f, 200, exporter);
            cook(ModItems.DRAGONFLY.get(), ModItems.COOKED_DRAGONFLY, 0.35f, 200, exporter);
        }

        private void cook(ItemLike ingredient, RegistryEntry<Item> result, float exp, int cookTicks, Consumer<FinishedRecipe> exporter) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result.get(), exp, cookTicks).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, result.getId().withSuffix("_from_smelting"));
            SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result.get(), exp, cookTicks / 2).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, result.getId().withSuffix("_from_cooking"));
            SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result.get(), exp, cookTicks * 3).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, result.getId().withSuffix("_from_campfire"));
        }
    }

    private static class LootTableDataGen extends SimpleFabricLootTableProvider {

        public LootTableDataGen(FabricDataOutput output, LootContextParamSet lootContextType) {
            super(output, LootContextParamSets.ENTITY);
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
           /* biConsumer.accept(ModEntityTypes.DRAGONFLY.getId(),
                    new LootTable.Builder()
                            .pool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                    .with(LootItem.lootTableItem(ModItems.CRAB_MEAT.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f))).build())));
            */
        }
    }

    static class AdvancementsDataGen extends FabricAdvancementProvider{
        protected AdvancementsDataGen(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateAdvancement(Consumer<Advancement> consumer) {
            Advancement advancement = Advancement.Builder.advancement()
                    .display(
                            ModItems.COOKED_DRAGONFLY.get(),
                            Component.translatable("advancement.%s.cooked_dragonfly",ThaiDelight.MOD_ID),
                            Component.empty(),
                            new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                            FrameType.TASK,
                            true,
                            true,
                            true).addCriterion("got_cooked_dragonfly", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_DRAGONFLY.get())).save(consumer,ThaiDelight.MOD_ID+"/root");
        }
    }
}