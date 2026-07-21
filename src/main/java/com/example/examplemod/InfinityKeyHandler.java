package com.example.examplemod;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

@Mod.EventBusSubscriber(
        modid = GojoInfinityMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class InfinityKeyHandler {

    private static final KeyMapping INFINITY_KEY =
            new KeyMapping(
                    "key.gojoinfinity.toggle",
                    GLFW.GLFW_KEY_G,
                    "key.categories.gojoinfinity"
            );


    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(INFINITY_KEY);
    }


    @Mod.EventBusSubscriber(
            modid = GojoInfinityMod.MODID
    )
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {

            if (INFINITY_KEY.consumeClick()) {

                Minecraft minecraft = Minecraft.getInstance();

                if (minecraft.player != null) {

                    UUID uuid = minecraft.player.getUUID();

                    InfinityState.toggle(uuid);

                    boolean active = InfinityState.isActive(uuid);

                    minecraft.player.sendSystemMessage(
                            Component.literal(
                                    active
                                            ? "§bInfinity ativado!"
                                            : "§cInfinity desativado!"
                            )
                    );
                }
            }
        }
    }
}