package net.firemuffin303.muffinsthaidelightfabric.mixin.integration.fishofthieves;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.firemuffin303.muffinsthaidelightfabric.integration.midnightLib.ThaiDelightConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;

@Pseudo
@Mixin(targets = "com.stevekung.fishofthieves.fabric.FishOfThievesFabric")
public abstract class FishOfThievesFabricMixin {

    @WrapWithCondition(method = "onInitialize",at = @At(value = "INVOKE",
            target = "Lnet/fabricmc/fabric/api/biome/v1/BiomeModifications;addFeature(Ljava/util/function/Predicate;Lnet/minecraft/world/level/levelgen/GenerationStep$Decoration;Lnet/minecraft/resources/ResourceKey;)V",
            ordinal = 1
    ))
    public boolean muffins$preventCoconutSpawn(Predicate<BiomeSelectionContext> biomeSelector, GenerationStep.Decoration step, ResourceKey<PlacedFeature> placedFeatureRegistryKey){
        if(ThaiDelightConfig.coconutTreeType == ThaiDelightConfig.TreeType.THAI_DELIGHT){
            return false;
        }
        return true;
    }

    @WrapWithCondition(method = "onInitialize",at = @At(value = "INVOKE",
            target = "Lnet/fabricmc/fabric/api/biome/v1/BiomeModifications;addFeature(Ljava/util/function/Predicate;Lnet/minecraft/world/level/levelgen/GenerationStep$Decoration;Lnet/minecraft/resources/ResourceKey;)V",
            ordinal = 3
    ))
    public boolean muffins$preventMangoSpawn(Predicate<BiomeSelectionContext> biomeSelector, GenerationStep.Decoration step, ResourceKey<PlacedFeature> placedFeatureRegistryKey){
        if(ThaiDelightConfig.mangoTreeType == ThaiDelightConfig.TreeType.THAI_DELIGHT){
            return false;
        }

        return true;
    }
}
