package net.firemuffin303.thaidelight.datagen.provider.tag;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ThaiDelightEntityTagProvider extends EntityTypeTagsProvider {


    public ThaiDelightEntityTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(arg, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tag(EntityTypeTags.FROG_FOOD).add(ModEntityTypes.DRAGONFLY.get());
    }
}
