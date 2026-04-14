package net.firemuffin303.thaidelight.integration.rei;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.DrawableConsumer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.integration.rei.category.CauldronREICategory;
import net.firemuffin303.thaidelight.integration.rei.category.MortarREICategory;
import net.firemuffin303.thaidelight.integration.rei.display.CauldronCraftingREIDisplay;
import net.firemuffin303.thaidelight.integration.rei.display.MortarREIDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.List;

public class ThaiDelightClientREI implements REIClientPlugin {
    public static final CategoryIdentifier<MortarREIDisplay> MORTAR_ID = CategoryIdentifier.of(ThaiDelightCommon.modid("plugin/mortar"));
    public static final CategoryIdentifier<CauldronCraftingREIDisplay> CAULDRON_ID = CategoryIdentifier.of(ThaiDelightCommon.modid("plugin/cauldron"));

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(MortarRecipe.class, ModRecipes.MORTAR.get(),MortarREIDisplay::new);
        registry.add(new CauldronCraftingREIDisplay(
                List.of(EntryIngredients.ofItemTag(ModTags.COMMON_RAW_FISHES)),
                List.of(EntryIngredients.of(ModItems.FERMENTED_FISH.get())),
                EntryIngredients.of(new ItemStack(Items.BOWL)),
                ImmutableList.of(translateKey("fermented_fish1"),translateKey("fermented_fish2"),translateKey("fermented_fish3"))
                ){
                         @Override
                         public List<Widget> renderExtra(List<Widget> widgets, Point origin, Point startPoint) {
                             widgets.add(Widgets.createTexturedWidget(CauldronREICategory.BACKGROUND,startPoint.x+62,startPoint.y+2,154f,0f,17,22));


                            widgets.add(Widgets.withTranslate(Widgets.createDrawableWidget(new DrawableConsumer() {
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

                                    renderFermentedCauldron(guiGraphics,startPoint,state);
                                }
                            }),origin.x + 63,origin.y + 20,10));
                            return widgets;
                         }
                }
        );


        registry.add(new CauldronCraftingREIDisplay(
                List.of(EntryIngredients.ofItemTag(ModTags.COCONUT)),
                List.of(EntryIngredients.of(ModItems.COCONUT_MILK_BOTTLE.get())),
                EntryIngredients.of(new ItemStack(Items.GLASS_BOTTLE)),
                List.of(translateKey("coconut_milk_bottle"))
                ){
                         @Override
                         public List<Widget>  renderExtra(List<Widget> widgets, Point origin, Point startPoint) {
                             widgets.add(Widgets.createSlot(new Point(startPoint.x+63,startPoint.y+6)).entries(EntryIngredients.of(Items.WATER_BUCKET)));
                             widgets.add(Widgets.withTranslate(Widgets.createDrawableWidget(new DrawableConsumer() {
                                 @Override
                                 public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
                                     renderCoconutCauldron(graphics,mouseX,mouseY,delta,startPoint);
                                 }
                             }),origin.x + 63,origin.y + 20,10));
                             //I SPENT 3 HOURS BECAUSE I HAVE TO PUT Z AS 10????? WTF I THOUGHT TRANSLATE IS GOOD ENOUGH.
                             return widgets;
                         }
                     }
        );
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new MortarREICategory());
        registry.add(new CauldronREICategory());
        registry.addWorkstations(MORTAR_ID, EntryIngredient.of(EntryStacks.of(ModItems.MORTAR.get())));
        registry.addWorkstations(CAULDRON_ID,EntryIngredient.of(EntryStacks.of(Items.CAULDRON)));
    }

    public static MutableComponent translateKey(@NotNull String suffix) {
        return Component.translatable( ThaiDelightCommon.MOD_ID+ ".jei.cauldron." + suffix);
    }

    public static void renderCoconutCauldron(GuiGraphics graphics, int mouseX, int mouseY, float delta,Point startPoint){
        PoseStack poseStack = graphics.pose();

        poseStack.pushPose();
        poseStack.scale(20f,20f,-20f);
        poseStack.translate(0f,0f,-3);
        poseStack.rotateAround(new Quaternionf().rotateXYZ(0.33633232F, -2.7F, 3.1415927F),1,1,1);
        BlockState blockState = ModBlocks.COCONUT_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL,3);
        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                graphics.bufferSource().getBuffer(Sheets.solidBlockSheet()),
                blockState,
                Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState),
                1, 1, 1, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY
        );
        //LogUtils.getLogger().info("{}",poseStack.last().pose());

        poseStack.popPose();

    }

    private static void renderFermentedCauldron(GuiGraphics guiGraphics,Point startPoint,int state){
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        int k = 4159204;
        float f = (float)(k >> 16 & 0xFF) / 255.0f;
        float g = (float)(k >> 8 & 0xFF) / 255.0f;
        float h = (float)(k & 0xFF) / 255.0f;

        poseStack.scale(20f,20f,-20f);
        poseStack.translate(0f,0f,-3);
        poseStack.rotateAround(new Quaternionf().rotateXYZ(0.33633232F, -2.7F, 3.1415927F),1,1,1);

        BlockState blockState = ModBlocks.FERMENTED_FISH_CAULDRON.get().defaultBlockState().setValue(FermentedFishCauldronBlock.FERMENT,state).setValue(FermentedFishCauldronBlock.LEVEL,3);

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
}
