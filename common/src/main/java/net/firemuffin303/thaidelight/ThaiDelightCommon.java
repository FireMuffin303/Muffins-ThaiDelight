package net.firemuffin303.thaidelight;

import net.firemuffin303.thaidelight.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;

public class ThaiDelightCommon {
    public static final String MOD_ID = "muffins_thaidelight";

    public static void init(){
        ModSoundEvents.init();
        ModCriteriaTriggers.init();
        ModDamageTypes.init();

        ModBlockStateProviderTypes.init();
        ModFeatures.init();
        ModTreeDecoratorTypes.init();

        ModEntityTypes.init();
        ModMobEffects.init();

        ModMenuType.init();

        ModBlockSetTypes.init();
        ModBlockEntityTypes.init();
        ModBlocks.init();
        ModItems.init();

        ModRecipes.init();




    }

    public static void postInit(){
        ModCauldronInteraction.init();
        ModDispenserBehavior.init();

        Item.BY_BLOCK.put(ModBlocks.COCONUT_LEAF.get(),ModItems.COCONUT_LEAF.get());
        Item.BY_BLOCK.put(ModBlocks.HANGING_DURIAN.get(),ModItems.DURIAN.get());
        Item.BY_BLOCK.put(ModBlocks.HANGING_MANGO_BLOCK.get(),ModItems.MANGO.get());
        Item.BY_BLOCK.put(ModBlocks.PAPAYA.get(),ModItems.PAPAYA.get());
        Item.BY_BLOCK.put(ModBlocks.FERMENTED_FISH_CAULDRON.get(), Items.CAULDRON);
        Item.BY_BLOCK.put(ModBlocks.COCONUT_CAULDRON.get(),Items.CAULDRON);
        Item.BY_BLOCK.put(ModBlocks.COCONUT_MILK_CAULDRON.get(),Items.CAULDRON);
    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
