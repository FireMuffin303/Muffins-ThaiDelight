package net.firemuffin303.thaidelight.common.menu;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.List;

public class MortarInput extends CraftingInput {

    public MortarInput(List<ItemStack> list){
        super(2,2,list);
    }
}
