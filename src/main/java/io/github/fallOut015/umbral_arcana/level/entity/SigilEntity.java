package io.github.fallOut015.umbral_arcana.level.entity;

import io.github.fallOut015.umbral_arcana.particles.ParticleTypesUmbralArcana;
import io.github.fallOut015.umbral_arcana.util.math.MathHelperUmbralArcana;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SigilEntity extends Entity {
    static final DataParameter<Integer> MAX_LIFE = EntityDataManager.defineId(SigilEntity.class, DataSerializers.INT);
    static final DataParameter<String> TEXTURE = EntityDataManager.defineId(SigilEntity.class, DataSerializers.STRING); // TODO make client only
    static final DataParameter<Integer> ROTATION_SPEED = EntityDataManager.defineId(SigilEntity.class, DataSerializers.INT); // TODO make client only

    public SigilEntity(EntityType<Entity> entityType, World level) {
        super(entityType, level);
    }
    public SigilEntity(World level) {
        this((EntityType<Entity>) EntitiesUmbralArcana.SIGIL.get(), level);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.tickCount >= this.getMaxLife()) {
            this.remove();
        }

        if(this.level.isClientSide) {
            if(this.tickCount % 4 == 0) {
                double xoff = (this.random.nextFloat() * 2.82d - 1.41d) * this.scale(this.tickCount);
                double zoff = (this.random.nextFloat() * 2.82d - 1.41d) * this.scale(this.tickCount);
                this.level.addParticle(ParticleTypesUmbralArcana.UMBRAL_DUST.get(), this.getX() + xoff, this.getY(), this.getZ() + zoff, 0, 0, 0);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(MAX_LIFE, 0);
        this.entityData.define(TEXTURE, "");
        this.entityData.define(ROTATION_SPEED, 1);
    }
    @Override
    protected void readAdditionalSaveData(CompoundNBT compoundNBT) {
        this.setMaxLife(compoundNBT.getInt("MAX_LIFE"));
        this.setTexture(compoundNBT.getString("TEXTURE"));
        this.setRotationSpeed(compoundNBT.getInt("ROTATION_SPEED"));
    }
    @Override
    protected void addAdditionalSaveData(CompoundNBT compoundNBT) {
        compoundNBT.putInt("MAX_LIFE", this.getMaxLife());
        compoundNBT.putString("TEXTURE", this.getTexture());
        compoundNBT.putInt("ROTATION_SPEED", this.getRotationSpeed());
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
    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }
    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }
    public int getRotationSpeed() {
        return entityData.get(ROTATION_SPEED);
    }
    public void setRotationSpeed(int rotationSpeed) {
        this.entityData.set(ROTATION_SPEED, rotationSpeed);
    }

    public double scale(double x) {
        return MathHelperUmbralArcana.quad(x, this.entityData.get(MAX_LIFE), 1.0f, 6, true);
    }
}