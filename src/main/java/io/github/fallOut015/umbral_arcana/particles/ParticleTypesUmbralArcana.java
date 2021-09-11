package io.github.fallOut015.umbral_arcana.particles;

import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleTypesUmbralArcana {
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MainUmbralArcana.MODID);



    public static final RegistryObject<BasicParticleType> UMBRAL_DUST = PARTICLE_TYPES.register("umbral_dust", () -> new BasicParticleType(false));



    public static void register(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
    }
}