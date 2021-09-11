package io.github.fallOut015.umbral_arcana.client.renderer;

import io.github.fallOut015.umbral_arcana.level.world.block.BlocksUmbralArcana;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderTypeLookupUmbralArcana {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlocksUmbralArcana.GLOWCAPS.get(), RenderType.cutout());
    }
}