package com.drazisil.examplemod;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static com.drazisil.examplemod.registry.EntityRegistry.ENTITIES;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
public class ExampleMod
{
    // Directly reference a log4j logger.
    // I want to be able to use this logger instance throughout my mod, so I'm making it public
    public static final Logger MOD_LOGGER = LogManager.getLogger();

    public static final String MODID = "examplemod";

    public ExampleMod() {

        /*
        Hello! Welcome to the mod. The goal here, to start, is to get a custom entity working, with rendering.

        We will be using a stick as our "wand" to summon the entity.

        We will be listening to events using the event buss in com.drazisil.examplemod.bus_subscribers.ForgeEventBusSubscriber
         */


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        // This is needed for the @SubscribeEvent method in this class to work
        MinecraftForge.EVENT_BUS.register(this);

        // We need to call our EntityRegistry class, otherwise the entity will not be in EntityTypes when we try to use it
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // We will also need a renderer for our custom class, as net.minecraft.client.renderer.entity.EntityRenderer.shouldRender is returning null
        // Let's start by trying to tell our customer rabbit to use the default rabbit renderer
        // this.register(EntityType.RABBIT, new RabbitRenderer(this));

        // Since that worked, we will now create a custom renderer. Due to how models and renderers are linked,
        // we will also have to create a custom model at the same time. For now, let's try to copy the existing rabbit ones.
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        MOD_LOGGER.info("HELLO FROM PREINIT");
        MOD_LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        MOD_LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld",
                () -> { MOD_LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        MOD_LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
}
