package com.example.examplemod.bus_subscribers.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientForgeEventBusSubscriber {

    // This event happens on the mod event bus and only client side
    // It registers the rendering handler
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ExampleMod.MOD_LOGGER.info("Registering rendering handler");
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.WARE_RABBIT.get(), RabbitRenderer::new);
    }
}
