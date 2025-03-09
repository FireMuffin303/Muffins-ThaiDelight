package net.firemuffin303.muffinsthaidelightfabric.common.component;

import com.mojang.logging.LogUtils;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class FlavorItemComponent extends ItemComponent {
    private ItemStack itemStack;
    public static final int FLAVOR_MAX_LEVEL = 3;

    public FlavorItemComponent(ItemStack stack) {
        super(stack);
    }

    public void clear(){
        this.putList("flavor",new ListTag());
    }

    public boolean isFlavored(){
        int i = 0;
        for(Tag tag : this.getList("flavor",NbtType.COMPOUND)){
            if(tag instanceof CompoundTag compoundTag){
                i += compoundTag.getInt("level");
            }
        }
        return i > 0;
    }

    public CompoundTag createFlavorCompound(String id, int level){
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("id",id);
        compoundTag.putInt("level",level);
        return compoundTag;
    }


    public void setSpicyLevel(int level){
        ListTag listTag = this.getList("flavor",NbtType.COMPOUND);
        listTag.removeIf(tag -> {
            if(tag instanceof CompoundTag compoundTag){
                return compoundTag.getString("id").equals("spicy");
            }
            return false;
        });

        listTag.add(createFlavorCompound("spicy",level));
        this.putList("flavor",listTag);
    }

    public void setSourLevel(int level){
        ListTag listTag = this.getList("flavor",NbtType.COMPOUND);
        listTag.removeIf(tag -> {
            if(tag instanceof CompoundTag compoundTag){
                return compoundTag.getString("id").equals("sour");
            }
            return false;
        });

        listTag.add(createFlavorCompound("sour",level));
        this.putList("flavor",listTag);
    }

    public void setSaltyLevel(int level){
        ListTag listTag = this.getList("flavor",NbtType.COMPOUND);
        listTag.removeIf(tag -> {
            if(tag instanceof CompoundTag compoundTag){
                return compoundTag.getString("id").equals("salt");
            }
            return false;
        });

        listTag.add(createFlavorCompound("salt",level));
        this.putList("flavor",listTag);
    }

    public void setSweetLevel(int level){
        ListTag listTag = this.getList("flavor",NbtType.COMPOUND);
        listTag.removeIf(tag -> {
            if(tag instanceof CompoundTag compoundTag){
                return compoundTag.getString("id").equals("sweet");
            }
            return false;
        });

        listTag.add(createFlavorCompound("sweet",level));
        this.putList("flavor",listTag);
    }

    public int getSpicyLevel() {
        for (Tag tag : this.getList("flavor", NbtType.COMPOUND)) {
            if (tag instanceof CompoundTag c && c.getString("id").equals("spicy")) {
                return c.getInt("level");
            }
        }
        return 0;
    }

    public int getSourLevel(){
        for (Tag tag : this.getList("flavor", NbtType.COMPOUND)) {
            if (tag instanceof CompoundTag c && c.getString("id").equals("sour")) {
                return c.getInt("level");
            }
        }
        return 0;
    }

    public int getSaltyLevel(){
        for (Tag tag : this.getList("flavor", NbtType.COMPOUND)) {
            if (tag instanceof CompoundTag c && c.getString("id").equals("salt")) {
                return c.getInt("level");
            }
        }
        return 0;
    }

    public int getSweetLevel(){
        for (Tag tag : this.getList("flavor", NbtType.COMPOUND)) {
            if (tag instanceof CompoundTag c && c.getString("id").equals("sweet")) {
                return c.getInt("level");
            }
        }
        return 0;
    }

    public ListTag getFlavor() {
        return this.getList("flavor",NbtType.COMPOUND);
    }
}
