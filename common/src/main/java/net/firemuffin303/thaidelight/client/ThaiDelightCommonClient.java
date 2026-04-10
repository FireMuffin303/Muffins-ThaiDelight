package net.firemuffin303.thaidelight.client;

import net.firemuffin303.muffinsmcapi.api.CustomEffectRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.model.armor.DurianHelmetModel;
import net.firemuffin303.thaidelight.client.model.entity.DragonflyModel;
import net.firemuffin303.thaidelight.client.model.entity.FlowerCrabModel;
import net.firemuffin303.thaidelight.client.renderer.CrabRenderer;
import net.firemuffin303.thaidelight.client.renderer.DragonflyRenderer;
import net.firemuffin303.thaidelight.client.renderer.blocks.SackBlockEntityRenderer;
import net.firemuffin303.thaidelight.client.renderer.customEffectRender.DurianHeatEffectRenderer;
import net.firemuffin303.thaidelight.client.renderer.customEffectRender.SpicyEffectRenderer;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ThaiDelightCommonClient {
    public static final ModelResourceLocation SACK_MODEL_IN_HAND = new ModelResourceLocation(ThaiDelightCommon.MOD_ID,"sack_in_hand","inventory");
    public static final ModelResourceLocation FULL_SACK_MODEL_IN_HAND = new ModelResourceLocation(ThaiDelightCommon.MOD_ID,"full_sack_in_hand","inventory");
    public static final ModelResourceLocation SACK_MODEL = new ModelResourceLocation(ThaiDelightCommon.MOD_ID,"sack","inventory");
    public static final ModelResourceLocation FILLED_SACK_MODEL = new ModelResourceLocation(ThaiDelightCommon.MOD_ID,"filled_sack","inventory");


    public static void registerCustomEffectRenderer(){
        CustomEffectRegistry.register(new DurianHeatEffectRenderer());
        CustomEffectRegistry.register(new SpicyEffectRenderer());
    }


    @SuppressWarnings("unchecked")
    public static void entityRendererRegister(EntityRendererSupplier entityRendererSupplier){
        entityRendererSupplier.create(ModEntityTypes.FLOWER_CRAB.get(), CrabRenderer::new);
        entityRendererSupplier.create(ModEntityTypes.DRAGONFLY.get(),DragonflyRenderer::new);
    }

    public static void entityModelRegister(EntityModelLayerRegister entityModelLayerRegister){
        entityModelLayerRegister.register(FlowerCrabModel.LAYER,FlowerCrabModel::createBodyLayer);
        entityModelLayerRegister.register(DragonflyModel.LAYER,DragonflyModel::createBodyLayer);
        entityModelLayerRegister.register(DurianHelmetModel.DURIAN_HELMET,DurianHelmetModel::createLayer);
    }

    @SuppressWarnings("unchecked")
    public static void blockEntityRenderRegister(BlockEntityRegister blockEntityRegister){
        blockEntityRegister.register(ModBlockEntityTypes.SACK_BLOCK_ENTITY.get(), SackBlockEntityRenderer::new);
    }

    @FunctionalInterface
    public interface EntityRendererSupplier<T extends Entity>{
        void create(EntityType<? extends Entity> entityType, EntityRendererProvider<T> entityRendererProvider);
    }

    @FunctionalInterface
    public interface EntityModelLayerRegister{
        void register(ModelLayerLocation modelLayer, Supplier<LayerDefinition> provider);
    }

    @FunctionalInterface
    public interface BlockEntityRegister<T extends BlockEntity>{
        void register(BlockEntityType<? extends BlockEntity> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider);
    }
}
