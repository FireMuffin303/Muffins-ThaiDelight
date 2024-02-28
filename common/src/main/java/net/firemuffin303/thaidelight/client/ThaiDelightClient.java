package net.firemuffin303.thaidelight.client;

import net.firemuffin303.thaidelight.client.registry.ModBlockEntityClient;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.client.registry.ModScreensClient;

public class ThaiDelightClient {
    public static void init(){
        ModEntityClient.init();
        ModScreensClient.init();


    }
}
