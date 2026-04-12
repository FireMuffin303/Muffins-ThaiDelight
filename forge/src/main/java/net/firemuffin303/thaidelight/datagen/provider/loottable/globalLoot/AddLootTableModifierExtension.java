package net.firemuffin303.thaidelight.datagen.provider.loottable.globalLoot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import vectorwing.farmersdelight.common.loot.modifier.AddLootTableModifier;

public class AddLootTableModifierExtension extends AddLootTableModifier {
    public AddLootTableModifierExtension(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
        super(conditionsIn, lootTable);
    }
}
