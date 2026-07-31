package net.firemuffin303.thaidelight.neoforge;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

import java.util.List;
import java.util.function.Supplier;

public class ModEnums {
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_HOLD = new EnumProxy<>(HumanoidModel.ArmPose.class, true, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_SHOULDER_HOLD = new EnumProxy<>(HumanoidModel.ArmPose.class, false, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_SWING = new EnumProxy<>(HumanoidModel.ArmPose.class, true, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_SEARCH = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS)) );
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_MEALS = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(ModItems.SOMTAM_FEAST.get())));
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_MISC = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)));


}
