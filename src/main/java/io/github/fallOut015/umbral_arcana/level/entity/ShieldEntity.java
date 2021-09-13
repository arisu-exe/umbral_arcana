package io.github.fallOut015.umbral_arcana.level.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class ShieldEntity extends Entity {
    static final DataParameter<Optional<UUID>> CASTER_ID = EntityDataManager.defineId(ShieldEntity.class, DataSerializers.OPTIONAL_UUID);
    @Nullable LivingEntity caster;

    public ShieldEntity(EntityType<Entity> entityType, World level) {
        super(entityType, level);
    }
    public ShieldEntity(World level) {
        this((EntityType<Entity>) EntitiesUmbralArcana.SHIELD.get(), level);
    }

    @Override
    public void tick() {
        if(this.caster == null && this.entityData.get(CASTER_ID).isPresent()) {
            this.caster = this.level.getPlayerByUUID(this.entityData.get(CASTER_ID).get());
        }
        if(this.caster != null) {
            this.setPos(caster.getX(), caster.getY(), caster.getZ());
        }
    }

    public void setCaster(@Nullable LivingEntity caster) {
        this.caster = caster;
    }
    public float getCasterEyeHeight() {
        return this.caster == null ? 0 : this.caster.getEyeHeight();
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CASTER_ID, Optional.empty());
    }
    @Override
    protected void readAdditionalSaveData(CompoundNBT compoundNBT) {
        if(compoundNBT.contains("CASTER_ID")) {
            this.entityData.set(CASTER_ID, Optional.of(compoundNBT.getUUID("CASTER_ID")));
        }
    }
    @Override
    protected void addAdditionalSaveData(CompoundNBT compoundNBT) {
        this.entityData.get(CASTER_ID).ifPresent(casterID -> compoundNBT.putUUID("CASTER_ID", casterID));
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
