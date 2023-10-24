package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class ModPotions {
    public static final ResourcefulRegistry<Potion> POTION = ResourcefulRegistries.create(BuiltInRegistries.POTION, ThaiDelight.MOD_ID);

    public static final RegistryEntry<Potion> CRAB_REACH = POTION.register("crab_reach",() -> new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH.get(),3600)));
    public static final RegistryEntry<Potion> LONG_CRAB_REACH = POTION.register("long_crab_reach",() -> new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH.get(),9600)));
    public static final RegistryEntry<Potion> STRONG_CRAB_REACH = POTION.register("strong_crab_reach",() -> new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH.get(),1800,1)));


    public static void potionBrewing(){
        ModPlatform.registerPotionBrewing(() -> Potions.AWKWARD, ModItems.CRAB_MEAT,ModPotions.CRAB_REACH);
        ModPlatform.registerPotionBrewing(ModPotions.CRAB_REACH, () -> Items.REDSTONE,ModPotions.LONG_CRAB_REACH);
        ModPlatform.registerPotionBrewing(ModPotions.STRONG_CRAB_REACH, () -> Items.GLOWSTONE,ModPotions.STRONG_CRAB_REACH);

    }

    public static void postInit(){
        potionBrewing();
    }
}
