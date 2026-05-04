package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;

public class LangDataGen extends FabricLanguageProvider {
    protected LangDataGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //Advancement
        translationBuilder.add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
        translationBuilder.add("advancement.muffins_thaidelight.got_mortar.description","It's time to taste Thai Food!");


        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtains Cooked Dragonfly. Is it really edible?");

        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime","Is it snapshot day?");
        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime.description","Obtains Sliced Lime. What are bugs they fixed this time?");

        translationBuilder.add("advancement.muffins_thaidelight.got_pepper","The true ingredient");
        translationBuilder.add("advancement.muffins_thaidelight.got_pepper.description","Obtains Pepper.");

        translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad","Yummy Larb");
        translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad.description","Obtains Larb. Yummy");

        translationBuilder.add("advancement.muffins_thaidelight.got_somtam","Pepper Please");
        translationBuilder.add("advancement.muffins_thaidelight.got_somtam.description","Obtains Somtam, is one pepper really enough?");

        translationBuilder.add("advancement.muffins_thaidelight.being_stinky","Uh Oh Stinky");
        translationBuilder.add("advancement.muffins_thaidelight.being_stinky.description","Has Stinky effect. Take a bath.");

        translationBuilder.add("advancement.muffins_thaidelight.susie_prize","The Susie Prize");
        translationBuilder.add("advancement.muffins_thaidelight.susie_prize.description","Light a papaya flower with glow ink sac. C'mon you deserve one.");

        translationBuilder.add("advancement.muffins_thaidelight.big_big_nut","Big Big Nut");
        translationBuilder.add("advancement.muffins_thaidelight.big_big_nut.description","Catch a coconut with a sack. Coconut is a giant nut.");

        translationBuilder.add("advancement.muffins_thaidelight.gravity_not_invent","Gravity not invent");
        translationBuilder.add("advancement.muffins_thaidelight.gravity_not_invent.description","Got a durian fall on the head. I don't think that is an apple tree.");

        translationBuilder.add("item.muffins_thaidelight.tasty","Tasty");

        translationBuilder.add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        translationBuilder.add("farmersdelight.tooltip.lime_juice","Clear Blindness Effect");
        translationBuilder.add("farmersdelight.tooltip.honey_lime_juice","Clear Blindness and Poison Effect");
        translationBuilder.add("farmersdelight.tooltip.coconut_water","Clear Weakness Effect");
        translationBuilder.add("farmersdelight.tooltip.butterfly_pea_tea","Clear Slowness");
        translationBuilder.add("jei.info.papaya_log","Right click with bone meal to grow papayas.");


        translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        //Blocks
        translationBuilder.add(ModItems.MORTAR.get(),"Mortar");
        translationBuilder.add(ModItems.SACK.get(),"Sack");
        translationBuilder.add(ModItems.LIME_CRATE.get(),"Lime Crate");
        translationBuilder.add(ModItems.PEPPER_CRATE.get(),"Pepper Crate");
        translationBuilder.add(ModItems.RAW_PAPAYA_CRATE.get(),"Raw Papaya Crate");
        translationBuilder.add(ModItems.PAPAYA_CRATE.get(),"Papaya Crate");
        translationBuilder.add(ModItems.MANGO_CRATE.get(),"Mango Crate");
        translationBuilder.add(ModItems.HOLY_BASIL_CRATE.get(),"Holy Basil Crate");
        translationBuilder.add(ModItems.BASIL_CRATE.get(),"Thai Basil Crate");
        translationBuilder.add(ModItems.BAMBOO_SHOOT_CRATE.get(),"Bamboo Shoot Crate");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_CRATE.get(),"Butterfly Pea Crate");
        translationBuilder.add(ModItems.CRAB_SPAWN_EGG.get(),"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_EGG.get(),"Flower Crab Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET.get(),"Flower Crab Bucket");
        translationBuilder.add(ModItems.CRAB_MEAT.get(),"Raw Flower Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT.get(),"Cooked Flower Crab");
        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG.get(),"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE.get(),"Bottle of Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY.get(),"Dragonfly");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY.get(),"Cooked Dragonfly");
        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE.get(),"Fish Sauce Bottle");
        translationBuilder.add(ModItems.FERMENTED_FISH.get(),"Fermented Fish");
        translationBuilder.add(ModItems.PAPAYA_JUICE.get(),"Papaya Juice");
        translationBuilder.add(ModItems.LIME_JUICE.get(),"Lime Juice");
        translationBuilder.add(ModItems.HONEY_LIME_JUICE.get(),"Honey Lime Juice");
        translationBuilder.add(ModItems.COCONUT_WATER.get(),"Coconut Water");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_TEA.get(),"Butterfly Pea Tea");
        translationBuilder.add(ModItems.LIME_SAPLING.get(),"Lime Sapling");
        translationBuilder.add(ModItems.LIME.get(),"Lime");
        translationBuilder.add(ModItems.SLICED_LIME.get(),"Lime Slice");
        translationBuilder.add(ModItems.WILD_PEPPER_CROP.get(),"Wild Pepper");
        translationBuilder.add(ModItems.PEPPER.get(),"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED.get(),"Pepper Seeds");

        translationBuilder.add(ModItems.DURIAN_SAPLING.get(),"Durian Sapling");
        translationBuilder.add(ModItems.DURIAN_LEAVES.get(),"Durian Leaves");
        translationBuilder.add(ModItems.DURIAN_FLOWER.get(),"Durian Flower");
        translationBuilder.add(ModItems.SMALL_DURIAN.get(),"Small Durian");
        translationBuilder.add(ModItems.DURIAN.get(),"Durian");
        translationBuilder.add(ModItems.DURIAN_PULP.get(),"Durian Pulp");
        translationBuilder.add(ModItems.DURIAN_PEEL.get(),"Durian Peel");
        translationBuilder.add(ModItems.DURIAN_PEEL_BLOCK.get(),"Durian Peel Block");
        translationBuilder.add(ModItems.DURIAN_HELMET.get(),"Durian Helmet");
        translationBuilder.add(ModItems.DURIAN_LOG.get(),"Durian Log");
        translationBuilder.add(ModItems.DURIAN_WOOD.get(),"Durian Wood");
        translationBuilder.add(ModItems.STRIPPED_DURIAN_LOG.get(),"Stripped Durian Log");
        translationBuilder.add(ModItems.STRIPPED_DURIAN_WOOD.get(),"Stripped Durian Wood");
        translationBuilder.add(ModItems.DURIAN_PLANKS.get(),"Durian Planks");
        translationBuilder.add(ModItems.DURIAN_STAIRS.get(),"Durian Stairs");
        translationBuilder.add(ModItems.DURIAN_SLAB.get(),"Durian Slab");
        translationBuilder.add(ModItems.DURIAN_FENCE.get(),"Durian Fence");
        translationBuilder.add(ModItems.DURIAN_FENCE_GATE.get(),"Durian Fence Gate");
        translationBuilder.add(ModItems.DURIAN_DOOR.get(),"Durian Door");
        translationBuilder.add(ModItems.DURIAN_TRAPDOOR.get(),"Durian Trapdoor");
        translationBuilder.add(ModItems.DURIAN_PRESSURE_PLATE.get(),"Durian Pressure Plate");
        translationBuilder.add(ModItems.DURIAN_BUTTON.get(),"Durian Button");
        translationBuilder.add(ModItems.DURIAN_SIGN.get(),"Durian Sign");
        translationBuilder.add(ModItems.DURIAN_HANGING_SIGN.get(),"Durian Hanging Sign");
        translationBuilder.add(ModItems.DURIAN_CABINET.get(),"Durian Cabinet");
        translationBuilder.add(ModItems.DURIAN_BOAT.get(),"Durian Boat");
        translationBuilder.add(ModItems.DURIAN_CHEST_BOAT.get(),"Durian Chest Boat");
        translationBuilder.add(ModItems.COCONUT_SAPLING.get(),"Coconut Sapling");
        translationBuilder.add(ModItems.COCONUT_LEAF.get(),"Coconut Leaf");
        translationBuilder.add(ModItems.BUDDING_COCONUT_LEAF.get(),"Budding Coconut Leaf");
        translationBuilder.add(ModItems.COCONUT_LEAF_BLOCK.get(),"Coconut Leaf Block");
        translationBuilder.add(ModItems.COCONUT_LEAF_MAT.get(),"Coconut Leaf Carpet");
        translationBuilder.add(ModItems.COCONUT_LOG.get(),"Coconut Log");
        translationBuilder.add(ModItems.COCONUT_WOOD.get(),"Coconut Wood");
        translationBuilder.add(ModItems.STRIPPED_COCONUT_LOG.get(),"Stripped Coconut Log");
        translationBuilder.add(ModItems.STRIPPED_COCONUT_WOOD.get(),"Stripped Coconut Wood");
        translationBuilder.add(ModItems.COCONUT_PLANKS.get(),"Coconut Planks");
        translationBuilder.add(ModItems.COCONUT_STAIRS.get(),"Coconut Stairs");
        translationBuilder.add(ModItems.COCONUT_SLAB.get(),"Coconut Slab");
        translationBuilder.add(ModItems.COCONUT_FENCE.get(),"Coconut Fence");
        translationBuilder.add(ModItems.COCONUT_FENCE_GATE.get(),"Coconut Fence Gate");
        translationBuilder.add(ModItems.COCONUT_DOOR.get(),"Coconut Door");
        translationBuilder.add(ModItems.COCONUT_TRAPDOOR.get(),"Coconut Trapdoor");
        translationBuilder.add(ModItems.COCONUT_PRESSURE_PLATE.get(),"Coconut Pressure Plate");
        translationBuilder.add(ModItems.COCONUT_BUTTON.get(),"Coconut Button");
        translationBuilder.add(ModItems.COCONUT_SIGN.get(),"Coconut Sign");
        translationBuilder.add(ModItems.COCONUT_HANGING_SIGN.get(),"Coconut Hanging Sign");
        translationBuilder.add(ModItems.COCONUT_BOAT.get(),"Coconut Boat");
        translationBuilder.add(ModItems.COCONUT_CHEST_BOAT.get(),"Coconut Chest Boat");
        translationBuilder.add(ModItems.COCONUT_CABINET.get(),"Coconut Cabinet");
        translationBuilder.add(ModItems.COCONUT.get(),"Coconut");
        translationBuilder.add(ModItems.STRIPPED_COCONUT.get(),"Stripped Coconut");
        translationBuilder.add(ModItems.COCONUT_SLICE.get(),"Coconut Slice");
        translationBuilder.add(ModItems.MANGO_SAPLING.get(),"Mango Sapling");
        translationBuilder.add(ModItems.MANGO_LEAVES.get(),"Mango Leaves");
        translationBuilder.add(ModItems.MANGO_LOG.get(),"Mango Log");
        translationBuilder.add(ModItems.MANGO_WOOD.get(),"Mango Wood");
        translationBuilder.add(ModItems.STRIPPED_MANGO_LOG.get(),"Stripped Mango Log");
        translationBuilder.add(ModItems.STRIPPED_MANGO_WOOD.get(),"Stripped Mango Wood");
        translationBuilder.add(ModItems.MANGO_PLANKS.get(),"Mango Planks");
        translationBuilder.add(ModItems.MANGO_STAIRS.get(),"Mango Stairs");
        translationBuilder.add(ModItems.MANGO_SLAB.get(),"Mango Slab");
        translationBuilder.add(ModItems.MANGO_FENCE.get(),"Mango Fence");
        translationBuilder.add(ModItems.MANGO_FENCE_GATE.get(),"Mango Fence Gate");
        translationBuilder.add(ModItems.MANGO_DOOR.get(),"Mango Door");
        translationBuilder.add(ModItems.MANGO_TRAPDOOR.get(),"Mango Trapdoor");
        translationBuilder.add(ModItems.MANGO_PRESSURE_PLATE.get(),"Mango Pressure Plate");
        translationBuilder.add(ModItems.MANGO_BUTTON.get(),"Mango Button");
        translationBuilder.add(ModItems.MANGO_SIGN.get(),"Mango Sign");
        translationBuilder.add(ModItems.MANGO_HANGING_SIGN.get(),"Mango Hanging Sign");
        translationBuilder.add(ModItems.MANGO_BOAT.get(),"Mango Boat");
        translationBuilder.add(ModItems.MANGO_CHEST_BOAT.get(),"Mango Chest Boat");
        translationBuilder.add(ModItems.MANGO_CABINET.get(),"Mango Cabinet");
        translationBuilder.add(ModItems.MANGO.get(),"Mango");
        translationBuilder.add(ModItems.MANGO_SLICE.get(),"Mango Slice");
        translationBuilder.add(ModItems.PAPAYA.get(),"Papaya");
        translationBuilder.add(ModItems.PAPAYA_FLOWER.get(),"Papaya Flower");
        translationBuilder.add(ModItems.SLICED_PAPAYA.get(),"Papaya Slice");
        translationBuilder.add(ModItems.RAW_PAPAYA.get(),"Raw Papaya");
        translationBuilder.add(ModItems.RAW_PAPAYA_SLICE.get(),"Raw Papaya Slice");
        translationBuilder.add(ModItems.PAPAYA_LOG.get(),"Papaya Log");
        translationBuilder.add(ModItems.STRIPPED_PAPAYA_LOG.get(),"Stripped Papaya Log");
        translationBuilder.add(ModItems.PAPAYA_WOOD.get(),"Papaya Wood");
        translationBuilder.add(ModItems.STRIPPED_PAPAYA_WOOD.get(),"Stripped Papaya Wood");
        translationBuilder.add(ModItems.PAPAYA_LEAVES.get(),"Papaya Leaves");
        translationBuilder.add(ModItems.PAPAYA_SAPLING.get(),"Papaya Sapling");
        translationBuilder.add(ModItems.PAPAYA_SEEDS.get(),"Papaya Seeds");
        translationBuilder.add(ModItems.WILD_BASIL.get(),"Wild Thai Basil");
        translationBuilder.add(ModItems.BASIL.get(),"Thai Basil");

        translationBuilder.add(ModItems.BUTTERFLY_PEA.get(),"Butterfly Pea");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_SEEDS.get(),"Butterfly Pea Seeds");
        translationBuilder.add(ModItems.BAMBOO_SHOOT.get(),"Bamboo Shoot");
        translationBuilder.add(ModItems.PESTO_SAUCE.get(),"Pesto Sauce");
        translationBuilder.add(ModItems.FRIED_DURIAN.get(),"Fried Durian");
        translationBuilder.add(ModItems.SOMTAM_FEAST.get(),"Somtam");
        translationBuilder.add(ModItems.SOMTAM.get(),"Plate of Somtam");
        translationBuilder.add(ModItems.LARB_FEAST.get(),"Larb");
        translationBuilder.add(ModItems.LARB.get(),"Plate of Larb");
        translationBuilder.add(ModItems.CRAB_FRIED_RICE_FEAST.get(),"Crab Fried Rice");
        translationBuilder.add(ModItems.CRAB_FRIED_RICE.get(),"Plate of Crab Fried Rice");
        translationBuilder.add(ModItems.PHAT_KAPHRAO_FEAST.get(),"Phat Kaphrao");
        translationBuilder.add(ModItems.PHAT_KAPHRAO.get(),"Plate of Phat Kaphrao");
        translationBuilder.add(ModItems.MANGO_STICKY_RICE_FEAST.get(),"Mango Sticky Rice");
        translationBuilder.add(ModItems.MANGO_STICKY_RICE.get(),"Plate of Mango Sticky Rice");
        translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get(),"Pineapple Fried Rice");
        translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE.get(),"Plate of Pineapple Fried Rice");
        translationBuilder.add(ModItems.STIR_FRIED_NOODLE.get(),"Stir Fried Noodle");
        translationBuilder.add(ModItems.COCONUT_MILK_BOTTLE.get(),"Coconut Milk Bottle");
        translationBuilder.add(ModItems.DURIAN_CURRY.get(),"Durian Curry");
        translationBuilder.add(ModItems.DURIAN_CAKE.get(),"Durian Cake");
        translationBuilder.add(ModItems.DURIAN_CAKE_SLICE.get(),"Durian Cake Slice");
        translationBuilder.add(ModItems.MANGO_CHEESECAKE.get(),"Mango Cheesecake");
        translationBuilder.add(ModItems.MANGO_CHEESECAKE_SLICE.get(),"Mango Cheesecake Slice");
        translationBuilder.add(ModItems.COCONUT_JELLY.get(),"Coconut Jelly");
        translationBuilder.add(ModItems.KHANOM_BABIN.get(),"Khanom Babin");
        translationBuilder.add(ModItems.COCONUT_PIE.get(),"Coconut Pie");
        translationBuilder.add(ModItems.COCONUT_PIE_SLICE.get(),"Coconut Pie Slice");
        translationBuilder.add(ModItems.HONEY_COCONUT_PIE.get(),"Honey Coconut Pie");
        translationBuilder.add(ModItems.HONEY_COCONUT_PIE_SLICE.get(),"Honey Coconut Pie Slice");
        translationBuilder.add(ModItems.OMELETTE_FEAST.get(),"Omelette");
        translationBuilder.add(ModItems.OMELETTE.get(),"Plate of Omelette");
        translationBuilder.add(ModItems.BASIL_OMELETTE_FEAST.get(),"Basil Omelette");
        translationBuilder.add(ModItems.BASIL_OMELETTE.get(),"Plate of Basil Omelette");
        translationBuilder.add(ModItems.BAMBOO_SHOOT_SOUP.get(),"Bamboo Shoot Soup");
        translationBuilder.add(ModItems.STEAMED_BAMBOO_SHOOT.get(),"Steamed Bamboo Shoot");
        translationBuilder.add(ModItems.BANANA_IN_COCONUT_MILK.get(),"Banana in Coconut Milk");
        translationBuilder.add(ModItems.KHANOM_CHAN.get(),"Khanom Chan");
        translationBuilder.add(ModItems.COCONUT_MILK_ICE_CREAM.get(),"Coconut Milk Ice Cream");


        translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","Red");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","Yellow");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","Green");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","Blue");
        translationBuilder.add("container.muffins_thaidelight.mortar","Mortar");

        translationBuilder.add(ModMobEffects.STINKY.get(),"Stinky");
        translationBuilder.add(ModMobEffects.APPETITE_LOSS.get(),"Appetite Loss");
        translationBuilder.add("item.minecraft.potion.effect.stench","Potion of Stench");
        translationBuilder.add("item.minecraft.potion.effect.long_stench","Potion of Stench");
        translationBuilder.add("item.minecraft.potion.effect.strong_stench","Potion of Stench");

        translationBuilder.add("item.minecraft.splash_potion.effect.stench","Splash Potion of Stench");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_stench","Splash Potion of Stench");
        translationBuilder.add("item.minecraft.splash_potion.effect.strong_stench","Splash Potion of Stench");

        translationBuilder.add("item.minecraft.lingering_potion.effect.stench","Lingering Potion of Stench");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_stench","Lingering Potion of Stench");
        translationBuilder.add("item.minecraft.lingering_potion.effect.strong_stench","Lingering Potion of Stench");

        translationBuilder.add("item.minecraft.tipped_arrow.effect.stench","Arrow of Stench");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_stench","Arrow of Stench");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_stench","Arrow of Stench");

        translationBuilder.add("muffins_thaidelight.jei.cauldron.fermented_fish1","Put a fish into Water Cauldron");
        translationBuilder.add("muffins_thaidelight.jei.cauldron.fermented_fish2","and wait for 6 - 15 minutes");
        translationBuilder.add("muffins_thaidelight.jei.cauldron.fermented_fish3","until it fully fermented.");

        translationBuilder.add("muffins_thaidelight.jei.cauldron.coconut_milk_bottle","Put water into filled Coconut Cauldron");

        translationBuilder.add("emi.category.muffins_thaidelight.mortar","Mortar");
        translationBuilder.add("emi.category.muffins_thaidelight.cauldron_crafting","Cauldron");

        translationBuilder.add("muffins_thaidelight.midnightconfig.title","Muffin's Thai's Delight Config");
        translationBuilder.add("muffins_thaidelight.midnightconfig.gameplay","Gameplay");
        translationBuilder.add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem","Enable Wandering Traders Trades Thai's Delight Items");
        translationBuilder.add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem.tooltip","Require Game/Server Restart");
        translationBuilder.add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem","Enable Villagers Trades Thai's Delight Items");
        translationBuilder.add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem.tooltip","Require Game/Server Restart");
        translationBuilder.add("muffins_thaidelight.midnightconfig.worldGen","World Generation");
        translationBuilder.add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn","Enable Thai House generates in Villages");
        translationBuilder.add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn.tooltip","Require Server Restart");
        translationBuilder.add("muffins_thaidelight.midnightconfig.stinkyShouldTriggerNeutral","Stinky Effect should trigger Neutral Mobs");
        translationBuilder.add("muffins_thaidelight.midnightconfig.fishofthieves","Fish Of Thieves Compatibility");
        translationBuilder.add("muffins_thaidelight.midnightconfig.coconutTreeType","Coconut Tree Generation");
        translationBuilder.add("muffins_thaidelight.midnightconfig.coconutTreeType.tooltip","Require Game/Server Restart");
        translationBuilder.add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn","Enable Thai's Delight Mango Tree Generation");
        translationBuilder.add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn.tooltip","Require Game/Server Restart");

        translationBuilder.add("muffins_thaidelight.midnightconfig.enum.TreeType.BOTH","Thai's Delight & Fish of Thieves");
        translationBuilder.add("muffins_thaidelight.midnightconfig.enum.TreeType.THAI_DELIGHT","Thai's Delight");
        translationBuilder.add("muffins_thaidelight.midnightconfig.enum.TreeType.FISH_OF_THIEVES","Fish of Thieves");

        translationBuilder.add("muffins_thaidelight.midnightconfig.developer","------ Developer ------");
        translationBuilder.add("muffins_thaidelight.midnightconfig.firemuffin","FireMuffin303");
        translationBuilder.add("muffins_thaidelight.midnightconfig.concept_artist","------ Concept Artists ------");
        translationBuilder.add("muffins_thaidelight.midnightconfig.lucas","lllLucaslll");
        translationBuilder.add("muffins_thaidelight.midnightconfig.dino_care","Dino_care");
        translationBuilder.add("muffins_thaidelight.midnightconfig.akalinka","No92");
        translationBuilder.add("muffins_thaidelight.midnightconfig.translator","------ Translators ------");
        translationBuilder.add("muffins_thaidelight.midnightconfig.en_us","--- English ---");
        translationBuilder.add("muffins_thaidelight.midnightconfig.en_firemuffin","FireMuffin303");
        translationBuilder.add("muffins_thaidelight.midnightconfig.th_th","--- Thai ---");
        translationBuilder.add("muffins_thaidelight.midnightconfig.th_firemuffin","FireMuffin303");
        translationBuilder.add("muffins_thaidelight.midnightconfig.uk_ua","--- Ukrainian ---");
        translationBuilder.add("muffins_thaidelight.midnightconfig.peaenka","P34enKa");
        translationBuilder.add("muffins_thaidelight.midnightconfig.zh_cn","--- Simplified Chinese ---");
        translationBuilder.add("muffins_thaidelight.midnightconfig.huantanhua","Huantanhua");

        translationBuilder.add("muffins_thaidelight.consume.durian_fermented_drinks","Your body heated up.");
        translationBuilder.add("muffins_thaidelight.custom_effect_render.durian_consumed","Durian Consumed");
        translationBuilder.add("muffins_thaidelight.custom_effect_render.heated_up","Heated Up");
        translationBuilder.add("muffins_thaidelight.custom_effect_render.spicy","Spicy");

        translationBuilder.add("death.attack.durian","%1$s was bonked by a falling durian.");
        translationBuilder.add("death.attack.durian.player","%1$s was bonked by a falling durian whilst fighting %2$s.");
    }

    public static class ThaiLangData extends FabricLanguageProvider{

        protected ThaiLangData(FabricDataOutput dataOutput) {
            super(dataOutput,"th_th");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            //Advancement
            translationBuilder.add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
            translationBuilder.add("advancement.muffins_thaidelight.got_mortar.description","ได้เวลาลิ้มรสชาติของอาหารไทยแล้ว!");

            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","มันกินไม่ได้!");
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","ได้รับแมลงปอทอด. มันกินได้จริง ๆ หรอ?");

            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

            translationBuilder.add("advancement.muffins_thaidelight.sliced_lime","วันนี้มีสแน๊ปช็อตหรอ?");
            translationBuilder.add("advancement.muffins_thaidelight.sliced_lime.description","ได้รับมะนาวหั่น. เขาแก้บัคอะไรบ้างนะ");

            translationBuilder.add("advancement.muffins_thaidelight.got_pepper","วัตถุดิบที่แท้จริง");
            translationBuilder.add("advancement.muffins_thaidelight.got_pepper.description","ได้รับพริก.");

            translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad","ลาบแซ่บ ๆ");
            translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad.description","ได้รับลาบเนื้อ แซ่บ ๆ");

            translationBuilder.add("advancement.muffins_thaidelight.got_somtam","พริกเพิ่มหน่อย");
            translationBuilder.add("advancement.muffins_thaidelight.got_somtam.description","ได้รับส้มตำ, เม็ดเดียวมันพอจริง ๆ หรอ?");

            translationBuilder.add("advancement.muffins_thaidelight.being_stinky","โอ๊ะ โอ่ ตัวเหม็น");
            translationBuilder.add("advancement.muffins_thaidelight.being_stinky.description","ได้รับสถานะ เหม็น. ไปอาบน้ำบ้างนะ.");

            translationBuilder.add("advancement.muffins_thaidelight.susie_prize","ของรางวัลซูซี่");
            translationBuilder.add("advancement.muffins_thaidelight.susie_prize.description","ทำให้ดอกมะละกอสว่างขึ้นด้วยหมึกเรืองแสง เอาน่าคุณควรได้รางวัลบ้างนะ");

            translationBuilder.add("advancement.muffins_thaidelight.big_big_nut","มะพร้าวอันเบิ้มๆ");
            translationBuilder.add("advancement.muffins_thaidelight.big_big_nut.description","รับลูกมะพร้าวร่วงด้วยกระสอบ ลูกเบิ้มๆเลย");

            translationBuilder.add("advancement.muffins_thaidelight.gravity_not_invent","แรงโน้มถ่วงไม่ถูกคิดค้น");
            translationBuilder.add("advancement.muffins_thaidelight.gravity_not_invent.description","โดนลูกทุเรียนหล่นใส่หัว อันนี้ไม่น่าใช่ต้นแอปเปิ้ลนะ");

            translationBuilder.add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
            translationBuilder.add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");
            translationBuilder.add("farmersdelight.tooltip.honey_lime_juice","ล้างตาบอดและพิษ");
            translationBuilder.add("farmersdelight.tooltip.coconut_water","ล้างโจมตีเบา");
            translationBuilder.add("farmersdelight.tooltip.butterfly_pea_tea","ล้างวิ่งช้า");
            translationBuilder.add("jei.info.papaya_log","คลิ๊กขวาด้วยผงกระดูกเพื่อโตผลมะละกอ");

            translationBuilder.add("item.muffins_thaidelight.tasty","อยากอาหาร");

            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");


            translationBuilder.add(ModItems.MORTAR.get(),"ครก");
            translationBuilder.add(ModItems.SACK.get(),"กระสอบ");

            translationBuilder.add(ModItems.LIME_CRATE.get(),"ลังมะนาว");
            translationBuilder.add(ModItems.PEPPER_CRATE.get(),"ลังพริก");
            translationBuilder.add(ModItems.RAW_PAPAYA_CRATE.get(),"ลังมะละกอดิบ");
            translationBuilder.add(ModItems.PAPAYA_CRATE.get(),"ลังมะละกอ");
            translationBuilder.add(ModItems.MANGO_CRATE.get(),"ลังมะม่วง");
            translationBuilder.add(ModItems.HOLY_BASIL_CRATE.get(),"ลังกะเพรา");
            translationBuilder.add(ModItems.BASIL_CRATE.get(),"ลังโหระพา");
            translationBuilder.add(ModItems.BAMBOO_SHOOT_CRATE.get(),"ลังหน่อไม้ไผ่");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_CRATE.get(),"ลังดอกอัญชัญ");

            translationBuilder.add(ModItems.CRAB_SPAWN_EGG.get(),"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_EGG.get(),"ไข่ปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET.get(),"ถังปูม้า");
            translationBuilder.add(ModItems.CRAB_MEAT.get(),"เนื้อปูม้า");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT.get(),"เนื้อปูม้าสุก");

            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG.get(),"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE.get(),"ขวดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY.get(),"แมลงปอ");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY.get(),"แมลงปอสุก");

            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE.get(),"ขวดน้ำปลา");
            translationBuilder.add(ModItems.FERMENTED_FISH.get(),"ปลาร้า");
            translationBuilder.add(ModItems.PAPAYA_JUICE.get(),"น้ำมะละกอ");
            translationBuilder.add(ModItems.LIME_JUICE.get(),"น้ำมะนาว");
            translationBuilder.add(ModItems.HONEY_LIME_JUICE.get(),"น้ำผึ้งมะนาว");
            translationBuilder.add(ModItems.COCONUT_WATER.get(),"น้ำมะพร้าว");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_TEA.get(),"ชาดอกอัญชัญ");

            translationBuilder.add(ModItems.LIME_SAPLING.get(),"ต้นนอ่อนมะนาว");
            translationBuilder.add(ModItems.LIME.get(),"มะนาว");
            translationBuilder.add(ModItems.SLICED_LIME.get(),"มะนาวหั่น");
            translationBuilder.add(ModItems.WILD_PEPPER_CROP.get(),"พริกป่า");
            translationBuilder.add(ModItems.PEPPER.get(),"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED.get(),"เมล็ดพริก");

            translationBuilder.add(ModItems.DURIAN_SAPLING.get(),"ต้นอ่อนทุเรียน");
            translationBuilder.add(ModItems.DURIAN_LEAVES.get(),"ใบไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FLOWER.get(),"ดอกทุเรียน");
            translationBuilder.add(ModItems.SMALL_DURIAN.get(),"ทุเรียนผลเล็ก");
            translationBuilder.add(ModItems.DURIAN.get(),"ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PULP.get(),"เนื้อทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PEEL.get(),"เปลือกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PEEL_BLOCK.get(),"บล็อกเปลือกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_HELMET.get(),"เกราะหมวกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_LOG.get(),"ท่อนไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_WOOD.get(),"ไม้ทุเรียน");
            translationBuilder.add(ModItems.STRIPPED_DURIAN_LOG.get(),"ท่อนไม้ทุเรียนลอกเปลือก");
            translationBuilder.add(ModItems.STRIPPED_DURIAN_WOOD.get(),"ไม้ทุเรียนลอกเปลือก");
            translationBuilder.add(ModItems.DURIAN_PLANKS.get(),"แผ่นไม้กระดานทุเรียน");
            translationBuilder.add(ModItems.DURIAN_STAIRS.get(),"บันไดไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_SLAB.get(),"แผ่นไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FENCE.get(),"รั้วไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FENCE_GATE.get(),"ประตูรั้วไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_DOOR.get(),"ประตูไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_TRAPDOOR.get(),"ประตูกับดักไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PRESSURE_PLATE.get(),"แป้นเหยียบไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_BUTTON.get(),"ปุ่มไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_SIGN.get(),"ป้ายไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_HANGING_SIGN.get(),"ป้ายแขวนทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CABINET.get(),"ตู้เก็บของทุเรียน");
            translationBuilder.add(ModItems.DURIAN_BOAT.get(),"เรือไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CHEST_BOAT.get(),"เรือไม้ทุเรียนพร้อมหีบ");
            translationBuilder.add(ModItems.COCONUT_SAPLING.get(),"ต้นอ่อนมะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF.get(),"ใบไม้มะพร้าว");
            translationBuilder.add(ModItems.BUDDING_COCONUT_LEAF.get(),"ต้นกำเนิดใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF_BLOCK.get(),"บล็อกใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF_MAT.get(),"พรมใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LOG.get(),"ท่อนไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_WOOD.get(),"ไม้มะพร้าว");
            translationBuilder.add(ModItems.STRIPPED_COCONUT_LOG.get(),"ท่อนไม้มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.STRIPPED_COCONUT_WOOD.get(),"ไม้มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.COCONUT_PLANKS.get(),"แผ่นกระดานไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_STAIRS.get(),"บันไดไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_SLAB.get(),"แผ่นไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_FENCE.get(),"รั้วไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_FENCE_GATE.get(),"ประตูรั้วไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_DOOR.get(),"ประตูไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_TRAPDOOR.get(),"ประตูกับดักไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_PRESSURE_PLATE.get(),"แป้นเหยียบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_BUTTON.get(),"ปุ่มไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_SIGN.get(),"ป้ายไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_HANGING_SIGN.get(),"ป้ายแขวนไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_BOAT.get(),"เรือไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_CHEST_BOAT.get(),"เรือไม้มะพร้าวพร้อมหีบ");
            translationBuilder.add(ModItems.COCONUT_CABINET.get(),"ตู้เก็บของไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT.get(),"มะพร้าว");
            translationBuilder.add(ModItems.STRIPPED_COCONUT.get(),"มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.COCONUT_SLICE.get(),"มะพร้าวหั่น");
            translationBuilder.add(ModItems.MANGO_SAPLING.get(),"ต้นอ่อนมะม่วง");
            translationBuilder.add(ModItems.MANGO_LEAVES.get(),"ใบไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_LOG.get(),"ท่อนไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_WOOD.get(),"ไม้มะม่วง");
            translationBuilder.add(ModItems.STRIPPED_MANGO_LOG.get(),"ท่อนไม้มะม่วง");
            translationBuilder.add(ModItems.STRIPPED_MANGO_WOOD.get(),"ไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_PLANKS.get(),"แผ่นกระดานไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_STAIRS.get(),"บันไดไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_SLAB.get(),"แผ่นไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_FENCE.get(),"รั้วไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_FENCE_GATE.get(),"ประตูรั้วไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_DOOR.get(),"ประตูไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_TRAPDOOR.get(),"ประตูกับดักไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_PRESSURE_PLATE.get(),"แป้นเหยียบไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_BUTTON.get(),"ปุ่มไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_SIGN.get(),"ป้ายไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_HANGING_SIGN.get(),"ป้ายแขวนไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_BOAT.get(),"เรือไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_CHEST_BOAT.get(),"เรือไม้มะม่วงพร้อมหีบ");
            translationBuilder.add(ModItems.MANGO_CABINET.get(),"ตู้เก็บของไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO.get(),"มะม่วง");
            translationBuilder.add(ModItems.MANGO_SLICE.get(),"มะม่วงหั่น");
            translationBuilder.add(ModItems.PAPAYA.get(),"มะละกอ");
            translationBuilder.add(ModItems.PAPAYA_FLOWER.get(),"ดอกมะละกอ");
            translationBuilder.add(ModItems.SLICED_PAPAYA.get(),"มะละกอหั่น");
            translationBuilder.add(ModItems.RAW_PAPAYA.get(),"มะละกอดิบ");
            translationBuilder.add(ModItems.RAW_PAPAYA_SLICE.get(),"มะละกอดิบหั่น");
            translationBuilder.add(ModItems.PAPAYA_LOG.get(),"ท่อนไม้มะละกอ");
            translationBuilder.add(ModItems.STRIPPED_PAPAYA_LOG.get(),"ท่อนไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModItems.PAPAYA_WOOD.get(),"ไม้มะละกอ");
            translationBuilder.add(ModItems.STRIPPED_PAPAYA_WOOD.get(),"ไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModItems.PAPAYA_LEAVES.get(),"ใบไม้มะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SAPLING.get(),"ต้นอ่อนมะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SEEDS.get(),"เมล็ดมะละกอ");
            translationBuilder.add(ModItems.WILD_BASIL.get(),"ต้นโหระพาป่า");
            translationBuilder.add(ModItems.BASIL.get(),"โหระพา");
            translationBuilder.add(ModItems.BUTTERFLY_PEA.get(),"ดอกอัญชัญ");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_SEEDS.get(),"เมล็ดดอกอัญชัญ");
            translationBuilder.add(ModItems.BAMBOO_SHOOT.get(),"หน่อไม้ไผ่");
            translationBuilder.add(ModItems.PESTO_SAUCE.get(),"ซอสเพสโต้");
            translationBuilder.add(ModItems.FRIED_DURIAN.get(),"ทุเรียนทอด");
            translationBuilder.add(ModItems.SOMTAM_FEAST.get(),"ส้มตำ");
            translationBuilder.add(ModItems.SOMTAM.get(),"จานส้มตำ");
            translationBuilder.add(ModItems.LARB_FEAST.get(),"ลาบ");
            translationBuilder.add(ModItems.LARB.get(),"จานลาบ");
            translationBuilder.add(ModItems.CRAB_FRIED_RICE_FEAST.get(),"ข้าวผัดปู");
            translationBuilder.add(ModItems.CRAB_FRIED_RICE.get(),"จานข้าวผัดปู");
            translationBuilder.add(ModItems.PHAT_KAPHRAO_FEAST.get(),"ข้าวผัดกะเพรา");
            translationBuilder.add(ModItems.PHAT_KAPHRAO.get(),"จานข้าวผัดกะเพรา");
            translationBuilder.add(ModItems.MANGO_STICKY_RICE_FEAST.get(),"ข้าวเหนียวมะม่วง");
            translationBuilder.add(ModItems.MANGO_STICKY_RICE.get(),"จานข้าวเหนียวมะม่วง");
            translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get(),"ข้าวผัดสัปปะรด");
            translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE.get(),"จานข้าวผัดสัปปะรด");
            translationBuilder.add(ModItems.STIR_FRIED_NOODLE.get(),"ผัดหมี่");
            translationBuilder.add(ModItems.COCONUT_MILK_BOTTLE.get(),"ขวดน้ำกะทิ");
            translationBuilder.add(ModItems.DURIAN_CURRY.get(),"แกงทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CAKE.get(),"เค้กทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CAKE_SLICE.get(),"สไลด์เค้กทุเรียน");
            translationBuilder.add(ModItems.MANGO_CHEESECAKE.get(),"ชีสเค้กมะม่วง");
            translationBuilder.add(ModItems.MANGO_CHEESECAKE_SLICE.get(),"สไลด์ชีสเค้กมะม่วง");
            translationBuilder.add(ModItems.COCONUT_JELLY.get(),"วุ้นมะพร้าว");
            translationBuilder.add(ModItems.KHANOM_BABIN.get(),"ขนมบ้าบิ่น");
            translationBuilder.add(ModItems.COCONUT_PIE.get(),"พายมะพร้าว");
            translationBuilder.add(ModItems.COCONUT_PIE_SLICE.get(),"สไลด์พายมะพร้าว");
            translationBuilder.add(ModItems.HONEY_COCONUT_PIE.get(),"พายมะพร้าวราดน้ำผึ้ง");
            translationBuilder.add(ModItems.HONEY_COCONUT_PIE_SLICE.get(),"สไลด์พายมะพร้าวราดน้ำผึ้ง");
            translationBuilder.add(ModItems.OMELETTE_FEAST.get(),"ไข่เจียว");
            translationBuilder.add(ModItems.OMELETTE.get(),"จานไข่เจียว");
            translationBuilder.add(ModItems.BASIL_OMELETTE_FEAST.get(),"ไข่เจียวโหระพา");
            translationBuilder.add(ModItems.BASIL_OMELETTE.get(),"จานไข่เจียวโหระพา");
            translationBuilder.add(ModItems.BAMBOO_SHOOT_SOUP.get(),"แกงหน่อไม้");
            translationBuilder.add(ModItems.STEAMED_BAMBOO_SHOOT.get(),"ห่อหมก");
            translationBuilder.add(ModItems.BANANA_IN_COCONUT_MILK.get(),"กล้วยบวชชี");
            translationBuilder.add(ModItems.KHANOM_CHAN.get(),"ขนมชั้น");
            translationBuilder.add(ModItems.COCONUT_MILK_ICE_CREAM.get(),"ไอศกรีมกะทิ");




            translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","สีแดง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","สีเหลือง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","สีเขียว");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","สีฟ้า");
            translationBuilder.add("container.muffins_thaidelight.mortar","ครก");

            translationBuilder.add(ModMobEffects.STINKY.get(),"เหม็น");
            translationBuilder.add(ModMobEffects.APPETITE_LOSS.get(),"เบื่ออาหาร");
            translationBuilder.add("item.minecraft.potion.effect.stench","น้ำยาเหม็น");
            translationBuilder.add("item.minecraft.potion.effect.long_stench","น้ำยาเหม็น");
            translationBuilder.add("item.minecraft.potion.effect.strong_stench","น้ำยาเหม็น");

            translationBuilder.add("item.minecraft.splash_potion.effect.stench","น้ำยาเหม็นแบบปา");
            translationBuilder.add("item.minecraft.splash_potion.effect.long_stench","น้ำยาเหม็นแบบปา");
            translationBuilder.add("item.minecraft.splash_potion.effect.strong_stench","น้ำยาเหม็นแบบปา");

            translationBuilder.add("item.minecraft.lingering_potion.effect.stench","น้ำยาเหม็นแบบระเหย");
            translationBuilder.add("item.minecraft.lingering_potion.effect.long_stench","น้ำยาเหม็นแบบระเหย");
            translationBuilder.add("item.minecraft.lingering_potion.effect.strong_stench","น้ำยาเหม็นแบบระเหย");

            translationBuilder.add("item.minecraft.tipped_arrow.effect.stench","ลูกธนูอาบยาเหม็น");
            translationBuilder.add("item.minecraft.tipped_arrow.effect.long_stench","ลูกธนูอาบยาเหม็น");
            translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_stench","ลูกธนูอาบยาเหม็น");

            translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron1","นำปลาใส่ลงไปในหม้อน้ำ");
            translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron2","และรอ 6 - 15 นาที");
            translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron3","จนกว่าจะหมักเสร็จ.");
            translationBuilder.add("emi.category.muffins_thaidelight.mortar","ครก");
            translationBuilder.add("emi.category.muffins_thaidelight.cauldron_crafting","หม้อปรุงยา");

            translationBuilder.add("muffins_thaidelight.midnightconfig.title","Muffin's Thai's Delight Config");
            translationBuilder.add("muffins_thaidelight.midnightconfig.gameplay","เกมเพลย์");
            translationBuilder.add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem","อนุญาตให้พ่อค้าเร่ร่อนขายของ Thai's Delight");
            translationBuilder.add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
            translationBuilder.add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem","อนุญาตให้ชาวบ้านขายของ Thai's Delight");
            translationBuilder.add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
            translationBuilder.add("muffins_thaidelight.midnightconfig.worldGen","การเกิดโลก");
            translationBuilder.add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn","อนุญาตให้บ้านไทยเกิดในหมู่บ้าน");
            translationBuilder.add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn.tooltip","จำเป็นต้องเริ่มเซิฟเวอร์ใหม่เพื่อเห็นผล");
            translationBuilder.add("muffins_thaidelight.midnightconfig.stinkyShouldTriggerNeutral","เอฟเฟคเหม็นควรให้ม็อบเป็นกลางโกรธ");
            translationBuilder.add("muffins_thaidelight.midnightconfig.fishofthieves","Fish Of Thieves Compatibility");
            translationBuilder.add("muffins_thaidelight.midnightconfig.coconutTreeType","การเกิดต้นมะพร้าว");
            translationBuilder.add("muffins_thaidelight.midnightconfig.coconutTreeType.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
            translationBuilder.add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn","อนุญาตให้ต้นมะม่วงของ Thai's Delight เกิด");
            translationBuilder.add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");


            translationBuilder.add("muffins_thaidelight.midnightconfig.developer","------ ผู้พัฒนา ------");
            translationBuilder.add("muffins_thaidelight.midnightconfig.firemuffin","FireMuffin303");
            translationBuilder.add("muffins_thaidelight.midnightconfig.concept_artist","------ ศิลปินคอนเซปต์อาร์ท ------");
            translationBuilder.add("muffins_thaidelight.midnightconfig.lucas","lllLucaslll");
            translationBuilder.add("muffins_thaidelight.midnightconfig.dino_care","Dino_care");
            translationBuilder.add("muffins_thaidelight.midnightconfig.akalinka","No92");
            translationBuilder.add("muffins_thaidelight.midnightconfig.translator","------ ผู้แปล ------");
            translationBuilder.add("muffins_thaidelight.midnightconfig.en_us","--- ภาษาอังกฤษ ---");
            translationBuilder.add("muffins_thaidelight.midnightconfig.en_firemuffin","FireMuffin303");
            translationBuilder.add("muffins_thaidelight.midnightconfig.th_th","--- ภาษาไทย ---");
            translationBuilder.add("muffins_thaidelight.midnightconfig.th_firemuffin","FireMuffin303");
            translationBuilder.add("muffins_thaidelight.midnightconfig.uk_ua","--- ภาษายูเครน ---");
            translationBuilder.add("muffins_thaidelight.midnightconfig.peaenka","P34enKa");
            translationBuilder.add("muffins_thaidelight.midnightconfig.zh_cn","--- ภาษาจีนตัวย่อ ---");
            translationBuilder.add("muffins_thaidelight.midnightconfig.huantanhua","Huantanhua");

            translationBuilder.add("muffins_thaidelight.consume.durian_fermented_drinks","ร่างกายของคุณร้อนขึ้น");

            translationBuilder.add("muffins_thaidelight.custom_effect_render.durian_consumed","ทานทุเรียนแล้ว");
            translationBuilder.add("muffins_thaidelight.custom_effect_render.heated_up","ตัวร้อน");
            translationBuilder.add("muffins_thaidelight.custom_effect_render.spicy","เผ็ด");

            translationBuilder.add("death.attack.durian","%1$s ถูกลูกทุเรียนหล่นใส่หัว.");
            translationBuilder.add("death.attack.durian.player","%1$s ถูกลูกทุเรียนหล่นใส่หัวขณะสู้กับ %2$s.");
        }
    }
}
