package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.PapayaBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Iterator;
import java.util.List;

public class PapayaDecorator extends TreeDecorator {
    public static final MapCodec<PapayaDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(PapayaDecorator::new, (papayaDecorator) -> {
        return papayaDecorator.probability;
    });
    private final float probability;

    public PapayaDecorator(float f){
        this.probability = f;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.TREE_DECORATOR_PAPAYA.get();
    }

    @Override
    public void place(Context context) {
        RandomSource randomSource = context.random();
        if (!(randomSource.nextFloat() >= this.probability)) {
            List<BlockPos> list = context.logs();
            int i = list.get(list.size()-1).getY();
            list.stream().filter((blockPos) -> i - blockPos.getY() <= context.logs().size()-3).forEach((blockPos) -> {
                Iterator<Direction> var3 = Direction.Plane.HORIZONTAL.iterator();

                while(var3.hasNext()) {
                    Direction direction = var3.next();
                    if (randomSource.nextFloat() <= 0.75F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
                        if (context.isAir(blockPos2)) {
                            context.setBlock(blockPos2, ModBlocks.BUDDING_PAPAYA_FLOWER.get().defaultBlockState()
                                    .setValue(PapayaBlock.FACING, direction));
                        }
                    }
                }

            });
        }
    }
}
