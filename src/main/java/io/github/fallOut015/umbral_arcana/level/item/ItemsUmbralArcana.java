package io.github.fallOut015.umbral_arcana.level.item;

import io.github.fallOut015.umbral_arcana.MainUmbralArcana;
import io.github.fallOut015.umbral_arcana.client.renderer.entity.SigilRenderer;
import io.github.fallOut015.umbral_arcana.level.entity.ShieldEntity;
import io.github.fallOut015.umbral_arcana.level.entity.SigilEntity;
import io.github.fallOut015.umbral_arcana.level.world.block.BlocksUmbralArcana;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
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
    public static final RegistryObject<Item> SSTICK = ITEMS.register("sstick", () -> new Item(new Item.Properties()) {
        @Override
        public ActionResult<ItemStack> use(World level, PlayerEntity playerEntity, Hand hand) {
            if(playerEntity.isShiftKeyDown()) {
                if(!level.isClientSide) {
                    ShieldEntity shield = new ShieldEntity(level);
                    shield.setCaster(playerEntity);
                    level.addFreshEntity(shield);
                }
            } else {
                if(!level.isClientSide) {
                    float facing = playerEntity.getYHeadRot();

                    SigilEntity moon = new SigilEntity(level);
                    moon.setPos(playerEntity.getX() + Math.cos(facing - (Math.PI / 4d)) * 4d, playerEntity.getY(), playerEntity.getZ() + Math.sin(facing - (Math.PI / 4d)) * 4d);
                    moon.setTexture(SigilRenderer.MOON);
                    moon.setMaxLife(128);
                    moon.setRotationSpeed(8);
                    level.addFreshEntity(moon);

                    SigilEntity sun = new SigilEntity(level);
                    sun.setPos(playerEntity.getX() + Math.cos(facing + (Math.PI / 4d)) * 4d, playerEntity.getY(), playerEntity.getZ() + Math.sin(facing + (Math.PI / 4d)) * 4d);
                    sun.setTexture(SigilRenderer.SUN);
                    sun.setMaxLife(128);
                    sun.setRotationSpeed(8);
                    level.addFreshEntity(sun);
                }
            }
            return ActionResult.sidedSuccess(playerEntity.getItemInHand(hand), !level.isClientSide);
        }
    });



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}