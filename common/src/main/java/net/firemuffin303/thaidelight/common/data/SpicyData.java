package net.firemuffin303.thaidelight.common.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class SpicyData {
    private int spicyLevel = 20;

    public SpicyData(){}

    private void add(int amount){
        this.spicyLevel = Mth.clamp(amount + this.spicyLevel,0,20);
    }

    //TODO : FREEZE GOES DOWN FASTER, MORE FIRE DAMAGE
    public void tick(Player player){

    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.contains("spicyLevel", 99)) {
            this.spicyLevel = compoundTag.getInt("spicyLevel");
        }

    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("spicyLevel", this.spicyLevel);
    }

    public int getSpicyLevel() {
        return this.spicyLevel;
    }
}
