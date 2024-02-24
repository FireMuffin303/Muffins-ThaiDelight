package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.thaidelight.common.block.crops.LimeCrop;
import net.firemuffin303.thaidelight.common.block.crops.PapayaBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class BlockLootTableDataGen extends FabricBlockLootTableProvider {
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH).invert();
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected BlockLootTableDataGen(FabricDataOutput dataOutput) {
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

        this.add(ModBlocks.PAPAYA_LEAVES, (block) -> this.createLeavesDrops(block, ModBlocks.PAPAYA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));


        //net.minecraft.world.level.storage.loot.LootTable.Builder sauceBowl = this.applyExplosionDecay(ModBlocks.SAUCE_BOWL,
        //        LootTable.lootTable()
        //                .withPool(LootPool.lootPool()
        //                        .add(LootItem.lootTableItem(ModBlocks.SAUCE_BOWL))));

        //this.add(ModBlocks.SAUCE_BOWL,sauceBowl);
        //this.add(ModBlocks.SEAFOOD_SAUCE_BOWL,sauceBowl);
        //this.add(ModBlocks.FISH_SAUCE_SAUCE_BOWL,sauceBowl);
        //this.add(ModBlocks.HONEY_SAUCE_BOWL,sauceBowl);

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

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkLimeLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_CROP).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(LimeCrop.AGE, 2));
        this.add(ModBlocks.LIME_CROP, (net.minecraft.world.level.storage.loot.LootTable.Builder)
                this.applyExplosionDecay(ModBlocks.LIME_SAPLING,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)))
                                .withPool(LootPool.lootPool().when(checkLimeLevel)
                                        .add(LootItem.lootTableItem(ModItems.LIME)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        this.add(ModBlocks.LIME_SAPLING,
                this.applyExplosionDecay(ModBlocks.LIME_SAPLING,
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)))));

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
}
