package net.firemuffin303.muffinsthaidelightfabric.mixin.recipeBookType;

import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.ThaiDelightClient;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = RecipeBookSettings.class,priority = 2910)
public class RecipeBlockSettingMixin {
    @Final
    @Mutable
    @Shadow
    private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyTagFields(CallbackInfo ci) {
        Map<RecipeBookType, Pair<String, String>> newMap = new HashMap<>(TAG_FIELDS);
        newMap.put(ThaiDelightClient.MORTAR_RECIPE_BOOK_TYPE, Pair.of("isMortarBookGuiOpen", "isMortarBookFilteringCraftable"));
        TAG_FIELDS = Map.copyOf(newMap);
    }
}
