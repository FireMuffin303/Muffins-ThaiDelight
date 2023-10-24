package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component TURTLE_UPGRADE;
    private static final Component TURTLE_UPGRADE_APPLIES_TO;
    private static final Component TURTLE_UPGRADE_INGREDIENTS;
    private static final Component TURTLE_UPGRADE_BASE_SLOT_DESCRIPTION;
    private static final Component TURTLE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION;

    private static final ResourceLocation EMPTY_SLOT_ARMADILLO;
    private static final ResourceLocation EMPTY_SLOT_SCUTE;


    public ModSmithingTemplateItem(Component component, Component component2, Component component3, Component component4, Component component5, List<ResourceLocation> list, List<ResourceLocation> list2) {
        super(component, component2, component3, component4, component5, list, list2);
    }

    public static SmithingTemplateItem createTurtleArmadilloTemplate(){
        return new SmithingTemplateItem(TURTLE_UPGRADE_APPLIES_TO,TURTLE_UPGRADE_INGREDIENTS,TURTLE_UPGRADE,TURTLE_UPGRADE_BASE_SLOT_DESCRIPTION,TURTLE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,createArmadilloUpgradeIconList(),createTutleScuteUpgradeMaterialList());
    }

    private static List<ResourceLocation> createArmadilloUpgradeIconList() {
        return List.of(EMPTY_SLOT_ARMADILLO);
    }

    private static List<ResourceLocation> createTutleScuteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_SCUTE);
    }

    static{
        TURTLE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ThaiDelight.MOD_ID,"smithing_template.turtle_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
        TURTLE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ThaiDelight.MOD_ID,"smithing_template.turtle_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
        TURTLE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ThaiDelight.MOD_ID,"smithing_template.turtle_upgrade.base_slot_description")));
        TURTLE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ThaiDelight.MOD_ID,"smithing_template.turtle_upgrade.additions_slot_description")));
        TURTLE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(ThaiDelight.MOD_ID,"turtle_upgrade"))).withStyle(ChatFormatting.GRAY);
        EMPTY_SLOT_ARMADILLO = new ResourceLocation(ThaiDelight.MOD_ID,"item/empty_slot_armadillo");
        EMPTY_SLOT_SCUTE = new ResourceLocation(ThaiDelight.MOD_ID,"item/empty_slot_scute");


    }
}
