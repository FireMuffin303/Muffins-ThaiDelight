package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.common.advancement.SackCatchTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers {
    public static final SackCatchTrigger SACK_CATCH = CriteriaTriggers.register(new SackCatchTrigger());

    public static void init(){}
}
