package net.firemuffin303.thaidelight.datagen;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID,bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        //dataGenerator.addProvider(event.includeServer(), new ThaiDelightBiomeModifierProvider(packOutput,event.getLookupProvider()));
        /*
        dataGenerator.addProvider(event.includeServer(),new LootTableProvider(packOutput, Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(ModEntityLootTableProvider::new,LootContextParamSets.ENTITY),
                        new LootTableProvider.SubProviderEntry(ModChestLootTableProvider::new,LootContextParamSets.CHEST),
                        new LootTableProvider.SubProviderEntry(ModCustomLootTableProvider::new,LootContextParamSets.BLOCK)
                )
        ));

         */
        //dataGenerator.addProvider(event.includeServer(),new ThaiDelightGlobalLootModifierProvider(packOutput));
        /*
        dataGenerator.addProvider(event.includeServer(), new ThaiDelightRecipeProvider(packOutput,List.of(
                new ThaiDelightCraftRecipeProvider(),
                new ThaiDelightFurnaceRecipeProvider(),
                new ThaiDelightMortarRecipeProvider(),
                new ThaiDelightCookingPotRecipeProvider(),
                new ThaiDelightCuttingBoardRecipeProvider()
        )));

        //Tags
        ThaiDelightBlockTagProvider thaiDelightBlockTagProvider = new ThaiDelightBlockTagProvider(packOutput,event.getLookupProvider(),event.getExistingFileHelper());
        dataGenerator.addProvider(event.includeServer(),thaiDelightBlockTagProvider);
        dataGenerator.addProvider(event.includeServer(), new ThaiDelightItemTagProvider(packOutput,event.getLookupProvider(),thaiDelightBlockTagProvider.contentsGetter(),event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeServer(), new ThaiDelightEntityTagProvider(packOutput,event.getLookupProvider(),event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeServer(), new ThaiDelightBiomeTagProvider(packOutput,event.getLookupProvider(),event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeServer(), new ThaiDelightDamageTypeTagProvider(packOutput,event.getLookupProvider(),event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput,event.getLookupProvider(),event.getExistingFileHelper(),
                List.of(new ThaiDelightAdvancementProvider())));
        dataGenerator.addProvider(event.includeClient(),new ThaiDelightLangProviderEN(packOutput));
        dataGenerator.addProvider(event.includeClient(),new ThaiDelightLangProviderTH(packOutput));

        //BlockModels
        dataGenerator.addProvider(event.includeClient(),new ThaiDelightBlockModelProvider(packOutput,event.getExistingFileHelper()));
        */
    }
}
