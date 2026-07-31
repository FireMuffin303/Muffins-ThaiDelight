package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ThaiDelightLangProviderEN extends LanguageProvider {
    public ThaiDelightLangProviderEN(PackOutput output) {
        super(output, ThaiDelightCommon.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
        add("advancement.muffins_thaidelight.got_mortar.description","It's time to taste Thai Food!");

        add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
        add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtains Cooked Dragonfly. Is it really edible?");

        add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        add("advancement.muffins_thaidelight.sliced_lime","Is it snapshot day?");
        add("advancement.muffins_thaidelight.sliced_lime.description","Obtains Sliced Lime. What are bugs they fixed this time?");

        add("advancement.muffins_thaidelight.got_pepper","The true ingredient");
        add("advancement.muffins_thaidelight.got_pepper.description","Obtains Pepper.");

        add("advancement.muffins_thaidelight.got_spicy_meat_salad","Yummy Larb");
        add("advancement.muffins_thaidelight.got_spicy_meat_salad.description","Obtains Larb. Yummy");

        add("advancement.muffins_thaidelight.got_somtam","Pepper Please");
        add("advancement.muffins_thaidelight.got_somtam.description","Obtains Somtam, is one pepper really enough?");

        add("advancement.muffins_thaidelight.being_stinky","Uh Oh Stinky");
        add("advancement.muffins_thaidelight.being_stinky.description","Has Stinky effect. Take a bath.");

        add("advancement.muffins_thaidelight.susie_prize","The Susie Prize");
        add("advancement.muffins_thaidelight.susie_prize.description","Light a papaya flower with glow ink sac. C'mon you deserve one.");

        add("advancement.muffins_thaidelight.big_big_nut","Big Big Nut");
        add("advancement.muffins_thaidelight.big_big_nut.description","Catch a coconut with a sack. Coconut is a giant nut.");

        add("advancement.muffins_thaidelight.gravity_not_invent","Gravity not invent");
        add("advancement.muffins_thaidelight.gravity_not_invent.description","Got a durian fall on the head. I don't think that is an apple tree.");

        add("item.muffins_thaidelight.tasty","Tasty");

        add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        add("farmersdelight.tooltip.lime_juice","Clear Blindness Effect");
        add("farmersdelight.tooltip.honey_lime_juice","Clear Blindness and Poison Effect");
        add("farmersdelight.tooltip.coconut_water","Clear Weakness Effect");
        add("farmersdelight.tooltip.butterfly_pea_tea","Clear Slowness");
        add("jei.info.papaya_log","Right click with bone meal to grow papayas.");

        add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        addItem(ModItems.MORTAR,"Mortar");
        addItem(ModItems.SACK,"Sack");
        addItem(ModItems.LIME_CRATE,"Lime Crate");
        addItem(ModItems.PEPPER_CRATE,"Pepper Crate");
        addItem(ModItems.RAW_PAPAYA_CRATE,"Raw Papaya Crate");
        addItem(ModItems.PAPAYA_CRATE,"Papaya Crate");
        addItem(ModItems.MANGO_CRATE,"Mango Crate");
        addItem(ModItems.HOLY_BASIL_CRATE,"Holy Basil Crate");
        addItem(ModItems.BASIL_CRATE,"Basil Crate");
        addItem(ModItems.BAMBOO_SHOOT_CRATE,"Bamboo Shoot Crate");
        addItem(ModItems.BUTTERFLY_PEA_CRATE,"Butterfly Pea Crate");
        addItem(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
        addItem(ModItems.CRAB_EGG,"Flower Crab Egg");
        addItem(ModItems.CRAB_BUCKET,"Flower Crab Bucket");
        addItem(ModItems.CRAB_MEAT,"Raw Flower Crab");
        addItem(ModItems.COOKED_CRAB_MEAT,"Cooked Flower Crab");
        addItem(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
        addItem(ModItems.DRAGONFLY_BOTTLE,"Bottle of Dragonfly");
        addItem(ModItems.DRAGONFLY,"Dragonfly");
        addItem(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");
        addItem(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");
        addItem(ModItems.FERMENTED_FISH,"Fermented Fish");
        addItem(ModItems.PAPAYA_JUICE,"Papaya Juice");
        addItem(ModItems.LIME_JUICE,"Lime Juice");
        addItem(ModItems.HONEY_LIME_JUICE,"Honey Lime Juice");
        addItem(ModItems.COCONUT_WATER,"Coconut Water");
        addItem(ModItems.BUTTERFLY_PEA_TEA,"Butterfly Pea Tea");
        addItem(ModItems.LIME_SAPLING,"Lime Sapling");
        addItem(ModItems.LIME,"Lime");
        addItem(ModItems.SLICED_LIME,"Lime Slice");
        addItem(ModItems.WILD_PEPPER_CROP,"Wild Pepper");
        addItem(ModItems.PEPPER,"Pepper");
        addItem(ModItems.PEPPER_SEED,"Pepper Seeds");

        addItem(ModItems.DURIAN_SAPLING,"Durian Sapling");
        addItem(ModItems.DURIAN_LEAVES,"Durian Leaves");
        addItem(ModItems.DURIAN_FLOWER,"Durian Flower");
        addItem(ModItems.SMALL_DURIAN,"Small Durian");
        addItem(ModItems.DURIAN,"Durian");
        addItem(ModItems.DURIAN_PULP,"Durian Pulp");
        addItem(ModItems.DURIAN_PEEL,"Durian Peel");
        addItem(ModItems.DURIAN_PEEL_BLOCK,"Durian Peel Block");
        addItem(ModItems.DURIAN_HELMET,"Durian Helmet");
        addItem(ModItems.DURIAN_LOG,"Durian Log");
        addItem(ModItems.DURIAN_WOOD,"Durian Wood");
        addItem(ModItems.STRIPPED_DURIAN_LOG,"Stripped Durian Log");
        addItem(ModItems.STRIPPED_DURIAN_WOOD,"Stripped Durian Wood");
        addItem(ModItems.DURIAN_PLANKS,"Durian Planks");
        addItem(ModItems.DURIAN_STAIRS,"Durian Stairs");
        addItem(ModItems.DURIAN_SLAB,"Durian Slab");
        addItem(ModItems.DURIAN_FENCE,"Durian Fence");
        addItem(ModItems.DURIAN_FENCE_GATE,"Durian Fence Gate");
        addItem(ModItems.DURIAN_DOOR,"Durian Door");
        addItem(ModItems.DURIAN_TRAPDOOR,"Durian Trapdoor");
        addItem(ModItems.DURIAN_PRESSURE_PLATE,"Durian Pressure Plate");
        addItem(ModItems.DURIAN_BUTTON,"Durian Button");
        addItem(ModItems.DURIAN_SIGN,"Durian Sign");
        addItem(ModItems.DURIAN_HANGING_SIGN,"Durian Hanging Sign");
        addItem(ModItems.DURIAN_CABINET,"Durian Cabinet");
        addItem(ModItems.DURIAN_BOAT,"Durian Boat");
        addItem(ModItems.DURIAN_CHEST_BOAT,"Durian Chest Boat");
        addItem(ModItems.COCONUT_SAPLING,"Coconut Sapling");
        addItem(ModItems.COCONUT_LEAF,"Coconut Leaf");
        addItem(ModItems.BUDDING_COCONUT_LEAF,"Budding Coconut Leaf");
        addItem(ModItems.COCONUT_LEAF_BLOCK,"Coconut Leaf Block");
        addItem(ModItems.COCONUT_LEAF_MAT,"Coconut Leaf Carpet");
        addItem(ModItems.COCONUT_LOG,"Coconut Log");
        addItem(ModItems.COCONUT_WOOD,"Coconut Wood");
        addItem(ModItems.STRIPPED_COCONUT_LOG,"Stripped Coconut Log");
        addItem(ModItems.STRIPPED_COCONUT_WOOD,"Stripped Coconut Wood");
        addItem(ModItems.COCONUT_PLANKS,"Coconut Planks");
        addItem(ModItems.COCONUT_STAIRS,"Coconut Stairs");
        addItem(ModItems.COCONUT_SLAB,"Coconut Slab");
        addItem(ModItems.COCONUT_FENCE,"Coconut Fence");
        addItem(ModItems.COCONUT_FENCE_GATE,"Coconut Fence Gate");
        addItem(ModItems.COCONUT_DOOR,"Coconut Door");
        addItem(ModItems.COCONUT_TRAPDOOR,"Coconut Trapdoor");
        addItem(ModItems.COCONUT_PRESSURE_PLATE,"Coconut Pressure Plate");
        addItem(ModItems.COCONUT_BUTTON,"Coconut Button");
        addItem(ModItems.COCONUT_SIGN,"Coconut Sign");
        addItem(ModItems.COCONUT_HANGING_SIGN,"Coconut Hanging Sign");
        addItem(ModItems.COCONUT_BOAT,"Coconut Boat");
        addItem(ModItems.COCONUT_CHEST_BOAT,"Coconut Chest Boat");
        addItem(ModItems.COCONUT_CABINET,"Coconut Cabinet");
        addItem(ModItems.COCONUT,"Coconut");
        addItem(ModItems.STRIPPED_COCONUT,"Stripped Coconut");
        addItem(ModItems.COCONUT_SLICE,"Coconut Slice");
        addItem(ModItems.MANGO_SAPLING,"Mango Sapling");
        addItem(ModItems.MANGO_LEAVES,"Mango Leaves");
        addItem(ModItems.MANGO_LOG,"Mango Log");
        addItem(ModItems.MANGO_WOOD,"Mango Wood");
        addItem(ModItems.STRIPPED_MANGO_LOG,"Stripped Mango Log");
        addItem(ModItems.STRIPPED_MANGO_WOOD,"Stripped Mango Wood");
        addItem(ModItems.MANGO_PLANKS,"Mango Planks");
        addItem(ModItems.MANGO_STAIRS,"Mango Stairs");
        addItem(ModItems.MANGO_SLAB,"Mango Slab");
        addItem(ModItems.MANGO_FENCE,"Mango Fence");
        addItem(ModItems.MANGO_FENCE_GATE,"Mango Fence Gate");
        addItem(ModItems.MANGO_DOOR,"Mango Door");
        addItem(ModItems.MANGO_TRAPDOOR,"Mango Trapdoor");
        addItem(ModItems.MANGO_PRESSURE_PLATE,"Mango Pressure Plate");
        addItem(ModItems.MANGO_BUTTON,"Mango Button");
        addItem(ModItems.MANGO_SIGN,"Mango Sign");
        addItem(ModItems.MANGO_HANGING_SIGN,"Mango Hanging Sign");
        addItem(ModItems.MANGO_BOAT,"Mango Boat");
        addItem(ModItems.MANGO_CHEST_BOAT,"Mango Chest Boat");
        addItem(ModItems.MANGO_CABINET,"Mango Cabinet");
        addItem(ModItems.MANGO,"Mango");
        addItem(ModItems.MANGO_SLICE,"Mango Slice");
        addItem(ModItems.PAPAYA,"Papaya");
        addItem(ModItems.PAPAYA_FLOWER,"Papaya Flower");
        addItem(ModItems.SLICED_PAPAYA,"Papaya Slice");
        addItem(ModItems.RAW_PAPAYA,"Raw Papaya");
        addItem(ModItems.RAW_PAPAYA_SLICE,"Raw Papaya Slice");
        addItem(ModItems.PAPAYA_LOG,"Papaya Log");
        addItem(ModItems.STRIPPED_PAPAYA_LOG,"Stripped Papaya Log");
        addItem(ModItems.PAPAYA_WOOD,"Papaya Wood");
        addItem(ModItems.STRIPPED_PAPAYA_WOOD,"Stripped Papaya Wood");
        addItem(ModItems.PAPAYA_LEAVES,"Papaya Leaves");
        addItem(ModItems.PAPAYA_SAPLING,"Papaya Sapling");
        addItem(ModItems.PAPAYA_SEEDS,"Papaya Seeds");
        addItem(ModItems.WILD_BASIL,"Wild Basil");
        addItem(ModItems.BASIL,"Basil");

        addItem(ModItems.BUTTERFLY_PEA,"Butterfly Pea");
        addItem(ModItems.BUTTERFLY_PEA_SEEDS,"Butterfly Pea Seeds");
        addItem(ModItems.BAMBOO_SHOOT,"Bamboo Shoot");
        addItem(ModItems.PESTO_SAUCE,"Pesto Sauce");
        addItem(ModItems.FRIED_DURIAN,"Fried Durian");
        addItem(ModItems.SOMTAM_FEAST,"Somtam");
        addItem(ModItems.SOMTAM,"Plate of Somtam");
        addItem(ModItems.LARB_FEAST,"Larb");
        addItem(ModItems.LARB,"Plate of Larb");
        addItem(ModItems.CRAB_FRIED_RICE_FEAST,"Crab Fried Rice");
        addItem(ModItems.CRAB_FRIED_RICE,"Plate of Crab Fried Rice");
        addItem(ModItems.PHAT_KAPHRAO_FEAST,"Phat Kaphrao");
        addItem(ModItems.PHAT_KAPHRAO,"Plate of Phat Kaphrao");
        addItem(ModItems.MANGO_STICKY_RICE_FEAST,"Mango Sticky Rice");
        addItem(ModItems.MANGO_STICKY_RICE,"Plate of Mango Sticky Rice");
        addItem(ModItems.PINEAPPLE_FRIED_RICE_FEAST,"Pineapple Fried Rice");
        addItem(ModItems.PINEAPPLE_FRIED_RICE,"Plate of Pineapple Fried Rice");
        addItem(ModItems.STIR_FRIED_NOODLE,"Stir Fried Noodle");
        addItem(ModItems.COCONUT_MILK_BOTTLE,"Coconut Milk Bottle");
        addItem(ModItems.DURIAN_CURRY,"Durian Curry");
        addItem(ModItems.DURIAN_CAKE,"Durian Cake");
        addItem(ModItems.DURIAN_CAKE_SLICE,"Durian Cake Slice");
        addItem(ModItems.MANGO_CHEESECAKE,"Mango Cheesecake");
        addItem(ModItems.MANGO_CHEESECAKE_SLICE,"Mango Cheesecake Slice");
        addItem(ModItems.COCONUT_JELLY,"Coconut Jelly");
        addItem(ModItems.KHANOM_BABIN,"Khanom Babin");
        addItem(ModItems.COCONUT_PIE,"Coconut Pie");
        addItem(ModItems.COCONUT_PIE_SLICE,"Coconut Pie Slice");
        addItem(ModItems.HONEY_COCONUT_PIE,"Honey Coconut Pie");
        addItem(ModItems.HONEY_COCONUT_PIE_SLICE,"Honey Coconut Pie Slice");
        addItem(ModItems.OMELETTE_FEAST,"Omelette");
        addItem(ModItems.OMELETTE,"Plate of Omelette");
        addItem(ModItems.BASIL_OMELETTE_FEAST,"Basil Omelette");
        addItem(ModItems.BASIL_OMELETTE,"Plate of Basil Omelette");
        addItem(ModItems.BAMBOO_SHOOT_SOUP,"Bamboo Shoot Soup");
        addItem(ModItems.STEAMED_BAMBOO_SHOOT,"Steamed Bamboo Shoot");
        addItem(ModItems.BANANA_IN_COCONUT_MILK,"Banana in Coconut Milk");
        addItem(ModItems.KHANOM_CHAN,"Khanom Chan");
        addItem(ModItems.COCONUT_MILK_ICE_CREAM,"Coconut Milk Ice Cream");

        add("dragonfly.variant.muffins_thaidelight.red","Red");
        add("dragonfly.variant.muffins_thaidelight.yellow","Yellow");
        add("dragonfly.variant.muffins_thaidelight.green","Green");
        add("dragonfly.variant.muffins_thaidelight.blue","Blue");
        add("container.muffins_thaidelight.mortar","Mortar");

        addEffect(() -> ModMobEffects.STINKY.value(),"Stinky");
        addEffect(() -> ModMobEffects.APPETITE_LOSS.value(),"Appetite Loss");
        add("item.minecraft.potion.effect.stench","Potion of Stench");
        add("item.minecraft.potion.effect.long_stench","Potion of Stench");
        add("item.minecraft.potion.effect.strong_stench","Potion of Stench");

        add("item.minecraft.splash_potion.effect.stench","Splash Potion of Stench");
        add("item.minecraft.splash_potion.effect.long_stench","Splash Potion of Stench");
        add("item.minecraft.splash_potion.effect.strong_stench","Splash Potion of Stench");

        add("item.minecraft.lingering_potion.effect.stench","Lingering Potion of Stench");
        add("item.minecraft.lingering_potion.effect.long_stench","Lingering Potion of Stench");
        add("item.minecraft.lingering_potion.effect.strong_stench","Lingering Potion of Stench");

        add("item.minecraft.tipped_arrow.effect.stench","Arrow of Stench");
        add("item.minecraft.tipped_arrow.effect.long_stench","Arrow of Stench");
        add("item.minecraft.tipped_arrow.effect.strong_stench","Arrow of Stench");

        add("muffins_thaidelight.jei.cauldron.fermented_fish1","Put a fish into Water Cauldron");
        add("muffins_thaidelight.jei.cauldron.fermented_fish2","and wait for 6 - 15 minutes");
        add("muffins_thaidelight.jei.cauldron.fermented_fish3","until it fully fermented.");

        add("muffins_thaidelight.jei.cauldron.coconut_milk_bottle","Put water into filled Coconut Cauldron");

        add("emi.category.muffins_thaidelight.mortar","Mortar");
        add("emi.category.muffins_thaidelight.cauldron_crafting","Cauldron");

        add("muffins_thaidelight.midnightconfig.title","Muffin's Thai's Delight Config");
        add("muffins_thaidelight.midnightconfig.gameplay","Gameplay");
        add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem","Enable Wandering Traders Trades Thai's Delight Items");
        add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem.tooltip","Require Game/Server Restart");
        add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem","Enable Villagers Trades Thai's Delight Items");
        add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem.tooltip","Require Game/Server Restart");
        add("muffins_thaidelight.midnightconfig.worldGen","World Generation");
        add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn","Enable Thai House generates in Villages");
        add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn.tooltip","Require Server Restart");
        add("muffins_thaidelight.midnightconfig.stinkyShouldTriggerNeutral","Stinky Effect should trigger Neutral Mobs");
        add("muffins_thaidelight.midnightconfig.fishofthieves","Fish Of Thieves Compatibility");
        add("muffins_thaidelight.midnightconfig.coconutTreeType","Coconut Tree Generation");
        add("muffins_thaidelight.midnightconfig.coconutTreeType.tooltip","Require Game/Server Restart");
        add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn","Enable Thai's Delight Mango Tree Generation");
        add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn.tooltip","Require Game/Server Restart");

        add("muffins_thaidelight.midnightconfig.enum.TreeType.BOTH","Thai's Delight & Fish of Thieves");
        add("muffins_thaidelight.midnightconfig.enum.TreeType.THAI_DELIGHT","Thai's Delight");
        add("muffins_thaidelight.midnightconfig.enum.TreeType.FISH_OF_THIEVES","Fish of Thieves");

        add("muffins_thaidelight.midnightconfig.developer","------ Developer ------");
        add("muffins_thaidelight.midnightconfig.firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.concept_artist","------ Concept Artists ------");
        add("muffins_thaidelight.midnightconfig.lucas","lllLucaslll");
        add("muffins_thaidelight.midnightconfig.dino_care","Dino_care");
        add("muffins_thaidelight.midnightconfig.akalinka","No92");
        add("muffins_thaidelight.midnightconfig.translator","------ Translators ------");
        add("muffins_thaidelight.midnightconfig.en_us","--- English ---");
        add("muffins_thaidelight.midnightconfig.en_firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.th_th","--- Thai ---");
        add("muffins_thaidelight.midnightconfig.th_firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.uk_ua","--- Ukrainian ---");
        add("muffins_thaidelight.midnightconfig.peaenka","P34enKa");
        add("muffins_thaidelight.midnightconfig.zh_cn","--- Simplified Chinese ---");
        add("muffins_thaidelight.midnightconfig.huantanhua","Huantanhua");

        add("muffins_thaidelight.consume.durian_fermented_drinks","Your body heated up.");
        add("muffins_thaidelight.custom_effect_render.durian_consumed","Durian Consumed");
        add("muffins_thaidelight.custom_effect_render.heated_up","Heated Up");
        add("muffins_thaidelight.custom_effect_render.spicy","Spicy");

        add("death.attack.durian","%1$s was bonked by a falling durian.");
        add("death.attack.durian.player","%1$s was bonked by a falling durian whilst fighting %2$s.");
    }
}
