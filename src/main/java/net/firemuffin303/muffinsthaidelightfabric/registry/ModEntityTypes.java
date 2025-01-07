package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.BuffaloEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<FlowerCrabEntity> FLOWER_CRAB = register("flower_crab",EntityType.Builder.of(FlowerCrabEntity::new, MobCategory.CREATURE).sized(0.8f,0.5f).build(ThaiDelight.MOD_ID));
    public static final EntityType<DragonflyEntity> DRAGONFLY = register("dragonfly",EntityType.Builder.of(DragonflyEntity::new, MobCategory.AMBIENT).sized(0.8f,0.6f).build(ThaiDelight.MOD_ID));
    public static final EntityType<BuffaloEntity> BUFFALO = register("buffalo",EntityType.Builder.of(BuffaloEntity::new, MobCategory.CREATURE).sized(0.9f,1.4f).build(ThaiDelight.MOD_ID));

    public static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType){
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,id),entityType);
    }

    public static void init() {
    }
}
