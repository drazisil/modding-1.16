package com.example.examplemod.bus_subscribers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3d;
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

            // Let's create a rabbit. Rabbits come from wands, right?
            // the Rabbit is already registered in the EntityType enum. We will call it's create() method and pass in world.
            RabbitEntity rabbit = EntityType.RABBIT.create(world);

            // Add the rabbit to the world
            assert rabbit != null;
            world.addFreshEntity(rabbit);

            // Where is our rabbit? I don't see it at this point.

            // is is alive?
            MOD_LOGGER.info("Is rabbit alive? " + rabbit.isAlive());

            // It is. Where is it?
            MOD_LOGGER.info("player location: " + player.position());
            MOD_LOGGER.info("rabbit location: " + rabbit.position());

            // Well, well, well. It seems our rabbit is at 0, 0, 0.
            // This makes sense, since we only added it to the world, and didn't pass a location.
            // Let's move it to the player
//            MOD_LOGGER.info("Moving rabbit to player");
//            rabbit.move(MoverType.SELF, player.position());

            // Still not seeing my rabbit. Let's check if it moved.
//            MOD_LOGGER.info("player location: " + player.position());
//            MOD_LOGGER.info("rabbit location: " + rabbit.position());

            // It's still not here. It did move a bit, but not anywhere close.
            // That last command generated a lot of server lags as well.
            // This is because we asked the rabbit to come to the player, using it's normal move AI

            // Let's try teleporting it to the player instead.
            Vector3d playerPosition = player.position();
            double playerX = playerPosition.x;
            double playerY = playerPosition.y;
            double playerZ = playerPosition.z;
            rabbit.teleportTo(playerX, playerY, playerZ);

            // I have bunnies. More then one, because of the way interact works.

        }

        // If we have reached here, the item is not a stick, and we will let the event process normally
    }
}
