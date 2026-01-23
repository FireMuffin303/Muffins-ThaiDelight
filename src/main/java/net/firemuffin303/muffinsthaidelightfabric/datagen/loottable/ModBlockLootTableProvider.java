package net.firemuffin303.muffinsthaidelightfabric.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.common.block.BasilCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.HangingDurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.SmallDurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.feast.MangoStickyRiceFeastBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimePlantBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.StackableMangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaFlowerBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.StackablePapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.pepper.PepperCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.List;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH).invert();
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }


    @Override
    public void generate() {
        this.createSimpleLoot(ModBlocks.MORTAR);

        this.add(ModBlocks.SACK,block -> LootTable.lootTable().withPool(
                this.applyExplosionCondition(block,
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(((
                                        LootItem.lootTableItem(block).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                ).apply(
                                        CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                                .copy("Lock", "BlockEntityTag.Lock")
                                                .copy("LootTable", "BlockEntityTag.LootTable")
                                                .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                ).apply(
                                        SetContainerContents.setContents(ModBlockEntityTypes.SACK_BLOCK_ENTITY)
                                                .withEntry(DynamicLoot.dynamicEntry(SackBlock.CONTENTS)))))));

        this.createSimpleLoot(ModBlocks.LIME_CRATE);
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE);
        this.createSimpleLoot(ModBlocks.RAW_PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.MANGO_CRATE);
        this.createSimpleLoot(ModBlocks.HOLY_BASIL_CRATE);
        this.createSimpleLoot(ModBlocks.BASIL_CRATE);
        this.createSimpleLoot(ModBlocks.BAMBOO_SHOOT_CRATE);
        this.createSimpleLoot(ModBlocks.BUTTERFLY_PEA_CRATE);

        this.add(ModBlocks.LIME_PLANT,block ->  this.applyExplosionDecay(ModBlocks.LIME_SAPLING,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LimePlantBlock.HALF,DoubleBlockHalf.LOWER))
                                        )
                                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING))
                                )
                                .withPool(LootPool.lootPool()
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(LimePlantBlock.AGE,2)
                                                        .hasProperty(LimePlantBlock.HALF, DoubleBlockHalf.LOWER)
                                                )
                                        )
                                        .add(LootItem.lootTableItem(ModItems.LIME)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F, 3))
                                        )
                                )
                )
        );

        this.add(ModBlocks.LIME_BLOCK,block -> this.createStackableBlockDrop(block,List.of(1,2,3,4),LimeBlock.STACKS));

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

        this.createSimpleLoot(ModBlocks.DURIAN_SAPLING);
        this.add(ModBlocks.DURIAN_LEAVES,block -> this.createLeavesDrops(block,ModBlocks.DURIAN_SAPLING,NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.DURIAN_FLOWER);
        this.add(ModBlocks.HANGING_DURIAN, block -> LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(HangingDurianBlock.AGE,0)
                                ))
                        .add(LootItem.lootTableItem(ModItems.SMALL_DURIAN)
                                .when(VanillaBlockLoot.HAS_SILK_TOUCH)
                                .otherwise(this.applyExplosionDecay(block,LootItem.lootTableItem(ModItems.DURIAN_PULP)))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(HangingDurianBlock.AGE,1)
                                )
                        )
                        .add(LootItem.lootTableItem(ModItems.DURIAN)
                                .when(VanillaBlockLoot.HAS_SILK_TOUCH)
                                .otherwise(this.applyExplosionDecay(block,
                                        LootItem.lootTableItem(ModItems.DURIAN_PULP)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 2))
                                ))
                        )
                )

        );

        this.add(ModBlocks.SMALL_DURIAN_BLOCK,block ->
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(block)
                                        .when(VanillaBlockLoot.HAS_SILK_TOUCH)
                                        .apply(List.of(2,3),
                                                integer -> SetItemCountFunction.setCount(ConstantValue.exactly(integer))

                                                        .when(
                                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                                .hasProperty(SmallDurianBlock.STACKS,integer)
                                                                        )
                                                        )
                                        )
                                        .otherwise(this.applyExplosionDecay(block,
                                                LootItem.lootTableItem(ModItems.DURIAN_PULP)
                                                        .apply(List.of(2,3), integer -> SetItemCountFunction.setCount(ConstantValue.exactly(integer))
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                                        .hasProperty(SmallDurianBlock.STACKS,integer)
                                                                                )
                                                                )
                                                        )
                                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                                        )))
                )
        );

        this.add(ModBlocks.DURIAN_BLOCK,block -> VanillaBlockLoot.createSilkTouchDispatchTable(block,this.applyExplosionDecay(block,
                LootItem.lootTableItem(ModItems.DURIAN_PULP)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(4)))
        )));

        this.dropSelf(ModBlocks.DURIAN_PEEL_BLOCK);

        //Durian
        this.createSimpleLoot(ModBlocks.DURIAN_LOG);
        this.createSimpleLoot(ModBlocks.DURIAN_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_WOOD);
        this.createSimpleLoot(ModBlocks.DURIAN_PLANKS);
        this.createSimpleLoot(ModBlocks.DURIAN_STAIRS);
        this.createSlabItemTable(ModBlocks.DURIAN_SLAB);
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE);
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE_GATE);
        this.add(ModBlocks.DURIAN_DOOR, this::createDoorTable);
        this.createSimpleLoot(ModBlocks.DURIAN_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.DURIAN_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.DURIAN_BUTTON);
        this.createSimpleLoot(ModBlocks.DURIAN_SIGN);
        this.createSimpleLoot(ModBlocks.DURIAN_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.DURIAN_CABINET);

        this.dropOther(ModBlocks.COCONUT_SAPLING_CROP,ModItems.COCONUT);

        this.createSimpleLoot(ModBlocks.COCONUT_SAPLING);
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF);
        this.dropOther(ModBlocks.BUDDING_COCONUT_LEAF,ModBlocks.COCONUT_LEAF);
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF_BLOCK);
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF_CARPET);

        //Coconut
        this.createSimpleLoot(ModBlocks.COCONUT_LOG);
        this.createSimpleLoot(ModBlocks.COCONUT_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_WOOD);
        this.createSimpleLoot(ModBlocks.COCONUT_PLANKS);
        this.createSimpleLoot(ModBlocks.COCONUT_STAIRS);
        this.createSlabItemTable(ModBlocks.COCONUT_SLAB);
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE);
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE_GATE);
        this.add(ModBlocks.COCONUT_DOOR,this::createDoorTable);
        this.createSimpleLoot(ModBlocks.COCONUT_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.COCONUT_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.COCONUT_BUTTON);
        this.createSimpleLoot(ModBlocks.COCONUT_SIGN);
        this.createSimpleLoot(ModBlocks.COCONUT_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.COCONUT_CABINET);

        this.createSimpleLoot(ModBlocks.COCONUT);
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT);

        this.createSimpleLoot(ModBlocks.MANGO_SAPLING);
        this.add(ModBlocks.MANGO_LEAVES,block -> this.createLeavesDrops(block,ModBlocks.MANGO_SAPLING,NORMAL_LEAVES_SAPLING_CHANCES));

        //Mango
        this.createSimpleLoot(ModBlocks.MANGO_LOG);
        this.createSimpleLoot(ModBlocks.MANGO_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_WOOD);
        this.createSimpleLoot(ModBlocks.MANGO_PLANKS);
        this.createSimpleLoot(ModBlocks.MANGO_STAIRS);
        this.createSlabItemTable(ModBlocks.MANGO_SLAB);
        this.createSimpleLoot(ModBlocks.MANGO_FENCE);
        this.createSimpleLoot(ModBlocks.MANGO_FENCE_GATE);
        this.add(ModBlocks.MANGO_DOOR,this::createDoorTable);
        this.createSimpleLoot(ModBlocks.MANGO_TRAPDOOR);
        this.createSimpleLoot(ModBlocks.MANGO_PRESSURE_PLATE);
        this.createSimpleLoot(ModBlocks.MANGO_BUTTON);
        this.createSimpleLoot(ModBlocks.MANGO_SIGN);
        this.createSimpleLoot(ModBlocks.MANGO_HANGING_SIGN);
        this.createSimpleLoot(ModBlocks.MANGO_CABINET);

        this.add(ModBlocks.HANGING_MANGO_BLOCK,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HANGING_MANGO_BLOCK)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,2))
                        ).add(LootItem.lootTableItem(ModItems.MANGO))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
        );

        this.add(ModBlocks.STACKABLE_MANGO_BLOCK,block ->  this.createStackableBlockDrop(block,List.of(1,2,3),StackableMangoBlock.STACKS));
        this.add(ModBlocks.STACKABLE_PAPAYA,block ->  this.createStackableBlockDrop(block,List.of(1,2),StackablePapayaBlock.STACKS));
        this.add(ModBlocks.STACKABLE_RAW_PAPAYA,block ->  this.createStackableBlockDrop(block,List.of(1,2),StackablePapayaBlock.STACKS));

        this.createSimpleLoot(ModBlocks.PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_LOG);
        this.createSimpleLoot(ModBlocks.PAPAYA_WOOD);
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_WOOD);
        this.dropSelf(ModBlocks.PAPAYA_LEAVES);
        this.dropOther(ModBlocks.PAPAYA_LEAVES_STEM,ModBlocks.PAPAYA_LEAVES);
        this.createSimpleLoot(ModBlocks.PAPAYA_SAPLING);
        this.dropOther(ModBlocks.PAPAYA_CROP,ModItems.PAPAYA_SEEDS);


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

        this.add(ModBlocks.PAPAYA_FLOWER,block -> this.createStackableBlockDrop(block,List.of(1,2,3), PapayaFlowerBlock.FLOWERS));
        this.add(ModBlocks.WALL_PAPAYA_FLOWER,block -> this.createStackableBlockDrop(ModBlocks.PAPAYA_FLOWER,List.of(1,2,3), PapayaFlowerBlock.FLOWERS));
        this.dropOther(ModBlocks.BUDDING_PAPAYA_FLOWER,ModBlocks.PAPAYA_FLOWER);

        this.add(ModBlocks.WILD_HOLY_BASIL,block ->  this.applyExplosionDecay(block,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(block))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.HOLY_BASIL)
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))

                        )
        ));

        this.add(ModBlocks.WILD_BASIL,block ->  this.applyExplosionDecay(block,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(block))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.BASIL)
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))
                        )
        ));

        this.createSimpleLoot(ModBlocks.BUTTERFLY_PEA_WALL);
        this.add(ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,block -> this.createSingleItemTable(ModItems.BUTTERFLY_PEA_SEEDS));

        this.add(ModBlocks.BUTTERFLY_PEA_BLOCK,block -> {
            LootItemCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(ButterflyPeaVineBlock.VINE_AGE,2)
                    );

            return this.applyExplosionDecay(block,LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA)
                                            .when(condition)
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F, 3))
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3,4)))
                                            .apply(LimitCount.limitCount(IntRange.exact(4)))
                                            .otherwise(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS))
                                    )
                            ).withPool(LootPool.lootPool()
                                    .when(condition)
                                    .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS)
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F, 3))
                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                                            .apply(LimitCount.limitCount(IntRange.exact(5)))
                                    )
                            )
                    );
                }
        );


        this.dropSelf(ModBlocks.CRAB_EGG);

        this.dropOther(ModBlocks.BUDDING_PEPPER_CROP,ModItems.PEPPER_SEED);


        this.add(ModBlocks.PEPPER_CROP, block ->{
                    LootItemCondition.Builder pepperCropBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP)
                            .setProperties(
                                    StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(PepperCropBlock.AGE,2));

                   return this.applyExplosionDecay(block,
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool()
                                            .add(LootItem.lootTableItem(ModItems.PEPPER)
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                            Enchantments.BLOCK_FORTUNE,0.5714286F, 3))
                                                    .apply(LimitCount.limitCount(IntRange.upperBound(4)))
                                                    .when(pepperCropBuilder)
                                                    .otherwise(
                                                            LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                                    )
                                            )
                                    )
                                    .withPool(LootPool.lootPool()
                                            .when(pepperCropBuilder)
                                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)))

                    );
        }

        );




        this.add(ModBlocks.BASIL, block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                                .otherwise(
                                        LootItem.lootTableItem(ModItems.BASIL)
                                )
                        )
                ))
        );

        this.add(ModBlocks.HOLY_BASIL,block ->  this.applyExplosionDecay(block, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModItems.HOLY_BASIL)
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3)))
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                                        .otherwise(
                                                LootItem.lootTableItem(ModItems.HOLY_BASIL)
                                        )
                                )
                        )
                )
        );


        this.dropOther(ModBlocks.FERMENTED_FISH_CAULDRON,Items.CAULDRON);

        this.add(ModBlocks.SOMTAM_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.SOMTAM_FEAST))
                )
        ));

        this.add(ModBlocks.LARB_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.LARB_FEAST))
                )
        ));

        this.add(ModBlocks.CRAB_FRIED_RICE_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.CRAB_FRIED_RICE_FEAST))
                )
        ));

        this.add(ModBlocks.PHAT_KAPHRAO_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.PHAT_KAPHRAO_FEAST))
                )
        ));

        this.add(ModBlocks.MANGO_STICKY_RICE_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(MangoStickyRiceFeastBlock.MANGO_SERVINGS,3)
                                )
                        ).add(LootItem.lootTableItem(ModItems.MANGO_STICKY_RICE_FEAST))
                )
        ));

        this.add(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST,block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.PINEAPPLE_FRIED_RICE_FEAST))
                )
        ));
    }

    private void createSimpleLoot(Block block){
        this.add(block,this.applyExplosionDecay(block,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(block)))));
    }

    private LootTable.Builder createStackableBlockDrop(Block block, List<Integer> amount, IntegerProperty property){
        return LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                        .apply(amount,integer ->
                                                SetItemCountFunction.setCount(ConstantValue.exactly(integer))
                                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                .setProperties(
                                                                        StatePropertiesPredicate.Builder.properties()
                                                                                .hasProperty(property,integer)
                                                                )
                                                        )
                                        )
                        ))
        );
    }

    public net.minecraft.world.level.storage.loot.LootTable.Builder createLeavesDrops(Block block, Block block2, float... fs) {
        return createSilkTouchOrShearsDispatchTable(block, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)this.applyExplosionCondition(block, LootItem.lootTableItem(block2))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, fs))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_STICK_CHANCES))));
    }

}
