package net.firemuffin303.thaidelight.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.cardinalcomponents.SpicyComponent;
import net.minecraft.world.entity.LivingEntity;

public class ModCardinalComponents implements EntityComponentInitializer {
    public static final ComponentKey<DurianHeatComponent> DURIAN_HEAT = ComponentRegistry.getOrCreate(ThaiDelightCommon.modid("durian_heat"), DurianHeatComponent.class);
    public static final ComponentKey<SpicyComponent> SPICY_HEAT = ComponentRegistry.getOrCreate(ThaiDelightCommon.modid("spicy"), SpicyComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(LivingEntity.class,DURIAN_HEAT, DurianHeatComponent::new);
        entityComponentFactoryRegistry.registerFor(LivingEntity.class,SPICY_HEAT,SpicyComponent::new);
    }
}
