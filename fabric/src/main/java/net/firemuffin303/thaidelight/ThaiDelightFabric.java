package net.firemuffin303.thaidelight;

import com.mojang.logging.LogUtils;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
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

    }
}
