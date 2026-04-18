package net.firemuffin303.thaidelight.common.registry.fabric;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.vegetation.FabricBuddingButterflyPeaBlock;
import net.firemuffin303.thaidelight.common.block.feast.*;
import net.firemuffin303.thaidelight.common.block.vegetation.pepper.FabricBuddingPepperBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Supplier;

public class ModBlocksImpl {
    public static  Supplier<Block> createSomtamFeast() {
        Block somtamBlock = new SomtamFeastBlock();
        return () -> somtamBlock;
    }

    public static Supplier<Block> createLarbFeast() {
        Block block = new LarbFeastBlock();
        return () -> block;
    }

    public static Supplier<Block> createCrabFriedRice() {
        Block block = new CrabFriedRiceFeastBlock();
        return () -> block;
    }

    public static Supplier<Block> createCoconutPieBlock() {
        Block block = new CoconutPieBlock();
        return () -> block;
    }

    public static Supplier<Block> createPhatKaphraoBlock() {
        Block block = new PhatKaphraoFeastBlock();
        return () -> block;
    }

    public static Supplier<Block> createMangoStickyRiceBlock() {
        Block block = new MangoStickyRiceFeastBlock();
        return () -> block;
    }

    public static Supplier<Block> createOmeletteBlock(Supplier<Item> itemSupplier) {
        Block block = new OmeletteFeastBlock(itemSupplier);
        return () -> block;
    }

    public static Supplier<Block> createPineappleFeastBlock() {
        Block block = new PineappleFriedRiceFeastBlock();
        return () -> block;
    }

    public static Supplier<Block> createWildCropBlock(MobEffect stewEffect, int effectDuration, BlockBehaviour.Properties properties) {
        return () -> new WildCropBlock(stewEffect,effectDuration,properties);
    }


    public static Supplier<Block> createButterflyPeaBlock() {
        return FabricBuddingButterflyPeaBlock::new;
    }

    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }

    public static Supplier<Block> createBuddingPepperBlock(BlockBehaviour.Properties properties) {
        return () -> new FabricBuddingPepperBlock(properties);
    }

    public static <T extends Block> Supplier<T> register(String id, Supplier<T> block) {
        T registeredBlock = Registry.register(BuiltInRegistries.BLOCK, ThaiDelightCommon.modid(id),block.get());
        return () -> registeredBlock;
    }

    public static Supplier<Block> createPieBlock(BlockBehaviour.Properties properties, Supplier<Item> supplier) {
        return () -> new PieBlock(properties,supplier);
    }


    public static Supplier<Block> getThaiDelightBlock(String id, Supplier<Block> blockSupplier) {
        return blockSupplier;
    }

    public static BlockBehaviour.Properties dropLike(Supplier<Block> blockSupplier) {
        return BlockBehaviour.Properties.of().dropsLike(blockSupplier.get());
    }


}
