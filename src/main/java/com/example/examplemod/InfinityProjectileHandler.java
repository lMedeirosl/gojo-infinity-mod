package com.example.examplemod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;

@Mod.EventBusSubscriber(modid = GojoInfinityMod.MODID)
public class InfinityProjectileHandler {


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


            if (entity instanceof AbstractArrow) {

                applyInfinity(entity, player);

            }


            if (entity instanceof ThrowableItemProjectile) {

                applyInfinity(entity, player);

            }

        }
    }


    private static void applyInfinity(Entity entity, Player player) {


        double distance = entity.distanceTo(player);


        if (distance > RANGE)
            return;



        
        if (distance < 1.5) {

            entity.setDeltaMovement(0,0,0);
            entity.hurtMarked = true;

            return;
        }



        
        double factor = Math.pow(distance / RANGE, 2);



        Vec3 motion = entity.getDeltaMovement();


        entity.setDeltaMovement(
                motion.x * factor,
                motion.y * factor,
                motion.z * factor
        );


        entity.hurtMarked = true;
    }
}