package net.firemuffin303.thaidelight.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import java.util.List;

public class FermentedFishRecipeCategory implements IRecipeCategory<FermentedFishRecipeCategory.FermentedFishDummy> {
    private final ResourceLocation CONTAINER_LOCATION = ThaiDelightCommon.modid("textures/gui/jei/fermented_fish_jei.png");
    private final IDrawable icon;
    private final IDrawable background;
    private final IDrawable time_icon;
    private final IDrawable slot;

    public FermentedFishRecipeCategory(IGuiHelper iGuiHelper) {
        this.icon = iGuiHelper.createDrawableItemStack(new ItemStack(Items.CAULDRON));
        this.background = iGuiHelper.createDrawable(CONTAINER_LOCATION,0,0,154,65);
        this.time_icon = iGuiHelper.createDrawable(CONTAINER_LOCATION,154,0,17,22);
        this.slot = iGuiHelper.createDrawable(CONTAINER_LOCATION,154,22,18,18);
    }

    @Override
    public RecipeType<FermentedFishDummy> getRecipeType() {
        return ThaiDelightJEIIntegration.FERMENTED_FISH;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.muffins_thaidelight.cauldron_crafting");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FermentedFishDummy recipe, IFocusGroup focuses) {
        if(!recipe.extraIngredient().isEmpty()){
            builder.addSlot(RecipeIngredientRole.INPUT,68,4).addItemStack(recipe.extraIngredient());
        }

        builder.addSlot(RecipeIngredientRole.INPUT,22,33).addIngredients(Ingredient.of(recipe.ingredient));
        builder.addSlot(RecipeIngredientRole.INPUT,98,14).addItemStack(new ItemStack(recipe.container));
        builder.addSlot(RecipeIngredientRole.OUTPUT,123,33).addItemStack(new ItemStack(recipe.result));
    }

    @Override
    public void draw(FermentedFishDummy recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.slot.draw(guiGraphics,97,13);

        recipe.renderExtra(guiGraphics,this.time_icon,this.slot);
        recipe.cauldronRender.draw(guiGraphics);
    }

    @Override
    public List<Component> getTooltipStrings(FermentedFishDummy recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if(ClientRenderUtils.isCursorInsideBounds(60,27,34,29,mouseX,mouseY)){
            return recipe.tooltip();
        }

        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    public static MutableComponent translateKey(@NotNull String suffix) {
        return Component.translatable( ThaiDelightCommon.MOD_ID+ ".jei.cauldron." + suffix);
    }

    public static class FermentedFishDummy{
        public TagKey<Item> ingredient;
        public ItemLike container;
        public ItemLike result;
        public IDrawable cauldronRender;

        public FermentedFishDummy(TagKey<Item> ingredient, ItemLike container, ItemLike result,IDrawable cauldronRendering){
            this.ingredient = ingredient;
            this.container = container;
            this.result = result;
            this.cauldronRender = cauldronRendering;
        }

         public void renderExtra(GuiGraphics guiGraphics,IDrawable timeIcon,IDrawable slot){

         }

         public ItemStack extraIngredient(){
            return ItemStack.EMPTY;
         }

         public List<Component> tooltip(){
            return List.of();
         }

    }

    public static class FermentedFishCauldronDrawable implements IDrawableAnimated{
        public ITickTimer timer = new ITickTimer() {
            private final long startTime = System.currentTimeMillis();

            @Override
            public int getValue() {
                long currentTime = System.currentTimeMillis();
                return getValue(startTime, currentTime, 2, 200*50, false);
            }

            @Override
            public int getMaxValue() {
                return 2;
            }

            public static int getValue(long startTime, long currentTime, int maxValue, int msPerCycle, boolean countDown) {
                long msPassed = (currentTime - startTime) % msPerCycle;
                int value = (int) Math.floorDiv(msPassed * (maxValue + 1), msPerCycle);
                if (countDown) {
                    return maxValue - value;
                } else {
                    return value;
                }
            }
        };

        @Override
        public int getWidth() {
            return 34;
        }

        @Override
        public int getHeight() {
            return 29;
        }

        @Override
        public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
            int animationValue = this.timer.getValue();

            int k = 4159204;
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;

            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();


            poseStack.scale(20f,20f,-20f);
            poseStack.translate(3.08f,0.85,-10);
            poseStack.rotateAround(new Quaternionf().rotateXYZ(0.33633232F, -2.7F, 3.1415927F),1,1,1);
            BlockState blockState = ModBlocks.FERMENTED_FISH_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.FERMENT,animationValue).setValue(FermentedFishCauldronBlock.LEVEL,3);
            Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                    guiGraphics.pose().last(),
                    guiGraphics.bufferSource().getBuffer(Sheets.solidBlockSheet()),
                    blockState,
                    Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState),
                    animationValue == 0 ? f : 1,
                    animationValue == 0 ? g : 1,
                    animationValue == 0 ? h : 1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY
            );

            poseStack.popPose();
        }
    }

    public static class CoconutCauldronDrawable implements IDrawable{

        @Override
        public int getWidth() {
            return 34;
        }

        @Override
        public int getHeight() {
            return 29;
        }

        @Override
        public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();


            poseStack.scale(20f,20f,-20f);
            poseStack.translate(3.08f,0.85,-10);
            poseStack.rotateAround(new Quaternionf().rotateXYZ(0.33633232F, -2.7F, 3.1415927F),1,1,1);
            BlockState blockState = ModBlocks.COCONUT_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.LEVEL,3);
            Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                    guiGraphics.pose().last(),
                    guiGraphics.bufferSource().getBuffer(Sheets.solidBlockSheet()),
                    blockState,
                    Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState),1,1, 1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY
            );

            poseStack.popPose();
        }
    }
}
