package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public class ModArmorMaterials {
    public static final ResourceRegistry<ArmorMaterial> ARMORS = ResourceRegistry.create(Registries.ARMOR_MATERIAL, ThaiDelightCommon.MOD_ID);


    public static final Holder<ArmorMaterial> DURIAN = ARMORS.registerHolder("durian",() -> new ArmorMaterial(
            Map.of(ArmorItem.Type.HELMET,2,
                    ArmorItem.Type.CHESTPLATE,6,
                    ArmorItem.Type.LEGGINGS,5,
                    ArmorItem.Type.BOOTS,2
            ),9, SoundEvents.ARMOR_EQUIP_TURTLE,
            () -> Ingredient.of(ModItems.DURIAN.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ThaiDelightCommon.MOD_ID,"durian"))),
            0f,0f
    ));

    public static void init(){
        ARMORS.init();
    }
}
