package com.drazisil.examplemod;

import com.drazisil.examplemod.entity.AirPigEntity;
import com.drazisil.examplemod.registry.EntityRegistry;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import static com.drazisil.examplemod.ExampleMod.MOD_LOGGER;

public class Helpers {

    public static void spawnWareRabbit(World world, Vector3d targetPos) {
        // Let's create a rabbit. Rabbits come from wands, right?
        // the Rabbit is already registered in the EntityType enum. We will call it's create() method and pass in world.
        RabbitEntity rabbit = EntityRegistry.WARE_RABBIT.get().create(world);

        // Add the rabbit to the world
        assert rabbit != null;
        world.addFreshEntity(rabbit);

        // Where is our rabbit? I don't see it at this point.

        // is is alive?
        MOD_LOGGER.info("Is rabbit alive? " + rabbit.isAlive());

        // It is. Where is it?
        MOD_LOGGER.info("target location: " + targetPos);
        MOD_LOGGER.info("rabbit location: " + rabbit.position());

        // Well, well, well. It seems our rabbit is at 0, 0, 0.
        // This makes sense, since we only added it to the world, and didn't pass a location.
        // Let's move it to the target position
//            MOD_LOGGER.info("Moving rabbit to target position");
//            rabbit.move(MoverType.SELF, targetPos);

        // Still not seeing my rabbit. Let's check if it moved.
//            MOD_LOGGER.info("target location: " + targetPos);
//            MOD_LOGGER.info("rabbit location: " + rabbit.position());

        // It's still not here. It did move a bit, but not anywhere close.
        // That last command generated a lot of server lags as well.
        // This is because we asked the rabbit to come to the target position, using it's normal move AI

        // Let's try teleporting it to the player instead.
        double targetX = targetPos.x;
        double targetY = targetPos.y;
        double targetZ = targetPos.z;
        rabbit.teleportTo(targetX, targetY, targetZ);

        // I have rabbits. More then one, because of the way interact works.
    }

    public static void spawnAirPig(World world, Vector3d targetPos) {
        // Let's create a rabbit. Rabbits come from wands, right?
        // the Rabbit is already registered in the EntityType enum. We will call it's create() method and pass in world.
        AirPigEntity air_pig = EntityRegistry.AIR_PIG.get().create(world);

        // Add the rabbit to the world
        assert air_pig != null;
        world.addFreshEntity(air_pig);

        double targetX = targetPos.x;
        double targetY = targetPos.y;
        double targetZ = targetPos.z;
        air_pig.teleportTo(targetX, targetY, targetZ);
    }
}
