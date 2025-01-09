package net.firemuffin303.muffinsthaidelightfabric.common.data;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;

public class FlavorItemData {
    public static final String FLAVOR_NBT = "flavors";
    public static final String SOUR_NBT = "sourLevel";
    public static final String SPICY_NBT = "spicyLevel";
    public static final String SALTY_NBT = "saltyLevel";

    public float sourLevel;
    public float saltyLevel;
    public float spicyLevel;


    public FlavorItemData(float sourLevel,float spicyLevel,float saltyLevel){
        this.sourLevel = sourLevel;
        this.spicyLevel = spicyLevel;
        this.saltyLevel = saltyLevel;
    }

    public static void saveData(ItemStack itemStack,FlavorItemData flavorItemData){
        CompoundTag flavorTag = new CompoundTag();
        flavorTag.putFloat(SOUR_NBT,flavorItemData.sourLevel);
        flavorTag.putFloat(SALTY_NBT,flavorItemData.saltyLevel);
        flavorTag.putFloat(SPICY_NBT,flavorItemData.spicyLevel);

        itemStack.getOrCreateTag().put(FLAVOR_NBT,flavorTag);
    }

    public void loadData(CompoundTag compoundTag){
        CompoundTag flavorTag = compoundTag.getCompound(FLAVOR_NBT);
        this.sourLevel = flavorTag.getInt(SOUR_NBT);
        this.saltyLevel = flavorTag.getInt(SALTY_NBT);
        this.spicyLevel = flavorTag.getInt(SPICY_NBT);
    }

    public float getSourLevel() {
        return sourLevel;
    }

    public float getSpicyLevel() {
        return spicyLevel;
    }

    public float getSaltyLevel() {
        return saltyLevel;
    }

    public static FlavorItemData getFromNBT(ItemStack itemStack){

        return new FlavorItemData(FlavorItemData.getSourLevel(itemStack),FlavorItemData.getSpicyLevel(itemStack),FlavorItemData.getSaltyLevel(itemStack));
    }

    public static float getSaltyLevel(ItemStack itemStack){
        return getLevel(itemStack,SALTY_NBT);
    }

    public static float getSpicyLevel(ItemStack itemStack){
        return getLevel(itemStack,SPICY_NBT);
    }

    public static float getSourLevel(ItemStack itemStack){
        return getLevel(itemStack,SOUR_NBT);
    }

    public static float getLevel(ItemStack itemStack,String s){
        if(FlavorItemData.hasFlavorTag(itemStack)){
            CompoundTag compoundTag = itemStack.getTag();
            return compoundTag.getCompound(FLAVOR_NBT).getFloat(s);
        }
        return 0;
    }

    public static boolean hasFlavorTag(ItemStack itemStack){
        return (itemStack.getTag() != null || itemStack.hasTag()) && itemStack.getTag().contains(FLAVOR_NBT);
    }
}
