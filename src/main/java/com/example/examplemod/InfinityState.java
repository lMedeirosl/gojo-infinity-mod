package com.example.examplemod;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InfinityState {


    private static final Set<UUID> ACTIVE_PLAYERS = new HashSet<>();



    public static boolean isActive(UUID player) {

        return ACTIVE_PLAYERS.contains(player);

    }



    public static void toggle(UUID player) {


        if (isActive(player)) {

            deactivate(player);

        } else {

            activate(player);

        }

    }



    public static void activate(UUID player) {

        ACTIVE_PLAYERS.add(player);

    }



    public static void deactivate(UUID player) {

        ACTIVE_PLAYERS.remove(player);

    }

}