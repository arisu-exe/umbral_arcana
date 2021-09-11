package io.github.fallOut015.umbral_arcana.client.particle;

import io.github.fallOut015.umbral_arcana.particles.ParticleTypesUmbralArcana;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;

public class ParticleManagerUmbralArcana {
    public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleTypesUmbralArcana.UMBRAL_DUST.get(), UmbralDustParticle.Factory::new);
    }
}