package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.thaidelight.common.block.crops.LimeCrop;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

public class BlockLootTableDataGen extends FabricBlockLootTableProvider {
    protected BlockLootTableDataGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.createSimpleLoot(ModBlocks.MORTAR);

        this.createSimpleLoot(ModBlocks.LIME_CRATE);
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE);
        this.createSimpleLoot(ModBlocks.UNRIPE_PAPAYA_CRATE);
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE);


        net.minecraft.world.level.storage.loot.LootTable.Builder sauceBowl = this.applyExplosionDecay(ModBlocks.SAUCE_BOWL,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModBlocks.SAUCE_BOWL))));

        this.add(ModBlocks.SAUCE_BOWL,sauceBowl);
        this.add(ModBlocks.SEAFOOD_SAUCE_BOWL,sauceBowl);
        this.add(ModBlocks.FISH_SAUCE_SAUCE_BOWL,sauceBowl);
        this.add(ModBlocks.HONEY_SAUCE_BOWL,sauceBowl);

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

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPapayaLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA_TOP_STEM).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPapayaLevel6 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PAPAYA_TOP_STEM).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 6));
        this.add(ModBlocks.PAPAYA_TOP_STEM, (net.minecraft.world.level.storage.loot.LootTable.Builder)
                this.applyExplosionDecay(ModBlocks.PAPAYA_TOP_STEM,
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
}
