package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ThaiDelightLangProviderTH extends LanguageProvider {
    public ThaiDelightLangProviderTH(PackOutput output) {
        super(output, ThaiDelightCommon.MOD_ID, "th_th");
    }

    @Override
    protected void addTranslations() {
        add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
        add("advancement.muffins_thaidelight.got_mortar.description","ได้เวลาลิ้มรสชาติของอาหารไทยแล้ว!");

        add("advancement.muffins_thaidelight.cooked_dragonfly","มันกินไม่ได้!");
        add("advancement.muffins_thaidelight.cooked_dragonfly.description","ได้รับแมลงปอทอด. มันกินได้จริง ๆ หรอ?");

        add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        add("advancement.muffins_thaidelight.sliced_lime","วันนี้มีสแน๊ปช็อตหรอ?");
        add("advancement.muffins_thaidelight.sliced_lime.description","ได้รับมะนาวหั่น. เขาแก้บัคอะไรบ้างนะ");

        add("advancement.muffins_thaidelight.got_pepper","วัตถุดิบที่แท้จริง");
        add("advancement.muffins_thaidelight.got_pepper.description","ได้รับพริก.");

        add("advancement.muffins_thaidelight.got_spicy_meat_salad","ลาบแซ่บ ๆ");
        add("advancement.muffins_thaidelight.got_spicy_meat_salad.description","ได้รับลาบเนื้อ แซ่บ ๆ");

        add("advancement.muffins_thaidelight.got_somtam","พริกเพิ่มหน่อย");
        add("advancement.muffins_thaidelight.got_somtam.description","ได้รับส้มตำ, เม็ดเดียวมันพอจริง ๆ หรอ?");

        add("advancement.muffins_thaidelight.being_stinky","โอ๊ะ โอ่ ตัวเหม็น");
        add("advancement.muffins_thaidelight.being_stinky.description","ได้รับสถานะ เหม็น. ไปอาบน้ำบ้างนะ.");

        add("advancement.muffins_thaidelight.susie_prize","ของรางวัลซูซี่");
        add("advancement.muffins_thaidelight.susie_prize.description","ทำให้ดอกมะละกอสว่างขึ้นด้วยหมึกเรืองแสง เอาน่าคุณควรได้รางวัลบ้างนะ");

        add("advancement.muffins_thaidelight.big_big_nut","มะพร้าวอันเบิ้มๆ");
        add("advancement.muffins_thaidelight.big_big_nut.description","รับลูกมะพร้าวร่วงด้วยกระสอบ ลูกเบิ้มๆเลย");

        add("advancement.muffins_thaidelight.gravity_not_invent","แรงโน้มถ่วงไม่ถูกคิดค้น");
        add("advancement.muffins_thaidelight.gravity_not_invent.description","โดนลูกทุเรียนหล่นใส่หัว อันนี้ไม่น่าใช่ต้นแอปเปิ้ลนะ");

        add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
        add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");
        add("farmersdelight.tooltip.honey_lime_juice","ล้างตาบอดและพิษ");
        add("farmersdelight.tooltip.coconut_water","ล้างโจมตีเบา");
        add("farmersdelight.tooltip.butterfly_pea_tea","ล้างวิ่งช้า");
        add("jei.info.papaya_log","คลิ๊กขวาด้วยผงกระดูกเพื่อโตผลมะละกอ");

        add("item.muffins_thaidelight.tasty","อยากอาหาร");

        add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        addItem(ModItems.MORTAR,"ครก");
        addItem(ModItems.SACK,"กระสอบ");

        addItem(ModItems.LIME_CRATE,"ลังมะนาว");
        addItem(ModItems.PEPPER_CRATE,"ลังพริก");
        addItem(ModItems.RAW_PAPAYA_CRATE,"ลังมะละกอดิบ");
        addItem(ModItems.PAPAYA_CRATE,"ลังมะละกอ");
        addItem(ModItems.MANGO_CRATE,"ลังมะม่วง");
        addItem(ModItems.HOLY_BASIL_CRATE,"ลังกะเพรา");
        addItem(ModItems.BASIL_CRATE,"ลังโหระพา");
        addItem(ModItems.BAMBOO_SHOOT_CRATE,"ลังหน่อไม้ไผ่");
        addItem(ModItems.BUTTERFLY_PEA_CRATE,"ลังดอกอัญชัญ");

        addItem(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
        addItem(ModItems.CRAB_EGG,"ไข่ปูม้า");
        addItem(ModItems.CRAB_BUCKET,"ถังปูม้า");
        addItem(ModItems.CRAB_MEAT,"เนื้อปูม้า");
        addItem(ModItems.COOKED_CRAB_MEAT,"เนื้อปูม้าสุก");

        addItem(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
        addItem(ModItems.DRAGONFLY_BOTTLE,"ขวดแมลงปอ");
        addItem(ModItems.DRAGONFLY,"แมลงปอ");
        addItem(ModItems.COOKED_DRAGONFLY,"แมลงปอสุก");

        addItem(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");
        addItem(ModItems.FERMENTED_FISH,"ปลาร้า");
        addItem(ModItems.PAPAYA_JUICE,"น้ำมะละกอ");
        addItem(ModItems.LIME_JUICE,"น้ำมะนาว");
        addItem(ModItems.HONEY_LIME_JUICE,"น้ำผึ้งมะนาว");
        addItem(ModItems.COCONUT_WATER,"น้ำมะพร้าว");
        addItem(ModItems.BUTTERFLY_PEA_TEA,"ชาดอกอัญชัญ");

        addItem(ModItems.LIME_SAPLING,"ต้นนอ่อนมะนาว");
        addItem(ModItems.LIME,"มะนาว");
        addItem(ModItems.SLICED_LIME,"มะนาวหั่น");
        addItem(ModItems.WILD_PEPPER_CROP,"พริกป่า");
        addItem(ModItems.PEPPER,"พริก");
        addItem(ModItems.PEPPER_SEED,"เมล็ดพริก");

        addItem(ModItems.DURIAN_SAPLING,"ต้นอ่อนทุเรียน");
        addItem(ModItems.DURIAN_LEAVES,"ใบไม้ทุเรียน");
        addItem(ModItems.DURIAN_FLOWER,"ดอกทุเรียน");
        addItem(ModItems.SMALL_DURIAN,"ทุเรียนผลเล็ก");
        addItem(ModItems.DURIAN,"ทุเรียน");
        addItem(ModItems.DURIAN_PULP,"เนื้อทุเรียน");
        addItem(ModItems.DURIAN_PEEL,"เปลือกทุเรียน");
        addItem(ModItems.DURIAN_PEEL_BLOCK,"บล็อกเปลือกทุเรียน");
        addItem(ModItems.DURIAN_HELMET,"เกราะหมวกทุเรียน");
        addItem(ModItems.DURIAN_LOG,"ท่อนไม้ทุเรียน");
        addItem(ModItems.DURIAN_WOOD,"ไม้ทุเรียน");
        addItem(ModItems.STRIPPED_DURIAN_LOG,"ท่อนไม้ทุเรียนลอกเปลือก");
        addItem(ModItems.STRIPPED_DURIAN_WOOD,"ไม้ทุเรียนลอกเปลือก");
        addItem(ModItems.DURIAN_PLANKS,"แผ่นไม้กระดานทุเรียน");
        addItem(ModItems.DURIAN_STAIRS,"บันไดไม้ทุเรียน");
        addItem(ModItems.DURIAN_SLAB,"แผ่นไม้ทุเรียน");
        addItem(ModItems.DURIAN_FENCE,"รั้วไม้ทุเรียน");
        addItem(ModItems.DURIAN_FENCE_GATE,"ประตูรั้วไม้ทุเรียน");
        addItem(ModItems.DURIAN_DOOR,"ประตูไม้ทุเรียน");
        addItem(ModItems.DURIAN_TRAPDOOR,"ประตูกับดักไม้ทุเรียน");
        addItem(ModItems.DURIAN_PRESSURE_PLATE,"แป้นเหยียบไม้ทุเรียน");
        addItem(ModItems.DURIAN_BUTTON,"ปุ่มไม้ทุเรียน");
        addItem(ModItems.DURIAN_SIGN,"ป้ายไม้ทุเรียน");
        addItem(ModItems.DURIAN_HANGING_SIGN,"ป้ายแขวนทุเรียน");
        addItem(ModItems.DURIAN_CABINET,"ตู้เก็บของทุเรียน");
        addItem(ModItems.DURIAN_BOAT,"เรือไม้ทุเรียน");
        addItem(ModItems.DURIAN_CHEST_BOAT,"เรือไม้ทุเรียนพร้อมหีบ");
        addItem(ModItems.COCONUT_SAPLING,"ต้นอ่อนมะพร้าว");
        addItem(ModItems.COCONUT_LEAF,"ใบไม้มะพร้าว");
        addItem(ModItems.BUDDING_COCONUT_LEAF,"ต้นกำเนิดใบไม้มะพร้าว");
        addItem(ModItems.COCONUT_LEAF_BLOCK,"บล็อกใบไม้มะพร้าว");
        addItem(ModItems.COCONUT_LEAF_MAT,"พรมใบไม้มะพร้าว");
        addItem(ModItems.COCONUT_LOG,"ท่อนไม้มะพร้าว");
        addItem(ModItems.COCONUT_WOOD,"ไม้มะพร้าว");
        addItem(ModItems.STRIPPED_COCONUT_LOG,"ท่อนไม้มะพร้าวลอกเปลือก");
        addItem(ModItems.STRIPPED_COCONUT_WOOD,"ไม้มะพร้าวลอกเปลือก");
        addItem(ModItems.COCONUT_PLANKS,"แผ่นกระดานไม้มะพร้าว");
        addItem(ModItems.COCONUT_STAIRS,"บันไดไม้มะพร้าว");
        addItem(ModItems.COCONUT_SLAB,"แผ่นไม้มะพร้าว");
        addItem(ModItems.COCONUT_FENCE,"รั้วไม้มะพร้าว");
        addItem(ModItems.COCONUT_FENCE_GATE,"ประตูรั้วไม้มะพร้าว");
        addItem(ModItems.COCONUT_DOOR,"ประตูไม้มะพร้าว");
        addItem(ModItems.COCONUT_TRAPDOOR,"ประตูกับดักไม้มะพร้าว");
        addItem(ModItems.COCONUT_PRESSURE_PLATE,"แป้นเหยียบไม้มะพร้าว");
        addItem(ModItems.COCONUT_BUTTON,"ปุ่มไม้มะพร้าว");
        addItem(ModItems.COCONUT_SIGN,"ป้ายไม้มะพร้าว");
        addItem(ModItems.COCONUT_HANGING_SIGN,"ป้ายแขวนไม้มะพร้าว");
        addItem(ModItems.COCONUT_BOAT,"เรือไม้มะพร้าว");
        addItem(ModItems.COCONUT_CHEST_BOAT,"เรือไม้มะพร้าวพร้อมหีบ");
        addItem(ModItems.COCONUT_CABINET,"ตู้เก็บของไม้มะพร้าว");
        addItem(ModItems.COCONUT,"มะพร้าว");
        addItem(ModItems.STRIPPED_COCONUT,"มะพร้าวลอกเปลือก");
        addItem(ModItems.COCONUT_SLICE,"มะพร้าวหั่น");
        addItem(ModItems.MANGO_SAPLING,"ต้นอ่อนมะม่วง");
        addItem(ModItems.MANGO_LEAVES,"ใบไม้มะม่วง");
        addItem(ModItems.MANGO_LOG,"ท่อนไม้มะม่วง");
        addItem(ModItems.MANGO_WOOD,"ไม้มะม่วง");
        addItem(ModItems.STRIPPED_MANGO_LOG,"ท่อนไม้มะม่วง");
        addItem(ModItems.STRIPPED_MANGO_WOOD,"ไม้มะม่วง");
        addItem(ModItems.MANGO_PLANKS,"แผ่นกระดานไม้มะม่วง");
        addItem(ModItems.MANGO_STAIRS,"บันไดไม้มะม่วง");
        addItem(ModItems.MANGO_SLAB,"แผ่นไม้มะม่วง");
        addItem(ModItems.MANGO_FENCE,"รั้วไม้มะม่วง");
        addItem(ModItems.MANGO_FENCE_GATE,"ประตูรั้วไม้มะม่วง");
        addItem(ModItems.MANGO_DOOR,"ประตูไม้มะม่วง");
        addItem(ModItems.MANGO_TRAPDOOR,"ประตูกับดักไม้มะม่วง");
        addItem(ModItems.MANGO_PRESSURE_PLATE,"แป้นเหยียบไม้มะม่วง");
        addItem(ModItems.MANGO_BUTTON,"ปุ่มไม้มะม่วง");
        addItem(ModItems.MANGO_SIGN,"ป้ายไม้มะม่วง");
        addItem(ModItems.MANGO_HANGING_SIGN,"ป้ายแขวนไม้มะม่วง");
        addItem(ModItems.MANGO_BOAT,"เรือไม้มะม่วง");
        addItem(ModItems.MANGO_CHEST_BOAT,"เรือไม้มะม่วงพร้อมหีบ");
        addItem(ModItems.MANGO_CABINET,"ตู้เก็บของไม้มะม่วง");
        addItem(ModItems.MANGO,"มะม่วง");
        addItem(ModItems.MANGO_SLICE,"มะม่วงหั่น");
        addItem(ModItems.PAPAYA,"มะละกอ");
        addItem(ModItems.PAPAYA_FLOWER,"ดอกมะละกอ");
        addItem(ModItems.SLICED_PAPAYA,"มะละกอหั่น");
        addItem(ModItems.RAW_PAPAYA,"มะละกอดิบ");
        addItem(ModItems.RAW_PAPAYA_SLICE,"มะละกอดิบหั่น");
        addItem(ModItems.PAPAYA_LOG,"ท่อนไม้มะละกอ");
        addItem(ModItems.STRIPPED_PAPAYA_LOG,"ท่อนไม้มะละกอลอกเปลือก");
        addItem(ModItems.PAPAYA_WOOD,"ไม้มะละกอ");
        addItem(ModItems.STRIPPED_PAPAYA_WOOD,"ไม้มะละกอลอกเปลือก");
        addItem(ModItems.PAPAYA_LEAVES,"ใบไม้มะละกอ");
        addItem(ModItems.PAPAYA_SAPLING,"ต้นอ่อนมะละกอ");
        addItem(ModItems.PAPAYA_SEEDS,"เมล็ดมะละกอ");
        addItem(ModItems.WILD_BASIL,"ต้นโหระพาป่า");
        addItem(ModItems.BASIL,"โหระพา");
        addItem(ModItems.BUTTERFLY_PEA,"ดอกอัญชัญ");
        addItem(ModItems.BUTTERFLY_PEA_SEEDS,"เมล็ดดอกอัญชัญ");
        addItem(ModItems.BAMBOO_SHOOT,"หน่อไม้ไผ่");
        addItem(ModItems.PESTO_SAUCE,"ซอสเพสโต้");
        addItem(ModItems.FRIED_DURIAN,"ทุเรียนทอด");
        addItem(ModItems.SOMTAM_FEAST,"ส้มตำ");
        addItem(ModItems.SOMTAM,"จานส้มตำ");
        addItem(ModItems.LARB_FEAST,"ลาบ");
        addItem(ModItems.LARB,"จานลาบ");
        addItem(ModItems.CRAB_FRIED_RICE_FEAST,"ข้าวผัดปู");
        addItem(ModItems.CRAB_FRIED_RICE,"จานข้าวผัดปู");
        addItem(ModItems.PHAT_KAPHRAO_FEAST,"ข้าวผัดกะเพรา");
        addItem(ModItems.PHAT_KAPHRAO,"จานข้าวผัดกะเพรา");
        addItem(ModItems.MANGO_STICKY_RICE_FEAST,"ข้าวเหนียวมะม่วง");
        addItem(ModItems.MANGO_STICKY_RICE,"จานข้าวเหนียวมะม่วง");
        addItem(ModItems.PINEAPPLE_FRIED_RICE_FEAST,"ข้าวผัดสัปปะรด");
        addItem(ModItems.PINEAPPLE_FRIED_RICE,"จานข้าวผัดสัปปะรด");
        addItem(ModItems.STIR_FRIED_NOODLE,"ผัดหมี่");
        addItem(ModItems.COCONUT_MILK_BOTTLE,"ขวดน้ำกะทิ");
        addItem(ModItems.DURIAN_CURRY,"แกงทุเรียน");
        addItem(ModItems.DURIAN_CAKE,"เค้กทุเรียน");
        addItem(ModItems.DURIAN_CAKE_SLICE,"สไลด์เค้กทุเรียน");
        addItem(ModItems.MANGO_CHEESECAKE,"ชีสเค้กมะม่วง");
        addItem(ModItems.MANGO_CHEESECAKE_SLICE,"สไลด์ชีสเค้กมะม่วง");
        addItem(ModItems.COCONUT_JELLY,"วุ้นมะพร้าว");
        addItem(ModItems.KHANOM_BABIN,"ขนมบ้าบิ่น");
        addItem(ModItems.COCONUT_PIE,"พายมะพร้าว");
        addItem(ModItems.COCONUT_PIE_SLICE,"สไลด์พายมะพร้าว");
        addItem(ModItems.HONEY_COCONUT_PIE,"พายมะพร้าวราดน้ำผึ้ง");
        addItem(ModItems.HONEY_COCONUT_PIE_SLICE,"สไลด์พายมะพร้าวราดน้ำผึ้ง");
        addItem(ModItems.OMELETTE_FEAST,"ไข่เจียว");
        addItem(ModItems.OMELETTE,"จานไข่เจียว");
        addItem(ModItems.BASIL_OMELETTE_FEAST,"ไข่เจียวโหระพา");
        addItem(ModItems.BASIL_OMELETTE,"จานไข่เจียวโหระพา");
        addItem(ModItems.BAMBOO_SHOOT_SOUP,"แกงหน่อไม้");
        addItem(ModItems.STEAMED_BAMBOO_SHOOT,"ห่อหมก");
        addItem(ModItems.BANANA_IN_COCONUT_MILK,"กล้วยบวชชี");
        addItem(ModItems.KHANOM_CHAN,"ขนมชั้น");
        addItem(ModItems.COCONUT_MILK_ICE_CREAM,"ไอศกรีมกะทิ");


        add("dragonfly.variant.muffins_thaidelight.red","สีแดง");
        add("dragonfly.variant.muffins_thaidelight.yellow","สีเหลือง");
        add("dragonfly.variant.muffins_thaidelight.green","สีเขียว");
        add("dragonfly.variant.muffins_thaidelight.blue","สีฟ้า");
        add("container.muffins_thaidelight.mortar","ครก");

        addEffect(ModMobEffects.STINKY,"เหม็น");
        addEffect(ModMobEffects.APPETITE_LOSS,"เบื่ออาหาร");
        add("item.minecraft.potion.effect.stench","น้ำยาเหม็น");
        add("item.minecraft.potion.effect.long_stench","น้ำยาเหม็น");
        add("item.minecraft.potion.effect.strong_stench","น้ำยาเหม็น");

        add("item.minecraft.splash_potion.effect.stench","น้ำยาเหม็นแบบปา");
        add("item.minecraft.splash_potion.effect.long_stench","น้ำยาเหม็นแบบปา");
        add("item.minecraft.splash_potion.effect.strong_stench","น้ำยาเหม็นแบบปา");

        add("item.minecraft.lingering_potion.effect.stench","น้ำยาเหม็นแบบระเหย");
        add("item.minecraft.lingering_potion.effect.long_stench","น้ำยาเหม็นแบบระเหย");
        add("item.minecraft.lingering_potion.effect.strong_stench","น้ำยาเหม็นแบบระเหย");

        add("item.minecraft.tipped_arrow.effect.stench","ลูกธนูอาบยาเหม็น");
        add("item.minecraft.tipped_arrow.effect.long_stench","ลูกธนูอาบยาเหม็น");
        add("item.minecraft.tipped_arrow.effect.strong_stench","ลูกธนูอาบยาเหม็น");

        add("muffins_thaidelight.jei.fermented_fish.cauldron1","นำปลาใส่ลงไปในหม้อน้ำ");
        add("muffins_thaidelight.jei.fermented_fish.cauldron2","และรอ 6 - 15 นาที");
        add("muffins_thaidelight.jei.fermented_fish.cauldron3","จนกว่าจะหมักเสร็จ.");
        add("emi.category.muffins_thaidelight.mortar","ครก");
        add("emi.category.muffins_thaidelight.cauldron_crafting","หม้อปรุงยา");

        add("muffins_thaidelight.midnightconfig.title","Muffin's Thai's Delight Config");
        add("muffins_thaidelight.midnightconfig.gameplay","เกมเพลย์");
        add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem","อนุญาตให้พ่อค้าเร่ร่อนขายของ Thai's Delight");
        add("muffins_thaidelight.midnightconfig.wanderingTraderShouldTradeTDItem.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
        add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem","อนุญาตให้ชาวบ้านขายของ Thai's Delight");
        add("muffins_thaidelight.midnightconfig.villagerShouldTradeTDItem.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
        add("muffins_thaidelight.midnightconfig.worldGen","การเกิดโลก");
        add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn","อนุญาตให้บ้านไทยเกิดในหมู่บ้าน");
        add("muffins_thaidelight.midnightconfig.shouldThaiHouseSpawn.tooltip","จำเป็นต้องเริ่มเซิฟเวอร์ใหม่เพื่อเห็นผล");
        add("muffins_thaidelight.midnightconfig.stinkyShouldTriggerNeutral","เอฟเฟคเหม็นควรให้ม็อบเป็นกลางโกรธ");
        add("muffins_thaidelight.midnightconfig.fishofthieves","Fish Of Thieves Compatibility");
        add("muffins_thaidelight.midnightconfig.coconutTreeType","การเกิดต้นมะพร้าว");
        add("muffins_thaidelight.midnightconfig.coconutTreeType.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");
        add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn","อนุญาตให้ต้นมะม่วงของ Thai's Delight เกิด");
        add("muffins_thaidelight.midnightconfig.shouldMangoTreeSpawn.tooltip","จำเป็นต้องเริ่มเกม/เซิฟเวอร์ใหม่เพื่อเห็นผล");


        add("muffins_thaidelight.midnightconfig.developer","------ ผู้พัฒนา ------");
        add("muffins_thaidelight.midnightconfig.firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.concept_artist","------ ศิลปินคอนเซปต์อาร์ท ------");
        add("muffins_thaidelight.midnightconfig.lucas","lllLucaslll");
        add("muffins_thaidelight.midnightconfig.dino_care","Dino_care");
        add("muffins_thaidelight.midnightconfig.akalinka","No92");
        add("muffins_thaidelight.midnightconfig.translator","------ ผู้แปล ------");
        add("muffins_thaidelight.midnightconfig.en_us","--- ภาษาอังกฤษ ---");
        add("muffins_thaidelight.midnightconfig.en_firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.th_th","--- ภาษาไทย ---");
        add("muffins_thaidelight.midnightconfig.th_firemuffin","FireMuffin303");
        add("muffins_thaidelight.midnightconfig.uk_ua","--- ภาษายูเครน ---");
        add("muffins_thaidelight.midnightconfig.peaenka","P34enKa");
        add("muffins_thaidelight.midnightconfig.zh_cn","--- ภาษาจีนตัวย่อ ---");
        add("muffins_thaidelight.midnightconfig.huantanhua","Huantanhua");

        add("muffins_thaidelight.consume.durian_fermented_drinks","ร่างกายของคุณร้อนขึ้น");

        add("muffins_thaidelight.custom_effect_render.durian_consumed","ทานทุเรียนแล้ว");
        add("muffins_thaidelight.custom_effect_render.heated_up","ตัวร้อน");
        add("muffins_thaidelight.custom_effect_render.spicy","เผ็ด");

        add("death.attack.durian","%1$s ถูกลูกทุเรียนหล่นใส่หัว.");
        add("death.attack.durian.player","%1$s ถูกลูกทุเรียนหล่นใส่หัวขณะสู้กับ %2$s.");
    }
}
