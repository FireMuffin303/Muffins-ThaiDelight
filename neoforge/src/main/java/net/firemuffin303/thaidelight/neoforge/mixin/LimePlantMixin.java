package net.firemuffin303.thaidelight.neoforge.mixin;

import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimePlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LimePlantBlock.class)
public abstract class LimePlantMixin implements IBlockExtension {

    @Override
    public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        return PathType.DAMAGE_OTHER;
    }

    @Override
    public @Nullable PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
        return PathType.DANGER_OTHER;
    }
}
