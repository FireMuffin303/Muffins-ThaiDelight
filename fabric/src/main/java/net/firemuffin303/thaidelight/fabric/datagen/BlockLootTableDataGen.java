package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.firemuffin303.thaidelight.common.block.crops.LimeCrop;
import net.firemuffin303.thaidelight.common.block.crops.PapayaBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
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
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockLootTableDataGen extends FabricBlockLootTableProvider {
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected BlockLootTableDataGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.createSimpleLoot(ModBlocks.MORTAR.get());

        this.createSimpleLoot(ModBlocks.LIME_CRATE.get());
        this.createSimpleLoot(ModBlocks.PEPPER_CRATE.get());
        this.createSimpleLoot(ModBlocks.RAW_PAPAYA_CRATE.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_CRATE.get());

        this.createSimpleLoot(ModBlocks.PAPAYA_LOG.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_LOG.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_WOOD.get());
        this.createSimpleLoot(ModBlocks.STRIPPED_PAPAYA_WOOD.get());
        this.createSimpleLoot(ModBlocks.PAPAYA_SAPLING.get());

        this.add(ModBlocks.PAPAYA_LEAVES.get(), (block) -> this.createLeavesDrops(block, ModBlocks.PAPAYA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


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

        this.add(ModBlocks.CRAB_EGG.get(),this.applyExplosionDecay(ModBlocks.CRAB_EGG.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(hasSilkTouch())
                                .add(LootItem.lootTableItem(ModBlocks.CRAB_EGG.get())))));

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkLimeLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIME_CROP.get()).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(LimeCrop.AGE, 2));
        this.add(ModBlocks.LIME_CROP.get(), (net.minecraft.world.level.storage.loot.LootTable.Builder)
                this.applyExplosionDecay(ModBlocks.LIME_SAPLING.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())))
                                .withPool(LootPool.lootPool().when(checkLimeLevel)
                                        .add(LootItem.lootTableItem(ModItems.LIME.get())
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

        this.add(ModBlocks.LIME_SAPLING.get(),
                this.applyExplosionDecay(ModBlocks.LIME_SAPLING.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())))));

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder checkPepperLevel = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get()).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(CarrotBlock.AGE, 7));
        this.add(ModBlocks.PEPPER_CROP.get(),
                this.applyExplosionDecay(ModBlocks.PEPPER_CROP.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.PEPPER.get())))
                                .withPool(LootPool.lootPool().when(checkPepperLevel).add(LootItem.lootTableItem(ModItems.PEPPER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));


        this.add(ModBlocks.WILD_PEPPER_CROP.get(), this.applyExplosionDecay(ModBlocks.WILD_PEPPER_CROP.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS)
                                .add(LootItem.lootTableItem(ModBlocks.WILD_PEPPER_CROP.get()))

                        )

                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))

                        )
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.invert())
                                .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))
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
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchOrShearsDispatchTable(block,
                ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)this.applyExplosionCondition(block, LootItem.lootTableItem(block2))).when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), fs))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS.or(this.hasSilkTouch()).invert()).add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))).when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES))));
    }

    public LootItemCondition.Builder hasSilkTouch() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(registryLookup.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1))))));
    }


}
