package io.github.fallOut015.umbral_arcana.client.registry;

import io.github.fallOut015.umbral_arcana.client.renderer.entity.ShieldRenderer;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.SigilRenderer;
import io.github.fallOut015.umbral_arcana.level.entity.EntitiesUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.entity.ShieldEntity;
import io.github.fallOut015.umbral_arcana.level.entity.SigilEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderingRegistryUmbralArcana {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<SigilEntity>) EntitiesUmbralArcana.SIGIL.get(), SigilRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<ShieldEntity>) EntitiesUmbralArcana.SHIELD.get(), ShieldRenderer::new);
    }
}