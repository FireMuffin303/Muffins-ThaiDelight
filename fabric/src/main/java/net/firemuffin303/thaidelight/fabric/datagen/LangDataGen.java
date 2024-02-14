package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;

public class LangDataGen extends FabricLanguageProvider {
    protected LangDataGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //Advancement
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtains Cooked Dragonfly.");

        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime","Is it snapshot day?");
        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime.description","Obtains Sliced Lime");

        translationBuilder.add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        translationBuilder.add("farmersdelight.tooltip.lime_juice","Clear Blindness and Darkness Effect");


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
        translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"Somtam");
        translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"Spicy Minced Pork Salad");
        translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST,"Crab Fried Rice");


        translationBuilder.add(ModBlocks.WILD_PEPPER_CROP,"Wild Pepper");
        translationBuilder.add(ModItems.PEPPER,"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED,"Pepper Seeds");

        translationBuilder.add(ModBlocks.LIME_CROP,"Lime Bush");
        translationBuilder.add(ModItems.LIME,"Lime");
        translationBuilder.add(ModItems.SLICED_LIME,"Lime Slice");
        translationBuilder.add(ModBlocks.LIME_SAPLING,"Lime Sapling");

        translationBuilder.add(ModItems.PAPAYA,"Papaya");
        translationBuilder.add(ModItems.SLICED_PAPAYA,"Papaya Slice");
        translationBuilder.add(ModItems.RAW_PAPAYA,"Raw Papaya");
        translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"Raw Papaya Slice");
        translationBuilder.add(ModBlocks.PAPAYA_SEEDS,"Papaya Seeds");

        //Food
        translationBuilder.add(ModItemsFabric.SOMTAM,"Plate of Somtam");
        translationBuilder.add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD,"Plate of Spicy Minced Pork Salad");
        translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"Plate of Crab Fried Rice");
        translationBuilder.add(ModItemsFabric.STIR_FRIED_NOODLE,"Stir Fried Noodle");
        translationBuilder.add(ModItems.PAPAYA_JUICE,"Papaya Juice");
        translationBuilder.add(ModItems.LIME_JUICE,"Lime Juice");

        translationBuilder.add(ModItems.SEAFOOD_BOTTLE,"Seafood Bottle");
        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");

        //Mobs
        translationBuilder.add(ModBlocks.CRAB_EGG,"Flower Crab Egg");
        translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET,"Flower Crab in a Bucket");
        translationBuilder.add(ModItems.CRAB_MEAT,"Raw Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"Cooked Crab");
        translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"Seafooded Cooked Crab");

        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY,"Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"Dragonfly in a Bottle");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");
    }

    static class ThaiLangDataGen extends FabricLanguageProvider{

        protected ThaiLangDataGen(FabricDataOutput dataOutput) {
            super(dataOutput,"th_th");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            //Advancement
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","มันกินไม่ได้!");
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","ได้รับแมลงปอทอด");

            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","ไหนซอส?");
            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","ได้รับถ้วยน้ำจิ้ม");

            translationBuilder.add("advancement.muffins_thaidelight.sliced_lime","วันนี้มี Snapshot หรอ?");
            translationBuilder.add("advancement.muffins_thaidelight.sliced_lime.description","ได้รับมะนาวหั่น");

            translationBuilder.add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
            translationBuilder.add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");


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
            translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"ส้มตำ");
            translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"ลาบ");
            translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST,"ข้าวผัดปู");

            //Crops
            translationBuilder.add(ModBlocks.WILD_PEPPER_CROP, "ต้นพริกป่า");
            translationBuilder.add(ModBlocks.LIME_SAPLING,"ต้นอ่อนมะนาว");

            translationBuilder.add(ModBlocks.PAPAYA_SEEDS,"เมล็ดมะละกอ");
            translationBuilder.add(ModItems.PAPAYA,"มะละกอ");
            translationBuilder.add(ModItems.SLICED_PAPAYA,"มะละกอหั่น");
            translationBuilder.add(ModItems.RAW_PAPAYA,"มะละกอดิบ");
            translationBuilder.add(ModItems.RAW_PAPAYA_SLICE,"มะละกอดิบหั่น");

            translationBuilder.add(ModItems.PEPPER,"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED,"เมล็ดพริก");

            translationBuilder.add(ModBlocks.LIME_CROP,"ต้นมะนาว");
            translationBuilder.add(ModItems.LIME,"มะนาว");
            translationBuilder.add(ModItems.SLICED_LIME,"มะนาวหั่น");

            //Food
            translationBuilder.add(ModItems.SEAFOOD_BOTTLE,"ขวดซีฟู้ด");
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");
            translationBuilder.add(ModItems.PAPAYA_JUICE,"น้ำมะละกอ");
            translationBuilder.add(ModItems.LIME_JUICE,"น้ำมะนาว");

            translationBuilder.add(ModItemsFabric.SOMTAM,"ถ้วยส้มตำ");
            translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"ถ้วยข้าวผัดปู");
            translationBuilder.add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD,"ถ้วยลาบ");
            translationBuilder.add(ModItemsFabric.STIR_FRIED_NOODLE,"ผัดหมี่");

            //Mobs
            translationBuilder.add(ModBlocks.CRAB_EGG,"ไข่ปูม้า");
            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET,"ปูม้าในถัง");
            translationBuilder.add(ModItems.CRAB_MEAT,"เนื้อปูสด");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"เนื้อปูสุก");
            translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"เนื้อปูสุกเคลือบซีฟู้ด");

            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY,"แมลงปอสด");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"แมลงปอในขวดแก้ว");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"แมลงปอทอด");
        }
    }
}
