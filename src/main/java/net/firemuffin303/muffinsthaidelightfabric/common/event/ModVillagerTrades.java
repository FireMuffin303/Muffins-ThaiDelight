package net.firemuffin303.muffinsthaidelightfabric.common.event;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.List;

public class ModVillagerTrades {
    public static List<ModVillagerTrade> trades(){
        List<ModVillagerTrade> modVillagerTrades = new ArrayList<>();
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(ModItems.PEPPER,16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(ModItems.LIME,16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(ModItems.PAPAYA,16),new ItemStack(Items.EMERALD),16,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(ModItems.RAW_PAPAYA,16),new ItemStack(Items.EMERALD),16,2,1)));

        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(Items.EMERALD,1),new ItemStack(ModBlocks.PAPAYA_SAPLING,1),8,2,1)));
        modVillagerTrades.add(new ModVillagerTrade(VillagerProfession.FARMER,1,new MerchantOffer(new ItemStack(Items.EMERALD,1),new ItemStack(ModItems.LIME_SAPLING,1),8,2,1)));


        return modVillagerTrades;
    }

    public static List<MerchantOffer> wanderTrade(){
        List<MerchantOffer> merchantOfferList = new ArrayList<>();

        merchantOfferList.add(new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModBlocks.PAPAYA_SAPLING,1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModItems.LIME_SAPLING,1),16,2,1));
        merchantOfferList.add(new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModItems.PEPPER_SEED,1),16,2,1));

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
