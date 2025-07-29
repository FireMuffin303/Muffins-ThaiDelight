package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
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

        translationBuilder.add("item.muffins_thaidelight.tasty","Tasty");

        translationBuilder.add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        translationBuilder.add("farmersdelight.tooltip.lime_juice","Clear Blindness Effect");
        translationBuilder.add("jei.info.papaya_log","Right click with bone meal to grow papayas.");


        translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        //Blocks
        translationBuilder.add(ModBlocks.MORTAR,"Mortar");

        translationBuilder.add(ModBlocks.LIME_CRATE, "Lime Crate");
        translationBuilder.add(ModBlocks.PEPPER_CRATE, "Pepper Crate");
        translationBuilder.add(ModBlocks.RAW_PAPAYA_CRATE, "Raw Papaya Crate");
        translationBuilder.add(ModBlocks.PAPAYA_CRATE, "Papaya Crate");
        translationBuilder.add(ModBlocks.PAPAYA_LOG, "Papaya Log");
        translationBuilder.add(ModBlocks.PAPAYA_WOOD, "Papaya Wood");
        translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_LOG, "Stripped Papaya Log");
        translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_WOOD, "Stripped Papaya Wood");
        translationBuilder.add(ModBlocks.PAPAYA_LEAVES, "Papaya Leaves");

        //translationBuilder.add(ModItems.ESAN_MUSIC_DISC,"Music Disc");
        //translationBuilder.add("item.muffins_thaidelight.music_disc_northeast.desc","FireMuffin303 - untitled");

        //Feast
        translationBuilder.add(ModBlocks.SOMTAM_FEAST,"Somtam");
        translationBuilder.add(ModBlocks.LARB_FEAST,"Larb");
        translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST,"Crab Fried Rice");


        translationBuilder.add(ModBlocks.WILD_PEPPER_CROP,"Wild Pepper");
        translationBuilder.add(ModItems.PEPPER,"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED,"Pepper Seeds");

        translationBuilder.add(ModBlocks.LIME_BUSH,"Lime Bush");
        translationBuilder.add(ModBlocks.LIME_SAPLING,"Lime Sapling");
        translationBuilder.add(ModItems.LIME,"Lime");
        translationBuilder.add(ModItems.SLICED_LIME,"Lime Slice");

        translationBuilder.add(ModItems.PAPAYA,"Papaya");
        translationBuilder.add(ModItems.SLICED_PAPAYA,"Papaya Slice");
        translationBuilder.add(ModItems.RAW_PAPAYA,"Raw Papaya");
        translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"Raw Papaya Slice");
        translationBuilder.add(ModBlocks.PAPAYA_SAPLING,"Papaya Sapling");
        translationBuilder.add(ModItems.PAPAYA_SEEDS,"Papaya Seeds");

        translationBuilder.add(ModItems.DURIAN_SAPLING,"Durian Sapling");
        translationBuilder.add(ModItems.DURIAN_LEAVES,"Durian Leaves");
        translationBuilder.add(ModItems.DURIAN_FLOWER,"Durian Flower");
        translationBuilder.add(ModItems.DURIAN,"Durian");
        translationBuilder.add(ModItems.DURIAN_PULP,"Durian Pulp");

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
        translationBuilder.add(ModItems.DURIAN_CHEST_BOAT,"Durian Boat with Chest");
        translationBuilder.add(ModItems.FRIED_DURIAN,"Fried Durian");

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
        translationBuilder.add(ModItems.MANGO_CABINET,"Mango Cabinet");
        translationBuilder.add(ModItems.MANGO_BOAT,"Mango Boat");
        translationBuilder.add(ModItems.MANGO_CHEST_BOAT,"Mango Boat with Chest");

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
        translationBuilder.add(ModItems.COCONUT_CABINET,"Coconut Cabinet");
        translationBuilder.add(ModItems.COCONUT_BOAT,"Coconut Boat");
        translationBuilder.add(ModItems.COCONUT_CHEST_BOAT,"Coconut Boat with Chest");

        //Food
        translationBuilder.add(ModItems.SOMTAM,"Plate of Somtam");
        translationBuilder.add(ModItems.LARB,"Plate of Larb");
        translationBuilder.add(ModItems.CRAB_FRIED_RICE,"Plate of Crab Fried Rice");
        translationBuilder.add(ModItems.STIR_FRIED_NOODLE,"Stir Fried Noodle");
        translationBuilder.add(ModItems.PAPAYA_JUICE,"Papaya Juice");
        translationBuilder.add(ModItems.LIME_JUICE,"Lime Juice");

        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");
        translationBuilder.add(ModItems.FERMENTED_FISH,"Fermented Fish");
        translationBuilder.add(ModBlocks.FERMENTED_FISH_CAULDRON,"Fermented Fish Cauldron");

        //Mobs
        translationBuilder.add(ModBlocks.CRAB_EGG,"Flower Crab Egg");
        translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET,"Bucket of Flower Crab");
        translationBuilder.add(ModItems.CRAB_MEAT,"Raw Flower Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"Cooked Flower Crab");

        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY,"Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"Bottle of Dragonfly");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","Red");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","Yellow");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","Green");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","Blue");
        translationBuilder.add("container.muffins_thaidelight.mortar","Mortar");

        translationBuilder.add(ModMobEffects.STINKY,"Stinky");
        translationBuilder.add(ModMobEffects.GLUTTONY,"Gluttony");
        translationBuilder.add("item.minecraft.potion.effect.stinkiness","Potion of Stinkiness");
        translationBuilder.add("item.minecraft.potion.effect.long_stinkiness","Potion of Stinkiness");
        translationBuilder.add("item.minecraft.potion.effect.strong_stinkiness","Potion of Stinkiness");

        translationBuilder.add("item.minecraft.splash_potion.effect.stinkiness","Splash Potion of Stinkiness");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_stinkiness","Splash Potion of Stinkiness");
        translationBuilder.add("item.minecraft.splash_potion.effect.strong_stinkiness","Splash Potion of Stinkiness");

        translationBuilder.add("item.minecraft.lingering_potion.effect.stinkiness","Lingering Potion of Stinkiness");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_stinkiness","Lingering Potion of Stinkiness");
        translationBuilder.add("item.minecraft.lingering_potion.effect.strong_stinkiness","Lingering Potion of Stinkiness");

        translationBuilder.add("item.minecraft.tipped_arrow.effect.stinkiness","Arrow of Stinkiness");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_stinkiness","Arrow of Stinkiness");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_stinkiness","Arrow of Stinkiness");

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

            translationBuilder.add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
            translationBuilder.add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");
            translationBuilder.add("jei.info.papaya_log","คลิ๊กขวาด้วยผงกระดูกเพื่อโตผลมะละกอ");

            translationBuilder.add("item.muffins_thaidelight.tasty","อยากอาหาร");



            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");
            //Block
            translationBuilder.add(ModBlocks.MORTAR,"ครก");

            translationBuilder.add(ModBlocks.LIME_CRATE, "ลังมะนาว");
            translationBuilder.add(ModBlocks.PEPPER_CRATE, "ลังพริก");
            translationBuilder.add(ModBlocks.RAW_PAPAYA_CRATE, "ลังมะละกอดิบ");
            translationBuilder.add(ModBlocks.PAPAYA_CRATE, "ลังมะละกอ");
            translationBuilder.add(ModBlocks.PAPAYA_LOG, "ท่อนไม้มะละกอ");
            translationBuilder.add(ModBlocks.PAPAYA_WOOD, "ไม้มะละกอ");
            translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_LOG, "ท่อนไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_WOOD, "ไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModBlocks.PAPAYA_LEAVES, "ใบไม้มะละกอ");

            //translationBuilder.add(ModItems.ESAN_MUSIC_DISC,"แผ่นเพลง");
            //translationBuilder.add("item.muffins_thaidelight.music_disc_northeast.desc","FireMuffin303 - untitled");

            //Feast
            translationBuilder.add(ModBlocks.SOMTAM_FEAST,"ส้มตำ");
            translationBuilder.add(ModBlocks.LARB_FEAST,"ลาบ");
            translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST,"ข้าวผัดปู");

            //Crops
            translationBuilder.add(ModBlocks.WILD_PEPPER_CROP, "ต้นพริกป่า");

            translationBuilder.add(ModBlocks.PAPAYA_SAPLING,"ต้นอ่อนมะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SEEDS,"เมล็ดมะละกอ");
            translationBuilder.add(ModItems.PAPAYA,"มะละกอ");
            translationBuilder.add(ModItems.SLICED_PAPAYA,"มะละกอหั่น");
            translationBuilder.add(ModItems.RAW_PAPAYA,"มะละกอดิบ");
            translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"มะละกอดิบหั่น");

            translationBuilder.add(ModItems.PEPPER,"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED,"เมล็ดพริก");

            translationBuilder.add(ModBlocks.LIME_BUSH,"ต้นมะนาว");
            translationBuilder.add(ModBlocks.LIME_SAPLING,"ต้นอ่อนมะนาว");
            translationBuilder.add(ModItems.LIME,"มะนาว");
            translationBuilder.add(ModItems.SLICED_LIME,"มะนาวหั่น");

            translationBuilder.add(ModItems.DURIAN_PULP,"เนื้อทุเรียน");

            //Food
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");
            translationBuilder.add(ModItems.FERMENTED_FISH,"ปลาร้า");
            translationBuilder.add(ModBlocks.FERMENTED_FISH_CAULDRON,"หม้อปลาร้า");
            translationBuilder.add(ModItems.PAPAYA_JUICE,"น้ำมะละกอ");
            translationBuilder.add(ModItems.LIME_JUICE,"น้ำมะนาว");

            translationBuilder.add(ModItems.SOMTAM,"ถ้วยส้มตำ");
            translationBuilder.add(ModItems.CRAB_FRIED_RICE,"ถ้วยข้าวผัดปู");
            translationBuilder.add(ModItems.LARB,"ถ้วยลาบ");
            translationBuilder.add(ModItems.STIR_FRIED_NOODLE,"ผัดหมี่");

            //Mobs
            translationBuilder.add(ModBlocks.CRAB_EGG,"ไข่ปูม้า");
            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET,"ถังปูม้า");
            translationBuilder.add(ModItems.CRAB_MEAT,"เนื้อปูม้าสด");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"เนื้อปูม้าสุก");

            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY,"แมลงปอสด");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"ขวดแก้วแมลงปอ");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"แมลงปอทอด");


            translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","สีแดง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","สีเหลือง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","สีเขียว");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","สีฟ้า");
            translationBuilder.add("container.muffins_thaidelight.mortar","ครก");

            translationBuilder.add(ModMobEffects.STINKY,"เหม็น");
            translationBuilder.add(ModMobEffects.GLUTTONY,"ตะกละ");
            translationBuilder.add("item.minecraft.potion.effect.stinkiness","น้ำยาเหม็น");
            translationBuilder.add("item.minecraft.potion.effect.long_stinkiness","น้ำยาเหม็น");
            translationBuilder.add("item.minecraft.potion.effect.strong_stinkiness","น้ำยาเหม็น");

            translationBuilder.add("item.minecraft.splash_potion.effect.stinkiness","น้ำยาเหม็นแบบปา");
            translationBuilder.add("item.minecraft.splash_potion.effect.long_stinkiness","น้ำยาเหม็นแบบปา");
            translationBuilder.add("item.minecraft.splash_potion.effect.strong_stinkiness","น้ำยาเหม็นแบบปา");

            translationBuilder.add("item.minecraft.lingering_potion.effect.stinkiness","น้ำยาเหม็นแบบระเหย");
            translationBuilder.add("item.minecraft.lingering_potion.effect.long_stinkiness","น้ำยาเหม็นแบบระเหย");
            translationBuilder.add("item.minecraft.lingering_potion.effect.strong_stinkiness","น้ำยาเหม็นแบบระเหย");

            translationBuilder.add("item.minecraft.tipped_arrow.effect.stinkiness","ลูกธนูอาบยาเหม็น");
            translationBuilder.add("item.minecraft.tipped_arrow.effect.long_stinkiness","ลูกธนูอาบยาเหม็น");
            translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_stinkiness","ลูกธนูอาบยาเหม็น");

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
