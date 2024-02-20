package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.mixin.SpawnPlacementMixin;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModEntityTypes {

    public static final ArrayList<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

    public static final EntityType<FlowerCrabEntity> FLOWER_CRAB = EntityType.Builder.of(FlowerCrabEntity::new, MobCategory.CREATURE).sized(0.8f,0.5f).build(ThaiDelight.MOD_ID);
    public static final EntityType<Dragonfly> DRAGONFLY = EntityType.Builder.of(Dragonfly::new, MobCategory.AMBIENT).sized(0.8f,0.6f).build(ThaiDelight.MOD_ID);


    public static void init(){
        register("flower_crab",FLOWER_CRAB);
        register("dragonfly",DRAGONFLY);
    }

    private static <T extends Entity> void register(String id,EntityType<T> entityType){
        ModPlatform.registerEntityType(id,entityType);
        ENTITY_TYPES.add(entityType);
    }

    public static void registerAttributes(BiConsumer<Supplier<? extends EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>> attributes){
        attributes.accept(() -> FLOWER_CRAB, FlowerCrabEntity::createAttributes);
        attributes.accept(() -> DRAGONFLY, Dragonfly::createAttributes);


    }

    public static void postInit(){
        SpawnPlacementMixin.invokeRegister(ModEntityTypes.FLOWER_CRAB, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacementMixin.invokeRegister(ModEntityTypes.DRAGONFLY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
   }


}
