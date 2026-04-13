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
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.function.Supplier;

public class ThaiDelightCommonClient {
    public static final List<Supplier<Block>> CUTOUT;

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

    static {
        CUTOUT = List.of(
                ModBlocks.SOMTAM_FEAST,
                ModBlocks.WILD_PEPPER_CROP,
                ModBlocks.PEPPER_CROP,
                ModBlocks.PAPAYA,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.PAPAYA_CROP,
                ModBlocks.CRAB_EGG,
                ModBlocks.LIME_SAPLING,
                ModBlocks.HANGING_DURIAN,
                ModBlocks.SMALL_DURIAN_BLOCK,
                ModBlocks.DURIAN_BLOCK,
                ModBlocks.DURIAN_FLOWER,
                ModBlocks.DURIAN_LEAVES,
                ModBlocks.DURIAN_SAPLING,
                ModBlocks.HANGING_MANGO_BLOCK,
                ModBlocks.LIME_PLANT,
                ModBlocks.MANGO_SAPLING,
                ModBlocks.POTTED_LIME_SAPLING,
                ModBlocks.POTTED_COCONUT_SAPLING,
                ModBlocks.POTTED_DURIAN_SAPLING,
                ModBlocks.POTTED_MANGO_SAPLING,
                ModBlocks.HOLY_BASIL,
                ModBlocks.BASIL,
                ModBlocks.COCONUT_LEAF,
                ModBlocks.COCONUT_LEAF_END,
                ModBlocks.BUTTERFLY_PEA_WALL,
                ModBlocks.BUTTERFLY_PEA_BLOCK,
                ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
                ModBlocks.STACKABLE_MANGO_BLOCK,
                ModBlocks.COCONUT,
                ModBlocks.BUDDING_PEPPER_CROP,
                ModBlocks.BUDDING_PAPAYA_FLOWER,
                ModBlocks.PAPAYA_FLOWER,
                ModBlocks.WALL_PAPAYA_FLOWER,
                ModBlocks.WALL_PAPAYA_LEAVES,
                ModBlocks.PAPAYA_LEAVES,
                ModBlocks.PAPAYA_LEAVES_STEM,
                ModBlocks.DURIAN_DOOR,
                ModBlocks.DURIAN_TRAPDOOR,
                ModBlocks.MANGO_DOOR,
                ModBlocks.MANGO_TRAPDOOR,
                ModBlocks.COCONUT_DOOR,
                ModBlocks.COCONUT_TRAPDOOR,
                ModBlocks.BUDDING_COCONUT_LEAF,
                ModBlocks.PINEAPPLE_FRIED_RICE_FEAST,
                ModBlocks.STRIPPED_COCONUT,
                ModBlocks.COCONUT_SAPLING,
                ModBlocks.WILD_BASIL,
                ModBlocks.WILD_HOLY_BASIL,
                ModBlocks.POTTED_HOLY_BASIL,
                ModBlocks.POTTED_BASIL,
                ModBlocks.LARB_FEAST
        );
    }
}
