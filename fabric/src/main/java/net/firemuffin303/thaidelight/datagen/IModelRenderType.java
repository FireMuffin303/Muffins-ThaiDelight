package net.firemuffin303.thaidelight.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.resources.ResourceLocation;

public interface IModelRenderType {

    default JsonObject injectRenderType(JsonObject jsonObject){
        if(this.muffins_thaidelight$getRenderType() != null){
            jsonObject.addProperty("render_type",this.muffins_thaidelight$getRenderType().toString());
        }

        return jsonObject;
    }

    ResourceLocation muffins_thaidelight$getRenderType();

    ModelTemplate muffins_thaidelight$setRenderType(ResourceLocation renderType);
}
