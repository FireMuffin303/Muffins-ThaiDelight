package net.firemuffin303.muffinsthaidelightfabric.integration.emi;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.DrawableWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import java.util.List;

public class EMIFermentedFishRecipe implements EmiRecipe {
    private final EmiIngredient ingredient;
    private final EmiStack container;
    private final EmiStack result;
    private final ResourceLocation BACKGROUND = ThaiDelight.modid("textures/gui/jei/fermented_fish_jei.png");

    public EMIFermentedFishRecipe(){
        this.ingredient = EmiIngredient.of(ItemTags.FISHES);
        this.container = EmiStack.of(Items.BOWL);
        this.result = EmiStack.of(ModItems.FERMENTED_FISH);
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ThaiDelightEMI.FERMENTED_FISH;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return ThaiDelight.modid("/fermented_fish");
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
                BlockState blockState = ModBlocks.FERMENTED_FISH_CAULDRON.defaultBlockState().setValue(FermentedFishCauldronBlock.FERMENT,state).setValue(FermentedFishCauldronBlock.LEVEL,3);
                Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                        poseStack.last(),
                        guiGraphics.bufferSource().getBuffer(Sheets.solidBlockSheet()),
                        blockState,
                        Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState),
                        state == 0 ? f : 1,
                        state == 0 ? g : 1,
                        state == 0 ? h : 1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY
                );

                poseStack.popPose();
            }
        });

        widgetHolder.addTooltip((mouseX, mouseY) -> {
            if(ClientRenderUtils.isCursorInsideBounds(60,27,34,29,mouseX,mouseY) ||
                    ClientRenderUtils.isCursorInsideBounds(69,2,17,22,mouseX,mouseY)
            ){
                return ImmutableList.of(createTooltip("cauldron1"),createTooltip("cauldron2"),createTooltip("cauldron3"));
            }
            return List.of();
        }, 0, 0, widgetHolder.getWidth(), widgetHolder.getHeight());
    }

    private static ClientTooltipComponent createTooltip(@NotNull String suffix) {
        return ClientTooltipComponent.create(Component.translatable(ThaiDelight.MOD_ID + ".jei.fermented_fish." + suffix).getVisualOrderText());
    }
}
