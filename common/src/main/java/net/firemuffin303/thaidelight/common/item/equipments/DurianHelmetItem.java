package net.firemuffin303.thaidelight.common.item.equipments;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class DurianHelmetItem extends ArmorItem {
    public DurianHelmetItem() {
        super(createMaterial(), Type.HELMET, new Properties().stacksTo(1));
    }

    public static ArmorMaterial createMaterial(){
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.@NotNull Type type) {
                int i = 12;
                return switch (type){
                    case HELMET -> 11;
                    case CHESTPLATE -> 16;
                    case LEGGINGS -> 15;
                    case BOOTS -> 13;
                } * i;
            }

            @Override
            public int getDefenseForType(ArmorItem.@NotNull Type type) {
                return switch (type){
                    case HELMET -> 2;
                    case CHESTPLATE -> 6;
                    case LEGGINGS -> 5;
                    case BOOTS -> 2;
                };
            }

            @Override
            public int getEnchantmentValue() {
                return 9;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_TURTLE;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(ModItems.DURIAN_PEEL.get());
            }

            @Override
            public @NotNull String getName() {
                return "durian";
            }

            @Override
            public float getToughness() {
                return 0f;
            }

            @Override
            public float getKnockbackResistance() {
                return 0f;
            }
        };
    }
}
