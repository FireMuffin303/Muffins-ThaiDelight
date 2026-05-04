package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.item.DragonflyBottleItem;
import net.minecraft.world.item.*;

import java.util.Arrays;
import java.util.List;

public class RegistryUtils {

    public static void itemsGenerator(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output){
        output.accept(ModItems.MORTAR.get());
        output.accept(ModItems.SACK.get());

        output.accept(ModItems.LIME_CRATE.get());
        output.accept(ModItems.PEPPER_CRATE.get());
        output.accept(ModItems.RAW_PAPAYA_CRATE.get());
        output.accept(ModItems.PAPAYA_CRATE.get());
        output.accept(ModItems.MANGO_CRATE.get());
        output.accept(ModItems.HOLY_BASIL_CRATE.get());
        output.accept(ModItems.BASIL_CRATE.get());
        output.accept(ModItems.BAMBOO_SHOOT_CRATE.get());
        output.accept(ModItems.BUTTERFLY_PEA_CRATE.get());

        output.accept(ModItems.LIME_SAPLING.get());
        output.accept(ModItems.LIME.get());
        output.accept(ModItems.SLICED_LIME.get());

        output.accept(ModItems.WILD_PEPPER_CROP.get());
        output.accept(ModItems.PEPPER.get());
        output.accept(ModItems.PEPPER_SEED.get());

        output.accept(ModItems.DURIAN_SAPLING.get());
        output.accept(ModItems.DURIAN_LEAVES.get());
        output.accept(ModItems.DURIAN_FLOWER.get());
        output.accept(ModItems.SMALL_DURIAN.get());
        output.accept(ModItems.DURIAN.get());
        output.accept(ModItems.DURIAN_PULP.get());
        output.accept(ModItems.DURIAN_PEEL.get());
        output.accept(ModItems.DURIAN_PEEL_BLOCK.get());
        output.accept(ModItems.DURIAN_HELMET.get());
        output.accept(ModItems.DURIAN_LOG.get());
        output.accept(ModItems.DURIAN_WOOD.get());
        output.accept(ModItems.STRIPPED_DURIAN_LOG.get());
        output.accept(ModItems.STRIPPED_DURIAN_WOOD.get());
        output.accept(ModItems.DURIAN_PLANKS.get());
        output.accept(ModItems.DURIAN_STAIRS.get());
        output.accept(ModItems.DURIAN_SLAB.get());
        output.accept(ModItems.DURIAN_FENCE.get());
        output.accept(ModItems.DURIAN_FENCE_GATE.get());
        output.accept(ModItems.DURIAN_DOOR.get());
        output.accept(ModItems.DURIAN_TRAPDOOR.get());
        output.accept(ModItems.DURIAN_PRESSURE_PLATE.get());
        output.accept(ModItems.DURIAN_BUTTON.get());
        output.accept(ModItems.DURIAN_SIGN.get());
        output.accept(ModItems.DURIAN_HANGING_SIGN.get());
        output.accept(ModItems.DURIAN_CABINET.get());
        output.accept(ModItems.DURIAN_BOAT.get());
        output.accept(ModItems.DURIAN_CHEST_BOAT.get());

        output.accept(ModItems.COCONUT_SAPLING.get());
        output.accept(ModItems.COCONUT_LEAF.get());
        output.accept(ModItems.BUDDING_COCONUT_LEAF.get());
        output.accept(ModItems.COCONUT_LEAF_BLOCK.get());
        output.accept(ModItems.COCONUT_LEAF_MAT.get());

        output.accept(ModItems.COCONUT_LOG.get());
        output.accept(ModItems.COCONUT_WOOD.get());
        output.accept(ModItems.STRIPPED_COCONUT_LOG.get());
        output.accept(ModItems.STRIPPED_COCONUT_WOOD.get());
        output.accept(ModItems.COCONUT_PLANKS.get());
        output.accept(ModItems.COCONUT_STAIRS.get());
        output.accept(ModItems.COCONUT_SLAB.get());
        output.accept(ModItems.COCONUT_FENCE.get());
        output.accept(ModItems.COCONUT_FENCE_GATE.get());
        output.accept(ModItems.COCONUT_DOOR.get());
        output.accept(ModItems.COCONUT_TRAPDOOR.get());
        output.accept(ModItems.COCONUT_PRESSURE_PLATE.get());
        output.accept(ModItems.COCONUT_BUTTON.get());
        output.accept(ModItems.COCONUT_SIGN.get());
        output.accept(ModItems.COCONUT_HANGING_SIGN.get());
        output.accept(ModItems.COCONUT_BOAT.get());
        output.accept(ModItems.COCONUT_CHEST_BOAT.get());
        output.accept(ModItems.COCONUT_CABINET.get());

        output.accept(ModItems.COCONUT.get());
        output.accept(ModItems.STRIPPED_COCONUT.get());
        output.accept(ModItems.COCONUT_SLICE.get());

        output.accept(ModItems.MANGO_SAPLING.get());
        output.accept(ModItems.MANGO_LEAVES.get());

        output.accept(ModItems.MANGO_LOG.get());
        output.accept(ModItems.MANGO_WOOD.get());
        output.accept(ModItems.STRIPPED_MANGO_LOG.get());
        output.accept(ModItems.STRIPPED_MANGO_WOOD.get());
        output.accept(ModItems.MANGO_PLANKS.get());
        output.accept(ModItems.MANGO_STAIRS.get());
        output.accept(ModItems.MANGO_SLAB.get());
        output.accept(ModItems.MANGO_FENCE.get());
        output.accept(ModItems.MANGO_FENCE_GATE.get());
        output.accept(ModItems.MANGO_DOOR.get());
        output.accept(ModItems.MANGO_TRAPDOOR.get());
        output.accept(ModItems.MANGO_PRESSURE_PLATE.get());
        output.accept(ModItems.MANGO_BUTTON.get());
        output.accept(ModItems.MANGO_SIGN.get());
        output.accept(ModItems.MANGO_HANGING_SIGN.get());
        output.accept(ModItems.MANGO_BOAT.get());
        output.accept(ModItems.MANGO_CHEST_BOAT.get());
        output.accept(ModItems.MANGO_CABINET.get());

        output.accept(ModItems.MANGO.get());
        output.accept(ModItems.MANGO_SLICE.get());

        output.accept(ModItems.PAPAYA.get());
        output.accept(ModItems.SLICED_PAPAYA.get());
        output.accept(ModItems.RAW_PAPAYA.get());
        output.accept(ModItems.RAW_PAPAYA_SLICE.get());
        output.accept(ModItems.PAPAYA_FLOWER.get());
        output.accept(ModItems.PAPAYA_LOG.get());
        output.accept(ModItems.STRIPPED_PAPAYA_LOG.get());
        output.accept(ModItems.PAPAYA_WOOD.get());
        output.accept(ModItems.STRIPPED_PAPAYA_WOOD.get());
        output.accept(ModItems.PAPAYA_LEAVES.get());
        output.accept(ModItems.PAPAYA_SAPLING.get());
        output.accept(ModItems.PAPAYA_SEEDS.get());

        output.accept(ModItems.WILD_BASIL.get());
        output.accept(ModItems.BASIL.get());

        output.accept(ModItems.BUTTERFLY_PEA.get());
        output.accept(ModItems.BUTTERFLY_PEA_SEEDS.get());

        output.accept(ModItems.BAMBOO_SHOOT.get());

        output.accept(ModItems.CRAB_SPAWN_EGG.get());
        output.accept(ModItems.DRAGONFLY_SPAWN_EGG.get());

        output.accept(ModItems.CRAB_EGG.get());
        output.accept(ModItems.CRAB_BUCKET.get());
        output.accept(ModItems.CRAB_MEAT.get());
        output.accept(ModItems.COOKED_CRAB_MEAT.get());

        Arrays.stream(DragonflyEntity.DragonflyVariant.values()).forEach(dragonflyVariant -> {
            ItemStack itemStack = new ItemStack(ModItems.DRAGONFLY_BOTTLE.get());
            DragonflyBottleItem.setVariant(itemStack,dragonflyVariant);
            output.accept(itemStack);
        });
        output.accept(ModItems.DRAGONFLY.get());
        output.accept(ModItems.COOKED_DRAGONFLY.get());

        output.accept(ModItems.FISH_SAUCE_BOTTLE.get());
        output.accept(ModItems.FERMENTED_FISH.get());
        output.accept(ModItems.PAPAYA_JUICE.get());
        output.accept(ModItems.LIME_JUICE.get());
        output.accept(ModItems.HONEY_LIME_JUICE.get());
        output.accept(ModItems.COCONUT_WATER.get());
        output.accept(ModItems.BUTTERFLY_PEA_TEA.get());

        output.accept(ModItems.PESTO_SAUCE.get());
        output.accept(ModItems.FRIED_DURIAN.get());
        output.accept(ModItems.SOMTAM_FEAST.get());
        output.accept(ModItems.SOMTAM.get());
        output.accept(ModItems.LARB_FEAST.get());
        output.accept(ModItems.LARB.get());
        output.accept(ModItems.CRAB_FRIED_RICE_FEAST.get());
        output.accept(ModItems.CRAB_FRIED_RICE.get());
        output.accept(ModItems.PHAT_KAPHRAO_FEAST.get());
        output.accept(ModItems.PHAT_KAPHRAO.get());
        output.accept(ModItems.MANGO_STICKY_RICE_FEAST.get());
        output.accept(ModItems.MANGO_STICKY_RICE.get());
        output.accept(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get());
        output.accept(ModItems.PINEAPPLE_FRIED_RICE.get());
        output.accept(ModItems.STIR_FRIED_NOODLE.get());
        output.accept(ModItems.COCONUT_MILK_BOTTLE.get());
        output.accept(ModItems.DURIAN_CURRY.get());
        output.accept(ModItems.DURIAN_CAKE.get());
        output.accept(ModItems.DURIAN_CAKE_SLICE.get());
        output.accept(ModItems.MANGO_CHEESECAKE.get());
        output.accept(ModItems.MANGO_CHEESECAKE_SLICE.get());

        output.accept(ModItems.COCONUT_JELLY.get());
        output.accept(ModItems.KHANOM_BABIN.get());
        output.accept(ModItems.COCONUT_PIE.get());
        output.accept(ModItems.COCONUT_PIE_SLICE.get());
        output.accept(ModItems.HONEY_COCONUT_PIE.get());
        output.accept(ModItems.HONEY_COCONUT_PIE_SLICE.get());

        output.accept(ModItems.OMELETTE_FEAST.get());
        output.accept(ModItems.OMELETTE.get());
        output.accept(ModItems.BASIL_OMELETTE_FEAST.get());
        output.accept(ModItems.BASIL_OMELETTE.get());

        output.accept(ModItems.BAMBOO_SHOOT_SOUP.get());
        output.accept(ModItems.STEAMED_BAMBOO_SHOOT.get());

        output.accept(ModItems.BANANA_IN_COCONUT_MILK.get());

        Item[] dyeItems = new Item[]{
                Items.LIGHT_GRAY_DYE,Items.GRAY_DYE,Items.BLACK_DYE,
                Items.BROWN_DYE,Items.RED_DYE,Items.ORANGE_DYE,Items.YELLOW_DYE,
                Items.LIME_DYE,Items.GREEN_DYE,Items.CYAN_DYE,Items.LIGHT_BLUE_DYE,
                Items.BLUE_DYE,Items.MAGENTA_DYE,Items.PURPLE_DYE,Items.PINK_DYE
        };

        output.accept(ModItems.KHANOM_CHAN.get());
        for(Item dyeItem: dyeItems){
            output.accept(DyeableLeatherItem.dyeArmor(new ItemStack(ModItems.KHANOM_CHAN.get()),List.of((DyeItem) dyeItem)));
        }


        output.accept(ModItems.COCONUT_MILK_ICE_CREAM.get());
        for(Item dyeItem: dyeItems){
            output.accept(DyeableLeatherItem.dyeArmor(new ItemStack(ModItems.COCONUT_MILK_ICE_CREAM.get()), List.of((DyeItem) dyeItem)));
        }
    }
}
