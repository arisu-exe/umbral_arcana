package io.github.fallOut015.umbral_arcana.level.world.block;

import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.world.block.material.MaterialUmbralArcana;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksUmbralArcana {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MainUmbralArcana.MODID);



    public static final RegistryObject<Block> UMBRITE = BLOCKS.register("umbrite", () -> new Block(AbstractBlock.Properties.of(MaterialUmbralArcana.UMBRITE).strength(5.0f)));
    public static final RegistryObject<Block> POLISHED_UMBRITE = BLOCKS.register("polished_umbrite", () -> new Block(AbstractBlock.Properties.of(MaterialUmbralArcana.UMBRITE).strength(5.0f)));
    public static final RegistryObject<Block> UMBRIUM = BLOCKS.register("umbrium", () -> new Block(AbstractBlock.Properties.of(MaterialUmbralArcana.UMBRIUM).strength(4.0f)));
    public static final RegistryObject<Block> GLOWCAPS = BLOCKS.register("glowcaps", () -> new Block(AbstractBlock.Properties.of(Material.PLANT).strength(2.0f).noCollission().emissiveRendering((blockState, blockReader, blockPos) -> true).lightLevel((blockState) -> 3).noOcclusion()));



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}