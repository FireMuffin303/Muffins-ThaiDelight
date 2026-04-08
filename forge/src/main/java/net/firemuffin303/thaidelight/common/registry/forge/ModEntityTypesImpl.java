package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntityTypesImpl {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ThaiDelightCommon.MOD_ID);

    public static <T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> entityType) {
       return ENTITY_TYPES.register(id,() -> entityType.build(id));
    }
}
