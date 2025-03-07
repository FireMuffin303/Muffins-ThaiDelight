package net.firemuffin303.muffinsthaidelightfabric.common.manager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlavorManager implements SimpleSynchronousResourceReloadListener {
    public static final Map<Item, FlavorEntry> FLAVORS = new HashMap<>();
    private static final ResourceLocation ID = ThaiDelight.modid("_flavor");

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        Map<ResourceLocation, List<Resource>> flavorResources = resourceManager.listResourceStacks("flavor", resourceLocation -> resourceLocation.getNamespace().equals(ThaiDelight.MOD_ID) && resourceLocation.getPath().endsWith(".json"));

        FLAVORS.clear();
        flavorResources.forEach((resourceLocation, resources) -> {
            for(Resource resource : resources){
                try(InputStream inputStream = resource.open()) {
                    JsonObject jsonObject = JsonParser.parseReader(new JsonReader(new InputStreamReader(inputStream))).getAsJsonObject();
                    ResourceLocation itemId = ResourceLocation.tryParse(jsonObject.get("item").getAsString());
                    JsonObject flavorObject = jsonObject.getAsJsonObject("flavor");
                    FLAVORS.put(BuiltInRegistries.ITEM.get(itemId),new FlavorEntry(flavorObject.get("sour").getAsInt(),flavorObject.get("spicy").getAsInt(),flavorObject.get("salty").getAsInt(),flavorObject.get("sweet").getAsInt()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public record FlavorEntry(int sour, int spicy, int salty, int sweet){

    }
}
