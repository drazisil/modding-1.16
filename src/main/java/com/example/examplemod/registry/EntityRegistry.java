package com.example.examplemod.registry;

import com.example.examplemod.entity.WareRabbitEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.example.examplemod.ExampleMod.MODID;

public class EntityRegistry {

    // Here we create a DeferredRegistry for entities
    // https://mcforge.readthedocs.io/en/latest/concepts/registries/#methods-for-registering
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    // Next, we create the RegistryObject
    public static final RegistryObject<EntityType<WareRabbitEntity>> WARE_RABBIT = ENTITIES.register(
            // All instructions say to use `create()`, but this method appears to have been renamed to `of()`
            "ware_rabbit", () -> EntityType.Builder.of(WareRabbitEntity::new, EntityClassification.CREATURE).build("ware_rabbit")
    );
}
