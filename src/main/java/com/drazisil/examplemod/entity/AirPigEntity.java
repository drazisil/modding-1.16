package com.drazisil.examplemod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class AirPigEntity extends AnimalEntity implements IFlyingAnimal, IEquipable {
    private static final DataParameter<Boolean> DATA_SADDLE_ID = EntityDataManager.defineId(AirPigEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DATA_BOOST_TIME = EntityDataManager.defineId(AirPigEntity.class, DataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.GOLDEN_CARROT);
    private final BoostHelper steering = new BoostHelper(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);

    public AirPigEntity(EntityType<? extends AnimalEntity> p_i50250_1_, World p_i50250_2_) {
        super(p_i50250_1_, p_i50250_2_);
        this.moveControl = new FlyingMovementController(this, 20, true);
    }

    // This method is called to add attributes to this EntityType
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.FLYING_SPEED, (double)0.6F);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return true;
//            return playerentity.getMainHandItem().getItem() == Items.CARROT_ON_A_STICK || playerentity.getOffhandItem().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

//    public boolean isSaddled() {
//        return this.steering.hasSaddle();
//    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    public void equipSaddle(@Nullable SoundCategory p_230266_1_) {
        this.steering.setSaddle(true);
        if (p_230266_1_ != null) {
            this.level.playSound((PlayerEntity)null, this, SoundEvents.PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }

    }

    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    public void travel(Vector3d p_213352_1_) {
        if (this.isAlive()) {
            if (this.isVehicle() && this.canBeControlledByRider() && this.isSaddled()) {
                LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
                this.yRot = livingentity.yRot;
                this.yRotO = this.yRot;
                this.xRot = livingentity.xRot * 0.5F;
                this.setRot(this.yRot, this.xRot);
                this.yBodyRot = this.yRot;
                this.yHeadRot = this.yBodyRot;
                float f = livingentity.xxa * 0.5F;
                float f1 = livingentity.zza;
//                if (f1 <= 0.0F) {
//                    f1 *= 0.25F;
//                    this.gallopSoundCounter = 0;
//                }
//
//                if (this.onGround && this.playerJumpPendingScale == 0.0F && this.isStanding() && !this.allowStandSliding) {
//                    f = 0.0F;
//                    f1 = 0.0F;
//                }

//                if (this.playerJumpPendingScale > 0.0F && !this.isJumping() && this.onGround) {
//                    double d0 = this.getCustomJump() * (double)this.playerJumpPendingScale * (double)this.getBlockJumpFactor();
//                    double d1;
//                    if (this.hasEffect(Effects.JUMP)) {
//                        d1 = d0 + (double)((float)(this.getEffect(Effects.JUMP).getAmplifier() + 1) * 0.1F);
//                    } else {
//                        d1 = d0;
//                    }
//
//                    Vector3d vector3d = this.getDeltaMovement();
//                    this.setDeltaMovement(vector3d.x, d1, vector3d.z);
//                    this.setIsJumping(true);
//                    this.hasImpulse = true;
//                    net.minecraftforge.common.ForgeHooks.onLivingJump(this);
//                    if (f1 > 0.0F) {
//                        float f2 = MathHelper.sin(this.yRot * ((float)Math.PI / 180F));
//                        float f3 = MathHelper.cos(this.yRot * ((float)Math.PI / 180F));
//                        this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f2 * this.playerJumpPendingScale), 0.0D, (double)(0.4F * f3 * this.playerJumpPendingScale)));
//                    }
//
//                    this.playerJumpPendingScale = 0.0F;
//                }

                this.flyingSpeed = this.getSpeed() * 0.1F;
                if (this.isControlledByLocalInstance()) {
                    this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    super.travel(new Vector3d((double)f, p_213352_1_.y, (double)f1));
                } else if (livingentity instanceof PlayerEntity) {
                    this.setDeltaMovement(Vector3d.ZERO);
                }

//                if (this.onGround) {
//                    this.playerJumpPendingScale = 0.0F;
//                    this.setIsJumping(false);
//                }

                this.calculateEntityAnimation(this, false);
            } else {
                this.flyingSpeed = 0.02F;
                super.travel(p_213352_1_);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public boolean shouldRiderSit() {
        return super.shouldRiderSit();
    }

    @Override
    public boolean canRiderInteract() {
        return super.canRiderInteract();
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        this.steering.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.steering.readAdditionalSaveData(p_70037_1_);
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_BOOST_TIME.equals(p_184206_1_) && this.level.isClientSide) {
            this.steering.onSynced();
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SADDLE_ID, false);
        this.entityData.define(DATA_BOOST_TIME, 0);
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        boolean flag = this.isFood(p_230254_1_.getItemInHand(p_230254_2_));
        if (!flag && this.isSaddled() && !this.isVehicle() && !p_230254_1_.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                p_230254_1_.startRiding(this);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
            if (!actionresulttype.consumesAction()) {
                ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactLivingEntity(p_230254_1_, this, p_230254_2_) : ActionResultType.PASS;
            } else {
                return actionresulttype;
            }
        }
    }
}
