package net.firemuffin303.muffinsthaidelightfabric.util;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.UseAnim;

public class ModASMEarlyRiser implements Runnable{
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String armPose = remapper.mapClassName("intermediary", "net.minecraft.class_572$class_573");
        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_SWING",true).build();
        ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum("CATCHING_BAG_HOLD",true).build();

        String useAnim = remapper.mapClassName("intermediary","net.minecraft.class_1839");
        ClassTinkerers.enumBuilder(useAnim).addEnum("CATCHING_BAG").build();
    }

    public static UseAnim getDurianCatcherUseAnim(){
        return ClassTinkerers.getEnum(UseAnim.class,"CATCHING_BAG");
    }

    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose(){
        return ClassTinkerers.getEnum(HumanoidModel.ArmPose.class,"CATCHING_BAG_HOLD");
    }

    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose(){
        return ClassTinkerers.getEnum(HumanoidModel.ArmPose.class,"CATCHING_BAG_SWING");
    }
}
