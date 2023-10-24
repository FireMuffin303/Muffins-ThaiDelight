package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.fluid.SeafoodFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public class ModFluid {
    public static final ResourcefulRegistry<Fluid> FLUID = ResourcefulRegistries.create(BuiltInRegistries.FLUID, ThaiDelight.MOD_ID);
    public static final RegistryEntry<FlowingFluid> SEAFOOD = FLUID.register("seafood", SeafoodFluid.Source::new);
    public static final RegistryEntry<FlowingFluid> SEAFOOD_FLOWING = FLUID.register("flowing_seafood", SeafoodFluid.Flowing::new);

    public static final TagKey<Fluid> SEAFOOD_TAG = createTag("seafood");

    private static TagKey<Fluid> createTag(String string) {
        return TagKey.create(Registries.FLUID, new ResourceLocation(ThaiDelight.MOD_ID,string));
    }


}
