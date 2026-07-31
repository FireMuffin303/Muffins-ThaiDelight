package net.firemuffin303.thaidelight.datagen.provider.tag;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModDamageTypes;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ThaiDelightDamageTypeTagProvider extends DamageTypeTagsProvider {


    public ThaiDelightDamageTypeTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, ThaiDelightCommon.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        //this.tag(ModTags.FALLING_DURIAN).add(ModDamageTypes.FALLING_DURIAN);

        this.tag(ModTags.SPICY_RESISTANT_TO).add(
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
