package net.firemuffin303.thaidelight.common.event;

import com.ibm.icu.impl.Pair;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.List;

public class ModVillagerTrades {
    public static List<Pair<Integer,MerchantOffer>> farmerTrade(){
        List<Pair<Integer,MerchantOffer>> merchantOfferList = new ArrayList<>();
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(ModItems.PEPPER,16),new ItemStack(Items.EMERALD),16,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(ModItems.LIME,16),new ItemStack(Items.EMERALD),16,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(ModItems.PAPAYA,16),new ItemStack(Items.EMERALD),16,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(ModItems.RAW_PAPAYA,16),new ItemStack(Items.EMERALD),16,2,1)));

        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD,1),new ItemStack(ModItems.PAPAYA_SEED,1),8,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD,1),new ItemStack(ModBlocks.LIME_SAPLING,1),8,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD,1),new ItemStack(ModItems.PEPPER_SEED,1),8,2,1)));

        merchantOfferList.add(Pair.of(2,new MerchantOffer(new ItemStack(Items.EMERALD,8),new ItemStack(ModItems.LIME_JUICE),8,2,1)));
        merchantOfferList.add(Pair.of(2,new MerchantOffer(new ItemStack(Items.EMERALD,8),new ItemStack(ModItems.PAPAYA_JUICE),8,2,1)));

        return merchantOfferList;
    }

    public static List<Pair<Integer,MerchantOffer>> wanderTrade(){
        List<Pair<Integer,MerchantOffer>> merchantOfferList = new ArrayList<>();

        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModItems.PAPAYA_SEED,1),16,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModBlocks.LIME_SAPLING,1),16,2,1)));
        merchantOfferList.add(Pair.of(1,new MerchantOffer(new ItemStack(Items.EMERALD),new ItemStack(ModItems.PEPPER_SEED,1),16,2,1)));

        return merchantOfferList;
    }
}
