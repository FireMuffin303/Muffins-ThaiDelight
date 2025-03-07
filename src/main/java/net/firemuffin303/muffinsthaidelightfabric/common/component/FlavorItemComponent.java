package net.firemuffin303.muffinsthaidelightfabric.common.component;

import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.minecraft.world.item.ItemStack;

public class FlavorItemComponent extends ItemComponent {
    private ItemStack itemStack;
    public static final int FLAVOR_MAX_LEVEL = 3;

    public FlavorItemComponent(ItemStack stack) {
        super(stack);

    }

    public void setSpicyLevel(int level){
        this.putInt("spicyLevel",level);
    }

    public void setSourLevel(int level){
        this.putInt("sourLevel",level);
    }

    public void setSaltyLevel(int level){
        this.putInt("saltyLevel",level);
    }

    public void setSweetLevel(int level){
        this.putInt("sweetLevel",level);
    }

    public int getSpicyLevel() {
        return this.getInt("spicyLevel");
    }

    public int getSourLevel(){
        return this.getInt("sourLevel");
    }

    public int getSaltyLevel(){
        return this.getInt("saltyLevel");
    }

    public int getSweetLevel(){
        return this.getInt("sweetLevel");
    }
}
