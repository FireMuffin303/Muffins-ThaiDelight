package net.firemuffin303.thaidelight.common.advancement;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModCriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;
import java.util.Set;

public class SackCatchTrigger extends SimpleCriterionTrigger<SackCatchTrigger.TriggerInstance> {
    static final ResourceLocation ID = ThaiDelightCommon.modid("sack_catch");

    public void trigger(ServerPlayer serverPlayer, ItemStack itemStack) {
        this.trigger(serverPlayer, triggerInstance -> triggerInstance.matches(itemStack));
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player,ItemPredicate itemPredicate) implements SimpleInstance{
        public static final Codec<SackCatchTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(
                    EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SackCatchTrigger.TriggerInstance::player),
                    ItemPredicate.CODEC.fieldOf("item").forGetter(SackCatchTrigger.TriggerInstance::itemPredicate)
            ).apply(instance, SackCatchTrigger.TriggerInstance::new);
        });

        public boolean matches(ItemStack itemStack) {
            return this.itemPredicate.test(itemStack);
        }

        public static Criterion<TriggerInstance> sackCatch(ItemLike itemLike){
            return ModCriteriaTriggers.SACK_CATCH.get().createCriterion(
                    new TriggerInstance(Optional.empty(),ItemPredicate.Builder.item().of(new ItemLike[]{itemLike}).build()));
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
