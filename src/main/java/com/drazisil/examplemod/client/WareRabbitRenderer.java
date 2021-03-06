package com.drazisil.examplemod.client;

import com.drazisil.examplemod.ExampleMod;
import com.drazisil.examplemod.entity.WareRabbitEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WareRabbitRenderer extends MobRenderer<WareRabbitEntity, WareRabbitModel<WareRabbitEntity>> {

    private static final ResourceLocation TEXTURE_DAY = new ResourceLocation(ExampleMod.MODID, "textures/entity/warerabbit/brown.png");
    private static final ResourceLocation TEXTURE_NIGHT = new ResourceLocation(ExampleMod.MODID, "textures/entity/warerabbit/ware.png");

    public WareRabbitRenderer(EntityRendererManager p_i47196_1_) {
        super(p_i47196_1_, new WareRabbitModel<>(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(WareRabbitEntity entity) {

        if (entity.level.getDayTime() >= 0 && entity.level.getDayTime() <= 12000) {
            return TEXTURE_DAY;
        }
        return TEXTURE_NIGHT;

    }
}
