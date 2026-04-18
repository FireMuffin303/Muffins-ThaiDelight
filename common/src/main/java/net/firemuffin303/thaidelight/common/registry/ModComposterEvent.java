package net.firemuffin303.thaidelight.common.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;

public class ModComposterEvent {
    public static void init(){
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER_SEED.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_LEAVES.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME_SAPLING.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_SAPLING.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA.get(),0.65f);

        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_LIME.get(),0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA_SLICE.get(),0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_PAPAYA.get(),0.4f);

        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_LOG.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_LOG.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_WOOD.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_WOOD.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(ModItems.DURIAN_PEEL.get(),0.8f);
    }
}
