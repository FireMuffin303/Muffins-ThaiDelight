package net.firemuffin303.thaidelight;

import com.mojang.logging.LogUtils;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.firemuffin303.thaidelight.common.TDFabricEvents;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.firemuffin303.thaidelight.common.registry.fabric.ModItemsImpl;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

public class ThaiDelightFabric implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean IS_FOT_INSTALLED = false;
    public static boolean IS_TOUGH_AS_NAIL_INSTALLED = false;



    public static final TerraformBoatType DURIAN = new TerraformBoatType.Builder()
            .item(ModItems.DURIAN_BOAT.get())
            .chestItem(ModItems.DURIAN_CHEST_BOAT.get())
            .planks(ModItems.DURIAN_PLANKS.get())
            .build();

    private static final TerraformBoatType COCONUT = new TerraformBoatType.Builder()
            .item(ModItems.COCONUT_BOAT.get())
            .chestItem(ModItems.COCONUT_CHEST_BOAT.get())
            .planks(ModItems.COCONUT_PLANKS.get())
            .build();

    private static final TerraformBoatType MANGO = new TerraformBoatType.Builder()
            .item(ModItems.MANGO_BOAT.get())
            .chestItem(ModItems.MANGO_CHEST_BOAT.get())
            .planks(ModItems.MANGO_PLANKS.get())
            .build();

    @Override
    public void onInitialize() {
        ThaiDelightCommon.init();

        ModEntityTypes.registerAttribute(FabricDefaultAttributeRegistry::register);

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, ModItemsImpl.DURIAN_BOAT_KEY,DURIAN);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItemsImpl.COCONUT_BOAT_KEY,COCONUT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItemsImpl.MANGO_BOAT_KEY,MANGO);


        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.DURIAN_BOAT.get(),ModItemsImpl.DURIAN_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.DURIAN_CHEST_BOAT.get(),ModItemsImpl.DURIAN_BOAT_KEY,true);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.MANGO_BOAT.get(),ModItemsImpl.MANGO_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.MANGO_CHEST_BOAT.get(),ModItemsImpl.MANGO_BOAT_KEY,true);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.COCONUT_BOAT.get(),ModItemsImpl.COCONUT_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.COCONUT_CHEST_BOAT.get(),ModItemsImpl.COCONUT_BOAT_KEY,true);

        PotionBrewing.addMix(Potions.AWKWARD,ModItems.FERMENTED_FISH.get(), ModMobEffects.STENCH_POTION.get());
        PotionBrewing.addMix(ModMobEffects.STENCH_POTION.get(), Items.REDSTONE,ModMobEffects.LONG_STENCH_POTION.get());
        PotionBrewing.addMix(ModMobEffects.STENCH_POTION.get(), Items.GLOWSTONE,ModMobEffects.STRONG_STENCH_POTION.get());

        TillableBlockRegistry.register(Blocks.BAMBOO_SAPLING,useOnContext -> true,Blocks.AIR.defaultBlockState(),ModItems.BAMBOO_SHOOT.get());

        TDFabricEvents.worldGeneration();
        TDFabricEvents.registerFuel();
        TDFabricEvents.modifyLootTable();
        TDFabricEvents.initializeStinkyEffect();
        TDFabricEvents.setVillagerItem();
        TDFabricEvents.addVillagersTrades();
        TDFabricEvents.registerStrippable();
        TDFabricEvents.registerComposter();
        TDFabricEvents.registerAnimalFood();
    }



}
