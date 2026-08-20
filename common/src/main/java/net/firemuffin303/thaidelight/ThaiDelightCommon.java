package net.firemuffin303.thaidelight;

import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.mixin.accessor.VillagerAccessor;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class ThaiDelightCommon {
    public static final String MOD_ID = "muffins_thaidelight";

    public static final Map<RegistryHolder<Item>,Integer> FUEL_MAP = new HashMap<>();
    public static final Map<Block,BurnEntry> BURN_MAP = new HashMap<>();

    public static void init(){
        ModSoundEvents.init();
        ModCriteriaTriggers.init();
        ModDamageTypes.init();

        ModBlockStateProviderTypes.init();
        ModFeatures.init();
        ModTreeDecoratorTypes.init();

        ModMobEffects.init();
        ModEntityTypes.init();

        ModMenuType.init();

        ModArmorMaterials.init();

        ModBlockSetTypes.init();
        ModBlocks.init();
        ModBlockEntityTypes.init();
        ModItems.init();

        ModRecipes.init();
        ModBoatVariants.init();
    }

    public static void postInit(){
        ModCauldronInteraction.init();
        ModDispenserBehavior.init();
        ModComposterEvent.init();
        registerFuel();
        registerStrippable();
        registerAnimalFood();
        setVillagerItem();
        registerBurnBlock();

    }

    public static ResourceLocation modid(String id){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID,id);
    }


    public static void registerStrippable(){
        Map<Block,Block> block = new HashMap<>();
        block.put(ModBlocks.PAPAYA_LOG.get(),ModBlocks.STRIPPED_PAPAYA_LOG.get());
        block.put(ModBlocks.PAPAYA_WOOD.get(),ModBlocks.STRIPPED_PAPAYA_WOOD.get());
        block.put(ModBlocks.DURIAN_LOG.get(),ModBlocks.STRIPPED_DURIAN_LOG.get());
        block.put(ModBlocks.DURIAN_WOOD.get(),ModBlocks.STRIPPED_DURIAN_WOOD.get());
        block.put(ModBlocks.MANGO_LOG.get(),ModBlocks.STRIPPED_MANGO_LOG.get());
        block.put(ModBlocks.MANGO_WOOD.get(),ModBlocks.STRIPPED_MANGO_WOOD.get());
        block.put(ModBlocks.COCONUT_LOG.get(),ModBlocks.STRIPPED_COCONUT_LOG.get());
        block.put(ModBlocks.COCONUT_WOOD.get(),ModBlocks.STRIPPED_COCONUT_WOOD.get());

        PlatformUtil.registerStrippable(block);
    }

    public static void registerFuel(){
        FUEL_MAP.put(ModItems.DURIAN_PEEL,200);
        FUEL_MAP.put(ModItems.DURIAN_PEEL_BLOCK,1800);
        FUEL_MAP.put(ModItems.COCONUT_LEAF_BLOCK,4001);
        FUEL_MAP.put(ModItems.PAPAYA_LEAVES,100);
        FUEL_MAP.put(ModItems.DURIAN_CABINET,300);
        FUEL_MAP.put(ModItems.MANGO_CABINET,300);
        FUEL_MAP.put(ModItems.COCONUT_CABINET,300);
    }

    public static void registerAnimalFood(){
        /*
        ParrotTameFoodAccessor.getTameFood().add(Item.byBlock(ModBlocks.PAPAYA_SAPLING.get()));
        ParrotTameFoodAccessor.getTameFood().add(ModItems.PEPPER_SEED.get());
        ParrotTameFoodAccessor.getTameFood().add(ModItems.BUTTERFLY_PEA_SEEDS.get());

        Ingredient newPigFoods = Ingredient.of(
                ModItems.RAW_PAPAYA.get(),
                ModItems.PAPAYA.get(),
                ModItems.SLICED_PAPAYA.get(),
                ModItems.RAW_PAPAYA_SLICE.get(),
                ModItems.LIME.get(),
                ModItems.SLICED_LIME.get(),
                ModItems.BAMBOO_SHOOT.get()
        );
        Ingredient newChickenFoods = Ingredient.of(ModItems.PAPAYA_SEEDS.get(),ModItems.PEPPER_SEED.get(),ModItems.BUTTERFLY_PEA_SEEDS.get());

        Ingredient newFrogFoods = Ingredient.of(ModItems.DRAGONFLY.get(),ModItems.COOKED_DRAGONFLY.get());

        PigFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(PigFoodAccessor.getFoodItems().getItems()),Arrays.stream(newPigFoods.getItems()))
        ));

        ChickenFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(ChickenFoodAccessor.getFoodItems().getItems()),Arrays.stream(newChickenFoods.getItems()))
        ));

        FrogFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(FrogFoodAccessor.getFoodItems().getItems()),Arrays.stream(newFrogFoods.getItems()))
        ));


         */
    }

    public static void setVillagerItem(){
        Map<Item,Integer> villagerFoodPoint = new HashMap<>(VillagerAccessor.getFoodPoints());
        villagerFoodPoint.put(ModItems.LIME.get(),1);
        villagerFoodPoint.put(ModItems.PEPPER.get(),1);
        villagerFoodPoint.put(ModItems.PAPAYA.get(),1);
        villagerFoodPoint.put(ModItems.RAW_PAPAYA.get(),1);
        villagerFoodPoint.put(ModItems.DURIAN_PULP.get(),1);
        villagerFoodPoint.put(ModItems.BASIL.get(),1);
        villagerFoodPoint.put(ModItems.MANGO.get(),1);
        villagerFoodPoint.put(ModItems.COCONUT_SLICE.get(),1);

        VillagerAccessor.setFoodPoints(villagerFoodPoint);

        Set<Item> villagerWantedItems = new HashSet<>(VillagerAccessor.getWantedItems());
        villagerWantedItems.add(ModItems.LIME.get());
        villagerWantedItems.add(ModItems.PEPPER.get());
        villagerWantedItems.add(ModItems.PAPAYA.get());
        villagerWantedItems.add(ModItems.RAW_PAPAYA.get());
        villagerWantedItems.add(ModItems.DURIAN_PULP.get());
        villagerWantedItems.add(ModItems.BASIL.get());
        villagerWantedItems.add(ModItems.MANGO.get());
        villagerWantedItems.add(ModItems.COCONUT_SLICE.get());
        VillagerAccessor.setWantedItems(villagerWantedItems);
    }


    public static void registerBurnBlock(){
        BURN_MAP.put(ModBlocks.DURIAN_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_DURIAN_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.DURIAN_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_DURIAN_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.DURIAN_LEAVES.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.DURIAN_FLOWER.get(),new BurnEntry(60,100));
        BURN_MAP.put(ModBlocks.DURIAN_PEEL_BLOCK.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.DURIAN_PLANKS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.DURIAN_STAIRS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.DURIAN_SLAB.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.DURIAN_FENCE.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.DURIAN_FENCE_GATE.get(),new BurnEntry(5,20));

        BURN_MAP.put(ModBlocks.MANGO_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_MANGO_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.MANGO_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_MANGO_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.MANGO_LEAVES.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.MANGO_PLANKS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.MANGO_STAIRS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.MANGO_SLAB.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.MANGO_FENCE.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.MANGO_FENCE_GATE.get(),new BurnEntry(5,20));

        BURN_MAP.put(ModBlocks.COCONUT_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_COCONUT_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.COCONUT_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_COCONUT_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.BUDDING_COCONUT_LEAF.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.COCONUT_LEAF.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.COCONUT_LEAF_END.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.COCONUT_LEAF_BLOCK.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.COCONUT_LEAF_CARPET.get(),new BurnEntry(60,20));
        BURN_MAP.put(ModBlocks.COCONUT_PLANKS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.COCONUT_STAIRS.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.COCONUT_SLAB.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.COCONUT_FENCE.get(),new BurnEntry(5,20));
        BURN_MAP.put(ModBlocks.COCONUT_FENCE_GATE.get(),new BurnEntry(5,20));

        BURN_MAP.put(ModBlocks.PAPAYA_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_PAPAYA_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.PAPAYA_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_PAPAYA_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.PAPAYA_FLOWER.get(),new BurnEntry(60,100));
        BURN_MAP.put(ModBlocks.WALL_PAPAYA_FLOWER.get(),new BurnEntry(60,100));
        BURN_MAP.put(ModBlocks.BUDDING_PAPAYA_FLOWER.get(),new BurnEntry(60,100));
        BURN_MAP.put(ModBlocks.PAPAYA_LEAVES.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.WALL_PAPAYA_LEAVES.get(),new BurnEntry(30,60));
        BURN_MAP.put(ModBlocks.PAPAYA_LEAVES_STEM.get(),new BurnEntry(30,60));

        BURN_MAP.put(ModBlocks.PAPAYA_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_PAPAYA_LOG.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.PAPAYA_WOOD.get(),new BurnEntry(5,5));
        BURN_MAP.put(ModBlocks.STRIPPED_PAPAYA_WOOD.get(),new BurnEntry(5,5));

        BURN_MAP.put(ModBlocks.BUTTERFLY_PEA_WALL.get(),new BurnEntry(60,100));
        BURN_MAP.put(ModBlocks.LIME_PLANT.get(),new BurnEntry(60,100));
    }

    public static record BurnEntry(int burn,int spread){ }
}
