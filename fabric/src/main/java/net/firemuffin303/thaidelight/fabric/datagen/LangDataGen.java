package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LangDataGen extends FabricLanguageProvider {


    protected LangDataGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        //Advancement
        translationBuilder.add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
        translationBuilder.add("advancement.muffins_thaidelight.got_mortar.description",".");

        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly","It's inedible!");
        translationBuilder.add("advancement.muffins_thaidelight.cooked_dragonfly.description","Obtains Cooked Dragonfly. Is it really edible?");

        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl","Where is the sauce?");
        translationBuilder.add("advancement.muffins_thaidelight.sauce_bowl.description","Obtains Sauce Bowl");

        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime","Is it snapshot day?");
        translationBuilder.add("advancement.muffins_thaidelight.sliced_lime.description","Obtains Sliced Lime. What are bugs they fixed this time?");

        translationBuilder.add("advancement.muffins_thaidelight.got_pepper","The true ingredient");
        translationBuilder.add("advancement.muffins_thaidelight.got_pepper.description","Obtains Pepper.");

        translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad","Yummy Larb");
        translationBuilder.add("advancement.muffins_thaidelight.got_spicy_meat_salad.description","Obtains Spicy Meat Salad. Yummy");

        translationBuilder.add("advancement.muffins_thaidelight.got_somtam","Pepper Please");
        translationBuilder.add("advancement.muffins_thaidelight.got_somtam.description","Obtains Somtam, is one pepper really enough?");

        translationBuilder.add("farmersdelight.tooltip.papaya_juice","Clear Hunger Effect");
        translationBuilder.add("farmersdelight.tooltip.lime_juice","Clear Blindness Effect");
        translationBuilder.add("jei.info.papaya_log","Right click with bone meal to grow papayas.");


        translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");

        //Blocks
        translationBuilder.add(ModBlocks.MORTAR.get(),"Mortar");

        translationBuilder.add(ModBlocks.LIME_CRATE.get(), "Lime Crate");
        translationBuilder.add(ModBlocks.PEPPER_CRATE.get(), "Pepper Crate");
        translationBuilder.add(ModBlocks.RAW_PAPAYA_CRATE.get(), "Raw Papaya Crate");
        translationBuilder.add(ModBlocks.PAPAYA_CRATE.get(), "Papaya Crate");
        translationBuilder.add(ModBlocks.PAPAYA_LOG.get(), "Papaya Log");
        translationBuilder.add(ModBlocks.PAPAYA_WOOD.get(), "Papaya Wood");
        translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_LOG.get(), "Stripped Papaya Log");
        translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_WOOD.get(), "Stripped Papaya Wood");
        translationBuilder.add(ModBlocks.PAPAYA_LEAVES.get(), "Papaya Leaves");

        //translationBuilder.add(ModItems.ESAN_MUSIC_DISC,"Music Disc");
        //translationBuilder.add("item.muffins_thaidelight.music_disc_northeast.desc","FireMuffin303 - untitled");

        //Feast
        translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"Somtam");
        translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"Spicy Minced Meat Salad");
        translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST.get(),"Crab Fried Rice");


        translationBuilder.add(ModBlocks.WILD_PEPPER_CROP.get(),"Wild Pepper");
        translationBuilder.add(ModItems.PEPPER.get(),"Pepper");
        translationBuilder.add(ModItems.PEPPER_SEED.get(),"Pepper Seeds");

        translationBuilder.add(ModBlocks.LIME_CROP.get(),"Lime Bush");
        translationBuilder.add(ModItems.LIME.get(),"Lime");
        translationBuilder.add(ModItems.SLICED_LIME.get(),"Lime Slice");
        translationBuilder.add(ModBlocks.LIME_SAPLING.get(),"Lime Sapling");

        translationBuilder.add(ModItems.PAPAYA.get(),"Papaya");
        translationBuilder.add(ModItems.SLICED_PAPAYA.get(),"Papaya Slice");
        translationBuilder.add(ModItems.RAW_PAPAYA.get(),"Raw Papaya");
        translationBuilder.add(ModItems.RAW_PAPAYA_SLICE.get(),"Raw Papaya Slice");
        translationBuilder.add(ModBlocks.PAPAYA_SAPLING.get(),"Papaya Sapling");
        translationBuilder.add(ModItems.PAPAYA_SEEDS.get(),"Papaya Seeds");

        //Food
        translationBuilder.add(ModItemsFabric.SOMTAM,"Plate of Somtam");
        translationBuilder.add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD,"Plate of Spicy Minced Meat Salad");
        translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"Plate of Crab Fried Rice");
        translationBuilder.add(ModItemsFabric.STIR_FRIED_NOODLE,"Stir Fried Noodle");
        translationBuilder.add(ModItems.PAPAYA_JUICE.get(),"Papaya Juice");
        translationBuilder.add(ModItems.LIME_JUICE.get(),"Lime Juice");

        translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE.get(),"Fish Sauce Bottle");
        translationBuilder.add(ModItems.FERMENTED_FISH_BOTTLE.get(),"Fermented Fish Bottle");
        translationBuilder.add(ModBlocks.FERMENTED_FISH_CAULDRON.get(),"Fermented Fish Cauldron");

        //Mobs
        translationBuilder.add(ModBlocks.CRAB_EGG.get(),"Flower Crab Egg");
        translationBuilder.add(ModItems.CRAB_SPAWN_EGG.get(),"Flower Crab Spawn Egg");
        translationBuilder.add(ModItems.CRAB_BUCKET.get(),"Bucket of Flower Crab");
        translationBuilder.add(ModItems.CRAB_MEAT.get(),"Raw Flower Crab");
        translationBuilder.add(ModItems.COOKED_CRAB_MEAT.get(),"Cooked Flower Crab");

        translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG.get(),"Dragonfly Spawn Egg");
        translationBuilder.add(ModItems.DRAGONFLY.get(),"Dragonfly");
        translationBuilder.add(ModItems.DRAGONFLY_BOTTLE.get(),"Bottle of Dragonfly");
        translationBuilder.add(ModItems.COOKED_DRAGONFLY.get(),"Cooked Dragonfly");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","Red");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","Yellow");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","Green");
        translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","Blue");
        translationBuilder.add("container.muffins_thaidelight.mortar","Mortar");
    }


    static class ThaiLangDataGen extends FabricLanguageProvider{


        protected ThaiLangDataGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, "th_th", registryLookup);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
            //Advancement
            translationBuilder.add("advancement.muffins_thaidelight.got_mortar","Thai's Delight");
            translationBuilder.add("advancement.muffins_thaidelight.got_mortar.description",".");

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

            translationBuilder.add("farmersdelight.tooltip.papaya_juice","ล้างหิวเร็ว");
            translationBuilder.add("farmersdelight.tooltip.lime_juice","ล้างตาบอด");
            translationBuilder.add("jei.info.papaya_log","คลิ๊กขวาด้วยผงกระดูกเพื่อโตผลมะละกอ");



            translationBuilder.add("itemGroup.muffins_thaidelight.main","Muffin's Thai Delight");
            //Block
            translationBuilder.add(ModBlocks.MORTAR.get(),"ครก");

            translationBuilder.add(ModBlocks.LIME_CRATE.get(), "ลังมะนาว");
            translationBuilder.add(ModBlocks.PEPPER_CRATE.get(), "ลังพริก");
            translationBuilder.add(ModBlocks.RAW_PAPAYA_CRATE.get(), "ลังมะละกอดิบ");
            translationBuilder.add(ModBlocks.PAPAYA_CRATE.get(), "ลังมะละกอ");
            translationBuilder.add(ModBlocks.PAPAYA_LOG.get(), "ท่อนไม้มะละกอ");
            translationBuilder.add(ModBlocks.PAPAYA_WOOD.get(), "ไม้มะละกอ");
            translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_LOG.get(), "ท่อนไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModBlocks.STRIPPED_PAPAYA_WOOD.get(), "ไม้มะละกอลอกเปลือก");
            translationBuilder.add(ModBlocks.PAPAYA_LEAVES.get(), "ใบไม้มะละกอ");

            //translationBuilder.add(ModItems.ESAN_MUSIC_DISC,"แผ่นเพลง");
            //translationBuilder.add("item.muffins_thaidelight.music_disc_northeast.desc","FireMuffin303 - untitled");

            //Feast
            translationBuilder.add(ModBlocksFabric.SOMTAM_FEAST,"ส้มตำ");
            translationBuilder.add(ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST,"ลาบ");
            translationBuilder.add(ModBlocks.CRAB_FRIED_RICE_FEAST.get(),"ข้าวผัดปู");

            //Crops
            translationBuilder.add(ModBlocks.WILD_PEPPER_CROP.get(), "ต้นพริกป่า");
            translationBuilder.add(ModBlocks.LIME_SAPLING.get(),"ต้นอ่อนมะนาว");

            translationBuilder.add(ModBlocks.PAPAYA_SAPLING.get(),"ต้นอ่อนมะละกอ");
            translationBuilder.add(ModItems.PAPAYA_SEEDS.get(),"เมล็ดมะละกอ");
            translationBuilder.add(ModItems.PAPAYA.get(),"มะละกอ");
            translationBuilder.add(ModItems.SLICED_PAPAYA.get(),"มะละกอหั่น");
            translationBuilder.add(ModItems.RAW_PAPAYA.get(),"มะละกอดิบ");
            translationBuilder.add(ModItems.RAW_PAPAYA_SLICE.get(),"มะละกอดิบหั่น");

            translationBuilder.add(ModItems.PEPPER.get(),"พริก");
            translationBuilder.add(ModItems.PEPPER_SEED.get(),"เมล็ดพริก");

            translationBuilder.add(ModBlocks.LIME_CROP.get(),"ต้นมะนาว");
            translationBuilder.add(ModItems.LIME.get(),"มะนาว");
            translationBuilder.add(ModItems.SLICED_LIME.get(),"มะนาวหั่น");

            //Food
            translationBuilder.add(ModItems.FISH_SAUCE_BOTTLE.get(),"ขวดน้ำปลา");
            translationBuilder.add(ModItems.FERMENTED_FISH_BOTTLE.get(),"ขวดปลาร้า");
            translationBuilder.add(ModBlocks.FERMENTED_FISH_CAULDRON.get(),"หม้อปลาร้า");
            translationBuilder.add(ModItems.PAPAYA_JUICE.get(),"น้ำมะละกอ");
            translationBuilder.add(ModItems.LIME_JUICE.get(),"น้ำมะนาว");

            translationBuilder.add(ModItemsFabric.SOMTAM,"ถ้วยส้มตำ");
            translationBuilder.add(ModItemsFabric.CRAB_FRIED_RICE,"ถ้วยข้าวผัดปู");
            translationBuilder.add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD,"ถ้วยลาบ");
            translationBuilder.add(ModItemsFabric.STIR_FRIED_NOODLE,"ผัดหมี่");

            //Mobs
            translationBuilder.add(ModBlocks.CRAB_EGG.get(),"ไข่ปูม้า");
            translationBuilder.add(ModItems.CRAB_SPAWN_EGG.get(),"ไข่เกิดปูม้า");
            translationBuilder.add(ModItems.CRAB_BUCKET.get(),"ถังปูม้า");
            translationBuilder.add(ModItems.CRAB_MEAT.get(),"เนื้อปูม้าสด");
            translationBuilder.add(ModItems.COOKED_CRAB_MEAT.get(),"เนื้อปูม้าสุก");

            translationBuilder.add(ModItems.DRAGONFLY_SPAWN_EGG.get(),"ไข่เกิดแมลงปอ");
            translationBuilder.add(ModItems.DRAGONFLY.get(),"แมลงปอสด");
            translationBuilder.add(ModItems.DRAGONFLY_BOTTLE.get(),"ขวดแก้วแมลงปอ");
            translationBuilder.add(ModItems.COOKED_DRAGONFLY.get(),"แมลงปอทอด");


            translationBuilder.add("dragonfly.variant.muffins_thaidelight.red","สีแดง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.yellow","สีเหลือง");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.green","สีเขียว");
            translationBuilder.add("dragonfly.variant.muffins_thaidelight.blue","สีฟ้า");
            translationBuilder.add("container.muffins_thaidelight.mortar","ครก");
        }
    }
}
