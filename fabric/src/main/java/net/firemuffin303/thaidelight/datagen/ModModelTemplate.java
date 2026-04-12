package net.firemuffin303.thaidelight.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Optional;

public class ModModelTemplate extends ModelTemplate {
    public ResourceLocation renderType;

    public ModModelTemplate(Optional<ResourceLocation> optional, Optional<String> optional2,ResourceLocation resourceLocation, TextureSlot... textureSlots) {
        super(optional, optional2, textureSlots);
        this.renderType = resourceLocation;
    }

    public void setRenderType(ResourceLocation renderType) {
        this.renderType = renderType;
    }

    @Override
    public JsonObject createBaseTemplate(ResourceLocation resourceLocation, Map<TextureSlot, ResourceLocation> map) {
        JsonObject jsonObject = super.createBaseTemplate(resourceLocation, map);
        if(this.renderType != null){
            jsonObject.addProperty("render_type",this.renderType.toString());
        }

        return jsonObject;
    }
}
