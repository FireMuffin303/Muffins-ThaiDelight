package net.firemuffin303.thaidelight.forge.common.registry;

import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.block.SomtamFeastBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocksForge {
    public static final Supplier<Block> SOMTAM_BLOCK = SomtamFeastBlock::new;

    public static void init(){
        register("somtam",SOMTAM_BLOCK);
    }

    private static RegistryObject<Block> register(String id,Supplier<Block> blockSupplier){
        RegistryObject<Block> blockRegistryObject = ThaiDelightForge.BLOCK.register(id,blockSupplier);
        var item = new BlockItem(blockSupplier.get(),new Item.Properties());
        ThaiDelightForge.ITEMS.register(id,()-> item);
        return blockRegistryObject;
    }
}
