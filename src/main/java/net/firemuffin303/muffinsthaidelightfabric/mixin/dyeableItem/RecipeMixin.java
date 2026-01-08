package net.firemuffin303.muffinsthaidelightfabric.mixin.dyeableItem;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ArmorDyeRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(Recipe.class)
public interface RecipeMixin {

    @WrapWithCondition(method = "getRemainingItems",at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;"))
    default <E> boolean muffins$dyeCoconutMilkIcecreamWithBowlPlease(NonNullList instance, int i, E object, @Local Item item){
        if(this instanceof ArmorDyeRecipe && item == ModItems.COCONUT_MILK_ICE_CREAM){
            return false;
        }
        return true;
    }
}
