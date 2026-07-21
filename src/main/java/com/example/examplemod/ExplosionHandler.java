package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = GojoInfinityMod.MODID)
public class ExplosionHandler {


    @SubscribeEvent
    public static void onExplosion(ExplosionEvent event){


        for(Player player : event.getLevel()
                .players()){


            if(!InfinityState.isActive(player.getUUID()))
                continue;



            if(player.distanceToSqr(
                    event.getExplosion().getPosition()
            ) < 100){


                event.setCanceled(true);
                return;

            }

        }

    }

}