package io.github.fallOut015.umbral_arcana.level.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class SigilEntity extends Entity {
    static final DataParameter<Integer> MAX_LIFE = EntityDataManager.defineId(SigilEntity.class, DataSerializers.INT);

    public SigilEntity(EntityType<SigilEntity> entityType, World level) {
        super(entityType, level);
    }
    public SigilEntity(World level) {
        this((EntityType<SigilEntity>) EntitiesUmbralArcana.SIGIL.get(), level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(MAX_LIFE, 0);
    }
    @Override
    protected void readAdditionalSaveData(CompoundNBT compoundNBT) {
        this.setMaxLife(compoundNBT.getInt("MAX_LIFE"));
    }
    @Override
    protected void addAdditionalSaveData(CompoundNBT compoundNBT) {
        compoundNBT.putInt("MAX_LIFE", this.getMaxLife());
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }
    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    public int getMaxLife() {
        return this.entityData.get(MAX_LIFE);
    }
    public void setMaxLife(int maxLife) {
        this.entityData.set(MAX_LIFE, maxLife);
    }

    public double scale(double x) {
        return MainPactMagic.quad(x, this.entityData.get(MAX_LIFE), this.getBbWidth(), 2, true);
    }
}