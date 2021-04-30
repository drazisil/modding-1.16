package com.example.examplemod.bus_subscribers;

import com.example.examplemod.Helpers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.example.examplemod.ExampleMod.MOD_LOGGER;

// You can use EventBusSubscriber to automatically subscribe events on the contained class
@Mod.EventBusSubscriber()
public class ModEventBusSubscriber {

    // Here we are listening to all instanced of a player interacting (right-clicking) with something
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent event) {
        // There are a number of things we can gather from this event. Let's start with the player
        PlayerEntity player = event.getPlayer();

        // Next, we want to know what they are holding.
        ItemStack itemStack = event.getItemStack();

        // An ItemStack is how minecraft shores items. It's what allows you have have more
        // then one item in a slot. We are interested in what type of item this is.
        Item item = itemStack.getItem();

        // We need to know what world the player is in. This used to be named `world`. It's called `level` in this version
        // We want to get the world early, since we need to check what "side" the event is on.
        World world = player.level;

        // We want to only continue if this is a stick. We will compare it against Items.STICK
        // We also want to make sure that we only perform the rest of the actions on the server side.
        if (item == Items.STICK && !world.isClientSide) {
            // This is a stick!
            MOD_LOGGER.info("Stick!");

            // We have our stick. Can we summon anything?

            Helpers.spawnRabbit(world, player.position());

        }

        // If we have reached here, the item is not a stick, and we will let the event process normally
    }
}
