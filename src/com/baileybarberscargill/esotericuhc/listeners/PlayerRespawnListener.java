package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){

        Player player = event.getPlayer();

        //Player respawns at death point
        Location respawnpoint = player.getLocation();

        event.setRespawnLocation(respawnpoint);

    }



}
