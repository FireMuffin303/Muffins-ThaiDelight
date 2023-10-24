package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.mixin.SpawnPlacementMixin;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModEntityTypes {
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPE = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, ThaiDelight.MOD_ID);

    public static final RegistryEntry<EntityType<FlowerCrabEntity>> FLOWER_CRAB = ENTITY_TYPE.register("flower_crab",() -> EntityType.Builder.of(FlowerCrabEntity::new, MobCategory.CREATURE).sized(0.8f,0.5f).build(ThaiDelight.MOD_ID));
    public static final RegistryEntry<EntityType<Dragonfly>> DRAGONFLY = ENTITY_TYPE.register("dragonfly",() -> EntityType.Builder.of(Dragonfly::new, MobCategory.AMBIENT).sized(0.8f,0.5f).build(ThaiDelight.MOD_ID));


    public static void registerAttributes(BiConsumer<Supplier<? extends EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>> attributes){
        attributes.accept(FLOWER_CRAB, FlowerCrabEntity::createAttributes);
        attributes.accept(DRAGONFLY, Dragonfly::createAttributes);


    }

    public static void postInit(){
        SpawnPlacementMixin.invokeRegister(ModEntityTypes.FLOWER_CRAB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacementMixin.invokeRegister(ModEntityTypes.DRAGONFLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
   }


}
