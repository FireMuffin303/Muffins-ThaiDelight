package net.firemuffin303.muffinsthaidelightfabric.common.block.entity;

import net.fabricmc.fabric.api.util.NbtType;
import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SauceBowlBlockEntity extends BlockEntity {
    private int sour =0;
    private int spicy =0;
    private int salt =0;
    private int sweet =0;

    public SauceBowlBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.SAUCE_BOWL_BLOCK_ENTITY, blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        ListTag listTag = new ListTag();
        //Sour
        CompoundTag sourTag = new CompoundTag();
        sourTag.putString("id","sour");
        sourTag.putInt("level",this.sour);
        listTag.add(sourTag);

        CompoundTag spicyTag = new CompoundTag();
        spicyTag.putString("id","spicy");
        spicyTag.putInt("level",this.spicy);
        listTag.add(spicyTag);

        CompoundTag saltTag = new CompoundTag();
        saltTag.putString("id","salt");
        saltTag.putInt("level",this.salt);
        listTag.add(saltTag);

        CompoundTag sweetTag = new CompoundTag();
        sweetTag.putString("id","sweet");
        sweetTag.putInt("level",this.sweet);
        listTag.add(sweetTag);

        compoundTag.put("flavor",listTag);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        ListTag listTag = compoundTag.getList("flavor", NbtType.COMPOUND);
        for(int i = 0; i < listTag.size();i++){
            CompoundTag tag = listTag.getCompound(i);
            int level = tag.getInt("level");
            switch(tag.getString("id")){
                case "sour" -> this.sour = level;
                case "spicy" -> this.spicy = level;
                case "salt" -> this.salt = level;
                case "sweet" -> this.sweet = level;
            }

        }
    }

    public int getSour() {
        return sour;
    }

    public int getSpicy() {
        return spicy;
    }

    public int getSalt() {
        return salt;
    }

    public int getSweet() {
        return sweet;
    }

    public void fromItem(ItemStack itemStack){
        FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(itemStack);
        this.sour = flavorItemComponent.getSourLevel();
        this.spicy = flavorItemComponent.getSpicyLevel();
        this.salt = flavorItemComponent.getSaltyLevel();
        this.sweet = flavorItemComponent.getSweetLevel();
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
}
