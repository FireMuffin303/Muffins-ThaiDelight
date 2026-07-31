package net.firemuffin303.thaidelight.datagen.provider.loottable.globalLoot;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import vectorwing.farmersdelight.common.loot.modifier.AddItemModifier;

public class AddItemModifierExtension extends AddItemModifier {
    public AddItemModifierExtension(LootItemCondition[] conditionsIn, Item addedItemIn, int count) {
        super(conditionsIn, addedItemIn, count);
    }
}
