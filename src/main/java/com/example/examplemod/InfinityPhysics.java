package com.example.examplemod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class InfinityPhysics {


    private static final double RANGE = 10.0;



    public static boolean isInsideRange(Entity entity, Player player) {

        return entity.distanceTo(player) <= RANGE;

    }




    public static void slowDown(Entity entity, Player player) {


        double distance = entity.distanceTo(player);
        


        if(distance > RANGE)
            return;
        
        if(distance < 2.0){

    repel(entity, player);
    return;

}


        Vec3 entityVelocity = entity.getDeltaMovement();
Vec3 playerVelocity = PlayerMotionTracker.getPlayerVelocity(player);


        
        Vec3 directionToPlayer =
                player.position()
                .subtract(entity.position())
                .normalize();



    
         
        double approachingSpeed =
        entityVelocity
        .subtract(playerVelocity)
        .dot(directionToPlayer);


        
         
         
        double infinityFactor =
                Math.pow(distance / RANGE, 3);



        
         
         
         
        if(approachingSpeed > 0){


            entity.setDeltaMovement(
                    entityVelocity.x * infinityFactor,
                    entityVelocity.y * infinityFactor,
                    entityVelocity.z * infinityFactor
            );


        }



        
        
         
        if(distance < 2.0){


            repel(entity, player);


        }


        entity.hurtMarked = true;

    }





    private static void repel(Entity entity, Player player){


        Vec3 pushDirection =
                entity.position()
                .subtract(player.position())
                .normalize();



        double strength = 0.8;



        Vec3 velocity =
                entity.getDeltaMovement();



        entity.setDeltaMovement(
                pushDirection.x * strength,
                velocity.y,
                pushDirection.z * strength
        );


        entity.hurtMarked = true;


    }


}