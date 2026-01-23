package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;

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

        translationBuilder.add("item.muffins_thaidelight.tasty","Tasty");

        translationBuilder.add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        translationBuilder.add("farmersdelight.tooltip.lime_juice","Clear Blindness Effect");
        translationBuilder.add("farmersdelight.tooltip.honey_lime_juice","Clear Blindness and Poison Effect");
        translationBuilder.add("farmersdelight.tooltip.coconut_water","Clear Weakness Effect");
        translationBuilder.add("jei.info.papaya_log","Right click with bone meal to grow papayas.");


        translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        //Blocks
        translationBuilder.add(ModItems.MORTAR,"Mortar");
        translationBuilder.add(ModItems.SACK,"Sack");
        translationBuilder.add(ModItems.LIME_CRATE,"Lime Crate");
        translationBuilder.add(ModItems.PEPPER_CRATE,"Pepper Crate");
        translationBuilder.add(ModItems.RAW_PAPAYA_CRATE,"Raw Papaya Crate");
        translationBuilder.add(ModItems.PAPAYA_CRATE,"Papaya Crate");
        translationBuilder.add(ModItems.MANGO_CRATE,"Mango Crate");
        translationBuilder.add(ModItems.HOLY_BASIL_CRATE,"Holy Basil Crate");
        translationBuilder.add(ModItems.BASIL_CRATE,"Basil Crate");
        translationBuilder.add(ModItems.BAMBOO_SHOOT_CRATE,"Bamboo Shoot Crate");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_CRATE,"Butterfly Pea Crate");
        translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_EGG,"Flower Crab Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET,"Flower Crab Bucket");
        translationBuilder.add(ModItems.CRAB_MEAT,"Raw Flower Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"Cooked Flower Crab");
        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"Bottle of Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY,"Dragonfly");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");
        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");
        translationBuilder.add(ModItems.FERMENTED_FISH,"Fermented Fish");
        translationBuilder.add(ModItems.PAPAYA_JUICE,"Papaya Juice");
        translationBuilder.add(ModItems.LIME_JUICE,"Lime Juice");
        translationBuilder.add(ModItems.HONEY_LIME_JUICE,"Honey Lime Juice");
        translationBuilder.add(ModItems.COCONUT_WATER,"Coconut Water");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_TEA,"Butterfly Pea Tea");
        translationBuilder.add(ModItems.LIME_SAPLING,"Lime Sapling");
        translationBuilder.add(ModItems.LIME,"Lime");
        translationBuilder.add(ModItems.SLICED_LIME,"Lime Slice");
        translationBuilder.add(ModItems.WILD_PEPPER_CROP,"Wild Pepper");
        translationBuilder.add(ModItems.PEPPER,"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED,"Pepper Seeds");

        translationBuilder.add(ModItems.DURIAN_SAPLING,"Durian Sapling");
        translationBuilder.add(ModItems.DURIAN_LEAVES,"Durian Leaves");
        translationBuilder.add(ModItems.DURIAN_FLOWER,"Durian Flower");
        translationBuilder.add(ModItems.SMALL_DURIAN,"Small Durian");
        translationBuilder.add(ModItems.DURIAN,"Durian");
        translationBuilder.add(ModItems.DURIAN_PULP,"Durian Pulp");
        translationBuilder.add(ModItems.DURIAN_PEEL,"Durian Peel");
        translationBuilder.add(ModItems.DURIAN_PEEL_BLOCK,"Durian Peel Block");
        translationBuilder.add(ModItems.DURIAN_HELMET,"Durian Helmet");
        translationBuilder.add(ModItems.DURIAN_LOG,"Durian Log");
        translationBuilder.add(ModItems.DURIAN_WOOD,"Durian Wood");
        translationBuilder.add(ModItems.STRIPPED_DURIAN_LOG,"Stripped Durian Log");
        translationBuilder.add(ModItems.STRIPPED_DURIAN_WOOD,"Stripped Durian Wood");
        translationBuilder.add(ModItems.DURIAN_PLANKS,"Durian Planks");
        translationBuilder.add(ModItems.DURIAN_STAIRS,"Durian Stairs");
        translationBuilder.add(ModItems.DURIAN_SLAB,"Durian Slab");
        translationBuilder.add(ModItems.DURIAN_FENCE,"Durian Fence");
        translationBuilder.add(ModItems.DURIAN_FENCE_GATE,"Durian Fence Gate");
        translationBuilder.add(ModItems.DURIAN_DOOR,"Durian Door");
        translationBuilder.add(ModItems.DURIAN_TRAPDOOR,"Durian Trapdoor");
        translationBuilder.add(ModItems.DURIAN_PRESSURE_PLATE,"Durian Pressure Plate");
        translationBuilder.add(ModItems.DURIAN_BUTTON,"Durian Button");
        translationBuilder.add(ModItems.DURIAN_SIGN,"Durian Sign");
        translationBuilder.add(ModItems.DURIAN_HANGING_SIGN,"Durian Hanging Sign");
        translationBuilder.add(ModItems.DURIAN_CABINET,"Durian Cabinet");
        translationBuilder.add(ModItems.DURIAN_BOAT,"Durian Boat");
        translationBuilder.add(ModItems.DURIAN_CHEST_BOAT,"Durian Chest Boat");
        translationBuilder.add(ModItems.COCONUT_SAPLING,"Coconut Sapling");
        translationBuilder.add(ModItems.COCONUT_LEAF,"Coconut Leaf");
        translationBuilder.add(ModItems.BUDDING_COCONUT_LEAF,"Budding Coconut Leaf");
        translationBuilder.add(ModItems.COCONUT_LEAF_BLOCK,"Coconut Leaf Block");
        translationBuilder.add(ModItems.COCONUT_LEAF_MAT,"Coconut Leaf Carpet");
        translationBuilder.add(ModItems.COCONUT_LOG,"Coconut Log");
        translationBuilder.add(ModItems.COCONUT_WOOD,"Coconut Wood");
        translationBuilder.add(ModItems.STRIPPED_COCONUT_LOG,"Stripped Coconut Log");
        translationBuilder.add(ModItems.STRIPPED_COCONUT_WOOD,"Stripped Coconut Wood");
        translationBuilder.add(ModItems.COCONUT_PLANKS,"Coconut Planks");
        translationBuilder.add(ModItems.COCONUT_STAIRS,"Coconut Stairs");
        translationBuilder.add(ModItems.COCONUT_SLAB,"Coconut Slab");
        translationBuilder.add(ModItems.COCONUT_FENCE,"Coconut Fence");
        translationBuilder.add(ModItems.COCONUT_FENCE_GATE,"Coconut Fence Gate");
        translationBuilder.add(ModItems.COCONUT_DOOR,"Coconut Door");
        translationBuilder.add(ModItems.COCONUT_TRAPDOOR,"Coconut Trapdoor");
        translationBuilder.add(ModItems.COCONUT_PRESSURE_PLATE,"Coconut Pressure Plate");
        translationBuilder.add(ModItems.COCONUT_BUTTON,"Coconut Button");
        translationBuilder.add(ModItems.COCONUT_SIGN,"Coconut Sign");
        translationBuilder.add(ModItems.COCONUT_HANGING_SIGN,"Coconut Hanging Sign");
        translationBuilder.add(ModItems.COCONUT_BOAT,"Coconut Boat");
        translationBuilder.add(ModItems.COCONUT_CHEST_BOAT,"Coconut Chest Boat");
        translationBuilder.add(ModItems.COCONUT_CABINET,"Coconut Cabinet");
        translationBuilder.add(ModItems.COCONUT,"Coconut");
        translationBuilder.add(ModItems.STRIPPED_COCONUT,"Stripped Coconut");
        translationBuilder.add(ModItems.COCONUT_SLICE,"Coconut Slice");
        translationBuilder.add(ModItems.MANGO_SAPLING,"Mango Sapling");
        translationBuilder.add(ModItems.MANGO_LEAVES,"Mango Leaves");
        translationBuilder.add(ModItems.MANGO_LOG,"Mango Log");
        translationBuilder.add(ModItems.MANGO_WOOD,"Mango Wood");
        translationBuilder.add(ModItems.STRIPPED_MANGO_LOG,"Stripped Mango Log");
        translationBuilder.add(ModItems.STRIPPED_MANGO_WOOD,"Stripped Mango Wood");
        translationBuilder.add(ModItems.MANGO_PLANKS,"Mango Planks");
        translationBuilder.add(ModItems.MANGO_STAIRS,"Mango Stairs");
        translationBuilder.add(ModItems.MANGO_SLAB,"Mango Slab");
        translationBuilder.add(ModItems.MANGO_FENCE,"Mango Fence");
        translationBuilder.add(ModItems.MANGO_FENCE_GATE,"Mango Fence Gate");
        translationBuilder.add(ModItems.MANGO_DOOR,"Mango Door");
        translationBuilder.add(ModItems.MANGO_TRAPDOOR,"Mango Trapdoor");
        translationBuilder.add(ModItems.MANGO_PRESSURE_PLATE,"Mango Pressure Plate");
        translationBuilder.add(ModItems.MANGO_BUTTON,"Mango Button");
        translationBuilder.add(ModItems.MANGO_SIGN,"Mango Sign");
        translationBuilder.add(ModItems.MANGO_HANGING_SIGN,"Mango Hanging Sign");
        translationBuilder.add(ModItems.MANGO_BOAT,"Mango Boat");
        translationBuilder.add(ModItems.MANGO_CHEST_BOAT,"Mango Chest Boat");
        translationBuilder.add(ModItems.MANGO_CABINET,"Mango Cabinet");
        translationBuilder.add(ModItems.MANGO,"Mango");
        translationBuilder.add(ModItems.MANGO_SLICE,"Mango Slice");
        translationBuilder.add(ModItems.PAPAYA,"Papaya");
        translationBuilder.add(ModItems.PAPAYA_FLOWER,"Papaya Flower");
        translationBuilder.add(ModItems.SLICED_PAPAYA,"Papaya Slice");
        translationBuilder.add(ModItems.RAW_PAPAYA,"Raw Papaya");
        translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"Raw Papaya Slice");
        translationBuilder.add(ModItems.PAPAYA_LOG,"Papaya Log");
        translationBuilder.add(ModItems.STRIPPED_PAPAYA_LOG,"Stripped Papaya Log");
        translationBuilder.add(ModItems.PAPAYA_WOOD,"Papaya Wood");
        translationBuilder.add(ModItems.STRIPPED_PAPAYA_WOOD,"Stripped Papaya Wood");
        translationBuilder.add(ModItems.PAPAYA_LEAVES,"Papaya Leaves");
        translationBuilder.add(ModItems.PAPAYA_SAPLING,"Papaya Sapling");
        translationBuilder.add(ModItems.PAPAYA_SEEDS,"Papaya Seeds");
        translationBuilder.add(ModItems.WILD_HOLY_BASIL,"Wild Holy Basil");
        translationBuilder.add(ModItems.HOLY_BASIL,"Holy Basil");
        translationBuilder.add(ModItems.WILD_BASIL,"Wild Basil");
        translationBuilder.add(ModItems.BASIL,"Basil");

        translationBuilder.add(ModItems.BUTTERFLY_PEA,"Butterfly Pea");
        translationBuilder.add(ModItems.BUTTERFLY_PEA_SEEDS,"Butterfly Pea Seeds");
        translationBuilder.add(ModItems.BAMBOO_SHOOT,"Bamboo Shoot");
        translationBuilder.add(ModItems.PESTO_SAUCE,"Pesto Sauce");
        translationBuilder.add(ModItems.FRIED_DURIAN,"Fried Durian");
        translationBuilder.add(ModItems.SOMTAM_FEAST,"Somtam");
        translationBuilder.add(ModItems.SOMTAM,"Plate of Somtam");
        translationBuilder.add(ModItems.LARB_FEAST,"Larb");
        translationBuilder.add(ModItems.LARB,"Plate of Larb");
        translationBuilder.add(ModItems.CRAB_FRIED_RICE_FEAST,"Crab Fried Rice");
        translationBuilder.add(ModItems.CRAB_FRIED_RICE,"Plate of Crab Fried Rice");
        translationBuilder.add(ModItems.PHAT_KAPHRAO_FEAST,"Phat Kaphrao");
        translationBuilder.add(ModItems.PHAT_KAPHRAO,"Plate of Phat Kaphrao");
        translationBuilder.add(ModItems.MANGO_STICKY_RICE_FEAST,"Mango Sticky Rice");
        translationBuilder.add(ModItems.MANGO_STICKY_RICE,"Plate of Mango Sticky Rice");
        translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE_FEAST,"Pineapple Fried Rice");
        translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE,"Plate of Pineapple Fried Rice");
        translationBuilder.add(ModItems.STIR_FRIED_NOODLE,"Stir Fried Noodle");
        translationBuilder.add(ModItems.COCONUT_MILK_BOTTLE,"Coconut Milk Bottle");
        translationBuilder.add(ModItems.DURIAN_CURRY,"Durian Curry");
        translationBuilder.add(ModItems.DURIAN_CAKE,"Durian Cake");
        translationBuilder.add(ModItems.DURIAN_CAKE_SLICE,"Durian Cake Slice");
        translationBuilder.add(ModItems.MANGO_CHEESECAKE,"Mango Cheesecake");
        translationBuilder.add(ModItems.MANGO_CHEESECAKE_SLICE,"Mango Cheesecake Slice");
        translationBuilder.add(ModItems.COCONUT_JELLY,"Coconut Jelly");
        translationBuilder.add(ModItems.KHANOM_BABIN,"Khanom Babin");
        translationBuilder.add(ModItems.COCONUT_PIE,"Coconut Pie");
        translationBuilder.add(ModItems.COCONUT_PIE_SLICE,"Coconut Pie Slice");
        translationBuilder.add(ModItems.BASIL_OMELETTE_FEAST,"Basil Omelette");
        translationBuilder.add(ModItems.BASIL_OMELETTE,"Plate of Basil Omelette");
        translationBuilder.add(ModItems.BAMBOO_SHOOT_SOUP,"Bamboo Shoot Soup");
        translationBuilder.add(ModItems.STEAMED_BAMBOO_SHOOT,"Steamed Bamboo Shoot");
        translationBuilder.add(ModItems.BANANA_IN_COCONUT_MILK,"Banana in Coconut Milk");
        translationBuilder.add(ModItems.KHANOM_CHAN,"Khanom Chan");
        translationBuilder.add(ModItems.COCONUT_MILK_ICE_CREAM,"Coconut Milk Ice Cream");


        translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","Red");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","Yellow");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","Green");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","Blue");
        translationBuilder.add("container.muffins_thaidelight.mortar","Mortar");

        translationBuilder.add(ModMobEffects.STINKY,"Stinky");
        translationBuilder.add(ModMobEffects.APPETITE_LOSS,"Appetite Loss");
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

        translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron1","Putting a fish into Water Cauldron");
        translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron2","and wait for 6 - 15 minutes");
        translationBuilder.add("muffins_thaidelight.jei.fermented_fish.cauldron3","until it fully fermented.");

        translationBuilder.add("emi.category.muffins_thaidelight.mortar","Mortar");
        translationBuilder.add("emi.category.muffins_thaidelight.fermented_fish","Fermenting");

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

            translationBuilder.add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
            translationBuilder.add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");
            translationBuilder.add("farmersdelight.tooltip.honey_lime_juice","ล้างตาบอดและพิษ");
            translationBuilder.add("farmersdelight.tooltip.coconut_water","ล้างโจมตีเบา");
            translationBuilder.add("jei.info.papaya_log","คลิ๊กขวาด้วยผงกระดูกเพื่อโตผลมะละกอ");

            translationBuilder.add("item.muffins_thaidelight.tasty","อยากอาหาร");



            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");


            translationBuilder.add(ModItems.MORTAR,"ครก");
            translationBuilder.add(ModItems.SACK,"กระสอบ");

            translationBuilder.add(ModItems.LIME_CRATE,"ลังมะนาว");
            translationBuilder.add(ModItems.PEPPER_CRATE,"ลังพริก");
            translationBuilder.add(ModItems.RAW_PAPAYA_CRATE,"ลังมะละกอดิบ");
            translationBuilder.add(ModItems.PAPAYA_CRATE,"ลังมะละกอ");
            translationBuilder.add(ModItems.MANGO_CRATE,"ลังมะม่วง");
            translationBuilder.add(ModItems.HOLY_BASIL_CRATE,"ลังกะเพรา");
            translationBuilder.add(ModItems.BASIL_CRATE,"ลังโหระพา");
            translationBuilder.add(ModItems.BAMBOO_SHOOT_CRATE,"ลังหน่อไม้ไผ่");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_CRATE,"ลังดอกอัญชัญ");

            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_EGG,"ไข่ปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET,"ถังปูม้า");
            translationBuilder.add(ModItems.CRAB_MEAT,"เนื้อปูม้า");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"เนื้อปูม้าสุก");

            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"ขวดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY,"แมลงปอ");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"แมลงปอสุก");

            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");
            translationBuilder.add(ModItems.FERMENTED_FISH,"ปลาร้า");
            translationBuilder.add(ModItems.PAPAYA_JUICE,"น้ำมะละกอ");
            translationBuilder.add(ModItems.LIME_JUICE,"น้ำมะนาว");
            translationBuilder.add(ModItems.HONEY_LIME_JUICE,"น้ำผึ้งมะนาว");
            translationBuilder.add(ModItems.COCONUT_WATER,"น้ำมะพร้าว");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_TEA,"ชาดอกอัญชัญ");

            translationBuilder.add(ModItems.LIME_SAPLING,"ต้นนอ่อนมะนาว");
            translationBuilder.add(ModItems.LIME,"มะนาว");
            translationBuilder.add(ModItems.SLICED_LIME,"มะนาวหั่น");
            translationBuilder.add(ModItems.WILD_PEPPER_CROP,"พริกป่า");
            translationBuilder.add(ModItems.PEPPER,"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED,"เมล็ดพริก");

            translationBuilder.add(ModItems.DURIAN_SAPLING,"ต้นอ่อนทุเรียน");
            translationBuilder.add(ModItems.DURIAN_LEAVES,"ใบไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FLOWER,"ดอกทุเรียน");
            translationBuilder.add(ModItems.SMALL_DURIAN,"ทุเรียนผลเล็ก");
            translationBuilder.add(ModItems.DURIAN,"ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PULP,"เนื้อทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PEEL,"เปลือกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PEEL_BLOCK,"บล็อกเปลือกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_HELMET,"เกราะหมวกทุเรียน");
            translationBuilder.add(ModItems.DURIAN_LOG,"ท่อนไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_WOOD,"ไม้ทุเรียน");
            translationBuilder.add(ModItems.STRIPPED_DURIAN_LOG,"ท่อนไม้ทุเรียนลอกเปลือก");
            translationBuilder.add(ModItems.STRIPPED_DURIAN_WOOD,"ไม้ทุเรียนลอกเปลือก");
            translationBuilder.add(ModItems.DURIAN_PLANKS,"แผ่นไม้กระดานทุเรียน");
            translationBuilder.add(ModItems.DURIAN_STAIRS,"บันไดไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_SLAB,"แผ่นไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FENCE,"รั้วไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_FENCE_GATE,"ประตูรั้วไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_DOOR,"ประตูไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_TRAPDOOR,"ประตูกับดักไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_PRESSURE_PLATE,"แป้นเหยียบไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_BUTTON,"ปุ่มไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_SIGN,"ป้ายไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_HANGING_SIGN,"ป้ายแขวนทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CABINET,"ตู้เก็บของทุเรียน");
            translationBuilder.add(ModItems.DURIAN_BOAT,"เรือไม้ทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CHEST_BOAT,"เรือไม้ทุเรียนพร้อมหีบ");
            translationBuilder.add(ModItems.COCONUT_SAPLING,"ต้นอ่อนมะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF,"ใบไม้มะพร้าว");
            translationBuilder.add(ModItems.BUDDING_COCONUT_LEAF,"ต้นกำเนิดใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF_BLOCK,"บล็อกใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LEAF_MAT,"พรมใบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_LOG,"ท่อนไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_WOOD,"ไม้มะพร้าว");
            translationBuilder.add(ModItems.STRIPPED_COCONUT_LOG,"ท่อนไม้มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.STRIPPED_COCONUT_WOOD,"ไม้มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.COCONUT_PLANKS,"แผ่นกระดานไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_STAIRS,"บันไดไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_SLAB,"แผ่นไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_FENCE,"รั้วไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_FENCE_GATE,"ประตูรั้วไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_DOOR,"ประตูไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_TRAPDOOR,"ประตูกับดักไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_PRESSURE_PLATE,"แป้นเหยียบไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_BUTTON,"ปุ่มไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_SIGN,"ป้ายไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_HANGING_SIGN,"ป้ายแขวนไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_BOAT,"เรือไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT_CHEST_BOAT,"เรือไม้มะพร้าวพร้อมหีบ");
            translationBuilder.add(ModItems.COCONUT_CABINET,"ตู้เก็บของไม้มะพร้าว");
            translationBuilder.add(ModItems.COCONUT,"มะพร้าว");
            translationBuilder.add(ModItems.STRIPPED_COCONUT,"มะพร้าวลอกเปลือก");
            translationBuilder.add(ModItems.COCONUT_SLICE,"มะพร้าวหั่น");
            translationBuilder.add(ModItems.MANGO_SAPLING,"ต้นอ่อนมะม่วง");
            translationBuilder.add(ModItems.MANGO_LEAVES,"ใบไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_LOG,"ท่อนไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_WOOD,"ไม้มะม่วง");
            translationBuilder.add(ModItems.STRIPPED_MANGO_LOG,"ท่อนไม้มะม่วง");
            translationBuilder.add(ModItems.STRIPPED_MANGO_WOOD,"ไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_PLANKS,"แผ่นกระดานไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_STAIRS,"บันไดไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_SLAB,"แผ่นไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_FENCE,"รั้วไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_FENCE_GATE,"ประตูรั้วไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_DOOR,"ประตูไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_TRAPDOOR,"ประตูกับดักไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_PRESSURE_PLATE,"แป้นเหยียบไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_BUTTON,"ปุ่มไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_SIGN,"ป้ายไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_HANGING_SIGN,"ป้ายแขวนไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_BOAT,"เรือไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO_CHEST_BOAT,"เรือไม้มะม่วงพร้อมหีบ");
            translationBuilder.add(ModItems.MANGO_CABINET,"ตู้เก็บของไม้มะม่วง");
            translationBuilder.add(ModItems.MANGO,"มะม่วง");
            translationBuilder.add(ModItems.MANGO_SLICE,"มะม่วงหั่น");
            translationBuilder.add(ModItems.PAPAYA,"มะละกอ");
            translationBuilder.add(ModItems.PAPAYA_FLOWER,"ดอกมะละกอ");
            translationBuilder.add(ModItems.SLICED_PAPAYA,"มะละกอหั่น");
            translationBuilder.add(ModItems.RAW_PAPAYA,"มะละกอดิบ");
            translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"มะละกอดิบหั่น");
            translationBuilder.add(ModItems.PAPAYA_LOG,"ท่อนไม้มะละกอ");
            translationBuilder.add(ModItems.STRIPPED_PAPAYA_LOG,"ท่อนไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModItems.PAPAYA_WOOD,"ไม้มะละกอ");
            translationBuilder.add(ModItems.STRIPPED_PAPAYA_WOOD,"ไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModItems.PAPAYA_LEAVES,"ใบไม้มะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SAPLING,"ต้นอ่อนมะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SEEDS,"เมล็ดมะละกอ");
            translationBuilder.add(ModItems.WILD_HOLY_BASIL,"ต้นกะเพราป่า");
            translationBuilder.add(ModItems.HOLY_BASIL,"กะเพรา");
            translationBuilder.add(ModItems.WILD_BASIL,"ต้นโหระพาป่า");
            translationBuilder.add(ModItems.BASIL,"โหระพา");
            translationBuilder.add(ModItems.BUTTERFLY_PEA,"ดอกอัญชัญ");
            translationBuilder.add(ModItems.BUTTERFLY_PEA_SEEDS,"เมล็ดดอกอัญชัญ");
            translationBuilder.add(ModItems.BAMBOO_SHOOT,"หน่อไม้ไผ่");
            translationBuilder.add(ModItems.PESTO_SAUCE,"ซอสเพสโต้");
            translationBuilder.add(ModItems.FRIED_DURIAN,"ทุเรียนทอด");
            translationBuilder.add(ModItems.SOMTAM_FEAST,"ส้มตำ");
            translationBuilder.add(ModItems.SOMTAM,"จานส้มตำ");
            translationBuilder.add(ModItems.LARB_FEAST,"ลาบ");
            translationBuilder.add(ModItems.LARB,"จานลาบ");
            translationBuilder.add(ModItems.CRAB_FRIED_RICE_FEAST,"ข้าวผัดปู");
            translationBuilder.add(ModItems.CRAB_FRIED_RICE,"จานข้าวผัดปู");
            translationBuilder.add(ModItems.PHAT_KAPHRAO_FEAST,"ข้าวผัดกะเพรา");
            translationBuilder.add(ModItems.PHAT_KAPHRAO,"จานข้าวผัดกะเพรา");
            translationBuilder.add(ModItems.MANGO_STICKY_RICE_FEAST,"ข้าวเหนียวมะม่วง");
            translationBuilder.add(ModItems.MANGO_STICKY_RICE,"จานข้าวเหนียวมะม่วง");
            translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE_FEAST,"ข้าวผัดสัปปะรด");
            translationBuilder.add(ModItems.PINEAPPLE_FRIED_RICE,"จานข้าวผัดสัปปะรด");
            translationBuilder.add(ModItems.STIR_FRIED_NOODLE,"ผัดหมี่");
            translationBuilder.add(ModItems.COCONUT_MILK_BOTTLE,"ขวดน้ำกะทิ");
            translationBuilder.add(ModItems.DURIAN_CURRY,"แกงทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CAKE,"เค้กทุเรียน");
            translationBuilder.add(ModItems.DURIAN_CAKE_SLICE,"สไลด์เค้กทุเรียน");
            translationBuilder.add(ModItems.MANGO_CHEESECAKE,"ชีสเค้กมะม่วง");
            translationBuilder.add(ModItems.MANGO_CHEESECAKE_SLICE,"สไลด์ชีสเค้กมะม่วง");
            translationBuilder.add(ModItems.COCONUT_JELLY,"วุ้นมะพร้าว");
            translationBuilder.add(ModItems.KHANOM_BABIN,"ขนมบ้าบิ่น");
            translationBuilder.add(ModItems.COCONUT_PIE,"พายมะพร้าว");
            translationBuilder.add(ModItems.COCONUT_PIE_SLICE,"สไลด์พายมะพร้าว");
            translationBuilder.add(ModItems.BASIL_OMELETTE_FEAST,"ไข่เจียวโหระพา");
            translationBuilder.add(ModItems.BASIL_OMELETTE,"จานไข่เจียวโหระพา");
            translationBuilder.add(ModItems.BAMBOO_SHOOT_SOUP,"แกงหน่อไม้");
            translationBuilder.add(ModItems.STEAMED_BAMBOO_SHOOT,"ห่อหมก");
            translationBuilder.add(ModItems.BANANA_IN_COCONUT_MILK,"กล้วยบวชชี");
            translationBuilder.add(ModItems.KHANOM_CHAN,"ขนมชั้น");
            translationBuilder.add(ModItems.COCONUT_MILK_ICE_CREAM,"ไอศกรีมกะทิ");




            translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","สีแดง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","สีเหลือง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","สีเขียว");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","สีฟ้า");
            translationBuilder.add("container.muffins_thaidelight.mortar","ครก");

            translationBuilder.add(ModMobEffects.STINKY,"เหม็น");
            translationBuilder.add(ModMobEffects.APPETITE_LOSS,"เบื่ออาหาร");
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
            translationBuilder.add("emi.category.muffins_thaidelight.fermented_fish","การหมัก");

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
        }
    }
}
