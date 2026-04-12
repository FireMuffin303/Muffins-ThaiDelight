package net.firemuffin303.thaidelight.datagen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public class ModModelUtils {
    private static final List<Pair<BooleanProperty, Function<ResourceLocation, Variant>>> MULTIFACE_GENERATOR;


    public static void createCustomModelMultiFace(Block block, ResourceLocation resourceLocation, BlockModelGenerators blockModelGenerators){
        MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(block);
        Condition.TerminalCondition terminalCondition = (Condition.TerminalCondition) Util.make(Condition.condition(), (terminalConditionx) -> {
            MULTIFACE_GENERATOR.stream().map(Pair::getFirst).forEach((booleanProperty) -> {
                if (block.defaultBlockState().hasProperty(booleanProperty)) {
                    terminalConditionx.term(booleanProperty, false);
                }

            });
        });
        Iterator<Pair<BooleanProperty, Function<ResourceLocation, Variant>>> var5 = MULTIFACE_GENERATOR.iterator();

        while(var5.hasNext()) {
            Pair<BooleanProperty, Function<ResourceLocation, Variant>> pair = var5.next();
            BooleanProperty booleanProperty = pair.getFirst();
            Function<ResourceLocation, Variant> function = pair.getSecond();
            if (block.defaultBlockState().hasProperty(booleanProperty)) {
                multiPartGenerator.with(Condition.condition().term(booleanProperty, true), function.apply(resourceLocation));
                multiPartGenerator.with(terminalCondition, function.apply(resourceLocation));
            }
        }

        blockModelGenerators.blockStateOutput.accept(multiPartGenerator);
    }

    static {
        MULTIFACE_GENERATOR = List.of(Pair.of(BlockStateProperties.NORTH, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.MODEL, resourceLocation);
        }), Pair.of(BlockStateProperties.EAST, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true);
        }), Pair.of(BlockStateProperties.SOUTH, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.UV_LOCK, true);
        }), Pair.of(BlockStateProperties.WEST, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true);
        }), Pair.of(BlockStateProperties.UP, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90);
        }), Pair.of(BlockStateProperties.DOWN, (resourceLocation) -> {
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);
        }));
    }
}
