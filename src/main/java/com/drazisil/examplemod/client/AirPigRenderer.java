package com.drazisil.examplemod.client;

import com.drazisil.examplemod.ExampleMod;
import com.drazisil.examplemod.entity.AirPigEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.util.ResourceLocation;

public class AirPigRenderer  extends MobRenderer<AirPigEntity, AirPigModel<AirPigEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MODID, "textures/entity/air_pig/air_pig.png");

    public AirPigRenderer(EntityRendererManager p_i47196_1_) {
        super(p_i47196_1_, new AirPigModel<>(), 0.3F);
        this.addLayer(new SaddleLayer<>(this, new AirPigModel<>(0.5F), new ResourceLocation(ExampleMod.MODID,"textures/entity/air_pig/air_pig_saddle.png")));

    }

    @Override
    public ResourceLocation getTextureLocation(AirPigEntity entity) {

        return TEXTURE;

    }
}
