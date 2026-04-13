package net.firemuffin303.thaidelight.mixin.fabric.model;


import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.datagen.IModelRenderType;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Objects;

@Mixin(ModelTemplate.class)
public abstract class ModelTemplateMixin implements IModelRenderType {
    @Unique
    ResourceLocation renderType;

    @Unique
    @Override
    public ModelTemplate muffins_thaidelight$setRenderType(ResourceLocation renderType) {
        this.renderType = renderType;
        return (ModelTemplate)((Object) this);
    }

    @Unique
    @Override
    public ResourceLocation muffins_thaidelight$getRenderType() {
        return renderType;
    }

    @Inject(method = "createBaseTemplate",at = @At("RETURN"))
    public void muffins$injectRenderType(ResourceLocation resourceLocation, Map<TextureSlot, ResourceLocation> map, CallbackInfoReturnable<JsonObject> cir, @Local() JsonObject jsonObject){
        this.injectRenderType(jsonObject);
    }

}
