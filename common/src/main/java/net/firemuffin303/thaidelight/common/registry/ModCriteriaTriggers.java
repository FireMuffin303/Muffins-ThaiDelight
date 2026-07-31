package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.advancement.SackCatchTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;

import java.util.function.Supplier;

public class ModCriteriaTriggers {
    public static final ResourceRegistry<? super CriterionTrigger<?>> CRITERION = ResourceRegistry.create(Registries.TRIGGER_TYPE, ThaiDelightCommon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final Supplier<SackCatchTrigger> SACK_CATCH = (Supplier<SackCatchTrigger>)(Supplier<?>) CRITERION.register("sack_catch", SackCatchTrigger::new);

    public static void init(){
        CRITERION.init();
    }
}
