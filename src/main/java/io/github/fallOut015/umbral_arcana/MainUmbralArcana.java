package io.github.fallOut015.umbral_arcana;

import io.github.fallOut015.umbral_arcana.client.particle.ParticleManagerUmbralArcana;
import io.github.fallOut015.umbral_arcana.client.registry.RenderingRegistryUmbralArcana;
import io.github.fallOut015.umbral_arcana.client.renderer.RenderTypeLookupUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.entity.EntitiesUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.item.ItemsUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.world.block.BlocksUmbralArcana;
import io.github.fallOut015.umbral_arcana.particles.ParticleTypesUmbralArcana;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MainUmbralArcana.MODID)
public class MainUmbralArcana {
    public static final String MODID = "umbral_arcana";
    private static final Logger LOGGER = LogManager.getLogger();

    public MainUmbralArcana() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        BlocksUmbralArcana.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsUmbralArcana.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntitiesUmbralArcana.register(FMLJavaModLoadingContext.get().getModEventBus());
        ParticleTypesUmbralArcana.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookupUmbralArcana.doClientStuff(event);
        RenderingRegistryUmbralArcana.doClientStuff(event);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event) {
            ParticleManagerUmbralArcana.onParticleFactoryRegistry(event);
        }
    }
}