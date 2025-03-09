package net.firemuffin303.muffinsthaidelightfabric.common.item;

import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class SauceBowlItem extends BlockItem {
    public SauceBowlItem(Properties properties) {
        super(ModBlocks.SAUCE_BOWL,properties.stacksTo(1));
    }

    public static ItemStack createSauceBowl(int sour,int spicy,int salt,int sweet){
        ItemStack itemStack = new ItemStack(ModItems.SAUCE_BOWL);
        FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(itemStack);
        flavorItemComponent.setSourLevel(sour);
        flavorItemComponent.setSpicyLevel(spicy);
        flavorItemComponent.setSaltyLevel(salt);
        flavorItemComponent.setSweetLevel(sweet);
        return itemStack;
    }
}
