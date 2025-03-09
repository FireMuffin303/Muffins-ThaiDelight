package net.firemuffin303.muffinsthaidelightfabric.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.common.component.SpicyComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public class ModComponents implements EntityComponentInitializer, ItemComponentInitializer {
    public static final ComponentKey<SpicyComponent> SPICY = ComponentRegistry.getOrCreate(ThaiDelight.modid("spicy"), SpicyComponent.class);
    public static final ComponentKey<FlavorItemComponent> FLAVOR = ComponentRegistry.getOrCreate(ThaiDelight.modid("flavor"), FlavorItemComponent.class);

    public static void init(){

    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(LivingEntity.class,SPICY,SpicyComponent::new);
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry itemComponentFactoryRegistry) {
        itemComponentFactoryRegistry.register(item -> item.isEdible() || item == ModItems.SAUCE_BOWL,FLAVOR,FlavorItemComponent::new);
    }
}
