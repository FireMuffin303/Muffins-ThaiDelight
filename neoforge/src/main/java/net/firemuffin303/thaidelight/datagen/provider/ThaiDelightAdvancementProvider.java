package net.firemuffin303.thaidelight.datagen.provider;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeAdvancementProvider;

import java.util.function.Consumer;

public class ThaiDelightAdvancementProvider implements NeoForgeAdvancementProvider.AdvancementGenerator {
    private final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/oak_log.png");

    /*
    Advancement ROOT = Advancement.Builder.advancement()
            .display(ModBlocks.MORTAR.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_mortar"),
                    Component.translatable("advancement.muffins_thaidelight.got_mortar.description"),
                    BACKGROUND,
                    AdvancementType.TASK,
                    false,
                    false,
                    false)
            .addCriterion("seeds", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
            .build(ThaiDelightCommon.modid("root"));

    Advancement GOT_COOKED_DRAGONFLY = Advancement.Builder.advancement()
            .display(
                    ModItems.COOKED_DRAGONFLY.get(),
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly"),
                    Component.translatable("advancement.muffins_thaidelight.cooked_dragonfly.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_cooked_dragonfly",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COOKED_DRAGONFLY.get()))
            .parent(ROOT)
            .build(ThaiDelightCommon.modid("got_cooked_dragonfly"));


    Advancement GOT_SLICED_LIME = Advancement.Builder.advancement()
            .display(
                    ModItems.SLICED_LIME.get(),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime"),
                    Component.translatable("advancement.muffins_thaidelight.sliced_lime.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_sliced_lime",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SLICED_LIME.get()))
            .parent(ROOT)
            .build(ThaiDelightCommon.modid("got_sliced_lime"));

    Advancement GOT_PEPPER = Advancement.Builder.advancement()
            .display(
                    ModItems.PEPPER.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_pepper"),
                    Component.translatable("advancement.muffins_thaidelight.got_pepper.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEPPER.get()))
            .parent(ROOT).build(ThaiDelightCommon.modid("got_pepper"));


    Advancement GOT_SPICY_MEAT_SALAD = Advancement.Builder.advancement()
            .display(
                    ModItems.LARB.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad"),
                    Component.translatable("advancement.muffins_thaidelight.got_spicy_meat_salad.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LARB.get()))
            .parent(GOT_PEPPER)
            .build(ThaiDelightCommon.modid("got_spicy_meat_salad"));

    Advancement GOT_SOMTAM = Advancement.Builder.advancement()
            .display(
                    ModItems.SOMTAM.get(),
                    Component.translatable("advancement.muffins_thaidelight.got_somtam"),
                    Component.translatable("advancement.muffins_thaidelight.got_somtam.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false)
            .addCriterion("got_spicy_meat_salad",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SOMTAM.get()))
            .parent(GOT_PEPPER)
            .build(ThaiDelightCommon.modid("got_somtam"));

    Advancement BEING_STINKY = Advancement.Builder.advancement()
            .display(ModItems.FERMENTED_FISH.get(),
                    Component.translatable("advancement.muffins_thaidelight.being_stinky"),
                    Component.translatable("advancement.muffins_thaidelight.being_stinky.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,
                    true,
                    false
            ).addCriterion("being_stinky", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(ModMobEffects.STINKY.get())))
            .parent(ROOT)
            .build(ThaiDelightCommon.modid("being_stinky"));


    Advancement SUSIE_PRIZE = Advancement.Builder.advancement()
            .display(ModItems.PAPAYA_FLOWER.get(),
                    Component.translatable("advancement.muffins_thaidelight.susie_prize"),
                    Component.translatable("advancement.muffins_thaidelight.susie_prize.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,true,false
            )
            .addCriterion("susie_prize",
                    ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                            LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(
                                    ModBlocks.WALL_PAPAYA_FLOWER.get(),
                                    ModBlocks.PAPAYA_FLOWER.get()).build()),
                            ItemPredicate.Builder.item().of(Items.GLOW_INK_SAC)
                    )
            ).parent(ROOT)
            .build(ThaiDelightCommon.modid("susie_prize"));

    Advancement BIG_BIG_NUT = Advancement.Builder.advancement()
            .display(ModItems.COCONUT.get(),
                    Component.translatable("advancement.muffins_thaidelight.big_big_nut"),
                    Component.translatable("advancement.muffins_thaidelight.big_big_nut.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,true,false
            )
            .addCriterion("big_big_nut", SackCatchTrigger.TriggerInstance.sackCatch(ModItems.COCONUT.get())
            ).parent(ROOT).build(ThaiDelightCommon.modid("big_big_nut"));

    Advancement GRAVITY_NOT_INVENTED = Advancement.Builder.advancement()
            .display(ModItems.DURIAN.get(),
                    Component.translatable("advancement.muffins_thaidelight.gravity_not_invent"),
                    Component.translatable("advancement.muffins_thaidelight.gravity_not_invent.description"),
                    BACKGROUND,
                    FrameType.TASK,
                    true,true,false
            )
            .addCriterion("gravity_not_invent",EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(
                    DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(ModTags.FALLING_DURIAN))).blocked(false)
            )).parent(ROOT).build(ThaiDelightCommon.modid("gravity_not_invent"));


     */

    @Override
    public void generate(HolderLookup.Provider arg, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
        /*
        consumer.accept(ROOT);
        consumer.accept(GOT_COOKED_DRAGONFLY);
        consumer.accept(GOT_SLICED_LIME);
        consumer.accept(GOT_PEPPER);
        consumer.accept(GOT_SPICY_MEAT_SALAD);
        consumer.accept(GOT_SOMTAM);
        consumer.accept(BEING_STINKY);
        consumer.accept(SUSIE_PRIZE);
        consumer.accept(BIG_BIG_NUT);
        consumer.accept(GRAVITY_NOT_INVENTED);
        */
    }

}
