package com.example.examplemod;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.loading.FMLEnvironment;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GojoInfinityMod.MODID)
public class GojoInfinityMod {

    public static final String MODID = "gojoinfinity";
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


public static final RegistryObject<Item> INFINITY_CORE =
        ITEMS.register("infinity_core",
                () -> new Item(new Item.Properties()));

    private static final Logger LOGGER = LogUtils.getLogger();

    public GojoInfinityMod(FMLJavaModLoadingContext context) {

        IEventBus modEventBus = context.getModEventBus();

        // Registra a configuração do mod
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Registra eventos gerais do Forge
        MinecraftForge.EVENT_BUS.register(this);
        if (FMLEnvironment.dist.isClient()) {
    MinecraftForge.EVENT_BUS.register(InfinityKeyHandler.class);
}
        LOGGER.info("Gojo Infinity carregado!");
    }
}