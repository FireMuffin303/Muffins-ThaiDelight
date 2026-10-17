package net.firemuffin303.thaidelight.integration.emi;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.DrawableWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import java.util.List;

public class EMIFermentedFishRecipe implements EmiRecipe {
    private final EmiIngredient ingredient;
    private final EmiStack container;
    private final EmiStack result;
    private final ResourceLocation BACKGROUND = ThaiDelightCommon.modid("textures/gui/jei/fermented_fish_jei.png");
    private final TransformMode transformMode;

    private final BlockState blockState;

    public EMIFermentedFishRecipe(TagKey<Item> tagKey,int requiredAmount,Item container,Item result,TransformMode transformMode){
        this.ingredient = EmiIngredient.of(tagKey,requiredAmount);
        this.container = EmiStack.of(container);
        this.result = EmiStack.of(result);
        this.transformMode = transformMode;

        //I know this is stupid. but it works.
        if(this.transformMode == TransformMode.WAIT){
            this.blockState = ModBlocks.FERMENTED_FISH_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.LEVEL,3);
        } else {
            this.blockState = ModBlocks.COCONUT_CAULDRON.get().defaultBlockState();
        }

    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ThaiDelightEMI.FERMENTED_FISH;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return ThaiDelightCommon.modid("/%s_cauldron".formatted(this.result.getId().getPath()));
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(this.ingredient,this.container);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(this.result);
    }

    @Override
    public int getDisplayWidth() {
        return 154;
    }

    @Override
    public int getDisplayHeight() {
        return 65;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTexture(BACKGROUND,1,1,154,65,256,256);
        widgetHolder.addTexture(BACKGROUND,98,14,18,18,154,22);

        if(transformMode == TransformMode.WAIT){
            widgetHolder.addTexture(BACKGROUND,70,2,17,22,154,0);
        }else if(transformMode == TransformMode.WATER){
            widgetHolder.addTexture(BACKGROUND,69,4,18,18,154,22);
            widgetHolder.addSlot(EmiStack.of(Items.WATER_BUCKET),69,4).drawBack(false);
        }

        widgetHolder.addSlot(this.ingredient,22,33).drawBack(false);
        widgetHolder.addSlot(this.container,98,14).drawBack(false);
        widgetHolder.addSlot(this.result,123,33).drawBack(false).recipeContext(this);

        widgetHolder.addDrawable(0, 0, 34, 29, new DrawableWidget.DrawableWidgetConsumer() {
            float time = 0;
            int state = 0;

            @Override
            public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
                if(time < 60f){
                    time += delta;
                    if(time >= 60f){
                        if(state >= 2f){
                            state = 0;
                        }else{
                            state += 1;
                        }

                        time = 0f;
                    }
                }

                int k = 4159204;
                float f = (float)(k >> 16 & 0xFF) / 255.0f;
                float g = (float)(k >> 8 & 0xFF) / 255.0f;
                float h = (float)(k & 0xFF) / 255.0f;

                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();


                poseStack.scale(20f,20f,-20f);
                poseStack.translate(3.1f,0.9,-3);
                poseStack.rotateAround(new Quaternionf().rotateXYZ(0.33633232F, -2.7F, 3.1415927F),1,1,1);
                BlockState blockState;

                if(transformMode == TransformMode.WAIT){
                    blockState = EMIFermentedFishRecipe.this.blockState.setValue(FermentedFishCauldronBlock.FERMENT,state);
                }else {
                    blockState = EMIFermentedFishRecipe.this.blockState.setValue(LayeredCauldronBlock.LEVEL,3);
                }

                Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                        poseStack.last(),
                        guiGraphics.bufferSource().getBuffer(Sheets.solidBlockSheet()),
                        blockState,
                        Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState),
                        state == 0 && transformMode == TransformMode.WAIT ? f : 1,
                        state == 0 && transformMode == TransformMode.WAIT ? g : 1,
                        state == 0 && transformMode == TransformMode.WAIT ? h : 1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY
                );

                poseStack.popPose();
            }
        });

        widgetHolder.addTooltip((mouseX, mouseY) -> {
            if(ClientRenderUtils.isCursorInsideBounds(60,27,34,29,mouseX,mouseY)){
                return EMIFermentedFishRecipe.this.transformMode == TransformMode.WAIT ?
                        ImmutableList.of(createTooltip("fermented_fish1"),createTooltip("fermented_fish2"),createTooltip("fermented_fish3")) :
                        ImmutableList.of(createTooltip(this.result.getId().getPath()));
            }
            return List.of();
        }, 0, 0, widgetHolder.getWidth(), widgetHolder.getHeight());
    }

    private static ClientTooltipComponent createTooltip(@NotNull String suffix) {
        return ClientTooltipComponent.create(Component.translatable(ThaiDelightCommon.MOD_ID + ".jei.cauldron." + suffix).getVisualOrderText());
    }

    public static enum TransformMode{
        WAIT,
        WATER
    }
}
