package com.example.examplemod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;

public class WareRabbitEntity extends RabbitEntity {
    public WareRabbitEntity(EntityType<? extends RabbitEntity> p_i50247_1_, World p_i50247_2_) {
        // Our new entity crashes when the super method tries to call net.minecraft.entity.LivingEntity.getMaxHealth

        // Here we get into the apparently undocumented world of attributes
        // How does a normal rabbit get it's health? `getMaxHealth()` returns this.getAttributeValue(Attributes.MAX_HEALTH)

        // This gets set when GlobalEntityTypeAttributes adds this attribute to it's SUPPLIERS set with
        // .put(EntityType.RABBIT, RabbitEntity.createAttributes().build())

        // We need to do the same. How and where should we call this?
        // The `put()` method says that we should use net.minecraftforge.event.entity.EntityAttributeCreationEvent#put
        // Let's try calling it here com.example.examplemod.bus_subscribers.ModEventBusSubscriber.onEntityAttributeCreationEvent

        super(p_i50247_1_, p_i50247_2_);
    }

    // This method is called to add attributes to this EntityType
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }
}
