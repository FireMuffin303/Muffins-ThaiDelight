package net.firemuffin303.thaidelight.fabric.common.registry;

import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.block.SomtamFeastBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import software.bernie.example.registry.ItemRegistry;

public class ModBlocks {
    public static Block SOMTAM_FEAST = new SomtamFeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ItemsRegistry.SHEPHERDS_PIE.get(), true);

    public static void init(){
        register("somtam_block",SOMTAM_FEAST);
    }

    private static void register(String id,Block block){
        Registry.register(BuiltInRegistries.BLOCK,new ResourceLocation(ThaiDelight.MOD_ID,id),block);
        Item item = new BlockItem(block,new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
        ModItems.ITEMS.add(item);

    }
}
