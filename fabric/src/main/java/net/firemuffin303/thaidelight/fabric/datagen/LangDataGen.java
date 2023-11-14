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
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtains Cooked Dragonfly.");
        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");
        translationBuilder.add(ModBlocks.CRAB_EGG,"Flower Crab Egg");
        translationBuilder.add(ModBlocks.LIME_BUSH,"Lime Bush");
        translationBuilder.add(ModBlocks.MORTAR,"Mortar");

        translationBuilder.add(ModBlocks.SAUCE_BOWL,"Sauce Bowl");
        translationBuilder.add(ModBlocks.SEAFOOD_SAUCE_BOWL,"Seafood Sauce Bowl");
        translationBuilder.add(ModBlocks.FISH_SAUCE_SAUCE_BOWL,"Fish Sauce Bowl");
        translationBuilder.add(ModBlocks.HONEY_SAUCE_BOWL,"Honey Sauce Bowl");

        translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"Somtam");
        translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"Spicy Minced Pork Salad");

        translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET,"Flower Crab in a Bucket");
        translationBuilder.add(ModItems.CRAB_MEAT,"Raw Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"Cooked Crab");
        translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"Seafooded Cooked Crab");


        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY,"Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"Dragonfly in a Bottle");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY,"Cooked Dragonfly");

        translationBuilder.add(ModItems.SEAFOOD_BOTTLE,"Seafood Bottle");
        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"Fish Sauce Bottle");

        translationBuilder.add(ModItems.LIME,"Lime");
        translationBuilder.add(ModItems.LIME_SEED,"Lime Seed");
        translationBuilder.add(ModItems.PEPPER,"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED,"Pepper Seed");
        translationBuilder.add(ModItems.UNRIPE_PAPAYA,"Unripe Papaya");
        translationBuilder.add(ModItems.PAPAYA,"Papaya");
        translationBuilder.add(ModItems.PAPAYA_SEED,"Papaya Seed");

        translationBuilder.add(ModItems.LOINCLOTH,"Loincloth");

        translationBuilder.add(ModItemsFabric.SOMTAM,"Plate of Somtam");
        translationBuilder.add(ModItemsFabric.SPICY_MINCED_PORK_SALAD,"Plate of Spicy Minced Pork Salad");
        translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"Crab Fried Rice");



        translationBuilder.add(ModItemsFabric.STONE_PASTLE,"Stone Pastle");
        translationBuilder.add(ModItemsFabric.IRON_PASTLE,"Iron Pastle");
        translationBuilder.add(ModItemsFabric.GOLD_PASTLE,"Gold Pastle");
        translationBuilder.add(ModItemsFabric.DIAMOND_PASTLE,"Diamond Pastle");
        translationBuilder.add(ModItemsFabric.NETHERITE_PASTLE,"Netherite Pastle");


    }

    static class ThaiLangDataGen extends FabricLanguageProvider{

        protected ThaiLangDataGen(FabricDataOutput dataOutput) {
            super(dataOutput,"th_th");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","มันกินไม่ได้!");
            translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","ได้รับแมลงปอทอด");
            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","ไหนซอส?");
            translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","ได้รับถ้วยน้ำจิ้ม");

            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

            translationBuilder.add(ModBlocks.CRAB_EGG,"ไข่ปูม้า");
            translationBuilder.add(ModBlocks.LIME_BUSH,"ต้นมะนาว");
            translationBuilder.add(ModBlocks.MORTAR,"ครก");

            translationBuilder.add(ModBlocks.SAUCE_BOWL,"ถ้วยน้ำจิ้ม");
            translationBuilder.add(ModBlocks.SEAFOOD_SAUCE_BOWL,"ถ้วยน้ำจิ้มซีฟู้ด");
            translationBuilder.add(ModBlocks.FISH_SAUCE_SAUCE_BOWL,"ถ้วยน้ำปลา");
            translationBuilder.add(ModBlocks.HONEY_SAUCE_BOWL,"ถ้วยน้ำผึ้ง");

            translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"ส้มตำ");
            translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"ลาบหมู");


            translationBuilder.add(ModItems.CRAB_SPAWN_EGG,"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET,"ปูม้าในถัง");
            translationBuilder.add(ModItems.CRAB_MEAT,"เนื้อปูสด");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT,"เนื้อปูสุก");
            translationBuilder.add(ModItems.CRAB_MEAT_WITH_SEAFOOD,"เนื้อปูสุกเคลือบซีฟู้ด");


            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG,"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY,"แมลงปอสด");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE,"แมลงปอในขวดแก้ว");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY,"แมลงปอทอด");

            translationBuilder.add(ModItems.SEAFOOD_BOTTLE,"ขวดซีฟู้ด");
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE,"ขวดน้ำปลา");

            translationBuilder.add(ModItems.LIME,"มะนาว");
            translationBuilder.add(ModItems.LIME_SEED,"เมล็ดมะนาว");
            translationBuilder.add(ModItems.PEPPER,"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED,"เมล็ดพริก");
            translationBuilder.add(ModItems.UNRIPE_PAPAYA,"มะละกอไม่สุก");
            translationBuilder.add(ModItems.PAPAYA,"มะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SEED,"เมล็ดมะละกอ");

            translationBuilder.add(ModItemsFabric.SOMTAM,"ส้มตำ");
            translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"ข้าวผัดปู");


            translationBuilder.add(ModItems.LOINCLOTH,"ผ้าขาวม้า");

            translationBuilder.add(ModItemsFabric.STONE_PASTLE,"สากหิน");
            translationBuilder.add(ModItemsFabric.IRON_PASTLE,"สากเหล็ก");
            translationBuilder.add(ModItemsFabric.GOLD_PASTLE,"สากทอง");
            translationBuilder.add(ModItemsFabric.DIAMOND_PASTLE,"สากเพชร");
            translationBuilder.add(ModItemsFabric.NETHERITE_PASTLE,"สากเนเธอร์ไรต์");
        }
    }
}
