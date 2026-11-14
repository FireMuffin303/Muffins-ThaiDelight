package net.firemuffin303.thaidelight.mixin.fabric.recipeBookType;

import com.mojang.datafixers.util.Pair;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeBookSettings.class)
public interface RecipeBookSettingsAccessor {

    @Accessor("TAG_FIELDS")
    @Mutable
    static Map<RecipeBookType, Pair<String, String>> getTagField(){
        throw new  AssertionError();
    }

    @Accessor("TAG_FIELDS")
    @Mutable
    static void setTagFields(Map<RecipeBookType, Pair<String, String>> tagFields){
        throw new  AssertionError();
    }
}
