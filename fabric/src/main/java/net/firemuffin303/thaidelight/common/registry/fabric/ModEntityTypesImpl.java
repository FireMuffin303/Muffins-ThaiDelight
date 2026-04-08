package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class ModEntityTypesImpl {
    public static <T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> entityType) {
        EntityType<T> registeredEntity = Registry.register(BuiltInRegistries.ENTITY_TYPE, ThaiDelightCommon.modid(id),entityType.build(id));
        return () -> registeredEntity;
    }
}
