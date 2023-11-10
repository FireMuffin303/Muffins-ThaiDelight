package net.firemuffin303.thaidelight.forge.common.registry;

import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.block.SpicyMincedPorkSaladFeastBlock;
import net.firemuffin303.thaidelight.forge.common.block.SomtamFeastBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocksForge {
    public static final RegistryObject<Block> SOMTAM_BLOCK = register("somtam_block",SomtamFeastBlock::new);
    public static final RegistryObject<Block> SPICY_MINCED_PORK_SALAD_BLOCK = register("spicy_minced_pork_salad_block", SpicyMincedPorkSaladFeastBlock::new);

    public static void init(){
    }

    private static RegistryObject<Block> register(String id,Supplier<Block> blockSupplier){
        RegistryObject<Block> blockRegistryObject = ThaiDelightForge.BLOCK.register(id,blockSupplier);
        //var item = new BlockItem(blockSupplier.get(),new Item.Properties());
        //ThaiDelightForge.ITEMS.register(id,()-> item);
        return blockRegistryObject;
    }
}
