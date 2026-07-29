package net.firemuffin303.thaidelight.common.item.equipments;

import net.firemuffin303.thaidelight.common.registry.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;

public class DurianHelmetItem extends ArmorItem {
    public DurianHelmetItem() {
        super(ModArmorMaterials.DURIAN, Type.HELMET, new Properties().stacksTo(1));
    }

}
