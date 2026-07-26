package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.forge.common.block.feast.*;
import net.firemuffin303.thaidelight.forge.common.block.vegetation.ForgeBuddingButterflyPeaBlock;
import net.firemuffin303.thaidelight.forge.common.block.vegetation.pepper.ForgeBuddingPepperBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Supplier;

public class ModBlocksImpl {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, ThaiDelightCommon.MOD_ID);

    public static Supplier<Block> createSomtamFeast() {
        return SomtamFeastBlock::new;
    }

    public static Supplier<Block> createLarbFeast() {
        return LarbFeastBlock::new;
    }

    public static Supplier<Block> createCrabFriedRice() {
        return CrabFriedRiceFeastBlock::new;
    }

    public static Supplier<Block> createCoconutPieBlock() {
        return CoconutPieBlock::new;
    }

    public static Supplier<Block> createPhatKaphraoBlock() {
        return PhatKaphraoFeastBlock::new;
    }

    public static Supplier<Block> createMangoStickyRiceBlock() {
        return MangoStickyRiceFeastBlock::new;
    }

    public static Supplier<Block> createOmeletteBlock(Supplier<Item> itemSupplier) {
        return () -> new OmeletteFeastBlock(itemSupplier);
    }

    public static Supplier<Block> createPineappleFeastBlock() {
        return PineappleFriedRiceFeastBlock::new;
    }

    public static Supplier<Block> createWildCropBlock(MobEffect stewEffect, int effectDuration, BlockBehaviour.Properties properties) {
        return () -> new WildCropBlock(stewEffect,effectDuration,properties);
    }

    public static <T extends Block> Supplier<T> register(String id, Supplier<T> block) {
        return BLOCK.register(id,block);
    }

    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }

    public static Supplier<Block> createBuddingPepperBlock(BlockBehaviour.Properties properties) {
        return () -> new ForgeBuddingPepperBlock(properties);
    }

    public static Supplier<Block> createPieBlock(BlockBehaviour.Properties properties, Supplier<Item> supplier) {
        return () -> new PieBlock(properties,supplier);
    }

    public static Supplier<Block> createButterflyPeaBlock() {
        return ForgeBuddingButterflyPeaBlock::new;
    }


    public static Supplier<Block> getThaiDelightBlock(String id, Supplier<Block> blockSupplier) {
        return RegistryObject.create(ThaiDelightCommon.modid(id), ForgeRegistries.BLOCKS);
    }

    public static BlockBehaviour.Properties dropLike(Supplier<Block> blockSupplier) {

        return BlockBehaviour.Properties.of().lootFrom(blockSupplier);
    }

    public static Supplier<Block> createDurianSliceFeastBlock() {
        return DurianSliceFeastBlock::new;
    }


}
