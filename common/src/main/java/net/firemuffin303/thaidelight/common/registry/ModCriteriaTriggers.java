package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.advancement.SackCatchTrigger;
import net.firemuffin303.thaidelight.mixin.accessor.CriteriaTriggersAccessor;
import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers {
    public static final SackCatchTrigger SACK_CATCH = CriteriaTriggersAccessor.register(new SackCatchTrigger());

    public static void init(){}
}
