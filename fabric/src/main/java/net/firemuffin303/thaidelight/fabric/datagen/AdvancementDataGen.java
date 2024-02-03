package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class AdvancementDataGen extends FabricAdvancementProvider {
    protected AdvancementDataGen(FabricDataOutput output) {
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
            .addCriterion("got_mortar", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MORTAR)).build(new ResourceLocation(ThaiDelight.MOD_ID,"root"));

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

    /*
    Advancement GOT_SAUCE_BOWL = Advancement.Builder.advancement()
            .display(
                    ModBlocks.SAUCE_BOWL,
                    Component.translatable("advancement.muffins_thaidelight.sauce_bowl"),
                    Component.translatable("advancement.muffins_thaidelight.sauce_bowl.description"),
                    new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                    FrameType.TASK,
                    true,
                    true,
                    true)
            .addCriterion("got_sauce_bowl",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SAUCE_BOWL))
            .parent(ROOT)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_sauce_bowl"));
*/

    Advancement GOT_SLICED_LIME = Advancement.Builder.advancement()
            .display(
                    ModItems.SLICED_LIME,
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime"),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime.description"),
                    new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                    FrameType.TASK,
                    true,
                    true,
                    true)
            .addCriterion("got_sliced_lime",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SLICED_LIME))
            .parent(ROOT)
            .build(new ResourceLocation(ThaiDelight.MOD_ID,"got_sliced_lime"));


    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        consumer.accept(ROOT);
        consumer.accept(GOT_COOKED_DRAGONFLY);
        //consumer.accept(GOT_SAUCE_BOWL);
        consumer.accept(GOT_SLICED_LIME);
    }
}
