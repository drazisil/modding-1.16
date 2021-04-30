package com.example.examplemod.bus_subscribers;

import com.example.examplemod.ExampleMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

        // We want to only continue if this is a stick. We will compare it against Items.STICK
        if (item == Items.STICK) {
            // This is a stick!
            ExampleMod.MOD_LOGGER.info("Stick!");
        }

        // If we have reached here, the item is not a stick, and we will let the event process normally
    }
}
