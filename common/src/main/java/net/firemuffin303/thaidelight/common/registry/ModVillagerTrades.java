package net.firemuffin303.thaidelight.common.registry;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.List;

public class ModVillagerTrades {
    public static List<ModVillagerTrade> trades(){
        List<ModVillagerTrade> modVillagerTrades = new ArrayList<>();
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(ModItems.PEPPER.get(),16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(ModItems.LIME.get(),16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(ModItems.PAPAYA.get(),16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(ModItems.RAW_PAPAYA.get(),16),new ItemStack(Items.EMERALD),16,2,1)));

        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(Items.EMERALD,1),new ItemStack(ModBlocks.PAPAYA_SAPLING.get(),1),8,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemCost(Items.EMERALD,1),new ItemStack(ModItems.LIME_SAPLING.get(),1),8,2,1)));


        return modVillagerTrades;
    }

    public static List<MerchantOffer> wanderTrade(){
        List<MerchantOffer> merchantOfferList = new ArrayList<>();

        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD),new ItemStack(ModBlocks.PAPAYA_SAPLING.get(),1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD),new ItemStack(ModItems.LIME_SAPLING.get(),1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD),new ItemStack(ModItems.PEPPER_SEED.get(),1),16,2,1));

        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD,5), new ItemStack(ModItems.DURIAN_SAPLING.get(),1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD,5), new ItemStack(ModItems.MANGO_SAPLING.get(),1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemCost(Items.EMERALD,5), new ItemStack(ModItems.COCONUT_SAPLING.get(),1),16,2,1));

        return merchantOfferList;
    }

    public record ModVillagerTrade(VillagerProfession villagerProfession, int level, MerchantOffer merchantOffer){

        public ModVillagerTrade(VillagerProfession villagerProfession, int level, MerchantOffer merchantOffer){
            this.villagerProfession = villagerProfession;
            this.level = level;
            this.merchantOffer = merchantOffer;
        }
    }
}
