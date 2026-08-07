package net.firemuffin303.thaidelight.asm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

public class ModASMEarlyRiser implements Runnable{
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String armPose = remapper.mapClassName("intermediary", "net.minecraft.class_572$class_573");
        String recipeBookType = remapper.mapClassName("intermediary", "net.minecraft.class_5421");
        String recipeBookCategory = remapper.mapClassName("intermediary", "net.minecraft.class_7800");

        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_SWING",true).build();
        ClassTinkerers.enumBuilder(armPose,boolean.class).addEnum("SACK_SHOULDER_HOLD",false).build();
        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_HOLD",true).build();

        /*
        ClassTinkerers.enumBuilder(recipeBookType).addEnum("MUFFINS_THAIDELIGHT_MORTAR_RECIPE_BOOK_TYPE").build();
        ClassTinkerers.enumBuilder(recipeBookCategory, ItemStack[].class).addEnum("MUFFINS_THAIDELIGHT_MORTAR_SEARCH",() -> new Object[]{new ItemStack(Items.COMPASS)}).build();
        ClassTinkerers.enumBuilder(recipeBookCategory, ItemStack[].class).addEnum("MUFFINS_THAIDELIGHT_MORTAR_MEALS",
                () -> new Object[]{new ItemStack(ModItems.SOMTAM_FEAST.get())}).build();
        ClassTinkerers.enumBuilder(recipeBookCategory, ItemStack[].class).addEnum("MUFFINS_THAIDELIGHT_MORTAR_MISC",
                () -> new Object[]{new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)}).build();
         */

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
}
