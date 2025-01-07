package net.firemuffin303.muffinsthaidelightfabric.common.feature;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Iterator;
import java.util.List;

public class PapayaDecorator extends TreeDecorator {
    public static final Codec<PapayaDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(PapayaDecorator::new, (papayaDecorator) -> {
        return papayaDecorator.probability;
    }).codec();
    private final float probability;

    public PapayaDecorator(float f){
        this.probability = f;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.TREE_DECORATOR_PAPAYA;
    }

    @Override
    public void place(Context context) {
        RandomSource randomSource = context.random();
        if (!(randomSource.nextFloat() >= this.probability)) {
            List<BlockPos> list = context.logs();
            int i = ((BlockPos)list.get(list.size()-1)).getY();
            list.stream().filter((blockPos) -> {
                return i - blockPos.getY() <= 2;
            }).forEach((blockPos) -> {
                Iterator var3 = Direction.Plane.HORIZONTAL.iterator();

                while(var3.hasNext()) {
                    Direction direction = (Direction)var3.next();
                    if (randomSource.nextFloat() <= 0.75F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
                        if (context.isAir(blockPos2)) {
                            context.setBlock(blockPos2, (BlockState)((BlockState) ModBlocks.PAPAYA.defaultBlockState().setValue(PapayaBlock.AGE, randomSource.nextInt(2))).setValue(PapayaBlock.FACING, direction));
                        }
                    }
                }

            });
        }
    }
}
