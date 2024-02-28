package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class AdvancementDataGen extends FabricAdvancementProvider {
    private final ResourceLocation BACKGROUND = new ResourceLocation("minecraft","textures/block/oak_log.png");

    protected AdvancementDataGen(FabricDataOutput output) {
        super(output);
    }

    Advancement ROOT = Advancement.Builder.advancement()
            .display(ModBlocks.MORTAR,
                    Component.translatable("advancement.muffins_thaidelight.got_mortar"),
                    Component.translatable("advancement.muffins_thaidelight.got_mortar.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    false,
                    false,
                    false)
            .addCriterion("seeds", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{})).build(new ResourceLocation(ThaiDelight.MOD_ID,"root"));

    Advancement GOT_COOKED_DRAGONFLY = Advancement.Builder.advancement()
            .display(
                    ModItems.COOKED_DRAGONFLY,
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly"),
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_cooked_dragonfly",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_DRAGONFLY))
            .parent(ROOT)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_cooked_dragonfly"));


    Advancement GOT_SLICED_LIME = Advancement.Builder.advancement()
            .display(
                    ModItems.SLICED_LIME,
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime"),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_sliced_lime",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SLICED_LIME))
            .parent(ROOT)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_sliced_lime"));

    Advancement GOT_PEPPER = Advancement.Builder.advancement()
            .display(
                    ModItems.PEPPER,
                    Component.translatable("advancement.muffins_thaidelight.got_pepper"),
                    Component.translatable("advancement.muffins_thaidelight.got_pepper.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEPPER))
            .parent(ROOT).build(new ResourceLocation(ThaiDelight.MOD_ID,"got_pepper"));


    Advancement GOT_SPICY_MEAT_SALAD = Advancement.Builder.advancement()
            .display(
                    ModItemsFabric.SPICY_MINCED_MEAT_SALAD,
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad"),
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsFabric.SPICY_MINCED_MEAT_SALAD))
            .parent(GOT_PEPPER)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_spicy_meat_salad"));

    Advancement GOT_SOMTAM = Advancement.Builder.advancement()
            .display(
                    ModItemsFabric.SOMTAM,
                    Component.translatable("advancement.muffins_thaidelight.got_somtam"),
                    Component.translatable("advancement.muffins_thaidelight.got_somtam.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsFabric.SOMTAM))
            .parent(GOT_PEPPER)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_somtam"));



    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        consumer.accept(ROOT);
        consumer.accept(GOT_COOKED_DRAGONFLY);
        consumer.accept(GOT_SLICED_LIME);
        consumer.accept(GOT_PEPPER);
        consumer.accept(GOT_SPICY_MEAT_SALAD);
        consumer.accept(GOT_SOMTAM);
    }
}
