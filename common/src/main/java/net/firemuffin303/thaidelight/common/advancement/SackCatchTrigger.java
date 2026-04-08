package net.firemuffin303.thaidelight.common.advancement;

import com.google.gson.JsonObject;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Set;

public class SackCatchTrigger extends SimpleCriterionTrigger<SackCatchTrigger.TriggerInstance> {
    static final ResourceLocation ID = ThaiDelightCommon.modid("sack_catch");

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        ItemPredicate itemPredicate = ItemPredicate.fromJson(jsonObject.get("item"));
        return new TriggerInstance(contextAwarePredicate,itemPredicate);
    }

    public void trigger(ServerPlayer serverPlayer, ItemStack itemStack) {
        this.trigger(serverPlayer, triggerInstance -> triggerInstance.matches(itemStack));
    }


    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance{
        private final ItemPredicate itemPredicate;

        public TriggerInstance(ContextAwarePredicate contextAwarePredicate, ItemPredicate itemPredicate) {
            super(SackCatchTrigger.ID, contextAwarePredicate);
            this.itemPredicate = itemPredicate;
        }

        public boolean matches(ItemStack itemStack) {
            return this.itemPredicate.matches(itemStack);
        }

        public static TriggerInstance sackCatch(ItemLike itemLike){
            return new TriggerInstance(ContextAwarePredicate.ANY,new ItemPredicate(
                    null,
                    Set.of(itemLike.asItem()),
                    MinMaxBounds.Ints.ANY,
                    MinMaxBounds.Ints.ANY,
                    EnchantmentPredicate.NONE,
                    EnchantmentPredicate.NONE,
                    null,
                    NbtPredicate.ANY
                    )
            );
        }

        @Override
        public JsonObject serializeToJson(SerializationContext serializationContext) {
            JsonObject jsonObject = super.serializeToJson(serializationContext);
            jsonObject.add("item",this.itemPredicate.serializeToJson());
            return jsonObject;
        }
    }
}
