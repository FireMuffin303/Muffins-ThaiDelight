package net.firemuffin303.muffinsthaidelightfabric.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.common.block.BasilCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.HangingDurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimePlantBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH).invert();
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }


    @Override
    public void generate() {
        this.createSimpleLoot(ModBlocks.MORTAR);

        this.createSimpleLoot(ModBlocks.LIME_CRATE);
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE);
        this.createSimpleLoot(ModBlocks.RAW_PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.DURIAN_CRATE);
        this.createSimpleLoot(ModBlocks.MANGO_CRATE);
        this.createSimpleLoot(ModBlocks.COCONUT_CRATE);
        this.createSimpleLoot(ModBlocks.HOLY_BASIL_CRATE);
        this.createSimpleLoot(ModBlocks.BASIL_CRATE);

        this.createSimpleLoot(ModBlocks.PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.PAPAYA_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_WOOD);
        this.createSimpleLoot(ModBlocks.PAPAYA_SAPLING);
        this.createSimpleLoot(ModBlocks.LIME_SAPLING);
        this.createSimpleLoot(ModBlocks.DURIAN_SAPLING);
        this.createSimpleLoot(ModBlocks.MANGO_SAPLING);
        this.createSimpleLoot(ModBlocks.COCONUT_SAPLING);

        //Durian
        this.createSimpleLoot(ModBlocks.DURIAN_LOG);
        this.createSimpleLoot(ModBlocks.DURIAN_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_WOOD);
        this.createSimpleLoot(ModBlocks.DURIAN_PLANKS);
        this.createSimpleLoot(ModBlocks.DURIAN_STAIRS);
        this.createSimpleLoot(ModBlocks.DURIAN_SLAB);
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE);
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE_GATE);
        this.createSimpleLoot(ModBlocks.DURIAN_DOOR);
        this.createSimpleLoot(ModBlocks.DURIAN_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.DURIAN_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.DURIAN_BUTTON);
        this.createSimpleLoot(ModBlocks.DURIAN_SIGN);
        this.createSimpleLoot(ModBlocks.DURIAN_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.DURIAN_CABINET);

        this.add(ModBlocks.DURIAN_LEAVES, (block) -> this.createLeavesDrops(block, ModBlocks.DURIAN_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.DURIAN_FLOWER);

        this.add(ModBlocks.DURIAN_BLOCK,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DURIAN_BLOCK)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,0))
                        ).add(LootItem.lootTableItem(ModItems.DURIAN_PULP))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DURIAN_BLOCK)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,1))
                        ).add(LootItem.lootTableItem(ModItems.DURIAN_PULP))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(3)))
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DURIAN_BLOCK)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,2))
                        ).add(LootItem.lootTableItem(ModItems.DURIAN_PULP))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(4)))
                )
        );

        this.add(ModBlocks.MANGO_BLOCK,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MANGO_BLOCK)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,2))
                        ).add(LootItem.lootTableItem(ModItems.MANGO))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
        );

        //Mango
        this.createSimpleLoot(ModBlocks.MANGO_LOG);
        this.createSimpleLoot(ModBlocks.MANGO_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_WOOD);
        this.createSimpleLoot(ModBlocks.MANGO_PLANKS);
        this.createSimpleLoot(ModBlocks.MANGO_STAIRS);
        this.createSimpleLoot(ModBlocks.MANGO_SLAB);
        this.createSimpleLoot(ModBlocks.MANGO_FENCE);
        this.createSimpleLoot(ModBlocks.MANGO_FENCE_GATE);
        this.createSimpleLoot(ModBlocks.MANGO_DOOR);
        this.createSimpleLoot(ModBlocks.MANGO_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.MANGO_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.MANGO_BUTTON);
        this.createSimpleLoot(ModBlocks.MANGO_SIGN);
        this.createSimpleLoot(ModBlocks.MANGO_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.MANGO_CABINET);

        //Coconut
        this.createSimpleLoot(ModBlocks.COCONUT_LOG);
        this.createSimpleLoot(ModBlocks.COCONUT_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_WOOD);
        this.createSimpleLoot(ModBlocks.COCONUT_PLANKS);
        this.createSimpleLoot(ModBlocks.COCONUT_STAIRS);
        this.createSimpleLoot(ModBlocks.COCONUT_SLAB);
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE);
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE_GATE);
        this.createSimpleLoot(ModBlocks.COCONUT_DOOR);
        this.createSimpleLoot(ModBlocks.COCONUT_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.COCONUT_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.COCONUT_BUTTON);
        this.createSimpleLoot(ModBlocks.COCONUT_SIGN);
        this.createSimpleLoot(ModBlocks.COCONUT_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.COCONUT_CABINET);

        this.createSimpleLoot(ModBlocks.COCONUT_LEAF_BLOCK);
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF);

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
                                .add(LootItem.lootTableItem(ModBlocks.CRAB_EGG)))));

        this.add(ModBlocks.LIME_PLANT,this.applyExplosionDecay(ModBlocks.LIME_SAPLING,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_PLANT)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LimePlantBlock.HALF,DoubleBlockHalf.LOWER))
                                )
                                .add(LootItem.lootTableItem(ModItems.LIME_SAPLING))
                        )
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_PLANT)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LimePlantBlock.AGE,2))
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LimePlantBlock.HALF, DoubleBlockHalf.LOWER))
                                )
                                .add(LootItem.lootTableItem(ModItems.LIME)
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F, 3))
                                )
                        )
                )
        );


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

        this.add(ModBlocks.BASIL,new LootTable.Builder()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3))
                        )
                        .add(LootItem.lootTableItem(ModItems.BASIL)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3))
                        )
                        .add(LootItem.lootTableItem(ModItems.BASIL_SAPLING)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,2))
                        )
                        .add(LootItem.lootTableItem(ModItems.BASIL)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,2))
                        )
                        .add(LootItem.lootTableItem(ModItems.BASIL_SAPLING)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2)))
                        )
                )
        );

        this.add(ModBlocks.HOLY_BASIL,new LootTable.Builder()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HOLY_BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3))
                        )
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HOLY_BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3))
                        )
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL_SAPLING)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HOLY_BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,2))
                        )
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HOLY_BASIL)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,2))
                        )
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL_SAPLING)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2)))
                        )
                )
        );

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

}
