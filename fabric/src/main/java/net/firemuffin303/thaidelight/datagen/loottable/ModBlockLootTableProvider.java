package net.firemuffin303.thaidelight.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.thaidelight.common.block.SackBlock;
import net.firemuffin303.thaidelight.common.block.feast.MangoStickyRiceFeastBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.basil.BasilCropBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.durian.HangingDurianBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.durian.SmallDurianBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimeBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimePlantBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.StackableMangoBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.PapayaBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.PapayaFlowerBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.StackablePapayaBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.pepper.PepperCropBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
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

import java.lang.ref.Reference;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.createSimpleLoot(ModBlocks.MORTAR.get());

        this.add(ModBlocks.SACK.get(),block -> LootTable.lootTable().withPool(
                this.applyExplosionCondition(block,
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(((
                                        LootItem.lootTableItem(block).apply(CopyComponentsFunction.copyComponents(
                                                CopyComponentsFunction.Source.BLOCK_ENTITY
                                        )
                                                .include(DataComponents.CUSTOM_NAME)
                                                        .include(DataComponents.CONTAINER)
                                                        .include(DataComponents.LOCK)
                                                        .include(DataComponents.CONTAINER_LOOT)
                                        )
                                ))))));

        this.createSimpleLoot(ModBlocks.LIME_CRATE.get());
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE.get());
        this.createSimpleLoot(ModBlocks.RAW_PAPAYA_CRATE.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE.get());
        this.createSimpleLoot(ModBlocks.MANGO_CRATE.get());
        this.createSimpleLoot(ModBlocks.HOLY_BASIL_CRATE.get());
        this.createSimpleLoot(ModBlocks.BASIL_CRATE.get());
        this.createSimpleLoot(ModBlocks.BAMBOO_SHOOT_CRATE.get());
        this.createSimpleLoot(ModBlocks.BUTTERFLY_PEA_CRATE.get());

        this.add(ModBlocks.LIME_PLANT.get(),block ->  this.applyExplosionDecay(ModBlocks.LIME_SAPLING.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LimePlantBlock.HALF,DoubleBlockHalf.LOWER))
                                        )
                                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get()))
                                )
                                .withPool(LootPool.lootPool()
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(LimePlantBlock.AGE,2)
                                                        .hasProperty(LimePlantBlock.HALF, DoubleBlockHalf.LOWER)
                                                )
                                        )
                                        .add(LootItem.lootTableItem(ModItems.LIME.get())
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                        registryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F, 3))
                                        )
                                )
                )
        );

        this.add(ModBlocks.LIME_BLOCK.get(),block -> this.createStackableBlockDrop(block,List.of(1,2,3,4), LimeBlock.STACKS));

        this.add(ModBlocks.WILD_PEPPER_CROP.get(),this.applyExplosionDecay(ModBlocks.WILD_PEPPER_CROP.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(ModBlocks.WILD_PEPPER_CROP.get()))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))

                        )
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))
                        )
        ));

        this.createSimpleLoot(ModBlocks.DURIAN_SAPLING.get());
        this.add(ModBlocks.DURIAN_LEAVES.get(),block -> this.createLeavesDrops(block,ModBlocks.DURIAN_SAPLING.get(),NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.DURIAN_FLOWER.get());
        this.add(ModBlocks.HANGING_DURIAN.get(), block -> LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(HangingDurianBlock.AGE,0)
                                ))
                        .add(LootItem.lootTableItem(ModItems.SMALL_DURIAN.get())
                                .when(this.hasSilkTouch())
                                .otherwise(this.applyExplosionDecay(block,LootItem.lootTableItem(ModItems.DURIAN_PULP.get())))
                        )
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(HangingDurianBlock.AGE,1)
                                )
                        )
                        .add(LootItem.lootTableItem(ModItems.DURIAN.get()))
                )

        );

        this.add(ModBlocks.SMALL_DURIAN_BLOCK.get(),block ->
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(block)
                                        .when(this.hasSilkTouch())
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
                                                LootItem.lootTableItem(ModItems.DURIAN_PULP.get())
                                                        .apply(List.of(2,3), integer -> SetItemCountFunction.setCount(ConstantValue.exactly(integer))
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                                        .hasProperty(SmallDurianBlock.STACKS,integer)
                                                                                )
                                                                )
                                                        )
                                                        .apply(ApplyBonusCount.addUniformBonusCount(
                                                                registryLookup.getOrThrow(Enchantments.FORTUNE)
                                                        ))
                                                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                                        )))
                )
        );

        this.add(ModBlocks.DURIAN_BLOCK.get(),block -> this.createSilkTouchDispatchTable(block,this.applyExplosionDecay(block,
                LootItem.lootTableItem(ModItems.DURIAN_PULP.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(4)))
        )));

        this.dropSelf(ModBlocks.DURIAN_PEEL_BLOCK.get());

        //Durian
        this.createSimpleLoot(ModBlocks.DURIAN_LOG.get());
        this.createSimpleLoot(ModBlocks.DURIAN_WOOD.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_LOG.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_DURIAN_WOOD.get());
        this.createSimpleLoot(ModBlocks.DURIAN_PLANKS.get());
        this.createSimpleLoot(ModBlocks.DURIAN_STAIRS.get());
        this.createSlabItemTable(ModBlocks.DURIAN_SLAB.get());
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE.get());
        this.createSimpleLoot(ModBlocks.DURIAN_FENCE_GATE.get());
        this.add(ModBlocks.DURIAN_DOOR.get(), this::createDoorTable);
        this.createSimpleLoot(ModBlocks.DURIAN_TRAPDOOR.get());
        this.createSimpleLoot(ModBlocks.DURIAN_PRESSURE_PLATE.get());
        this.createSimpleLoot(ModBlocks.DURIAN_BUTTON.get());
        this.createSimpleLoot(ModBlocks.DURIAN_SIGN.get());
        this.createSimpleLoot(ModBlocks.DURIAN_HANGING_SIGN.get());
        this.createSimpleLoot(ModBlocks.DURIAN_CABINET.get());

        this.dropOther(ModBlocks.COCONUT_SAPLING_CROP.get(),ModItems.COCONUT.get());

        this.createSimpleLoot(ModBlocks.COCONUT_SAPLING.get());
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF.get());
        this.dropOther(ModBlocks.BUDDING_COCONUT_LEAF.get(),ModBlocks.COCONUT_LEAF.get());
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF_BLOCK.get());
        this.createSimpleLoot(ModBlocks.COCONUT_LEAF_CARPET.get());

        //Coconut
        this.createSimpleLoot(ModBlocks.COCONUT_LOG.get());
        this.createSimpleLoot(ModBlocks.COCONUT_WOOD.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_LOG.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        this.createSimpleLoot(ModBlocks.COCONUT_PLANKS.get());
        this.createSimpleLoot(ModBlocks.COCONUT_STAIRS.get());
        this.createSlabItemTable(ModBlocks.COCONUT_SLAB.get());
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE.get());
        this.createSimpleLoot(ModBlocks.COCONUT_FENCE_GATE.get());
        this.add(ModBlocks.COCONUT_DOOR.get(),this::createDoorTable);
        this.createSimpleLoot(ModBlocks.COCONUT_TRAPDOOR.get());
        this.createSimpleLoot(ModBlocks.COCONUT_PRESSURE_PLATE.get());
        this.createSimpleLoot(ModBlocks.COCONUT_BUTTON.get());
        this.createSimpleLoot(ModBlocks.COCONUT_SIGN.get());
        this.createSimpleLoot(ModBlocks.COCONUT_HANGING_SIGN.get());
        this.createSimpleLoot(ModBlocks.COCONUT_CABINET.get());

        this.createSimpleLoot(ModBlocks.COCONUT.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_COCONUT.get());

        this.createSimpleLoot(ModBlocks.MANGO_SAPLING.get());
        this.add(ModBlocks.MANGO_LEAVES.get(),block -> this.createLeavesDrops(block,ModBlocks.MANGO_SAPLING.get(),NORMAL_LEAVES_SAPLING_CHANCES));

        //Mango
        this.createSimpleLoot(ModBlocks.MANGO_LOG.get());
        this.createSimpleLoot(ModBlocks.MANGO_WOOD.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_LOG.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_MANGO_WOOD.get());
        this.createSimpleLoot(ModBlocks.MANGO_PLANKS.get());
        this.createSimpleLoot(ModBlocks.MANGO_STAIRS.get());
        this.createSlabItemTable(ModBlocks.MANGO_SLAB.get());
        this.createSimpleLoot(ModBlocks.MANGO_FENCE.get());
        this.createSimpleLoot(ModBlocks.MANGO_FENCE_GATE.get());
        this.add(ModBlocks.MANGO_DOOR.get(),this::createDoorTable);
        this.createSimpleLoot(ModBlocks.MANGO_TRAPDOOR.get());
        this.createSimpleLoot(ModBlocks.MANGO_PRESSURE_PLATE.get());
        this.createSimpleLoot(ModBlocks.MANGO_BUTTON.get());
        this.createSimpleLoot(ModBlocks.MANGO_SIGN.get());
        this.createSimpleLoot(ModBlocks.MANGO_HANGING_SIGN.get());
        this.createSimpleLoot(ModBlocks.MANGO_CABINET.get());

        this.add(ModBlocks.HANGING_MANGO_BLOCK.get(),LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HANGING_MANGO_BLOCK.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HangingDurianBlock.AGE,2))
                        ).add(LootItem.lootTableItem(ModItems.MANGO.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
        );

        this.add(ModBlocks.STACKABLE_MANGO_BLOCK.get(),block ->  this.createStackableBlockDrop(block,List.of(1,2,3), StackableMangoBlock.STACKS));
        this.add(ModBlocks.STACKABLE_PAPAYA.get(),block ->  this.createStackableBlockDrop(block,List.of(1,2), StackablePapayaBlock.STACKS));
        this.add(ModBlocks.STACKABLE_RAW_PAPAYA.get(),block ->  this.createStackableBlockDrop(block,List.of(1,2),StackablePapayaBlock.STACKS));

        this.createSimpleLoot(ModBlocks.PAPAYA_LOG.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_LOG.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_WOOD.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_WOOD.get());
        this.dropSelf(ModBlocks.PAPAYA_LEAVES.get());
        this.dropOther(ModBlocks.PAPAYA_LEAVES_STEM.get(),ModBlocks.PAPAYA_LEAVES.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_SAPLING.get());
        this.dropOther(ModBlocks.PAPAYA_CROP.get(),ModItems.PAPAYA_SEEDS.get());


        this.add(ModBlocks.PAPAYA.get(),this.applyExplosionDecay(ModBlocks.PAPAYA.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PapayaBlock.AGE,1))
                                )
                                .add(LootItem.lootTableItem(ModItems.RAW_PAPAYA.get()))
                        )

                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PapayaBlock.AGE,2))
                                ).add(LootItem.lootTableItem(ModItems.PAPAYA.get()))
                        )
        ));

        this.add(ModBlocks.PAPAYA_FLOWER.get(),block -> this.createStackableBlockDrop(block,List.of(1,2,3), PapayaFlowerBlock.FLOWERS));
        this.add(ModBlocks.WALL_PAPAYA_FLOWER.get(),block -> this.createStackableBlockDrop(ModBlocks.PAPAYA_FLOWER.get(),List.of(1,2,3), PapayaFlowerBlock.FLOWERS));
        this.dropOther(ModBlocks.BUDDING_PAPAYA_FLOWER.get(),ModBlocks.PAPAYA_FLOWER.get());

        this.add(ModBlocks.WILD_BASIL.get(),block ->  this.applyExplosionDecay(block,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(block))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.BASIL.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))
                        )
        ));

        this.createSimpleLoot(ModBlocks.BUTTERFLY_PEA_WALL.get());
        this.add(ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get(),block -> this.createSingleItemTable(ModItems.BUTTERFLY_PEA_SEEDS.get()));

        this.add(ModBlocks.BUTTERFLY_PEA_BLOCK.get(),block -> {
            LootItemCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(ButterflyPeaVineBlock.VINE_AGE,2)
                    );

            return this.applyExplosionDecay(block,LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA.get())
                                            .when(condition)
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F, 3))
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3,4)))
                                            .apply(LimitCount.limitCount(IntRange.exact(4)))
                                            .otherwise(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS.get()))
                                    )
                            ).withPool(LootPool.lootPool()
                                    .when(condition)
                                    .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS.get())
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F, 3))
                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                                            .apply(LimitCount.limitCount(IntRange.exact(5)))
                                    )
                            )
                    );
                }
        );


        this.dropSelf(ModBlocks.CRAB_EGG.get());

        this.dropOther(ModBlocks.BUDDING_PEPPER_CROP.get(),ModItems.PEPPER_SEED.get());


        this.add(ModBlocks.PEPPER_CROP.get(), block ->{
                    LootItemCondition.Builder pepperCropBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get())
                            .setProperties(
                                    StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(PepperCropBlock.AGE,2));

                   return this.applyExplosionDecay(block,
                            LootTable.lootTable()
                                    .withPool(LootPool.lootPool()
                                            .add(LootItem.lootTableItem(ModItems.PEPPER.get())
                                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                            registryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F, 3))
                                                    .apply(LimitCount.limitCount(IntRange.upperBound(4)))
                                                    .when(pepperCropBuilder)
                                                    .otherwise(
                                                            LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                                    )
                                            )
                                    )
                                    .withPool(LootPool.lootPool()
                                            .when(pepperCropBuilder)
                                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())))

                    );
        }

        );




        this.add(ModBlocks.BASIL.get(), block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL.get())
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BasilCropBlock.AGE,3)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                                .otherwise(
                                        LootItem.lootTableItem(ModItems.BASIL.get())
                                )
                        )
                ))
        );


        this.dropOther(ModBlocks.FERMENTED_FISH_CAULDRON.get(),Items.CAULDRON);

        this.add(ModBlocks.SOMTAM_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.SOMTAM_FEAST.get()))
                )
        ));

        this.add(ModBlocks.LARB_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.LARB_FEAST.get()))
                )
        ));

        this.add(ModBlocks.CRAB_FRIED_RICE_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.CRAB_FRIED_RICE_FEAST.get()))
                )
        ));

        this.add(ModBlocks.PHAT_KAPHRAO_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.PHAT_KAPHRAO_FEAST.get()))
                )
        ));

        this.add(ModBlocks.MANGO_STICKY_RICE_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(MangoStickyRiceFeastBlock.MANGO_SERVINGS,3)
                                )
                        ).add(LootItem.lootTableItem(ModItems.MANGO_STICKY_RICE_FEAST.get()))
                )
        ));

        this.add(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get()))
                )
        ));

        this.add(ModBlocks.DURIAN_SLICE_FEAST.get(),block -> this.applyExplosionDecay(block,LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(FeastBlock.SERVINGS,4)
                                )
                        ).add(LootItem.lootTableItem(ModItems.DURIAN_SLICE_FEAST.get()))
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



}
