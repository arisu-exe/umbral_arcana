package io.github.fallOut015.umbral_arcana.level.entity;

import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntitiesUmbralArcana {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MainUmbralArcana.MODID);



    public static final RegistryObject<EntityType<?>> SIGIL = ENTITIES.register("sigil", () -> EntityType.Builder.of(SigilEntity::new, EntityClassification.MISC).fireImmune().sized(4, 1).build("sigil"));



    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}