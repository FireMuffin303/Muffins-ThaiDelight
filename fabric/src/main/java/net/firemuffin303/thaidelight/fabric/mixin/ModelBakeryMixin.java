package net.firemuffin303.thaidelight.fabric.mixin;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static net.firemuffin303.thaidelight.fabric.ThaiDelightModFabricClient.*;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
    @Shadow
    protected abstract void loadTopLevel(ModelResourceLocation modelResourceLocation);

    @Inject(method = "<init>",at= @At(value = "INVOKE",target = "Lnet/minecraft/client/resources/model/ModelBakery;loadTopLevel(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V",ordinal = 3))
    public void addPastleModel(BlockColors blockColors, ProfilerFiller profilerFiller, Map map, Map map2, CallbackInfo ci){
        this.loadTopLevel(STONE_PASTLE_MODEL);
        this.loadTopLevel(IRON_PASTLE_MODEL);
        this.loadTopLevel(GOLDEN_PASTLE_MODEL);
        this.loadTopLevel(DIAMOND_PASTLE_MODEL);
        this.loadTopLevel(NETHERITE_PASTLE_MODEL);
    }
}
