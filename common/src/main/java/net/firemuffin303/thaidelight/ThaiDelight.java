package net.firemuffin303.thaidelight;

import com.google.common.collect.ImmutableList;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.mixin.ChickenFoodAccessor;
import net.firemuffin303.thaidelight.mixin.FrogFoodAccessor;
import net.firemuffin303.thaidelight.mixin.ParrotTameFoodAccessor;
import net.firemuffin303.thaidelight.mixin.PigFoodAccessor;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ThaiDelight {
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaiDelight.MOD_ID);
    public static final String MOD_ID = "muffins_thaidelight";

    public static final AttributeModifier CRAB_REACH = new AttributeModifier(UUID.fromString("9b61c8ad-e259-4a36-b6f6-382ab3c509ea"),"Range Modifier",1.0d,AttributeModifier.Operation.ADDITION);

    public static void init() {
        ModEntityTypes.init();
        ModBlocks.init();
        ModBlocks.ModBlockEntityTypes.init();
        ModFluid.init();
        ModItems.init();
        ModMobEffects.init();
        ModPotions.init();
        ModRecipes.init();
        ModConfiguredFeatures.init();
        ModRecipes.ModRecipeSerializer.init();
        ModMenuType.init();
        ModSoundEvents.init();
        ModTreeDecorator.init();
    }

    public static void postInit(){
        ModEntityTypes.postInit();
        ModPotions.postInit();
        registerComposterBlock();
        registerStrippables();
        registerAnimalFoodItem();
    }

    public static void registerComposterBlock(){
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER_SEED,0.3f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_LEAVES),0.3f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.LIME_SAPLING),0.3f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_SEEDS),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA,0.65f);

        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_LIME,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA_SLICE,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_PAPAYA,0.4f);

        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_LOG),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_LOG),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_WOOD),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_WOOD),0.8f);
    }

    public static void registerStrippables(){
        Map<Block,Block> stippables = new HashMap<>();
        stippables.put(ModBlocks.PAPAYA_LOG,ModBlocks.STRIPPED_PAPAYA_LOG);
        stippables.put(ModBlocks.PAPAYA_WOOD,ModBlocks.STRIPPED_PAPAYA_WOOD);

        ModPlatform.registerStrippables(stippables);
    }

    public static void registerAnimalFoodItem(){
        //Please Mojang, just turn animal food to ItemTags. I beg you.

        ParrotTameFoodAccessor.getTameFood().add(Item.byBlock(ModBlocks.PAPAYA_SEEDS) );
        ParrotTameFoodAccessor.getTameFood().add(ModItems.PEPPER_SEED);

        Ingredient newPigFoods = Ingredient.of(ModItems.RAW_PAPAYA,ModItems.PAPAYA,ModItems.SLICED_PAPAYA,ModItems.RAW_PAPAYA_SLICE,ModItems.LIME,ModItems.SLICED_LIME);
        Ingredient newChickenFoods = Ingredient.of(ModBlocks.PAPAYA_SEEDS,ModItems.PEPPER_SEED);

        Ingredient newFrogFoods = Ingredient.of(ModItems.DRAGONFLY,ModItems.COOKED_DRAGONFLY);

        PigFoodAccessor.setFoodItems(Ingredient.of(new ImmutableList.Builder<ItemStack>().addAll(Arrays.stream(PigFoodAccessor.getFoodItems().getItems()).iterator())
                .addAll(Arrays.asList(newPigFoods.getItems())).build().stream()));

        ChickenFoodAccessor.setFoodItems(Ingredient.of(
                new ImmutableList.Builder<ItemStack>().addAll(Arrays.stream(ChickenFoodAccessor.getFoodItems().getItems()).iterator())
                .addAll(Arrays.asList(newChickenFoods.getItems())).build().stream()));

        FrogFoodAccessor.setFoodItems(Ingredient.of(
                new ImmutableList.Builder<ItemStack>().addAll(Arrays.asList(FrogFoodAccessor.getFoodItems().getItems()).iterator())
                        .addAll(Arrays.asList(newFrogFoods.getItems())).build().stream()));
    }
}
