package com.example.examplemod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = GojoInfinityMod.MODID)
public class InfinityProtectionHandler {


    private static final double RANGE = 10.0;



    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){


        if(event.phase != TickEvent.Phase.END)
            return;


        Player player = event.player;


        if(!InfinityState.isActive(player.getUUID()))
            return;



        for(Entity entity : player.level().getEntities(
                player,
                player.getBoundingBox().inflate(RANGE)
        )){


            if(entity instanceof AbstractArrow ||
               entity instanceof ThrownPotion ||
               entity instanceof Fireball ||
               entity instanceof ThrowableItemProjectile){


                InfinityPhysics.slowDown(entity, player);

            }

        }

    }

}