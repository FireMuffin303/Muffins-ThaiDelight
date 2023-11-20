package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModelDataGen extends FabricModelProvider {
    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.CRAB_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_CRAB_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT_WITH_SEAFOOD, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_DRAGONFLY, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SEAFOOD_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FISH_SAUCE_BOTTLE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LIME, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LIME_SEED, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PEPPER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PEPPER_SEED, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PAPAYA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PAPAYA_SEED, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LOINCLOTH, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SOMTAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_FRIED_RICE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItemsFabric.STONE_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItemsFabric.IRON_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItemsFabric.GOLD_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItemsFabric.DIAMOND_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItemsFabric.NETHERITE_PASTLE,"_3d",PASTLE_3D);

        itemModelGenerator.generateFlatItem(ModItemsFabric.STONE_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItemsFabric.IRON_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItemsFabric.GOLD_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItemsFabric.DIAMOND_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItemsFabric.NETHERITE_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
    }

    private static ModelTemplate createModItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID, "item/" + string)),Optional.empty(), textureSlots);
    }

    private static ModelTemplate createMincraftItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation("item/" + string)),Optional.empty(), textureSlots);
    }

}
