package net.firemuffin303.thaidelight.common.block.grower;

import net.firemuffin303.thaidelight.common.registry.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower PAPAYA = new TreeGrower("papaya", Optional.empty(),Optional.of(ModConfiguredFeatures.FEATURE_PAPAYA_TREE),Optional.empty());
}
