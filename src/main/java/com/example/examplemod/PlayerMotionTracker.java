package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerMotionTracker {


    private static final Map<UUID, Vec3> LAST_POSITIONS = new HashMap<>();



    public static Vec3 getPlayerVelocity(Player player) {


        UUID id = player.getUUID();


        Vec3 current = player.position();


        Vec3 last = LAST_POSITIONS.get(id);


        LAST_POSITIONS.put(id, current);



        if(last == null)
            return Vec3.ZERO;



        return current.subtract(last);

    }

}