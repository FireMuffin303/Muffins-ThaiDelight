package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
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
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModBlockLootTableData extends FabricBlockLootTableProvider {
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH).invert();
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected ModBlockLootTableData(FabricDataOutput dataOutput) {
        super(dataOutput);
    }


    @Override
    public void generate() {
        this.createSimpleLoot(ModBlocks.MORTAR);

        this.createSimpleLoot(ModBlocks.LIME_CRATE);
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE);
        this.createSimpleLoot(ModBlocks.RAW_PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE);

        this.createSimpleLoot(ModBlocks.PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.PAPAYA_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_WOOD);
        this.createSimpleLoot(ModBlocks.PAPAYA_SAPLING);
        this.createSimpleLoot(ModBlocks.LIME_SAPLING);

        this.add(ModBlocks.PAPAYA_LEAVES, (block) -> this.createLeavesDrops(block, ModBlocks.PAPAYA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.PAPAYA,this.applyExplosionDecay(ModBlocks.PAPAYA,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PapayaBlock.AGE,1))
                                )
                                .add(LootItem.lootTableItem(ModItems.RAW_PAPAYA))
                        )

                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PapayaBlock.AGE,2))
                                ).add(LootItem.lootTableItem(ModItems.PAPAYA))
                        )
        ));

        this.add(ModBlocks.CRAB_EGG,this.applyExplosionDecay(ModBlocks.CRAB_EGG,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(BlockLootSubProvider.HAS_SILK_TOUCH)
                                .add(LootItem.lootTableItem(ModBlocks.CRAB_EGG)))));

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkLimeLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_BUSH).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(LimeCropBlock.AGE, 2));
        this.add(ModBlocks.LIME_BUSH, (net.minecraft.world.level.storage.loot.LootTable.Builder)
                this.applyExplosionDecay(ModItems.LIME_SAPLING,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.LIME_SAPLING)))
                                .withPool(LootPool.lootPool().when(checkLimeLevel)
                                        .add(LootItem.lootTableItem(ModItems.LIME)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPepperLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
        this.add(ModBlocks.PEPPER_CROP, (net.minecraft.world.level.storage.loot.LootTable.Builder)this.applyExplosionDecay(ModBlocks.PEPPER_CROP, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PEPPER))).withPool(LootPool.lootPool().when(checkPepperLevel).add(LootItem.lootTableItem(ModItems.PEPPER).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));


        this.add(ModBlocks.WILD_PEPPER_CROP,this.applyExplosionDecay(ModBlocks.WILD_PEPPER_CROP,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(ModBlocks.WILD_PEPPER_CROP))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER)
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))

                        )
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))
                        )
        ));
    }

    private void createSimpleLoot(Block block){
        this.add(block,this.applyExplosionDecay(block,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(block)))));
    }

    public net.minecraft.world.level.storage.loot.LootTable.Builder createLeavesDrops(Block block, Block block2, float... fs) {
        return createSilkTouchOrShearsDispatchTable(block, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)this.applyExplosionCondition(block, LootItem.lootTableItem(block2))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, fs))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_STICK_CHANCES))));
    }

    public static class ModEntityLootTableData extends SimpleFabricLootTableProvider{
        protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity().flags(net.minecraft.advancements.critereon.EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

        public ModEntityLootTableData(FabricDataOutput output) {
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

    public static class ModChestLootTableData extends SimpleFabricLootTableProvider{

        public ModChestLootTableData(FabricDataOutput output) {
            super(output, LootContextParamSets.CHEST);
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
            LootTable.Builder villageLoot = LootTable.lootTable().withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1,4))
                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModItems.LIME_SAPLING)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f)))));

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_desert_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_plains_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_taiga_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_snowy_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_savanna_house"),villageLoot);

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/abandoned_mineshaft"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModItems.LIME_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/pillager_outpost"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModItems.LIME_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"chests/village/village_thai_house"),
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1,6))
                                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModItems.LIME_SAPLING)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.CABBAGE_SEEDS.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.TOMATO_SEEDS.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(Items.WHEAT_SEEDS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                                    .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,5.0f))))
                                    .add(LootItem.lootTableItem(Items.CARROT)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                                    .add(LootItem.lootTableItem(Items.POTATO)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                            ));
        }
    }
}
