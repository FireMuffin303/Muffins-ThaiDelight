package net.firemuffin303.muffinsthaidelightfabric.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModDamageTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagDataGen extends FabricTagProvider<DamageType> {
    public ModDamageTypeTagDataGen(FabricDataOutput output,CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateTagBuilder(ModTags.FALLING_DURIAN).add(ModDamageTypes.FALLING_DURIAN);

        this.getOrCreateTagBuilder(ModTags.SPICY_RESISTANT_TO).add(
                DamageTypes.THORNS,
                DamageTypes.MAGIC,
                DamageTypes.INDIRECT_MAGIC,
                DamageTypes.SONIC_BOOM,
                DamageTypes.DRAGON_BREATH,
                DamageTypes.WITHER_SKULL,
                DamageTypes.WITHER
        );
    }
}
