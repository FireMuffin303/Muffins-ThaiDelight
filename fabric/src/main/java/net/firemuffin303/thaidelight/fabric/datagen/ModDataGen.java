package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeDataGen::new);
        pack.addProvider(AdvancementsDataGen::new);
        pack.addProvider(BlockLootTableDataGen::new);
        pack.addProvider(LangDataGen::new);
        pack.addProvider(ThaiLangDataGen::new);
        pack.addProvider(ItemModelData::new);
        pack.addProvider(LootTableDataGen::new);
    }

    private static class RecipeDataGen extends FabricRecipeProvider {

        public RecipeDataGen(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            craft(exporter);
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
        }
    }

    static class BlockLootTableDataGen extends FabricBlockLootTableProvider{

        protected BlockLootTableDataGen(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPepperLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
            this.add(ModBlocks.PEPPER_CROP, (net.minecraft.world.level.storage.loot.LootTable.Builder)this.applyExplosionDecay(ModBlocks.PEPPER_CROP, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PEPPER))).withPool(LootPool.lootPool().when(checkPepperLevel).add(LootItem.lootTableItem(ModItems.PEPPER).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

            net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkLimeLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_BUSH).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
            this.add(ModBlocks.LIME_BUSH, (net.minecraft.world.level.storage.loot.LootTable.Builder)this.applyExplosionDecay(ModBlocks.LIME_BUSH, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.LIME))).withPool(LootPool.lootPool().when(checkLimeLevel).add(LootItem.lootTableItem(ModItems.LIME).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

            net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPapayaLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA_CROP).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
            net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPapayaLevel6 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA_CROP).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 6));
            this.add(ModBlocks.PAPAYA_CROP, (net.minecraft.world.level.storage.loot.LootTable.Builder)
                    this.applyExplosionDecay(ModBlocks.PAPAYA_CROP,
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool()
                                            .when(checkPapayaLevel)
                                            .add(LootItem.lootTableItem(ModItems.PAPAYA)
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))
                                    .withPool(LootPool.lootPool()
                                            .when(checkPapayaLevel6)
                                            .add(LootItem.lootTableItem(ModItems.UNRIPE_PAPAYA)
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F, 3)))
                                    )
                    ));

        }
    }

    private static class LootTableDataGen extends SimpleFabricLootTableProvider {
        protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity().flags(net.minecraft.advancements.critereon.EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

        public LootTableDataGen(FabricDataOutput output) {
            super(output, LootContextParamSets.ENTITY);
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"entities/dragonfly"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0f))
                            .add(LootItem.lootTableItem(ModItems.DRAGONFLY)
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                    .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                    )));

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"entities/flower_crab"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0f))
                            .add(LootItem.lootTableItem(ModItems.CRAB_MEAT)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,2.0f)))
                                    .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                    .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 3.0F)))
                            )));
        }
    }

    static class AdvancementsDataGen extends FabricAdvancementProvider{
        protected AdvancementsDataGen(FabricDataOutput output) {
            super(output);
        }

        Advancement ROOT = Advancement.Builder.advancement()
                .display(ModBlocks.MORTAR,
                        Component.translatable("advancement.muffins_thaidelight.got_mortar"),
                        Component.translatable("advancement.muffins_thaidelight.got_mortar.description"),
                        new ResourceLocation("textures/gui/advancement/backgrounds/adventure.png"),
                        FrameType.TASK,
                        true,
                        true,
                        true)
                .addCriterion("got_mortar",InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MORTAR)).build(new ResourceLocation(ThaiDelight.MOD_ID,"root"));

        Advancement GOT_COOKED_DRAGONFLY = Advancement.Builder.advancement()
                .display(
                        ModItems.COOKED_DRAGONFLY,
                        Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly"),
                        Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                        FrameType.TASK,
                        true,
                        true,
                        true)
                .addCriterion("got_cooked_dragonfly",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_DRAGONFLY))
                .parent(ROOT)
                .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_cooked_dragonfly"));


        @Override
        public void generateAdvancement(Consumer<Advancement> consumer) {
            consumer.accept(ROOT);
            consumer.accept(GOT_COOKED_DRAGONFLY);

        }
    }

    static class LangDataGen extends FabricLanguageProvider {
        protected LangDataGen(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtain Cooked Dragonfly.");

            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");
            translationBuilder.add(ModBlocks.CRAB_EGG,"Flower Crab Egg");
            translationBuilder.add(ModBlocks.SEAFOOD,"Seafood");
            translationBuilder.add(ModBlocks.SEAFOOD_CAULDRON,"Seafood Cauldron");
            translationBuilder.add(ModBlocks.LIME_BUSH,"Lime Bush");
            translationBuilder.add(ModBlocks.MORTAR,"Mortar");

            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
            translationBuilder.add(ModItems.CRAB_BUCKET,"Flower Crab in a Bucket");
            translationBuilder.add(ModItems.CRAB_MEAT,"Raw Crab");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"Cooked Crab");
            translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"Seafooded Cooked Crab");


            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
            translationBuilder.add(ModItems.DRAGONFLY,"Dragonfly");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"Dragonfly in a Bottle");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");

            translationBuilder.add(ModItems.SEAFOOD_BUCKET,"Seafood Bucket");
            translationBuilder.add(ModItems.FISH_SAUCE_BUCKET,"Fish Sauce Bucket");
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");

            translationBuilder.add(ModItems.LIME,"Lime");

            translationBuilder.add(ModItems.LOINCLOTH,"Loincloth");
        }
    }

    static class ThaiLangDataGen extends FabricLanguageProvider{

        protected ThaiLangDataGen(FabricDataOutput dataOutput) {
            super(dataOutput,"th_th");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","มันกินไม่ได้!");
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","ได้แมลงปอทอด");

            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");
            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET,"ปูม้าในถัง");
            translationBuilder.add(ModItems.CRAB_MEAT,"เนื้อปูสด");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"เนื้อปูสุก");
            translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"เนื้อปูสุกเคลือบซีฟู้ด");


            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY,"แมลงปอสด");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"แมลงปอในขวดแก้ว");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"แมลงปอทอด");

            translationBuilder.add(ModItems.SEAFOOD_BUCKET,"ถังน้ำจิ้มซีฟู้ด");
            translationBuilder.add(ModItems.FISH_SAUCE_BUCKET,"ถังน้ำปลา");
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");

            translationBuilder.add(ModItems.LIME,"มะนาว");

            translationBuilder.add(ModItems.LOINCLOTH,"ผ้าขาวม้า");
        }
    }

    static class ItemModelData extends FabricModelProvider {
        public ItemModelData(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            itemModelGenerator.generateFlatItem(ModItems.CRAB_BUCKET, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.COOKED_CRAB_MEAT, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT_WITH_SEAFOOD, ModelTemplates.FLAT_ITEM);

            itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_BOTTLE, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.COOKED_DRAGONFLY, ModelTemplates.FLAT_ITEM);

            itemModelGenerator.generateFlatItem(ModItems.SEAFOOD_BUCKET, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.FISH_SAUCE_BUCKET, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.FISH_SAUCE_BOTTLE, ModelTemplates.FLAT_ITEM);

            itemModelGenerator.generateFlatItem(ModItems.LIME, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.LIME_SEED, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.PEPPER, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.PEPPER_SEED, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.PAPAYA, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(ModItems.PAPAYA_SEED, ModelTemplates.FLAT_ITEM);

            itemModelGenerator.generateFlatItem(ModItems.LOINCLOTH, ModelTemplates.FLAT_ITEM);
        }
    }
}