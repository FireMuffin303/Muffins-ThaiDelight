package net.firemuffin303.thaidelight.mixin.fabric.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LootPool.Builder.class)
public interface LootPoolBuilderAccessor {
    @Accessor("entries")
    List<LootPoolEntryContainer> getEntries();

    @Accessor("entries")
    void setEntry(List<LootPoolEntryContainer> list);
}
