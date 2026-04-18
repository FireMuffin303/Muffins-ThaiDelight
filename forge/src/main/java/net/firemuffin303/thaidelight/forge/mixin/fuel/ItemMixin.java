package net.firemuffin303.thaidelight.forge.mixin.fuel;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public abstract class ItemMixin implements IForgeItem {

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        if(ThaiDelightCommon.FUEL_MAP.containsKey(itemStack.getItem())){
            return ThaiDelightCommon.FUEL_MAP.get(itemStack.getItem());
        }

        return IForgeItem.super.getBurnTime(itemStack, recipeType);
    }
}
