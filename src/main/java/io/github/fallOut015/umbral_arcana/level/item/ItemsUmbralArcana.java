package io.github.fallOut015.umbral_arcana.level.item;

import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import io.github.fallOut015.umbral_arcana.level.world.block.BlocksUmbralArcana;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsUmbralArcana {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainUmbralArcana.MODID);



    public static final RegistryObject<Item> UMBRITE = ITEMS.register("umbrite", () -> new BlockItem(BlocksUmbralArcana.UMBRITE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> POLISHED_UMBRITE = ITEMS.register("polished_umbrite", () -> new BlockItem(BlocksUmbralArcana.POLISHED_UMBRITE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> UMBRIUM = ITEMS.register("umbrium", () -> new BlockItem(BlocksUmbralArcana.UMBRIUM.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GLOWCAPS = ITEMS.register("glowcaps", () -> new BlockItem(BlocksUmbralArcana.GLOWCAPS.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}