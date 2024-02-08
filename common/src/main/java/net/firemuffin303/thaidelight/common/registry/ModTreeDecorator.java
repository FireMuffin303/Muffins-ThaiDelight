package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.feature.PapayaDecorator;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ModTreeDecorator {
    public static final TreeDecoratorType<PapayaDecorator> TREE_DECORATOR_PAPAYA;

    public static void init(){}

    static {
        TREE_DECORATOR_PAPAYA = ModPlatform.registerTreeDecorator("papaya",PapayaDecorator.CODEC);
    }
}
