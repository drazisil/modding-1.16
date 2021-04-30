package com.example.examplemod.bus_subscribers;

import com.example.examplemod.entity.WareRabbitEntity;
import com.example.examplemod.registry.EntityRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.example.examplemod.ExampleMod.MOD_LOGGER;

// You can use EventBusSubscriber to automatically subscribe events on the contained class
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber {

    // This listener is going to add the EntityTypeAttributes for our custom EntityType(s)
    @SubscribeEvent
    public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        // First, is this getting called?
        MOD_LOGGER.info("Hello from the EntityAttributeCreationEvent!");
        event.put(EntityRegistry.WARE_RABBIT.get(), WareRabbitEntity.createAttributes().build());
    }
}
