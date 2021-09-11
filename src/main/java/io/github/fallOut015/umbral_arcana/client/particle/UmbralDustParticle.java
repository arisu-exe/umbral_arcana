package io.github.fallOut015.umbral_arcana.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UmbralDustParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteWithAge;

    protected UmbralDustParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.spriteWithAge = spriteWithAge;
        this.yd = 0.05f;
        this.rCol = 0.5f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.quadSize = scale;
        this.lifetime = 64;
        this.setSpriteFromAge(spriteWithAge);
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        super.tick();

        this.xd = 0;
        this.yd *= 0.99;
        this.zd = 0;

        float interpolation = (float) this.age / (float) this.lifetime;

        if(Math.floor(interpolation * 4f) % 4 == 0) {
            this.setSprite(this.spriteWithAge.get(0, 3));
        } else if(Math.floor(interpolation * 4f) % 4 == 1) {
            this.setSprite(this.spriteWithAge.get(1, 3));
        } else if(Math.floor(interpolation * 4f) % 4 == 2) {
            this.setSprite(this.spriteWithAge.get(2, 3));
        } else {
            this.setSprite(this.spriteWithAge.get(3, 3));
        }

        this.alpha = (1f - interpolation) * 1.0f;
        this.quadSize = (1f - interpolation) * 0.1f;
        this.roll += 0.0314f;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new UmbralDustParticle(worldIn, x, y, z, 0, ySpeed, 0, 0.1F, this.spriteSet);
        }
    }
}