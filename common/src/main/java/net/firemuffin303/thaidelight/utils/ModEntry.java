package net.firemuffin303.thaidelight.utils;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ModEntry<T> implements Supplier<T> {
    private final ResourceLocation id;
    private final Supplier<T> supplier;

    public ModEntry(String id, Supplier<T> supplier) {
        this.id = new ResourceLocation(ThaiDelight.MOD_ID,id);
        this.supplier = supplier;
    }


    public ResourceLocation getID(){
        return this.id;
    }


    @Override
    public T get() {
        return supplier.get();
    }
}
