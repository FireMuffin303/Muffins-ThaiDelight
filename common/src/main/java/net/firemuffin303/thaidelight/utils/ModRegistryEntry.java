package net.firemuffin303.thaidelight.utils;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModRegistryEntry<T> extends ArrayList<T> {



    public <I extends T> Supplier<I> register(String id,Supplier<I> supplier){
        return supplier;
    }

}
