package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final ResourceRegistry<EntityType<?>> ENTITY_TYPE = ResourceRegistry.create(Registries.ENTITY_TYPE, ThaiDelightCommon.MOD_ID);
    public static final Supplier<EntityType<?>> FLOWER_CRAB = ENTITY_TYPE.register("flower_crab",() ->EntityType.Builder.of(FlowerCrabEntity::new, MobCategory.CREATURE).sized(0.8f,0.5f).build("flower_crab"));
    public static final Supplier<EntityType<?>> DRAGONFLY = ENTITY_TYPE.register("dragonfly",() -> EntityType.Builder.of(DragonflyEntity::new, MobCategory.AMBIENT).sized(0.8f,0.6f).build("flower_crab"));
    //public static final EntityType<BuffaloEntity> BUFFALO = register("buffalo",EntityType.Builder.of(BuffaloEntity::new, MobCategory.CREATURE).sized(0.9f,1.4f).build(ThaiDelight.MOD_ID));

    @ExpectPlatform
    public static <T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder <T> entityType){
        throw new AssertionError();
    }

    public static void init() {
        ENTITY_TYPE.init();
    }

    public static void registerAttribute(EntityAttributeSupplier entityAttributeSupplier){
        entityAttributeSupplier.register((EntityType<? extends LivingEntity>) FLOWER_CRAB.get(),FlowerCrabEntity.createAttributes());
        entityAttributeSupplier.register((EntityType<? extends LivingEntity>) DRAGONFLY.get(),DragonflyEntity.createAttributes());
    }

    @FunctionalInterface
    public interface EntityAttributeSupplier{
        void register(EntityType<? extends LivingEntity> entity, AttributeSupplier.Builder builder);
    }

}
