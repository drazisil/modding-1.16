package com.drazisil.examplemod.client;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.entity.Entity;

public class AirPigModel<T extends Entity> extends QuadrupedModel<T> {
    public AirPigModel() {
        this(0.0F);
    }

    public AirPigModel(float p_i1151_1_) {
        super(6, p_i1151_1_, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head.texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, p_i1151_1_);
    }
}
