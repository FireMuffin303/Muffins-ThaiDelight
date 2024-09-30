package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementDataGen extends FabricAdvancementProvider {
    private final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/oak_log.png");

    protected AdvancementDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output,registryLookup);
    }

    AdvancementHolder ROOT = Advancement.Builder.advancement()
            .display(ModBlocks.MORTAR.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_mortar"),
                    Component.translatable("advancement.muffins_thaidelight.got_mortar.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    false,
                    false,
                    false)
            .addCriterion("seeds",
                    InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
            .build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"root"));

    AdvancementHolder GOT_COOKED_DRAGONFLY = Advancement.Builder.advancement()
            .display(
                    ModItems.COOKED_DRAGONFLY.get(),
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly"),
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_cooked_dragonfly",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_DRAGONFLY.get()))
            .parent(ROOT)
            .build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"got_cooked_dragonfly"));


    AdvancementHolder GOT_SLICED_LIME = Advancement.Builder.advancement()
            .display(
                    ModItems.SLICED_LIME.get(),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime"),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_sliced_lime",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SLICED_LIME.get()))
            .parent(ROOT)
            .build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"got_sliced_lime"));

    AdvancementHolder GOT_PEPPER = Advancement.Builder.advancement()
            .display(
                    ModItems.PEPPER.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_pepper"),
                    Component.translatable("advancement.muffins_thaidelight.got_pepper.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEPPER.get()))
            .parent(ROOT).build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"got_pepper"));


    AdvancementHolder GOT_SPICY_MEAT_SALAD = Advancement.Builder.advancement()
            .display(
                    ModItemsFabric.SPICY_MINCED_MEAT_SALAD,
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad"),
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsFabric.SPICY_MINCED_MEAT_SALAD))
            .parent(GOT_PEPPER)
            .build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"got_spicy_meat_salad"));

    AdvancementHolder GOT_SOMTAM = Advancement.Builder.advancement()
            .display(
                    ModItemsFabric.SOMTAM,
                    Component.translatable("advancement.muffins_thaidelight.got_somtam"),
                    Component.translatable("advancement.muffins_thaidelight.got_somtam.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsFabric.SOMTAM))
            .parent(GOT_PEPPER)
            .build(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"got_somtam"));



    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        consumer.accept(ROOT);
        consumer.accept(GOT_COOKED_DRAGONFLY);
        consumer.accept(GOT_SLICED_LIME);
        consumer.accept(GOT_PEPPER);
        consumer.accept(GOT_SPICY_MEAT_SALAD);
        consumer.accept(GOT_SOMTAM);
    }
}
