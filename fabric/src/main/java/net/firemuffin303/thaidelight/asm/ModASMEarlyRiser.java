package net.firemuffin303.thaidelight.asm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

public class ModASMEarlyRiser implements Runnable{
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String armPose = remapper.mapClassName("intermediary", "net.minecraft.class_572$class_573");
        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_SWING",true).build();
        ClassTinkerers.enumBuilder(armPose,boolean.class).addEnum("SACK_SHOULDER_HOLD",false).build();
        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_HOLD",true).build();

        String recipeBookType = remapper.mapClassName("intermediary", "net.minecraft.class_5421");
        ClassTinkerers.enumBuilder(recipeBookType).addEnum("MORTAR_RECIPE_BOOK_TYPE").build();

        String recipeBookCategory = remapper.mapClassName("intermediary", "net.minecraft.class_314");
        String itemstack = "[L" + remapper.mapClassName("intermediary", "net.minecraft.class_1799") + ';';
        ClassTinkerers.enumBuilder(recipeBookCategory, itemstack).addEnum("MORTAR_SEARCH", () -> new Object[]{ new ItemStack[]{new ItemStack(Items.COMPASS)}}).build();
        ClassTinkerers.enumBuilder(recipeBookCategory,itemstack).addEnum("MORTAR_MEALS", () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.SOMTAM_FEAST.get())}}).build();
        ClassTinkerers.enumBuilder(recipeBookCategory,itemstack).addEnum("MORTAR_MISC", () -> new Object[]{new ItemStack[]{new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)}}).build();

        String useAnim = remapper.mapClassName("intermediary","net.minecraft.class_1839");
        ClassTinkerers.enumBuilder(useAnim).addEnum("CATCHING_BAG").build();
    }

    public static UseAnim getDurianCatcherUseAnim(){
        return ClassTinkerers.getEnum(UseAnim.class,"CATCHING_BAG");
    }

    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose(){
        return ClassTinkerers.getEnum(HumanoidModel.ArmPose.class,"CATCHING_BAG_HOLD");
    }

    public static HumanoidModel.ArmPose getSackShoulderArmPose(){
        return ClassTinkerers.getEnum(HumanoidModel.ArmPose.class,"SACK_SHOULDER_HOLD");
    }

    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose(){
        return ClassTinkerers.getEnum(HumanoidModel.ArmPose.class,"CATCHING_BAG_SWING");
    }

    public static RecipeBookType getMortarBookType(){
        return ClassTinkerers.getEnum(RecipeBookType.class,"MORTAR_RECIPE_BOOK_TYPE");
    }

    public static RecipeBookCategories getMortarSearchCategory(){
        return ClassTinkerers.getEnum(RecipeBookCategories.class,"MORTAR_SEARCH");
    }

    public static RecipeBookCategories getMortarMealsCategory(){
        return ClassTinkerers.getEnum(RecipeBookCategories.class,"MORTAR_MEALS");
    }

    public static RecipeBookCategories getMortarMiscCategory(){
        return ClassTinkerers.getEnum(RecipeBookCategories.class,"MORTAR_MISC");
    }
}
