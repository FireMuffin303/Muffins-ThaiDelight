package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.fluid.SeafoodFluid;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;

public class ModFluid {
    public static final ArrayList<Fluid> FLUIDS = new ArrayList<>();

    public static final FlowingFluid SEAFOOD = new SeafoodFluid.Source();
    public static final FlowingFluid SEAFOOD_FLOWING = new SeafoodFluid.Flowing();

    public static final TagKey<Fluid> SEAFOOD_TAG = createTag("seafood");

    public static void init(){
        register("seafood",SEAFOOD);
        register("flowing_seafood",SEAFOOD_FLOWING);

    }

    private static void register(String id,Fluid fluid){
        ModPlatform.registerFluid(id,fluid);
        FLUIDS.add(fluid);
    }




    private static TagKey<Fluid> createTag(String string) {
        return TagKey.create(Registries.FLUID, new ResourceLocation(ThaiDelight.MOD_ID,string));
    }


}
