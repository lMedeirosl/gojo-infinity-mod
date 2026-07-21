package com.example.examplemod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = GojoInfinityMod.MODID)
public class InfinityHandler {


    private static final double RANGE = 10.0;


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {


        if (event.phase != TickEvent.Phase.END)
            return;


        Player player = event.player;


        if (!InfinityState.isActive(player.getUUID()))
            return;



        for (Entity entity : player.level().getEntities(
                player,
                player.getBoundingBox().inflate(RANGE)
        )) {


            if (entity == player)
                continue;


            InfinityPhysics.slowDown(entity, player);

        }

    }



    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {


        if (!(event.getEntity() instanceof Player player))
            return;


        if (!InfinityState.isActive(player.getUUID()))
            return;


        event.setCanceled(true);

    }

}