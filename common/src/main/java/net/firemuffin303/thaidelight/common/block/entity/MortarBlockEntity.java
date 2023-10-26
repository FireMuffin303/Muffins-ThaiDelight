package net.firemuffin303.thaidelight.common.block.entity;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MortarBlockEntity extends BlockEntity implements GeoBlockEntity {
    public static Container container;

    protected boolean playingSmashingAnimation = false;
    protected static final RawAnimation SMASH = RawAnimation.begin().thenPlayAndHold("smash");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public MortarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.ModBlockEntityTypes.MORTAR_BLOCK_ENTITY, blockPos, blockState);
    }




    // GeckoLib
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,this::deployAnimController));
    }

    protected <E extends MortarBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        if(state.getAnimatable().playingSmashingAnimation){
            state.getController().setAnimation(SMASH);
            if(state.getController().getAnimationState() != AnimationController.State.STOPPED) return PlayState.CONTINUE;
            state.getAnimatable().playingSmashingAnimation = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
