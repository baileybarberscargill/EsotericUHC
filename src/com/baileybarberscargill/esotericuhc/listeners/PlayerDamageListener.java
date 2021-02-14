package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static com.baileybarberscargill.esotericuhc.Main.epdamage;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(PlayerTeleportEvent event){
        Player player = event.getPlayer();

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            if (!epdamage) {
                event.setCancelled(true);
                player.teleport(event.getTo());
            }
        }
    }
}
